package com.app.cgb.moviepreview.model;

public interface IRequestModel {

    void loadMovieInTheater();

    void loadMovieComingNew();

    void loadTrailerList();

    void loadCommentList();

    void loadMovieNews(int pageIndex);

    void loadMTTopLists(int pageIndex);

    void loadTopListDetail(int topListId, int pageIndex);

    void loadMovieImages(int movieId);

    void loadEventList(int movieId);

    void loadCompanyList(int movieId);

    void loadMediaReview(int movieId);

    void loadMovieNews(int pageIndex, int movieId);

    void loadPersonImages(int personId);

    void loadVideoList(int pageIndex, int movieId );

    void loadMoviesOfCompany(int type, int companyId, int pageIndex);

//    void loadSearchResult(String keyword,int locationId,);

    void loadSearchResult(int searchType, String keyword, int pageIndex);

    void loadDailyRecommendation();

    void loadCredits(int MovieId);

    void loadBasicDetail(int movieId);

    void loadExtendDetail(int movieId);

    void loadNewsDetail(int newsId);

    void loadPersonDetail(int personId);

    void loadPersonRelations(int personId, int pageIndex);

    //    void loadTopListId(OnLoadCompleteListener listener);
    void loadClassicLines(int movieId);

    void loadBehindMake(int movieId);

    void loadReviewDetail(int reviewId);
}
