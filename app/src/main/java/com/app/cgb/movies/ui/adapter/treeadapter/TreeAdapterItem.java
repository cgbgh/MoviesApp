package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;

import com.app.cgb.moviepreview.ui.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public abstract class TreeAdapterItem<D> {

    protected final Context mContext;
    private D data;
    private List<TreeAdapterItem> children;
    private boolean expanded;
    public String title;

    public TreeAdapterItem(D data, Context context) {
        this.data = data;
        mContext = context;
        children = initChildren(data);
    }

    protected abstract List<TreeAdapterItem> initChildren(D data);

    public abstract void onBindViewHolder(ViewHolder holder);

    public abstract int getLayoutId();

    public boolean isExpand(){
        return expanded;
    }

    public boolean hasChild(){
        return children !=null && children.size()>0;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public List<TreeAdapterItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeAdapterItem> children) {
        this.children = children;
    }

    public void onExpand() {
        expanded = true;
    }

    public void onCollapse(){
        expanded = false;
    }

    public List<TreeAdapterItem> getAllChildren(){
        List<TreeAdapterItem> itemList = new ArrayList<>();
        if (hasChild()){
            for (int i = 0; i < children.size(); i++) {
                TreeAdapterItem treeAdapterItem = children.get(i);
                itemList.add(treeAdapterItem);
                List childrenItemList = treeAdapterItem.getAllChildren();
                if (childrenItemList!=null && childrenItemList.size()>0){
                    itemList.addAll(childrenItemList);
                }
            }
        }
        return itemList;
    }

    public List<TreeAdapterItem> getExpandChildren(){
        ArrayList<TreeAdapterItem> itemList = new ArrayList<>();
        if (hasChild() && isExpand()){
            for (int i = 0; i < children.size(); i++) {
                TreeAdapterItem treeAdapterItem = children.get(i);
                itemList.add(treeAdapterItem);
                List childrenItemList = treeAdapterItem.getExpandChildren();
                if (childrenItemList!=null && childrenItemList.size()>0){
                    itemList.addAll(childrenItemList);
                }
            }
        }
        return itemList;
    }

    public abstract int getSpanSize();
}
