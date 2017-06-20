package com.app.cgb.rlrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

class WrapAdapter extends RecyclerView.Adapter {

    private static final int TYPE_HEAD = -101;
    private static final int TYPE_FOOT = -102;
    private RecyclerView.Adapter mInnerAdapter;
    private View mHeadView;
    private View mFootView;
    private int mHeadCount;
    private int mFootCount;


    WrapAdapter setInnerAdapter(RecyclerView.Adapter adpter){
        mInnerAdapter = adpter;
        return this;
    }

    WrapAdapter setHeadView(View headView){
        mHeadCount = headView == null ? 0 : 1;
        this.mHeadView = headView;
        return this;
    }
    WrapAdapter setFootView(View footView){
        this.mFootView = footView;
        mFootCount = footView == null ? 0 : 1;
        return this;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return new RecyclerView.ViewHolder(mHeadView) {
                };
            case TYPE_FOOT:
                return new RecyclerView.ViewHolder(mFootView) {
                };
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < mHeadCount) {
            return;
        }
        if (position - mHeadCount < mInnerAdapter.getItemCount()) {
            mInnerAdapter.onBindViewHolder(holder, position - mHeadCount);
        }
    }

    @Override
    public int getItemCount() {
        if (mInnerAdapter == null) {
            return 0;
        }else if (mInnerAdapter.getItemCount() == 0){
            return mInnerAdapter.getItemCount() + mHeadCount;
        }
        return mInnerAdapter.getItemCount() + mHeadCount + mFootCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mHeadCount) {
            return TYPE_HEAD;
        }else if (position - mHeadCount < mInnerAdapter.getItemCount()) {
            return mInnerAdapter.getItemViewType(position - mHeadCount);
        }
        return TYPE_FOOT;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager layout = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = layout.getSpanSizeLookup();
            layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    return type == TYPE_HEAD ? layout.getSpanCount() :
                            (spanSizeLookup == null ? 1 :
                                    spanSizeLookup.getSpanSize(position+1));
                }
            });
        }
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        View itemView = holder.itemView;
        int type = holder.getItemViewType();
        ViewGroup.LayoutParams lp = itemView.getLayoutParams();
        if (lp == null) {
            return;
        }
        if (type == TYPE_HEAD) {
            if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
                itemView.setLayoutParams(p);
            }
        }
        mInnerAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return mInnerAdapter.onFailedToRecycleView(holder);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mInnerAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        mInnerAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        mInnerAdapter.onViewRecycled(holder);
    }

}