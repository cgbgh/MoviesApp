package com.app.cgb.moviepreview.presenter;

public interface ISearchResultPresenter {
//    void loadSearchResult(String keyword,int locationId);
    void loadSearchResult(int searchType, String keyword, int pageIndex);
}
