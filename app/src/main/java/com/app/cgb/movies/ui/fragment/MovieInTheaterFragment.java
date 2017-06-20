package com.app.cgb.moviepreview.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.MovieInTheater;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.activity.MTMovieDetailActivity;
import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TextUtils;
import com.app.cgb.moviepreview.ui.adapter.ViewHolder;

import java.util.List;

public class MovieInTheaterFragment extends BaseRefreshFragment<MovieInTheater> {

    private CommonAdapter<MovieInTheater.MsBean> mAdapter;
    private CommonAdapter.OnItemInit onItemInit = new CommonAdapter.OnItemInit() {
        @Override
        public void onBind(ViewHolder holder, int position, List data) {
            setupListItem(holder, position, data);
        }
    };

    @Override
    protected void initView() {
        super.initView();
        rvList.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        mAdapter = new CommonAdapter()
                .setLayoutId(R.layout.movie_in_theater_item)
                .setOnItemInit(onItemInit);
        return mAdapter;
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadMovieInTheater();
    }

    private void setupListItem(ViewHolder holder, int position, List<MovieInTheater.MsBean> data) {
        final MovieInTheater.MsBean msBean = data.get(position);
        String imgPath = msBean.getImg();
        String actor1 = msBean.getaN1();
        String actor2 = msBean.getaN2();
        PicLoadUtils.loadNormalPic(mContext, imgPath, (ImageView) holder.getView(R.id.iv_cover));
        holder.setText(R.id.title_cn, msBean.gettCn())
                .setText(R.id.title_en, TextUtils.handleEmptyText(msBean.gettEn()))
                .setText(R.id.rating, msBean.getR() > 0 ? String.valueOf(msBean.getR()) : "")
                .setText(R.id.desc, TextUtils.handleEmptyText(msBean.getCommonSpecial()))
                .setText(R.id.type, "类型：" + TextUtils.handleEmptyText(msBean.getMovieType()))
                .setText(R.id.actors, "主演：" +
                        TextUtils.handleEmptyText(actor1) + "/"
                        + TextUtils.handleEmptyText(actor2))
                .setText(R.id.show_time, "上映：" +
                        TextUtils.formatDate(msBean.getRd(), "yyyyMMdd", "yyyy年MM月dd日"))
                .setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MTMovieDetailActivity.class);
                        intent.putExtra(Constants.MOVIE_ID, msBean.getId());
                        mContext.startActivity(intent);
                    }
                });
    }

    @Override
    public void onResponse(MovieInTheater movieInTheater) {
        mAdapter.setData(movieInTheater.getMs());
    }
}
