package com.app.cgb.rlrecyclerview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public abstract class HeadViewAdapter {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_PREPARE = 1;
    public static final int STATE_REFRESHING = 2;
    public static final int STATE_DONE = 3;
    public static final int STATE_ERROR = 4;

    protected int measureHeight;
    private int state;
    private View headView;
    private boolean isRefreshing;
    private long lastRefreshTime;

    public HeadViewAdapter(Context context) {
        this.headView = initView(context);
        measureHeight = MeasureHeight();
    }


    protected abstract View initView(Context context);

    public abstract void onStateChanged(int preState, int currentState);

    public void onPull(float offSet) {
        if (isRefreshing) return;
        setVisibleHeight(Math.max(0,(int) offSet + getVisibleHeight()));
        if (getState() <= STATE_PREPARE) { // 未处于刷新状态，更新箭头
            if (getVisibleHeight() > measureHeight) {
                setState(STATE_PREPARE);
            } else {
                setState(STATE_NORMAL);
            }
        }
    }

    public boolean onRelease() {
        if (state == STATE_PREPARE) {
            onRefresh();
            return true;
        } else {
            reset();
            return false;
        }
    }

    private void onRefresh() {
        setState(STATE_REFRESHING);
        isRefreshing = true;
    }

    public void compeletRefresh() {
        lastRefreshTime = System.currentTimeMillis();
        setState(STATE_DONE);
        isRefreshing = false;
        headView.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(0);
            }
        },500);
    }

    public void reset() {
        smoothScrollTo(0);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state == this.state) return;
        onStateChanged(this.state, state);
        this.state = state;
    }

    public int getVisibleHeight() {
        return headView.getLayoutParams().height;
    }

    public void setVisibleHeight(int height) {
        ViewGroup.LayoutParams params = headView.getLayoutParams();
        params.height = height;
        headView.setLayoutParams(params);
    }

    private int MeasureHeight() {
        headView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return headView.getMeasuredHeight();
    }

    public int getMeasureHeight() {
        return measureHeight;
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public View getHeadView() {
        return headView;
    }

    public void setHeadView(View headView) {
        this.headView = headView;
        headView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
    }

    public void smoothScrollTo(final int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (destHeight == 0){
                    setState(STATE_NORMAL);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void onError() {
        setState(STATE_ERROR);
        isRefreshing = false;
        headView.postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo(0);
            }
        },1000);
    }

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setRefreshing(boolean refreshing) {
        if (isRefreshing == refreshing) return;
        if (refreshing){
            onRefresh();
        }else {
            compeletRefresh();
        }
    }
    public String friendlyTime(long time) {
        Context context = headView.getContext();
        String strTime = context.getResources().getString(R.string.rv_head_last_time);
        int ct = (int) ((System.currentTimeMillis() - time) / 1000);
        if (ct <= 0) {
            strTime += context.getResources().getString(R.string.refresh_just);
        }else if (ct < 60) {
            strTime += ct + context.getResources().getString(R.string.refresh_seconds_ago);
        }else if (ct < 3600){
            strTime += Math.max(ct / 60, 1) + context.getResources().getString(R.string.text_minute_ago);
        }else if (ct < 86400){
            strTime += ct / 3600 + context.getResources().getString(R.string.text_hour_ago);
        }else if (ct < 2592000) { //86400 * 30
            strTime += ct / 86400 + context.getResources().getString(R.string.text_day_ago);
        }else if (ct < 31104000) { //86400 * 30
            strTime += ct / 2592000 + context.getResources().getString(R.string.text_month_ago);
        }else {
            strTime += ct / 31104000 + context.getResources().getString(R.string.text_year_ago);
        }
        return strTime;
    }
}
