package com.app.cgb.moviepreview.presenter;

import com.app.cgb.moviepreview.Iview.BaseIView;
import com.app.cgb.moviepreview.OnLoadCompleteListener;
import com.app.cgb.moviepreview.model.RequestModelImpl;

public abstract class NetWorkPresenterImpl<T> extends BasePresenter<BaseIView> implements INetWorkPresenter, OnLoadCompleteListener<T> {

    private final RequestModelImpl model;

    public NetWorkPresenterImpl(BaseIView baseIView) {
        super(baseIView);
        model = new RequestModelImpl(this);
    }

    @Override
    public void getData() {
        mView.showLoading();
        loadData(model);
    }

    protected abstract void loadData(RequestModelImpl model);

    @Override
    public void onSuccess(T t) {
        mView.setupUI(t);
        mView.hideLoading(true);
    }

    @Override
    public void onFailed(String error) {
        mView.hideLoading(false);
        mView.onError(error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (model != null){
            model.onDestroy();
        }
    }
}
