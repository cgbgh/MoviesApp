package com.app.cgb.rlrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class RLRecyclerView extends RecyclerView {
    private static final int STATE_PULLING = 101;
    private static final int STATE_REFRESHING = 102;

    private static final int STATE_NORMAL = 200;

    private static final int STATE_PUSHING = 301;
//    private static final int STATE_LOADING = 302;

    private static final int DEFAULT_DAMPING = 2;
    private static final String TAG = RLRecyclerView.class.getSimpleName();

    private float mLastX = -1;
    private float mLastY = -1;
    private int mState;
    private HeadViewAdapter mHead;
    private FootViewAdapter mFoot;
    private OnRefreshListener mRefreshListener;
    private int mDamping;
    private OnLoadListener mOnLoadListener;
    private Adapter mInnerAdapter;
    private AdapterDataObserver mObserver = new InnerObserver();
    private boolean isRegisterObserver;
    private WrapAdapter mWrapAdapter = new WrapAdapter();
    private boolean canRefresh = true;
    private boolean canLoadMore = true;
    private boolean isNoMore;
    private int bottom;

    public RLRecyclerView(Context context) {
        this(context, null);
    }

    public RLRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RLRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        DefaulHeadAdapter defaultHead = new DefaulHeadAdapter(context);
        DefaultFootAdapter defaultFoot = new DefaultFootAdapter(context);
        setHead(defaultHead);
        setFoot(defaultFoot);
        setState(STATE_NORMAL);
        setDamping(DEFAULT_DAMPING);
    }

    @Override
    public void addItemDecoration(ItemDecoration decor, int index) {
        InnerItemDecoration decoration = new InnerItemDecoration(decor);
        super.addItemDecoration(decoration, index);
        Rect rect = new Rect();
        decor.getItemOffsets(rect, mHead.getHeadView(), RLRecyclerView.this, null);
        bottom = rect.bottom - rect.top;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mInnerAdapter != null && mObserver != null && isRegisterObserver) {
            mInnerAdapter.unregisterAdapterDataObserver(mObserver);
        }
        mInnerAdapter = adapter;
        mWrapAdapter.setInnerAdapter(adapter);
        if (canRefresh) mWrapAdapter.setHeadView(mHead.getHeadView());
        if (canLoadMore) mWrapAdapter.setFootView(mFoot.getFootView());
        super.setAdapter(mWrapAdapter);
        mInnerAdapter.registerAdapterDataObserver(mObserver);
        isRegisterObserver = true;
        mObserver.onChanged();
    }

    public HeadViewAdapter getHead() {
        return mHead;
    }

    public void setHead(HeadViewAdapter adapter) {
        if (adapter == null) setCanRefresh(false);
        this.mHead = adapter;
    }

    public FootViewAdapter getFoot() {
        return mFoot;
    }

    public void setFoot(FootViewAdapter adapter) {
        this.mFoot = adapter;
        if (adapter == null) {
            setCanLoadMore(false);
        } else {
            mFoot.getFootView().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mFoot.getState() != FootViewAdapter.STATE_NO_MORE) {
                        onLoadMore();
                    }
                }
            });
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        LayoutManager manager = null;
        if (layout instanceof GridLayoutManager) {
            GridLayoutManager.SpanSizeLookup spanSizeLookup =
                    ((GridLayoutManager) layout).getSpanSizeLookup();
            manager = new GridLayoutManager(getContext(),
                    ((GridLayoutManager) layout).getSpanCount(),
                    ((GridLayoutManager) layout).getOrientation(),
                    ((GridLayoutManager) layout).getReverseLayout()) {
                @Override
                public boolean canScrollVertically() {
                    return canScrollVertical();
                }
            };
            ((GridLayoutManager) manager).setSpanSizeLookup(spanSizeLookup);
        } else if (layout instanceof LinearLayoutManager) {
            manager = new LinearLayoutManager(getContext(),
                    ((LinearLayoutManager) layout).getOrientation(),
                    ((LinearLayoutManager) layout).getReverseLayout()) {
                @Override
                public boolean canScrollVertically() {
                    return canScrollVertical();
                }
            };
        } else if (layout instanceof StaggeredGridLayoutManager) {
            manager = new StaggeredGridLayoutManager(
                    ((StaggeredGridLayoutManager) layout).getSpanCount(),
                    ((StaggeredGridLayoutManager) layout).getOrientation());

        }
        super.setLayoutManager(manager);
    }

    private boolean canScrollVertical() {
//        return mState == STATE_PULLING ? false : true;
        if (mState == STATE_PULLING && mHead.getVisibleHeight() > 0){
            return false;
        }
//        return mHead.getVisibleHeight() > 0 ? false : true;
        return true;
    }

    public void setCanRefresh(boolean canRefresh) {
        this.canRefresh = canRefresh;
        mWrapAdapter.setHeadView(null);
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
        if (canLoadMore) {
            mWrapAdapter.setFootView(mFoot.getFootView());
        } else {
            mWrapAdapter.setFootView(null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = e.getRawY();
                mLastX = e.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = e.getRawY();
                float x = e.getRawX();
                moveByState(x, y);
                mLastX = x;
                mLastY = y;
                break;
            default:
                onRelease();
                break;
        }
        return super.onTouchEvent(e);
    }

    private void moveByState(float x, float y) {
        switch (mState) {
            case STATE_NORMAL:
            case STATE_REFRESHING:
                onNormal(x, y);
                break;
            case STATE_PULLING:
                onPull(y);
                break;
            case STATE_PUSHING:
                onPush(y);
                break;
        }
    }

    private void onPush(float y) {
        if (canLoadMore) {
            float deltaY = mLastY - y;
            if (mFoot.onPush(deltaY)) {
//                setState(STATE_LOADING);
                onLoadMore();
            }
        }
    }

    private void onPull(float y) {
        if (canRefresh) {
            float deltaY = y - mLastY;
            mHead.onPull(deltaY / mDamping);
        }
    }

    private void onNormal(float x, float y) {
        if (mHead.getHeadView().getParent() != null && (y - mLastY) > Math.abs(x - mLastX)) {
            setState(STATE_PULLING);
        } else if (!canScrollVertically(1) && (mLastY - y) > Math.abs(mLastX - x)) {
            setState(STATE_PUSHING);
        }else {
            setState(STATE_NORMAL);
        }
    }

    private void onRelease() {
        if (mState == STATE_PULLING) {
            if (mHead.onRelease()) {
                setState(STATE_REFRESHING);
                if (!mRefreshListener.onRefresh()) {
                    mHead.onError();
                } else {
                    setNoMoreData(false);
                }
            } else {
                reset();
            }
        } else if (mState == STATE_PUSHING) {
            mFoot.onRelease();
            reset();
        }
    }

    private boolean isLoading() {
        return mFoot.isLoading();
    }

    private void onLoadMore() {
//        setState(STATE_LOADING);
        mFoot.onLoading();
//        if (mOnLoadListener != null) {
        if (!mOnLoadListener.onLoad()) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
//                        mOnLoadListener.onError();
                    mFoot.onError();
                    reset();
                }
            }, 200);
//            }
        }
    }

    public void loadComplete() {
        if (!isNoMore) {
            mFoot.compeletLoad();
            mFoot.onRelease();
        }
        reset();
    }

    public void refreshComplete() {
        setRefreshing(false);
    }

    public boolean isRefreshing() {
        return mHead.isRefreshing();
    }

    private void reset() {
        setState(STATE_NORMAL);
    }

    private void setState(int state) {
        mState = state;
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    public int getDamping() {
        return mDamping;
    }

    public void setDamping(int damping) {
        this.mDamping = damping;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mRefreshListener = null;
        mOnLoadListener = null;
        if (mInnerAdapter != null && mObserver != null && isRegisterObserver) {
            mInnerAdapter.unregisterAdapterDataObserver(mObserver);
        }
    }

    public void setRefreshing(boolean refreshing) {
        if (canRefresh) {
            if (refreshing) {
                smoothScrollToPosition(0);
            }
            mHead.setRefreshing(refreshing);
        }
    }

    public void setOnLoadListener(OnLoadListener listener) {
        mOnLoadListener = listener;
    }

    public void setNoMoreData(boolean isNoMore) {
        setState(STATE_NORMAL);
        mFoot.setNoMore(isNoMore);
        this.isNoMore = isNoMore;
    }

    public void refreshError() {
        mHead.onError();
        reset();
    }

    public void loadError() {
        mFoot.onError();
        mFoot.onRelease();
        reset();
    }

    public interface OnRefreshListener {

        /**
         * 返回FALSE，直接取消刷新，返回TRUE则等到调用stopRefresh时停止刷新
         *
         * @return 是否能刷新
         */
        boolean onRefresh();

//        void onRefreshError();
    }

    public interface OnLoadListener {
        /**
         * 返回FALSE，直接取消加载，返回TRUE则等到调用stopLoad时停止加载
         *
         * @return 是否能加载更多
         */
        boolean onLoad();


//        void onError();
    }

    @Override
    public void invalidateItemDecorations() {
        super.invalidateItemDecorations();
    }

    private class InnerObserver extends AdapterDataObserver {

        @Override
        public void onChanged() {
            mWrapAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart + 1, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart + 1, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart + 1, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart + 1, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition + 1, toPosition + 1);
        }
    }

    private class InnerItemDecoration extends ItemDecoration {
        private final ItemDecoration innerDecor;

        public InnerItemDecoration(ItemDecoration decor) {
            this.innerDecor = decor;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, State state) {
//            super.onDraw(c, parent, state);
            innerDecor.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, State state) {
            innerDecor.onDrawOver(c, parent, state);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            boolean isHead = view.equals(mHead.getHeadView());
            boolean isFoot = view.equals(mFoot.getFootView());
            if (!isHead && !isFoot)
                innerDecor.getItemOffsets(outRect, view, parent, state);
        }
    }
}
