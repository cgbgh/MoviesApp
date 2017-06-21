package com.app.cgb.moviepreview.presenter;

import com.app.cgb.moviepreview.Iview.ISearchResultView;
import com.app.cgb.moviepreview.OnLoadCompleteListener;
import com.app.cgb.moviepreview.entity.SearchResult;
import com.app.cgb.moviepreview.model.RequestModelImpl;

public class SearchResultPresenterImpl extends BasePresenter<ISearchResultView> implements ISearchResultPresenter,OnLoadCompleteListener<SearchResult> {

    private RequestModelImpl mListModel;

    public SearchResultPresenterImpl(ISearchResultView iSearchResultView) {
        super(iSearchResultView);
        mListModel = new RequestModelImpl(this);
    }

    @Override
    public void onSuccess(SearchResult searchResult) {
        mView.setSearchResultData(searchResult);
        mView.hideLoading();
    }

    @Override
    public void onFailed(String error) {
        mView.hideLoading();
        mView.showToast(error);
    }

//    @Override
//    public void loadSearchResult(String keyword, int locationId) {
//        mView.showLoading();
//        mListModel.loadSearchResult(keyword,locationId,this);
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mListModel!=null){
            mListModel.onDestroy();
            mListModel = null;
        }
    }

    @Override
    public void loadSearchResult(int searchType, String keyword, int pageIndex) {
        mView.showLoading();
        mListModel.loadSearchResult(searchType,keyword,pageIndex);
    }
}
