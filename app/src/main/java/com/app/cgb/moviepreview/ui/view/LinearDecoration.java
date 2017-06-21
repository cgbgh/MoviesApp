package com.app.cgb.moviepreview.ui.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private final OnDecorationDraw mOnDecorationDraw;
    private int mOrientation;

    public LinearDecoration(int orientation, OnDecorationDraw onDecorationDraw) {
        mOnDecorationDraw = onDecorationDraw;
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        if (parent.getItemAnimator() != null && parent.getItemAnimator().isRunning()){
//            return;
//        }
        if (mOrientation == VERTICAL_LIST) {
            drawHorizontalLine(c, parent, state);
        } else {
            drawVerticalLine(c, parent, state);
        }
    }

    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        RecyclerView.Adapter adapter = parent.getAdapter();
        int childCount = adapter.getItemCount();
        for (int i = 0; i < childCount; i++) {
            Drawable decration = mOnDecorationDraw.getDecration(i);
            if (decration != null && i < childCount) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int left = child.getRight() + params.rightMargin;
                int right = left + decration.getIntrinsicWidth();
                decration.setBounds(left, top, right, bottom);
                decration.draw(c);
            }

        }
    }

    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Drawable decration = mOnDecorationDraw.getDecration(i);
            if (decration != null) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//                left += params.leftMargin;
//                right += params.rightMargin;
                int top = (int) (child.getBottom() + params.bottomMargin + child.getTranslationY());
                int bottom = top + decration.getIntrinsicHeight();
                decration.setBounds(left, top, right, bottom);
                decration.draw(c);
            }

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int itemCount = parent.getAdapter().getItemCount();
        if (position < 0 || position >= itemCount) {
            return;
        }
        Drawable decration = mOnDecorationDraw.getDecration(position);
        if (mOnDecorationDraw.shouldOffset(view, parent)) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, decration.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, decration.getIntrinsicWidth(), 0);
            }
        }

    }

    public interface OnDecorationDraw {

        Drawable getDecration(int position);

        boolean shouldOffset(View view, RecyclerView parent);
    }
}
