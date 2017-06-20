package com.app.cgb.moviepreview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.cgb.moviepreview.basic.BaseFragment;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.presenter.NetWorkPresenterImpl;
import com.app.cgb.moviepreview.Iview.BaseIView;
import com.app.cgb.moviepreview.utils.ToastUtils;


public abstract class BaseRequstFragment<T> extends BaseFragment implements BaseIView<T>{

    private NetWorkPresenterImpl<T> mPresenter;
    private T mData;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        request();
    }

    @Override
    protected void initData() {
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

    @Override
    public void scrollToTop() {
    }


    @Override
    public void onError(String msg) {
        ToastUtils.showShortToastSafe(mContext, msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading(boolean isSuccess) {

    }



    @Override
    public void setupUI(T t) {
        mData = t;
        onResponse(t);
    }

    protected abstract void onResponse(T t);

    public T getResponse(){
        return mData;
    }
}
