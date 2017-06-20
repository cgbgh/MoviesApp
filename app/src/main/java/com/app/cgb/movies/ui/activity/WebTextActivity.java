package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.cgb.moviepreview.Constants;
import com.app.cgb.moviepreview.R;
import com.app.cgb.moviepreview.model.RequestModelImpl;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class WebTextActivity extends BaseRequestActivity<ResponseBody> {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_web_text)
    TextView tvWebText;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    public static final int FROM_CLASSICLINES = 0;
    public static final int FROM_BEHINDMAKE = 1;
    private int movieId;
    private int from;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_classice_lines;
    }

    @Override
    protected void initView() {
        setupToolbar(toolbar,"");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        from = getIntent().getIntExtra(Constants.WEB_TEXT_FROM,-1);
    }

    private void setupText(ResponseBody responseBody) {
        try {
                String text = responseBody.string();
                text = text.substring(9, text.length() - 4);
                tvWebText.setText(Html.fromHtml(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRequest(RequestModelImpl model) {
        switch (from){
            case FROM_BEHINDMAKE:
                model.loadBehindMake(movieId);
                setToolbarTitle(R.string.activity_behind_make_toolbar);
                break;
            case FROM_CLASSICLINES:
                model.loadClassicLines(movieId);
                setToolbarTitle(R.string.activity_classic_lines_toolbar_title);
                break;
        }
    }

    @Override
    protected void onResponse(ResponseBody response) {
        setupText(response);
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess) {
        pbLoading.setVisibility(View.GONE);
    }

}
