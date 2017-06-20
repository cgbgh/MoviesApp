package com.app.cgb.moviepreview.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.CompanyList;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.ui.view.MyDecoration;

import java.util.List;

import butterknife.BindView;

public class CompanyListActivity extends BaseRequestActivity<CompanyList> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private CommonAdapter<CompanyList.BaseBean> mAdapter;
    private int movieId;

    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, final int position, List data) {
            CompanyList.BaseBean baseBean = (CompanyList.BaseBean) data.get(position);
            switch (holder.getItemViewType()) {
                case CommonAdapter.ITEM_SECTION:
                    holder.setText(R.id.tv_title, baseBean.getTitle());
                    break;
                default:
                    holder.setText(R.id.tv_content, baseBean.getName())
                            .setText(R.id.tv_location, baseBean.getLocationName())
                            .setOnItemClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startMovieOfCompanyActivity(position);
                                }
                            });
                    break;
            }
        }
    };
    private CommonAdapter.SectionSupport<CompanyList.BaseBean> sectionSupport = new CommonAdapter.SectionSupport<CompanyList.BaseBean>() {
        @Override
        public String getTitle(CompanyList.BaseBean baseBean) {
            return baseBean.getTitle();
        }
    };

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar,R.string.activity_company_list_toolbar);
        setupList();
    }

    private void setupList() {
        mAdapter = new CommonAdapter()
                .setLayoutId(R.layout.company_list_item)
                .setOnItemInit(onItemInit)
                .setSectionSupport(R.layout.list_title,sectionSupport);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        rvList.setAdapter(mAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadCompanyList(movieId);
    }

    @Override
    protected void onResponse(CompanyList response) {
        mAdapter.setData(response.getList());
    }

    private void startMovieOfCompanyActivity(int index) {
        CompanyList.BaseBean item = getResponse().getList().get(index);
        String name = item.getName();
        int id = item.getId();
        Intent intent = new Intent(CompanyListActivity.this, MoviesOfCompanyActivity.class);
        intent.putExtra(Constants.COMPANY_ID, id);
        intent.putExtra(Constants.COMPANY_NAME, name);
        startActivity(intent);
    }

}
