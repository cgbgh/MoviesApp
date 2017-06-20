package com.app.cgb.rlrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class DefaulHeadAdapter extends HeadViewAdapter {

    private static final int ROTATE_ANIM_DURATION = 180;
    private ImageView mArrowImageView;
    private TextView mStatusTextView;
    private RotateAnimation mRotateUpAnim;
    private RotateAnimation mRotateDownAnim;
    private TextView mTimeTextView;
    private ProgressBar mProgressBar;
    private boolean startPull = true;

    public DefaulHeadAdapter(Context context) {
        super(context);
    }

    @Override
    protected View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.refresh_rv_head, null, false);
        mArrowImageView = (ImageView)view.findViewById(R.id.iv_head_arrow);
        mStatusTextView = (TextView)view.findViewById(R.id.tv_refresh_status);

        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_refresh);

        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);

        mTimeTextView = (TextView)view.findViewById(R.id.tv_last_time);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        return view;
    }

    @Override
    public void onPull(float offSet) {
        if (startPull){
            mTimeTextView.setText(friendlyTime(getLastRefreshTime()));
            startPull = false;
        }
        super.onPull(offSet);
    }

    @Override
    public boolean onRelease() {
        startPull = true;
        return super.onRelease();
    }

    @Override
    public void onStateChanged(int preState, int currentState) {

        if (currentState < STATE_REFRESHING) {
            mArrowImageView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }

        switch(currentState){
            case STATE_NORMAL:
                turnToNormal(preState);
                break;
            case STATE_PREPARE:
                turnToPrepare();
                break;
            case STATE_REFRESHING:
                turnToRefreshing();
                break;
            case STATE_DONE:
                turnToRefreshDone();
                break;
            case STATE_ERROR:
                turnToError();
                break;
        }
    }

    private void turnToError() {
        mArrowImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mStatusTextView.setText(R.string.refresh_error);
        mTimeTextView.setText(friendlyTime(getLastRefreshTime()));
    }

    private void turnToRefreshDone() {
        mArrowImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mStatusTextView.setText(R.string.refresh_done);
        mTimeTextView.setText(friendlyTime(getLastRefreshTime()));
    }

    private void turnToRefreshing() {
        mArrowImageView.clearAnimation();
        mArrowImageView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        smoothScrollTo(measureHeight);
        mStatusTextView.setText(R.string.refreshing);
    }

    private void turnToPrepare() {
        mArrowImageView.clearAnimation();
        mArrowImageView.startAnimation(mRotateUpAnim);
        mStatusTextView.setText(R.string.rv_head_prepare);
    }

    private void turnToNormal(int preState) {
        if (preState == STATE_PREPARE) {
            mArrowImageView.startAnimation(mRotateDownAnim);
        }
        if (preState == STATE_REFRESHING) {
            mArrowImageView.clearAnimation();
        }
        mStatusTextView.setText(R.string.rv_head_normal);
    }
}
