package com.app.cgb.moviepreview.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews;
    private RecyclerView.Adapter mAdapter;

    public ViewHolder(View itemView,RecyclerView.Adapter adapter) {
        super(itemView);
        mAdapter = adapter;
        mViews = new SparseArray<>();
    }

    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }


    public static ViewHolder get(@LayoutRes int layoutId, ViewGroup parent,RecyclerView.Adapter adapter){
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view,adapter);
    }

    /**
     * 通过 viewId获取view控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = itemView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    public ViewHolder setText(@IdRes int viewId,String s){
        TextView tv = getView(viewId);
        tv.setText(s);
        return this;
    }

    public ViewHolder setImageResourse(@IdRes int viewId,@DrawableRes int imageResId){
        ImageView iv = getView(viewId);
        iv.setImageResource(imageResId);
        return this;
    }

    public ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    public ViewHolder setOnItemClickListener(View.OnClickListener listener){
        itemView.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(@IdRes int viewId, View.OnLongClickListener listener){
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public ViewHolder setOnItemLongClickListener(View.OnLongClickListener listener){
        itemView.setOnLongClickListener(listener);
        return this;
    }

    public void setBackgroundDrawable(@IdRes int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackground(drawable);
    }
}
