package com.app.cgb.moviepreview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.cgb.moviepreview.basic.BaseActivity;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.presenter.NetWorkPresenterImpl;
import com.app.cgb.moviepreview.Iview.BaseIView;
import com.app.cgb.moviepreview.utils.ToastUtils;


public abstract class BaseRequestActivity<T> extends BaseActivity implements BaseIView<T> {
    private NetWorkPresenterImpl<T> mPresenter;
    private T mResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request();
    }

    public void request() {
        if (mPresenter == null) {
            mPresenter = new NetWorkPresenterImpl<T>(this) {
                @Override
                protected void loadData(RequestModelImpl model) {
                    onRequest(model);
                }
            };
        }
        mPresenter.getData();
    }

    protected abstract void onRequest(RequestModelImpl model);

    protected abstract void onResponse(T response);

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean isSuccess) {

    }

    @Override
    public void onError(String msg) {
        ToastUtils.showShortToastSafe(this, msg);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public void setupUI(T t) {
        mResponse = t;
        onResponse(t);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mResponse == null){
            request();
        }
    }

    public T getResponse() {
        return mResponse;
    }
}
