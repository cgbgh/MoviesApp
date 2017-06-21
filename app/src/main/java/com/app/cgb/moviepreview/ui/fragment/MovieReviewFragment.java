package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieComment;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.ReviewDetailsActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.List;

public class MovieReviewFragment extends BaseRefreshFragment<List<MovieComment>>{

    private CommonAdapter<MovieComment> commentAdapter;
    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            setupListItems(holder,position,data);
        }
    };

    @Override
    protected void initView() {
        super.initView();
        getRvList().addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        commentAdapter = new CommonAdapter<>()
                .setLayoutId(R.layout.movie_comment_item)
                .setHead(R.layout.movie_news_header)
                .setOnItemInit(onItemInit);
        return commentAdapter;
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadCommentList();
    }

    @Override
    public void onResponse(List<MovieComment> movieComments) {
        commentAdapter.setData(movieComments);
    }

    private void startDetailActivity(int id) {
        Intent intent = new Intent(mContext, ReviewDetailsActivity.class);
        intent.putExtra(Constants.REVIEW_ID, id);
        mContext.startActivity(intent);
    }
    private void setupListItems(ViewHolder holder, int position, List<MovieComment> data) {
        final MovieComment movieComment;
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case CommonAdapter.ITEM_HEAD:
                movieComment = data.get(0);
                setupListHead(holder, movieComment);
                break;
            default:
                movieComment = data.get(position);
                setupListNormalItem(holder, movieComment);
                break;
        }
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDetailActivity(movieComment.getId());
            }
        });
    }

    private void setupListNormalItem(ViewHolder holder, final MovieComment movieComment) {
        ImageView ivCover = holder.getView(R.id.iv_cover);
        MovieComment.RelatedObjBean relatedObj = movieComment.getRelatedObj();
        if (relatedObj!=null){
            PicLoadUtils.loadNormalPic(mContext,relatedObj.getImage(),ivCover);
            holder.setText(R.id.tv_movie_name,relatedObj.getTitle());
        }
        PicLoadUtils.loadNormalPic(mContext,movieComment.getUserImage(),
                (ImageView) holder.getView(R.id.iv_avatar));

        holder.setText(R.id.tv_title,movieComment.getTitle())
                .setText(R.id.tv_desc, TextUtils.handleSpace(movieComment.getSummary()))
                .setText(R.id.tv_user_name,movieComment.getNickname()+"- 评");
    }

    private void setupListHead(ViewHolder holder, final MovieComment movieComment) {
        ImageView ivCover = holder.getView(R.id.iv_cover);
        MovieComment.RelatedObjBean relatedObj = movieComment.getRelatedObj();
        if (relatedObj!=null){
            PicLoadUtils.loadNormalPic(mContext, relatedObj.getImage(), ivCover);
        }
        holder.setText(R.id.tv_title_cn, movieComment.getTitle());
    }

}
