package com.app.cgb.moviepreview.Iview;


public interface BaseIView<T> {

    void showLoading();
    void hideLoading(boolean isSuccess);
    void onError(String msg);
    void setupUI(T t);
}
