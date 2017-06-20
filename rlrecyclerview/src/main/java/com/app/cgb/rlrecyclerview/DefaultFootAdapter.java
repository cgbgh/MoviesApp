package com.app.cgb.rlrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class DefaultFootAdapter extends FootViewAdapter {

    private static final int arrowId = R.drawable.pushtoload_arrow;
    private static final int infoId = android.R.drawable.ic_menu_info_details;

    private ProgressBar progressBar;
    private ImageView ivFoot;
    private TextView tvInfo;

    public DefaultFootAdapter(Context context) {
        super(context);
    }

    @Override
    protected View initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.load_layout, null, false);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
        ivFoot = (ImageView) view.findViewById(R.id.iv_foot);
        tvInfo = (TextView) view.findViewById(R.id.tv_info);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    @Override
    public void onStateChanged(int preState, int currentState) {
        switch (currentState){
            case STATE_NORMAL:
                turnToNormal();
                break;
            case STATE_LOADING:
                turnToLoading();
                break;
            case STATE_DONE:
                turnToDone();
                break;
            case STATE_ERROR:
                turnToError();
                break;
            case STATE_NO_MORE:
                turnToNoMore();
                break;
        }
    }

    private void turnToNoMore() {
        ivFoot.setVisibility(View.VISIBLE);
        ivFoot.setImageResource(infoId);
        progressBar.setVisibility(View.GONE);
        tvInfo.setText(R.string.load_no_more);
    }

    private void turnToError() {
        ivFoot.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvInfo.setText(R.string.load_error);
        ivFoot.setImageResource(infoId);
    }

    private void turnToDone() {
        ivFoot.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvInfo.setText(R.string.load_done);
    }

    private void turnToLoading() {
        tvInfo.setText(R.string.loading);
        ivFoot.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void turnToNormal() {
        tvInfo.setText(R.string.rv_foot_normal);
        ivFoot.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        ivFoot.setImageResource(arrowId);
    }
}
