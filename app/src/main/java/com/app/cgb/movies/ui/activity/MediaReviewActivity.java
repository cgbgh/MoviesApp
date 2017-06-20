package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MediaReview;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TimeUtils;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;

import java.util.List;

import butterknife.BindView;

public class MediaReviewActivity extends BaseRequestActivity<MediaReview> implements CommonAdapter.OnItemInit {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    private CommonAdapter<MediaReview.MediasBean> mAdapter;
    private int movieId;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar,R.string.activity_media_review_toolbar);
        setupList();
    }

    private void setupList() {
        mAdapter = new CommonAdapter<>();
        mAdapter.setLayoutId(R.layout.media_review_item)
                .setOnItemInit(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvList.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID,-1);
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadMediaReview(movieId);
    }

    @Override
    protected void onResponse(MediaReview response) {
        mAdapter.setData(response.getMedias());
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void onBind(ViewHolder holder, int position, List data) {
        MediaReview.MediasBean item = (MediaReview.MediasBean) data.get(position);
        if (item != null) {
            if (item.getIcon()!=null && !item.getIcon().isEmpty()){
                PicLoadUtils.loadNormalPic(this, item.getIcon(), (ImageView) holder.getView(R.id.iv_media_cover));
            }
            holder.setText(R.id.tv_media_name,item.getName());
            List<MediaReview.MediasBean.CommentsBean> comments = item.getComments();
            if (comments != null && comments.size() > 0) {
                MediaReview.MediasBean.CommentsBean commentsBean = comments.get(0);
                if (commentsBean!=null){
                    holder.setText(R.id.tv_media_content,commentsBean.getSummary())
                            .setText(R.id.tv_publish_time,
                                    TimeUtils.getPublishTime(commentsBean.getPublishTime()));
                }
            }
        }
    }
}
