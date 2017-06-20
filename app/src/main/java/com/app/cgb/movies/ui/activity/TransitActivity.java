package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.app.cgb.moviepreview.basic.App;
import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.squareup.picasso.Picasso;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class TransitActivity extends BaseActivity implements CommonAdapter.OnItemClickListener {

    @BindView(R.id.rv_activities)
    RecyclerView rvActiviyties;
    private ArrayList<String> activities;
    private ArrayList<Intent> intents;
    private App app;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_transit;
    }

    @Override
    protected void initView() {
        app = App.getApp();
        intents = app.getIntents();
        activities = app.getActivityStrs();
        setupRvList();
    }

    private void setupRvList() {
        CommonAdapter<String> adapter = new CommonAdapter<>(activities);
        adapter.setLayoutId(R.layout.transit_item)
                .setOnItemInit(new CommonAdapter.OnItemInit() {
                    @Override
                    public void onBind(ViewHolder holder, int position, List data) {
                        ImageView ivAc = holder.getView(R.id.iv_ac);
                        String name = (String) data.get(position);
                        String path = app.getPath(name);
                        Bitmap bitmap = app.getBitmapLoader().getBitmapFromMemoryCache(name);
                        if (bitmap != null) {
                            ivAc.setImageBitmap(bitmap);
                        } else {
                            Picasso.with(TransitActivity.this)
                                    .load(new File(path))
                                    .placeholder(android.R.color.darker_gray)
                                    .into(ivAc);
                        }
                    }
                });
        adapter.setOnItemClickListener(this);
//        final LinearLayoutManager manager = new LinearLayoutManager(this);
        GridLayoutManager manager = new GridLayoutManager(this,3);
        rvActiviyties.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvActiviyties.setLayoutManager(manager);
        rvActiviyties.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onItemClick(ViewHolder holder, int position) {
        Intent intent = intents.get(position);
        startActivityFromTransit(position,intent);
        finish();
    }
}
