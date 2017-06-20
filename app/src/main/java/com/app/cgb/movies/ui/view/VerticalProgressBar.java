package com.app.cgb.moviepreview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;


public class VerticalProgressBar extends ProgressBar {
    private static final String TAG = VerticalProgressBar.class.getSimpleName();

    public VerticalProgressBar(Context context) {
        this(context,null);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMeasuredHeight(),getMeasuredWidth());
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldw, oldh);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        canvas.rotate(-90);
        canvas.translate(-getHeight(),0);
        super.onDraw(canvas);
    }
}
