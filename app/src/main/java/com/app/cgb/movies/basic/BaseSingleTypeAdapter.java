package com.app.cgb.moviepreview.basic;

import android.content.Context;
import android.view.View;


public abstract class BaseSingleTypeAdapter<T> extends BaseAdapter<T> {

    protected static final int ITEM_HEAD = 1;
    protected static final int ITEM_FOOT = 2;
    protected static final int ITEM_NORMAL = 3;
    private boolean mHasFooter;
    private boolean mHasHeader;
    private long mQuickClickInterval = 500;
    private long mItemClickTime;
    private long mFootClickTime;
    private long mHeadClickTime;
    private ItemClickListener mItemClickListener;

    public BaseSingleTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final int pos = position;
        holder.fillView(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHasHeader) {
                    if (pos == 0) {
                        headClick();
                    } else if (mHasFooter && pos == getItemCount() - 1) {
                        footClick();
                    } else {
                        itemClick(pos - 1);
                    }
                } else if (mHasFooter && pos == getItemCount() - 1) {
                    footClick();
                } else {
                    itemClick(pos);
                }
            }
        });
    }

    public T getItem(int position) {
        if (mHasHeader) {
            if (position <= 0 || position >= getItemCount()) {
                return null;
            }
            if (mHasFooter && position >= getItemCount()-1){
                return null;
            }
            return mListData.get(position - 1);
        } else {
            if (position<0 || position >= getItemCount()){
                return null;
            }
            if (mHasFooter && position >= getItemCount()-1){
                return null;
            }
            return mListData.get(position);
        }

    }

    @Override
    public int getItemCount() {
        int dataSize = mListData.size();
        if (mHasHeader) {
            if (mHasFooter) {
                dataSize += 2;
            } else {
                dataSize += 1;
            }
        } else if (mHasFooter) {
            dataSize += 1;
        }
        return dataSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHasHeader && position == 0) {
            return ITEM_HEAD;
        }
        if (mHasFooter && position == getItemCount() - 1) {
            return ITEM_FOOT;
        }
        return ITEM_NORMAL;
    }

    public boolean hasFooter() {
        return mHasFooter;
    }

    public void setHasFooter(boolean hasFooter) {
        mHasFooter = hasFooter;
    }

    public boolean hasHeader() {
        return mHasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        mHasHeader = hasHeader;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    private void itemClick(int position) {
        if (System.currentTimeMillis() - mItemClickTime <= mQuickClickInterval) {
            return;
        }
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(position);
        }
        mItemClickTime = System.currentTimeMillis();
    }

    private void footClick() {
        if (System.currentTimeMillis() - mFootClickTime <= mQuickClickInterval) {
            return;
        }
        if (mItemClickListener != null) {
            mItemClickListener.onFootClick();
        }
        mFootClickTime = System.currentTimeMillis();
    }

    private void headClick() {
        if (System.currentTimeMillis() - mHeadClickTime <= mQuickClickInterval) {
            return;
        }
        if (mItemClickListener != null) {
            mItemClickListener.onHeadClick();
        }
        mHeadClickTime = System.currentTimeMillis();
    }

    public interface ItemClickListener {

        void onItemClick(int position);

        void onHeadClick();

        void onFootClick();
    }
}
