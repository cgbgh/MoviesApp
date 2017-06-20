package com.app.cgb.moviepreview.basic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder> {


    protected final Context mContext;
    protected final LayoutInflater mInflater;
    protected List<T> mListData = new ArrayList<>();

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract void fillView(int position);

    }

    public BaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(@Nullable List<T> data) {
        if (data == null || data.isEmpty()) {
            mListData.clear();
        } else {
            mListData = new ArrayList<>(data);
        }
        notifyDataSetChanged();
    }




    public void addData(List<T> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        mListData.addAll(data);
        notifyDataSetChanged();
    }

}
