package com.app.cgb.moviepreview.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.basic.BaseSingleTypeAdapter;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.ui.activity.PicActivity;
import com.app.cgb.moviepreview.ui.adapter.IndicatorAdapter;
import com.app.cgb.moviepreview.ui.adapter.PicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cgb on 2017/4/18.
 */

public class StaggeredPicsFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_indicator)
    RecyclerView rvIndicator;
    @BindView(R.id.rv_pics)
    RecyclerView rvPics;
    private static Images mImages;
    private PicAdapter picAdapter;
    private IndicatorAdapter indicatorAdapter;
    private long lastToolbarClickTime;
    private List<String> indicators;
    private int currentIndicator;
    private List<Integer> positionList;

    public static StaggeredPicsFragment newInstance(Images images) {
        StaggeredPicsFragment fragment = new StaggeredPicsFragment();
        mImages = images;
//        Bundle args = new Bundle();
//        args.putSerializable(Con／stants.IMAGE_OBJECT, images);
//        fragment.setArguments(args);／
        return fragment;
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_pics_list;
    }

    @Override
    protected void initView() {
        setupToolbar();
        setupIndicator();
        setupPicLayout();
    }

    private void setupToolbar() {
        mContext.setupToolbar(toolbar, "所有图片");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastToolbarClickTime > 1000) {
                    lastToolbarClickTime = System.currentTimeMillis();
                    return;
                }
                scrollToTop();
            }
        });
    }

    private void setupPicLayout() {
        picAdapter = new PicAdapter(mContext);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvPics.setLayoutManager(layoutManager);
        rvPics.setAdapter(picAdapter);
        rvPics.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments();
            }
        });
    }

    private void setupIndicator() {
        indicatorAdapter = new IndicatorAdapter(mContext);
        rvIndicator.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvIndicator.setAdapter(indicatorAdapter);
    }

    @Override
    protected void initData() {
//        Bundle args = getArguments();
//        Images images = (Images) args.getSerializable(Constants.IMAGE_OBJECT);
        if (mImages != null) {
            setData(mImages);
        }
    }

    private void setData(Images images) {
        List<Images.ImageTypesBean> imageTypes = images.getImageTypes();
        final List<Images.ImagesBean> imageList = images.getImages();
        if (imageList != null && imageList.size() > 0) {
            indicators = new ArrayList<>();
            for (int i = 0; i < imageTypes.size(); i++) {
                indicators.add(imageTypes.get(i).getTypeName());
            }
            indicatorAdapter.setData(indicators);
            picAdapter.setData(imageList);
//            currentList = imageList;
            setIndicatorItemClickLitener(imageList, imageTypes);
            setPicItemClickListener();
        }
    }

    private void setPicItemClickListener() {
        picAdapter.setOnItemClickListener(new BaseSingleTypeAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                PicDetailFragment picDetailFragment = PicDetailFragment.newInstance(currentList, position);
                ((PicActivity) mContext).switch2PicDetailFragment(positionList,position);
            }

            @Override
            public void onHeadClick() {

            }

            @Override
            public void onFootClick() {

            }
        });
    }

    private List<Images.ImagesBean> categoryImages(List<Images.ImagesBean> imageList, int type) {
        List<Images.ImagesBean> newImageList = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            Images.ImagesBean imagesBean = imageList.get(i);
            if (type == imagesBean.getType()) {
                newImageList.add(imagesBean);
            }
        }
        return newImageList;
    }
    private List<Integer> cateImages(List<Images.ImagesBean> imageList, int type) {
        ArrayList<Integer> positionList = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            Images.ImagesBean imagesBean = imageList.get(i);
            if (type == imagesBean.getType()) {
                positionList.add(i);
            }
        }
        return positionList;
    }

    private void setIndicatorItemClickLitener(final List<Images.ImagesBean> imageList, final List<Images.ImageTypesBean> imageTypes) {
        indicatorAdapter.setOnItemClickListener(new BaseSingleTypeAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == currentIndicator) {
                    return;
                }
                currentIndicator = position;
                indicatorAdapter.setCurrentItem(position);
//                rvIndicator.smoothScrollToPosition(position);
                Images.ImageTypesBean imageTypesBean = imageTypes.get(position);
                int type = imageTypesBean.getType();
//                picAdapter.setType(type);
//                picAdapter.clearData();
                if (type > 0) {
//                    currentList = categoryImages(imageList, type);
                    positionList = cateImages(imageList,type);
                    picAdapter.setList(positionList);
//                    picAdapter.setMovieAwards(currentList);
                    rvPics.smoothScrollToPosition(0);
                    mContext.getSupportActionBar().setTitle(imageTypesBean.getTypeName());
                } else {
//                    currentList = imageList;
                    positionList = null;
                    picAdapter.setList(null);
//                    picAdapter.setMovieAwards(imageList);
                    mContext.getSupportActionBar().setTitle("所有图片");
                }
            }

            @Override
            public void onHeadClick() {
            }

            @Override
            public void onFootClick() {

            }
        });
    }

    @Override
    public void scrollToTop() {
        rvPics.smoothScrollToPosition(0);
    }

    public void scrollToPosition(int position) {
        rvPics.smoothScrollToPosition(position);
    }
}
