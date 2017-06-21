package com.app.cgb.moviepreview.presenter;

public class BasePresenter<V> {

    protected V mView;

    public BasePresenter(V v){
        mView = v;
    }

    public void onDestroy(){
        mView = null;
    }
}
