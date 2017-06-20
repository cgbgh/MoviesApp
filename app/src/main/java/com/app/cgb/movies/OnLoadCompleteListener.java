package com.app.cgb.moviepreview;


public interface OnLoadCompleteListener<T> {

    void onSuccess(T t);

    void onFailed(String error);

}
