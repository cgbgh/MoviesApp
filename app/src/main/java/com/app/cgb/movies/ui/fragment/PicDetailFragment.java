package com.app.cgb.moviepreview.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.ui.activity.PicActivity;
import com.app.cgb.moviepreview.ui.adapter.ImgDetaiPageAdapter;

import java.util.List;

import butterknife.BindView;

public class PicDetailFragment extends BaseFragment {

    private static List<Images.ImagesBean> mImagList;
    private static int mPosition;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    private float startX;
    private long startTime;
    private ImgDetaiPageAdapter mAdapter;
    private int currentItem;
    private List<Integer> mPositionList;

    public static PicDetailFragment newInstance(List<Images.ImagesBean> imageList, int position) {
        PicDetailFragment fragment = new PicDetailFragment();
        mImagList = imageList;
        mPosition = position;
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int inflateView() {
        return R.layout.fragment_pic_detail;
    }

    @Override
    protected void initView() {
        setupToolbar();
        setupViewPager();
    }

    private void setupViewPager() {
//        PicPagerAdapter mAdapter = new PicPagerAdapter(mContext);
        mAdapter = new ImgDetaiPageAdapter(mContext,mImagList);
        vpContent.setAdapter(mAdapter);
        vpContent.setCurrentItem(mPosition);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentItem = position+1;
                int size = mImagList.size();
                if (mPositionList!=null){
                    size = mPositionList.size();
                }
                tvToolbarTitle.setText(currentItem+"/"+size);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vpContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getRawX();
                        long endTime = System.currentTimeMillis();
                        if (endTime-startTime< 100 && endX - startX <
                                ViewConfiguration.get(mContext).getScaledTouchSlop()){
                            ((PicActivity) mContext).switch2StaggeredPicsFragment(currentItem-1);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void setupToolbar() {
        mContext.setupNoTitleToolbar(toolbar);
        tvToolbarTitle.setText(mPosition+"/"+mImagList.size());
    }

    @Override
    protected void initData() {
    }

    @Override
    public void scrollToTop() {

    }

    public void setData(List<Integer> positionList, int position) {
//        mImagList = imageList;
        mPositionList = positionList;
        mAdapter.setList(positionList);
//        mAdapter.setData(mImagList);
        vpContent.setCurrentItem(position,false);
    }
}
