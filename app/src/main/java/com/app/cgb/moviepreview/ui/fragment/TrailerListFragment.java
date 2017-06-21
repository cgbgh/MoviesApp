package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.TrailerList;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.SystemPlayer;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import java.util.List;

public class TrailerListFragment extends BaseRefreshFragment<TrailerList> {

    private CommonAdapter<TrailerList.TrailersBean> mAdapter;
    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            setupListItems(holder, position, data);
        }
    };

    @Override
    protected RecyclerView.Adapter getAdapter() {
        mAdapter = new CommonAdapter()
                .setLayoutId(R.layout.movie_trailer_item)
                .setOnItemInit(onItemInit);
        return mAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }
    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadTrailerList();
    }

    @Override
    public void onResponse(TrailerList trailerList) {
        mAdapter.setData(getResponse().getTrailers());
    }

    private void startPlayer(int position) {
        Intent intent = new Intent(mContext, SystemPlayer.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.TRAILERLIST, getResponse());
        intent.putExtras(bundle);
        intent.putExtra("position", position);
        mContext.startActivity(intent);
    }

    private void setupListItems(ViewHolder holder, final int position, List<TrailerList.TrailersBean> data) {
        TrailerList.TrailersBean trailersBean = data.get(position);
        ImageView ivCover = holder.getView(R.id.iv_cover);
        PicLoadUtils.loadNormalPic(mContext, trailersBean.getCoverImg(), ivCover);

        holder.setText(R.id.tv_title_cn, trailersBean.getTitle())
                .setText(R.id.tv_duration,
                        TextUtils.formatDate(trailersBean.getVideoLength() * 1000L, "mm′ss″"))
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startPlayer(position);
                    }
                });
    }
}
