package com.app.cgb.moviepreview.model;

import com.app.cgb.moviepreview.basic.App;
import com.app.cgb.moviepreview.OnLoadCompleteListener;
import com.app.cgb.moviepreview.R;

import rx.Observer;


public class ResponseObserver<T> implements Observer<T> {
    private final OnLoadCompleteListener mListener;

    public ResponseObserver(OnLoadCompleteListener listener) {
        mListener = listener;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mListener.onFailed(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        if (t != null) {
            mListener.onSuccess(t);
        } else {
            mListener.onFailed(App.getApplication().getString(R.string.no_response_data));
        }
    }
}
