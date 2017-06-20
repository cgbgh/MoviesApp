package com.app.cgb.moviepreview.presenter;

import com.app.cgb.moviepreview.OnLoadCompleteListener;
import com.app.cgb.moviepreview.entity.MoviesOfCompany;
import com.app.cgb.moviepreview.model.RequestModelImpl;
import com.app.cgb.moviepreview.Iview.IMovieOfCompany;

public class MovieOfCompanyPresenterImpl extends BasePresenter<IMovieOfCompany> implements IMovieOfCompanyPresenter,OnLoadCompleteListener<MoviesOfCompany> {

    private RequestModelImpl mListModel;

    public MovieOfCompanyPresenterImpl(IMovieOfCompany iMovieOfCompany) {
        super(iMovieOfCompany);
        mListModel = new RequestModelImpl(this);
    }

    @Override
    public void onSuccess(MoviesOfCompany moviesOfCompany) {
        mView.setMovieOfCompany(moviesOfCompany);
        mView.hideLoading();
    }

    @Override
    public void onFailed(String error) {
        mView.hideLoading();
        mView.showToast(error);
    }

    @Override
    public void loadMovieOfCompany(int type, int companyId, int pageIndex) {
        mView.showLoading();
        mListModel.loadMoviesOfCompany(type,companyId,pageIndex);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListModel!=null){
            mListModel.onDestroy();
            mListModel = null;
        }
    }
}
