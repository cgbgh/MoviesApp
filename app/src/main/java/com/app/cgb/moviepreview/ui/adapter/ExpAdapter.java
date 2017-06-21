package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.entity.PersonsDetail;
import com.app.cgb.moviepreview.utils.PicLoadUtils;

import butterknife.BindView;

public class ExpAdapter extends BaseSingleTypeAdapter<PersonsDetail.DataBean.BackgroundBean.ExpriencesBean> {


    public ExpAdapter(Context context) {
        super(context);
        setHasFooter(true);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.person_experiences_item, parent, false);
        switch (viewType){
            case ITEM_NORMAL:
                return new ExpViewHolder(view);
            case ITEM_FOOT:
                return new FootViewHolder(view);
        }
        return null;
    }

    class ExpViewHolder extends BaseViewHolder {

        @BindView(R.id.iv_exp_cover)
        ImageView ivExpCover;
        @BindView(R.id.tv_exp_title)
        TextView tvExpTitle;
        @BindView(R.id.tv_exp_content)
        TextView tvExpContent;
        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.time_line_top)
        View timeLineTop;
        @BindView(R.id.timeline_line)
        LinearLayout timelineLine;
        @BindView(R.id.time_line_bottom)
        View timeLineBottom;
        @BindView(R.id.ll_child)
        LinearLayout llChild;
        @BindView(R.id.ll_parent)
        LinearLayout llParent;

        private boolean mIsVisiable;

        public ExpViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            if (position == 0){
                timeLineTop.setVisibility(View.INVISIBLE);
            }
            PersonsDetail.DataBean.BackgroundBean.ExpriencesBean item = getItem(position);
            PicLoadUtils.loadNormalPic(mContext, item.getImg(), ivExpCover);
            tvYear.setText(item.getYear() == 0 ? "" : item.getYear() + " ");
            tvExpTitle.setText(item.getTitle().trim());
            tvExpContent.setText(item.getContent().trim());
            llChild.setVisibility(View.GONE);
            mIsVisiable = false;
            llParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    llChild.setVisibility(mIsVisiable?View.GONE:View.VISIBLE);
                    mIsVisiable = !mIsVisiable;

                }
            });

        }
    }

    class FootViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_year)
        TextView tvYear;
        @BindView(R.id.ll_child)
        LinearLayout llChild;
        @BindView(R.id.time_line_bottom)
        View timeLineBottom;
        @BindView(R.id.down_arrow)
        ImageView downArrow;

        public FootViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            tvYear.setText("now");
            llChild.setVisibility(View.GONE);
            downArrow.setVisibility(View.GONE);
            timeLineBottom.setVisibility(View.INVISIBLE);
        }
    }
}
