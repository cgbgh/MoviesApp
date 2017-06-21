package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.entity.VideoList;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.TimeUtils;

import butterknife.BindView;

public class VideoListAdapter extends BaseSingleTypeAdapter<VideoList.VideoListBean> {

    private FootViewHolder mFootViewHolder;
    private boolean noMoreData;

    public VideoListAdapter(Context context) {
        super(context);
        setHasFooter(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case ITEM_NORMAL:
                view = mInflater.inflate(R.layout.video_list_item, parent, false);
                return new ItemViewHolder(view);
            case ITEM_FOOT:
                view = mInflater.inflate(R.layout.load_more_data, parent, false);
                mFootViewHolder = new FootViewHolder(view);
                return mFootViewHolder;
        }
        return null;
    }

    public void startLoading() {
        if (mFootViewHolder != null) {
            mFootViewHolder.tvLoad.setVisibility(View.GONE);
            mFootViewHolder.pbLoading.setVisibility(View.VISIBLE);
        }
    }

    public void stopLoading() {
        if (mFootViewHolder != null) {
            mFootViewHolder.pbLoading.setVisibility(View.GONE);
            mFootViewHolder.tvLoad.setVisibility(View.VISIBLE);
        }
    }

    public void setNoMoreData(boolean noMoreData) {
        this.noMoreData = noMoreData;
    }


    class ItemViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_duration)
        TextView tvDuration;

        public ItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            VideoList.VideoListBean item = getItem(position);
            String imageUrl = item.getImage();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                PicLoadUtils.loadNormalPic(mContext, imageUrl, ivCover);
            }

            tvTitle.setText(item.getTitle());
            tvDuration.setText(TimeUtils.sec2String(item.getLength()));
        }
    }

    class FootViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_load)
        TextView tvLoad;
        @BindView(R.id.pb_loading)
        ProgressBar pbLoading;

        public FootViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            if (noMoreData) {
                tvLoad.setText("貌似没有数据了(；′⌒`)");
            } else {
                tvLoad.setText("点击加载更多");
            }
        }
    }
}
