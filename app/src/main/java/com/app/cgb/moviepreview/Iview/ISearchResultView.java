package com.app.cgb.moviepreview.Iview;

import com.app.cgb.moviepreview.entity.SearchResult;

public interface ISearchResultView extends BaseView {
    void setSearchResultData(SearchResult searchResultData);
}
