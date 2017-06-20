package com.app.cgb.moviepreview.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.entity.DailyRecommendation;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.ui.fragment.MTTopListFragment;
import com.app.cgb.moviepreview.ui.fragment.MTimeFragment;
import com.app.cgb.moviepreview.ui.fragment.MovieNewsContainFragment;
import com.app.cgb.moviepreview.utils.PicLoadUtils;
import com.app.cgb.moviepreview.utils.SizeUtils;
import com.app.cgb.moviepreview.utils.TextUtils;

import butterknife.BindView;

public class MainActivity extends BaseRequestActivity<DailyRecommendation> implements View.OnClickListener{
    //    权限请求码
    private static final int PERMISSION_REQUEST = 0;
    //    抽屉视图所占屏幕的比例
    private static final float DRAWER_SIZE = 4f / 5f;
    //    再按一次退出最大时长
    private static final long EXIT_MAX_TIME = 2000;
    //    toolbar双击回到顶部最大时长
    private static final long TOOLBAR_MAX_CLICK_TIME = 1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.drawer_nav)
    NavigationView drawerNav;

    private SparseArray<String> mFragmentArrayMap = new SparseArray<>();
    private long exitTime;
    private int mNavMenuItemId;
    private String mTitle;
    private long toolbarClickTime;
    private DailyRecommendation.NewsBean news;
    private boolean isSwitching;
    private BaseFragment mCurrentFragment;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar, "");
        toolbar.setOnClickListener(this);
        addFragmentsWithTitles();
        setupDrawer();
        setupListener();
    }

    /**
     * 绑定fragment和title，存放于arrayMap中
     */
    private void addFragmentsWithTitles() {
        mFragmentArrayMap.put(R.id.movie_preview, getResources().getString(R.string.movie_preview));
        mFragmentArrayMap.put(R.id.movie_top_list, getResources().getString(R.string.toplist_title));
        mFragmentArrayMap.put(R.id.movie_news, getResources().getString(R.string.movie_news));
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BaseFragment defaultFragment = getDefaultFragment();
        transaction.add(R.id.content,defaultFragment,mTitle)
                .commit();
        mCurrentFragment = defaultFragment;
    }


    /**
     * 设置抽屉属性
     */
    private void setupDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
//        设置icon无渲染
        drawerNav.setItemIconTintList(null);
        ViewGroup.LayoutParams layoutParams = drawerNav.getLayoutParams();
        layoutParams.width = (int) (SizeUtils.getScreenWidth(this) * DRAWER_SIZE);
        drawerNav.setLayoutParams(layoutParams);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (newState == DrawerLayout.STATE_IDLE && isSwitching){
                    switchFragment();
                    isSwitching = false;
                }
            }
        });
    }

    /**
     * 根据mNavMenuItemId，得到fragment
     * fragment为空，切换到新建的默认fragment
     * 同步toolbar标题
     */
    private void switchFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit);
        BaseFragment fragment =
                (BaseFragment) manager.findFragmentByTag(mFragmentArrayMap.get(mNavMenuItemId));
        mTitle = mFragmentArrayMap.get(mNavMenuItemId);
        if (fragment == null){
            fragment = getFragmentById(mNavMenuItemId);
            if (fragment == null) {
                fragment = getDefaultFragment();
            }
            transaction.add(R.id.content,fragment,mTitle)
                    .hide(mCurrentFragment);
        }else {
            transaction.show(fragment)
                    .hide(mCurrentFragment);
        }
        mCurrentFragment = fragment;
        transaction.commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mTitle);
        }

    }

    /**
     * 根据id获取fragment
     *
     * @param itemId  抽屉导航菜单Id
     * @return fragment
     */
    private BaseFragment getFragmentById(int itemId) {
        if (mFragmentArrayMap == null) {
            addFragmentsWithTitles();
        }
        switch (itemId) {
            case R.id.movie_preview:
                return new MTimeFragment();
            case R.id.movie_top_list:
                return new MTTopListFragment();
            case R.id.movie_news:
                return new MovieNewsContainFragment();
        }
        return null;
    }

    private BaseFragment getDefaultFragment() {
        BaseFragment fragment = new MovieNewsContainFragment();
        mNavMenuItemId = R.id.movie_news;
        mTitle = mFragmentArrayMap.get(mNavMenuItemId);
        return fragment;
    }

    private void setupListener() {
        drawerNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START, true);
                }
                if (mNavMenuItemId != itemId) {
                    mNavMenuItemId = itemId;
                    isSwitching = true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                startActivity(intent);
                break;

        }
        return true;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        checkPermissions();
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        model.loadDailyRecommendation();
    }

    /**
     * 检测是否有内存卡读写权限
     */
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        initMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        int mMenuLayout = R.menu.main_activity_menu;
        menuInflater.inflate(mMenuLayout, menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                if (System.currentTimeMillis() - toolbarClickTime > TOOLBAR_MAX_CLICK_TIME) {
                    toolbarClickTime = System.currentTimeMillis();
                } else {
                    mCurrentFragment.scrollToTop();
                }
                break;

            case R.id.iv_nav:
                startNewsActivity();
                break;
        }

    }

    private void startNewsActivity() {
        if (news != null) {
            Intent intent = new Intent(this, NewsDetailActivity.class);
            intent.putExtra(Constants.NEWS_ID, news.getNewsID());
            startActivity(intent);
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START, true);
        } else {
            if (System.currentTimeMillis() - exitTime > EXIT_MAX_TIME) {
                Snackbar.make(toolbar, "再按一次退出", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")
                        .setMessage("请打开文件读写权限，否则部分功能将无法使用")
                        .setPositiveButton("现在打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setData(Uri.fromParts("package", getPackageName(), null));
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
//                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mFragmentArrayMap != null) {
            mFragmentArrayMap.clear();
            mFragmentArrayMap = null;
        }
    }

    @Override
    protected void onResponse(DailyRecommendation dailyRecommendation) {
        RelativeLayout rlNavHead = (RelativeLayout) drawerNav.getHeaderView(0);
        ImageView ivNav = (ImageView) rlNavHead.findViewById(R.id.iv_nav);
        TextView tvNavTitle = (TextView) rlNavHead.findViewById(R.id.tv_nav_title);
        news = dailyRecommendation.getNews();
        if (news != null) {
            PicLoadUtils.loadNormalPic(this, news.getImageUrl(), ivNav);
            tvNavTitle.setText(TextUtils.handleEmptyText(news.getTitle()));
            ivNav.setOnClickListener(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constants.NAVMENU_ITEM_ID, mNavMenuItemId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mNavMenuItemId = savedInstanceState.getInt(Constants.NAVMENU_ITEM_ID, -1);
        switchFragment();
    }

}
