package com.app.cgb.moviepreview.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.app.cgb.moviepreview.R;

public class CircleImageView extends AppCompatImageView {


    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = 0;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int GRAVITY_START = 0x01;
    private static final int GRAVITY_END = 0x02;
    private static final int GRAVITY_CENTER = 0x04;
    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private int mBorderWidth;
    private int mBorderColor;
    private int mGravity;
    private Bitmap mBitmap;
    private Paint mBorderPaint;
    private Paint mBitmapPaint;
    private RectF mBorderRec;
    private RectF mBitmapRec;
    private Matrix mShaderMatrix;
    private float mBorderRadius;
    private float mBitmapRaius;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private boolean mSetuped;
    private boolean mReady;

    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initAttrs(context, attrs, defStyleAttr);
        setScaleType(SCALE_TYPE);
        mReady = true;
        if (!mSetuped){
            setup();
            mSetuped = true;
        }
    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyleAttr, 0);
        mBorderWidth = ta.getDimensionPixelSize(R.styleable.CircleImageView_boder, DEFAULT_BORDER_WIDTH);
        mBorderColor = ta.getColor(R.styleable.CircleImageView_boderColor, DEFAULT_BORDER_COLOR);
        mGravity = ta.getInt(R.styleable.CircleImageView_gravity, -1);
        ta.recycle();
    }

    private void setup() {
        if (!mReady){
            mSetuped = false;
            return;
        }
        if (mBitmap == null){
            return;
        }
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapPaint = new Paint();
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBitmapRec = new RectF();
        mBorderRec = new RectF();
        mBorderRec.set(0, 0, getWidth(), getHeight());
        mBorderRadius = Math.min((mBorderRec.height() - mBorderWidth) / 2, (mBorderRec.width() - mBorderWidth) / 2);
        mBitmapRec.set(mBorderRec);
        mBitmapRec.inset(mBorderWidth, mBorderWidth);
        mBitmapRaius = Math.min(mBitmapRec.height() / 2, mBitmapRec.width() / 2);

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();

        updateShaderMatrix();
    }

    private void updateShaderMatrix() {
        float scale;
        float gravity = 0.5f;
        float dx = 0;
        float dy = 0;
        if ((mGravity & GRAVITY_CENTER) != GRAVITY_CENTER){
            if((mGravity & GRAVITY_START) == GRAVITY_START){
                gravity = 0;
            }else if ((mGravity & GRAVITY_END) == GRAVITY_END){
                gravity = 1;
            }
        }
        mShaderMatrix = new Matrix();
        mShaderMatrix.set(null);
        if (mBitmapRec.height() * mBitmapWidth > mBitmapRec.width() * mBitmapHeight) {
            scale = mBitmapRec.height() / (mBitmapHeight * 1.0f);
            dx = (mBitmapRec.width() - mBitmapWidth * scale) * gravity;
        } else {
            scale = mBitmapRec.width() / (mBitmapWidth * 1.0f);
            dy = (mBitmapRec.height() - mBitmapHeight * scale) * gravity;
        }
        mShaderMatrix.setScale(scale, scale);
        mShaderMatrix.postTranslate(dx + mBitmapRec.left, dy + mBitmapRec.top);
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (getDrawable() == null){
            return;
        }
        canvas.drawCircle(getWidth()/2,getHeight()/2,mBitmapRaius,mBitmapPaint);
        if (mBorderWidth > 0){
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mBorderRadius, mBorderPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        mBitmap = drawableToBitmap(getDrawable());
        setup();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        mBitmap = drawableToBitmap(getDrawable());
        setup();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = drawableToBitmap(getDrawable());
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setup();
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            //通常来说 我们的代码就是执行到这里就返回了。返回的就是我们最原始的bitmap
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION,
                        drawable.getOpacity() != PixelFormat.OPAQUE ?
                                Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ?
                                Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public int getGravity() {
        return mGravity;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.mBorderWidth = borderWidth;
    }
    public int getBorderColor(){
        return mBorderColor;
    }

    public void setBorderColor(int color){
        mBorderColor = color;
    }
}
