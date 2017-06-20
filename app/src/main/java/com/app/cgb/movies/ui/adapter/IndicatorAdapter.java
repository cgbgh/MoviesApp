package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.R;

import butterknife.BindView;

public class IndicatorAdapter extends BaseSingleTypeAdapter<String>{

    private int currentItem = 0;
    private RecyclerView mRecyclerView;

    public IndicatorAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.indicator_item, parent, false);
        return new TextViewHolder(view);
    }

    public void setCurrentItem(int position){
        if (position>=mListData.size() || position < 0){
            return;
        }
        currentItem = position;
        mRecyclerView.smoothScrollToPosition(position);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    class TextViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_indicator)
        TextView tvIndicator;

        public TextViewHolder(View view) {
            super(view);
        }

        @Override
        public void fillView(int position) {
            String item = getItem(position);
//            ((TextView) itemView).setText(item);
            tvIndicator.setText(item);
            if (position == currentItem){
                tvIndicator.setTextColor(mContext.getResources().getColor(R.color.attr_color_accent));
            }else {
                tvIndicator.setTextColor(mContext.getResources().getColor(R.color.text_color_light));
            }
        }
    }
}
