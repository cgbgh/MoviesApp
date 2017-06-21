package com.app.cgb.moviepreview.basic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

//    private App app;
//    private boolean shouldRemove = true;

    public abstract int setLayoutResId();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResId());
        ButterKnife.bind(this);
//        app = App.getApp();
        initView();
        initData(savedInstanceState);
    }

    public void setupNoTitleToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    public void setupToolbar(Toolbar toolbar, String tittle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(tittle);
        }
    }

    public void setupToolbar(Toolbar toolbar, @StringRes int tittle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(tittle);
        }
    }

    public void setToolbarTitle(int activity_behind_make_toolbar) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(activity_behind_make_toolbar);
        }
    }


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            startActivity(new Intent(this, TransitActivity.class));
//        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void startActivity(Intent intent) {
//        app.addActivity(this);
        super.startActivity(intent);
    }

    public void startActivityFromTransit(int position, Intent intent) {
//        ArrayList<String> activityStrs = app.getActivityStrs();
//        ArrayList<Intent> intents = app.getIntents();
//        for (int i= position + 1; i<activityStrs.size(); i++){
//            activityStrs.remove(i);
//            intents.remove(i);
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        super.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
//        if (shouldRemove){
//            App.getApp().removeActivity(this);
//        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        ArrayList<Intent> intents = app.getIntents();
//        ArrayList<String> activities = app.getActivityStrs();
//        outState.putStringArrayList(Constants.ACTIVITY, activities);
//        outState.putParcelableArrayList(Constants.INTENT, intents);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        app = App.getApp();
//        ArrayList<Intent> intents =
//                savedInstanceState.getParcelableArrayList(Constants.ACTIVITY);
//        ArrayList<String> activities =
//                savedInstanceState.getStringArrayList(Constants.ACTIVITY);
//        app.setIntents(intents);
//        app.setActivityStrs(activities);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
