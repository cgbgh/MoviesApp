package com.app.cgb.moviepreview.model;

import rx.subscriptions.CompositeSubscription;


public class BaseModelImpl implements BaseModel {

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onDestroy() {
        if (mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
            mCompositeSubscription = null;
        }
    }

    public CompositeSubscription getCompositeSubscription(){
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }
}
