package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Credits;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.CreditTypeItem;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.TreeAdapter;
import com.app.cgb.moviepreview.ui.adapter.treeadapter.TreeAdapterItem;
import com.app.cgb.moviepreview.ui.view.LinearDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreditsActivity extends BaseRequestActivity<Credits> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    private TreeAdapter adapter;
    private int movieId;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        initToolbar();
        setupList();
    }

    private void setupList() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeAdapter();
        rvList.setAdapter(adapter);
        rvList.addItemDecoration(new LinearDecoration(LinearDecoration.VERTICAL_LIST,
                new LinearDecoration.OnDecorationDraw() {
                    @Override
                    public Drawable getDecration(int position) {
                        return getResources().getDrawable(R.drawable.shape_line);
                    }

                    @Override
                    public boolean shouldOffset(View view, RecyclerView parent) {
                        return true;
                    }
                }));

        adapter.setOnTreeItemClick(new TreeAdapter.OnTreeItemClick() {
            @Override
            public void onTreeItemClick(TreeAdapterItem branch, ViewHolder holder) {
                Object data = branch.getData();
                if (data instanceof Credits.TypesBean.PersonsBean){
                    startPersonDetailActivity(((Credits.TypesBean.PersonsBean) data).getId());
                }
            }
        });
    }

    private void startPersonDetailActivity(int id) {
        Intent intent = new Intent(this, PersonDetailActivity.class);
        intent.putExtra(Constants.PERSON_ID,id);
        startActivity(intent);
    }

    private void initToolbar() {
        setupToolbar(toolbar, "演职人员");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadCredits(movieId);
    }

    @Override
    protected void onResponse(Credits response) {
        List<Credits.TypesBean> types = response.getTypes();
        ArrayList<TreeAdapterItem> treeAdapterItems = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            CreditTypeItem creditTypeItem = new CreditTypeItem(types.get(i), this);
            treeAdapterItems.add(creditTypeItem);
        }
        adapter.setBranches(treeAdapterItems);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
        llRoot.setVisibility(View.GONE);

    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
        llRoot.setVisibility(View.VISIBLE);
    }

}
