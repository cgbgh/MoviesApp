package com.app.cgb.moviepreview.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.cgb.moviepreview.ui.adapter.CommonAdapter;


public class MyDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    public static final int[] ATRRS = new int[]{
            android.R.attr.listDivider
    };

    public MyDecoration(Context context, int orientation) {
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
        setOrientation(orientation);
    }

    //设置屏幕的方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawHorizontalLine(c, parent, state);
        } else {
            drawVerticalLine(c, parent, state);
        }
    }

    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            final View child = parent.getChildAt(i);
            //获得child的布局信息
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private boolean isLastRow(RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager, View view) {
        int position = layoutManager.getPosition(view);
        return position == layoutManager.getItemCount() - 1 ||
                adapter.getItemViewType(position + 1) == CommonAdapter.ITEM_SECTION;
    }

    private boolean isTitle(RecyclerView.LayoutManager layoutManager, View view) {
        int itemViewType = layoutManager.getItemViewType(view);
        return itemViewType == CommonAdapter.ITEM_SECTION;
    }

    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        RecyclerView.Adapter adapter = parent.getAdapter();
        for (int i = 0; i < childCount; i++) {

            if (adapter.getItemViewType(i) != CommonAdapter.ITEM_SECTION) {

                View child = parent.getChildAt(i);

                //获得child的布局信息
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (mOrientation == HORIZONTAL_LIST) {
            //画竖线，就是往右偏移一个分割线的宽度
            if (!isTitle(layoutManager, view) && !isLastRow(adapter, layoutManager, view)) {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        } else {
            //画横线，就是往下偏移一个分割线的高度
            if (!isTitle(layoutManager, view) && !isLastRow(adapter, layoutManager, view)) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }
        }
    }

}
