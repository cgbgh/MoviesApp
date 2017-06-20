package com.app.cgb.moviepreview.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.EventList;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.ShapeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.List;

import butterknife.BindView;

public class EventsActivity extends BaseRequestActivity<EventList> {

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CommonAdapter<String> eventAdapter;
    private int movieId;

    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            String item = (String) data.get(position);
            String rank = String.valueOf(position + 1);
            Drawable shape = ShapeUtils.getOvalShape(8, 8,
                    getResources().getColor(R.color.attr_color_accent));
            holder.setText(R.id.tv_event_rank, rank)
                    .setText(R.id.tv_event_content, TextUtils.handleSpace(Html.fromHtml(item).toString()))
                    .setBackgroundDrawable(R.id.tv_event_rank, shape);
        }
    };

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar, "Events");
        setupList();
    }

    private void setupList() {
        eventAdapter = new CommonAdapter()
                .setLayoutId(R.layout.movie_event_item)
                .setOnItemInit(onItemInit);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(eventAdapter);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadEventList(movieId);
    }

    @Override
    protected void onResponse(EventList response) {
        List<String> responseList = response.getList();
        if (responseList != null && responseList.size() > 0) {
            eventAdapter.setData(responseList);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.setTitle(response.getTitle());
        }
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
    }

}
