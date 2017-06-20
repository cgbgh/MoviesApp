package com.app.cgb.rlrecyclerview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public abstract class FootViewAdapter {

    public final static int STATE_NORMAL = 0;
    public final static int STATE_LOADING = 1;
    public final static int STATE_DONE = 2;
    public final static int STATE_ERROR = 3;
    public final static int STATE_NO_MORE = 4;

    protected View footView;
    private int state;
    protected int measureHeight;
    private boolean isLoading;
    private boolean isNoMore;

    public FootViewAdapter(Context context) {
        this.footView = initView(context);
        measureHeight = MeasureHeight();
    }


    protected abstract View initView(Context context);

    public abstract void onStateChanged(int preState, int currentState);

    public boolean onPush(float offSet) {
        int targetHeight = (int) (getVisibleHeight() + offSet);
        if (targetHeight <= measureHeight) {
            targetHeight = measureHeight;
        }
        setVisibleHeight(targetHeight);
        if (state != STATE_LOADING && state != STATE_ERROR && !isNoMore) {
            onLoading();
            return true;
//            setState(STATE_LOADING);
        }
        return false;
    }

    public void onLoading() {
        setState(STATE_LOADING);
        isLoading = true;
    }

    public boolean onRelease() {
        smoothScrollTo(measureHeight);
        return true;
    }

    public void onError() {
        setState(STATE_ERROR);
        isLoading = false;
    }

    public void setNoMore(boolean isNoMore) {
        if (isNoMore) {
            setState(STATE_NO_MORE);
        } else {
            setState(STATE_NORMAL);
        }
        this.isNoMore = isNoMore;
        isLoading = false;
    }

    public void compeletLoad() {
        footView.postDelayed(new Runnable() {
            @Override
            public void run() {
                isLoading = false;
                setState(STATE_NORMAL);
            }
        }, 200);
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
        return footView.getLayoutParams().height;
    }

    public void setVisibleHeight(int height) {
        ViewGroup.LayoutParams params = footView.getLayoutParams();
        params.height = height;
        footView.setLayoutParams(params);
    }

    public int MeasureHeight() {
        footView.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return footView.getMeasuredHeight();
    }

    public int getMeasureHeight() {
        return measureHeight;
    }

    public View getFootView() {
        return footView;
    }

    public void setFootView(View footView) {
        this.footView = footView;
        footView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
    }

    public void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    public boolean isLoading() {
        return isLoading;
    }

    public boolean isNoMore() {
        return isNoMore;
    }

    public void reset() {
        isNoMore = false;
        isLoading = false;
        setState(STATE_NORMAL);
    }
}
