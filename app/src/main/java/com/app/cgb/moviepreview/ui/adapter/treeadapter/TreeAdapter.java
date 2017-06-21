package com.app.cgb.moviepreview.ui.adapter.treeadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.cgb.moviepreview.ui.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TreeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<TreeAdapterItem> branches = new ArrayList<>();
    private OnTreeItemClick mOnTreeItemClick;
    private Context mContext;


    public void setBranches(List<TreeAdapterItem> branches) {
        if (branches == null){
            this.branches.clear();
        }else {
            this.branches = new ArrayList<>(branches);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return branches.get(position).getLayoutId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.get(viewType,parent,this);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TreeAdapterItem branch = branches.get(position);
        branch.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpandOrCollapse(holder);
                if (mOnTreeItemClick != null){
                    mOnTreeItemClick.onTreeItemClick(branch,holder);
                }
            }
        });
    }

    private void ExpandOrCollapse(ViewHolder holder) {
        int position = holder.getAdapterPosition();
        TreeAdapterItem branch = branches.get(position);
        List childrenList = branch.getChildren();
        if (childrenList == null || childrenList.size()<=0){
            return;
        }
        boolean expanded = branch.isExpand();
        if (expanded){
            branches.removeAll(branch.getAllChildren());
//            notifyDataSetChanged();
            notifyItemRangeRemoved(position+1,branch.getExpandChildren().size());
            branch.onCollapse();
        }else {
            branches.addAll(position+1,childrenList);
            notifyItemRangeInserted(position+1,childrenList.size());
            branch.onExpand();
        }
    }

    @Override
    public int getItemCount() {
        return branches == null ? 0 : branches.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return branches.get(position).getSpanSize();
                }
            });
        }
    }

    public Context getContext(){
        return mContext;
    }

    public void setOnTreeItemClick(OnTreeItemClick pnTreeItemClick) {
        mOnTreeItemClick = pnTreeItemClick;
    }

    public interface OnTreeItemClick{
        void onTreeItemClick(TreeAdapterItem branch,ViewHolder holder);
    }
}

