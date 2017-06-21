package com.app.cgb.moviepreview.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private static final int HAS_HEAD = 0x01;
    private static final int HAS_FOOT = 0x02;
    private static final int HAS_HEAD_AND_FOOT = 0x03;
    public static final int ITEM_HEAD = -1;
    public static final int ITEM_FOOT = -2;
    public static final int ITEM_SECTION = -3;
    private static final String TAG = CommonAdapter.class.getSimpleName();

    private OnItemInit mOnItemInit;
    private List<T> mDatas;
    private int mFlag;
    private int mHeadLayoutId;
    private int mFootLayoutId;
    private boolean multiTypeSupported;
    private MultiTypeSupport mMultiTypeSupport;
    private SectionSupport mSectionSupport;
    private boolean sectionSupported;
    private LinkedHashMap<String, Integer> mSections;
    private Context mContext;
    private int mLayoutId;
    private int mSectionLayoutId;
    private OnItemDrag mOnItemDrag;
    private OnItemSwipe mOnItemSwipe;
    private boolean hasHead;
    private boolean hasFoot;
    private RecyclerView.AdapterDataObserver observer;
    private OnItemClickListener mOnItemClickListener;

    public CommonAdapter() {
    }

    public CommonAdapter(List<T> datas) {
        this.mDatas = datas;
    }

    /**
     * 如果设置了multiTypeSupport就不需要设置layoutId了。
     *
     * @param layoutId
     * @return
     */
    public CommonAdapter<T> setLayoutId(int layoutId) {
        this.mLayoutId = layoutId;
        return this;
    }


    public CommonAdapter<T> setData(final List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            mDatas.clear();
        } else {
            mDatas = datas;
        }
        notifyDataSetChanged();
        return this;
    }

    public CommonAdapter<T> setData(final List<T> datas, DiffUtil.DiffResult diffResult) {

        if (datas == null || datas.isEmpty()) {
            mDatas.clear();
        } else {
            mDatas = datas;
        }
        diffResult.dispatchUpdatesTo(this);
        return this;
    }

    public CommonAdapter<T> setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
        return this;
    }

    public CommonAdapter setOnItemInit(OnItemInit onItemInit) {
        mOnItemInit = onItemInit;
        return this;
    }

    public CommonAdapter setOnItemDrag(OnItemDrag onItemDrag) {
        mOnItemDrag = onItemDrag;
        return this;
    }

    public CommonAdapter setOnItemSwipe(OnItemSwipe onItemSwipe) {
        mOnItemSwipe = onItemSwipe;
        return this;
    }

    public CommonAdapter<T> setHead(@LayoutRes int headLayoutId) {
        addFlag(HAS_HEAD);
        mHeadLayoutId = headLayoutId;
        hasHead = true;
        return this;
    }

    public CommonAdapter<T> setFoot(@LayoutRes int footLayoutId) {
        addFlag(HAS_FOOT);
        mFootLayoutId = footLayoutId;
        hasFoot = true;
        return this;
    }

    public CommonAdapter<T> setMultiTypeSupport(MultiTypeSupport multiTypeSupport) {
        mMultiTypeSupport = multiTypeSupport;
        multiTypeSupported = true;
        return this;
    }

    public CommonAdapter<T> setSectionSupport(int layoutId, SectionSupport sectionSupport) {
        mSectionLayoutId = layoutId;
        mSectionSupport = sectionSupport;
        sectionSupported = true;
        mSections = new LinkedHashMap<>();
        findSection();
        observer = new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                findSection();
            }
        };
        registerAdapterDataObserver(observer);
        notifyDataSetChanged();
        return this;
    }

    private void findSection() {
        if (mDatas != null && mDatas.size() > 0) {

            int size = mDatas.size();
            int nSetion = 0;
            if ((mFlag & HAS_HEAD) == HAS_HEAD) {
                nSetion = 1;
            }
            mSections.clear();
            for (int i = 0; i < size; i++) {
                T t = mDatas.get(i);
                String title = mSectionSupport.getTitle(t);
                if (!mSections.containsKey(title)) {
                    mSections.put(title, i + nSetion);
                    nSetion++;
                }
            }

        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isHead(position)) {
            return ITEM_HEAD;
        } else if (isFoot(position)) {
            return ITEM_FOOT;
        } else if (isSection(position)) {
            return ITEM_SECTION;
        }
        if (multiTypeSupported) {
            return mMultiTypeSupport.getItemType(position);
        }
        return super.getItemViewType(position);
    }

    private boolean isSection(int position) {
        return sectionSupported && mSections.values().contains(position);
    }


    private boolean isFoot(int position) {
        return (mFlag == HAS_HEAD_AND_FOOT || mFlag == HAS_FOOT) && position == getItemCount() - 1;
    }

    private boolean isHead(int position) {
        return (mFlag == HAS_HEAD || mFlag == HAS_HEAD_AND_FOOT) && position == 0;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
//        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            private int lastState;

            @Override
            public boolean isItemViewSwipeEnabled() {
                return mOnItemDrag != null;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return mOnItemSwipe != null;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags;
                int swipFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags, swipFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                mOnItemDrag.onDrag(mDatas, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mOnItemSwipe.onSwipe(mDatas, viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    lastState = actionState;
                    mOnItemDrag.onSelectChange((ViewHolder) viewHolder);
                } else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    lastState = actionState;
                    mOnItemSwipe.onSelectChange((ViewHolder) viewHolder);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                if (lastState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    mOnItemDrag.onClearView(recyclerView, (ViewHolder) viewHolder);
                } else if (lastState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    mOnItemSwipe.onClearView(recyclerView, (ViewHolder) viewHolder);
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (observer != null) {
            unregisterAdapterDataObserver(observer);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_HEAD:
                return ViewHolder.get(mHeadLayoutId, parent, this);
            case ITEM_FOOT:
                return ViewHolder.get(mFootLayoutId, parent, this);
            case ITEM_SECTION:
                return ViewHolder.get(mSectionLayoutId, parent, this);
        }
        if (multiTypeSupported) {
            return ViewHolder.get(mMultiTypeSupport.getLayoutId(viewType), parent, this);
        }
        return ViewHolder.get(mLayoutId, parent, this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = getIndexForPosition(position);
        if (multiTypeSupported) {
            mMultiTypeSupport.onBind(holder, position, mDatas);
        } else {
            mOnItemInit.onBind(holder, position, mDatas);
        }
        if (mOnItemClickListener != null){
            final int index = position;
            final ViewHolder viewHolder = holder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viewHolder, index);
                }
            });
        }
    }

    public List<T> getDatas() {
        return mDatas;
    }

    private void addFlag(int flag) {
        mFlag |= flag;
    }

    public boolean isMultiTypeSupported() {
        return multiTypeSupported;
    }

    public boolean isSectionSupported() {
        return sectionSupported;
    }

    public boolean isHasHead() {
        return hasHead;
    }

    public boolean isHasFoot() {
        return hasFoot;
    }

    /**
     * 开启SectionSupport后需用此方法获取正确的position
     *
     * @param position
     * @return
     */
    private int getIndexForPosition(int position) {
        int index = position;
        int nSection = 0;
        if ((mFlag & HAS_HEAD) == HAS_HEAD) {
            index -= 1;
        }
        if (sectionSupported) {
            Set<Map.Entry<String, Integer>> entries = mSections.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                if (entry.getValue() < position) {
                    nSection++;
                }
            }
            index -= nSection;
        }
        return index;
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (mDatas != null) {
            itemCount += mDatas.size();
            if (sectionSupported && mSections != null) {
                itemCount += mSections.size();
            }
            if (mFlag == HAS_HEAD_AND_FOOT) {
                itemCount += 2;
            } else if (mFlag == HAS_HEAD || mFlag == HAS_FOOT) {
                itemCount += 1;
            }
        }
        return itemCount;
    }

    public Context getContext() {
        return mContext;
    }

    public void addData(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnItemInit {
        void onBind(ViewHolder holder, int position, List data);
    }

    public interface MultiTypeSupport {
        int getItemType(int position);

        int getLayoutId(int viewType);

        void onBind(ViewHolder holder, int position, List datas);
    }

    public interface SectionSupport<T> {
        String getTitle(T t);
    }

    public interface OnItemClickListener {
        void onItemClick(ViewHolder holder,int position);
    }

    public interface OnItemDrag {
        void onDrag(List mDatas, int from, int to);

        void onSelectChange(ViewHolder holder);

        void onClearView(RecyclerView recyclerView, ViewHolder holder);
    }

    public interface OnItemSwipe {
        void onSwipe(List mDatas, int position);

        void onSelectChange(ViewHolder holder);

        void onClearView(RecyclerView recyclerView, ViewHolder holder);
    }

}
