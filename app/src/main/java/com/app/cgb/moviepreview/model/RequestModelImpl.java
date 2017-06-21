package com.app.cgb.moviepreview.model;

import com.app.cgb.moviepreview.OnLoadCompleteListener;
import com.app.cgb.moviepreview.api.ApiManager;
import com.app.cgb.moviepreview.entity.CompanyList;
import com.app.cgb.moviepreview.entity.Credits;
import com.app.cgb.moviepreview.entity.DailyRecommendation;
import com.app.cgb.moviepreview.entity.EventList;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.entity.MTTopLists;
import com.app.cgb.moviepreview.entity.MediaReview;
import com.app.cgb.moviepreview.entity.MovieBasicDetail;
import com.app.cgb.moviepreview.entity.MovieComingNew;
import com.app.cgb.moviepreview.entity.MovieComment;
import com.app.cgb.moviepreview.entity.MovieExtendDetail;
import com.app.cgb.moviepreview.entity.MovieInTheater;
import com.app.cgb.moviepreview.entity.MovieNews;
import com.app.cgb.moviepreview.entity.MoviesOfCompany;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.entity.PersonsDetail;
import com.app.cgb.moviepreview.entity.PersonsRelation;
import com.app.cgb.moviepreview.entity.ReviewDetails;
import com.app.cgb.moviepreview.entity.SearchResult;
import com.app.cgb.moviepreview.entity.TopListDetails;
import com.app.cgb.moviepreview.entity.TrailerList;
import com.app.cgb.moviepreview.entity.VideoList;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RequestModelImpl extends BaseModelImpl implements IRequestModel {

    private final ApiManager mApiManager;
    private final OnLoadCompleteListener mListener;

    public RequestModelImpl(OnLoadCompleteListener listener) {
        mApiManager = ApiManager.getInstance();
        mListener = listener;
    }

    public void subscribe(Observable observable) {
        getCompositeSubscription().add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ResponseObserver(mListener)));
    }

    @Override
    public void loadMovieInTheater() {
        Observable<MovieInTheater> observable =
                mApiManager.getMTimeMovieApi().getMovieInThread();
        subscribe(observable);
    }

    @Override
    public void loadMovieComingNew() {
        Observable<MovieComingNew> observable =
                mApiManager.getMTimeMovieApi().getMovieComingNew();
        subscribe(observable);
    }

    @Override
    public void loadTrailerList() {
        Observable<TrailerList> observable =
                mApiManager.getMTimeMovieApi().getTrailerList();
        subscribe(observable);
    }

    @Override
    public void loadCommentList() {

        Observable<List<MovieComment>> observable =
                mApiManager.getMTimeMovieApi().getMovieReview();

        subscribe(observable);
    }

    @Override
    public void loadMovieNews(int pageIndex) {
        Observable<MovieNews> observable =
                mApiManager.getmTimeRefreshApi().getMovieNews(pageIndex);
        subscribe(observable);
    }

    @Override
    public void loadMTTopLists(int pageIndex) {

        Observable<MTTopLists> observable =
                mApiManager.getMTimeMovieApi().getMTTopLists(pageIndex);
        subscribe(observable);

    }

    @Override
    public void loadTopListDetail(int topListId, int pageIndex) {

        Observable<TopListDetails> observable =
                mApiManager.getMTimeMovieApi().getTopListDetails(topListId, pageIndex);

        subscribe(observable);
    }

    @Override
    public void loadMovieImages(int movieId) {
        Observable<Images> observable =
                mApiManager.getMTimeMovieApi().getImages(movieId);
        subscribe(observable);
    }

    @Override
    public void loadEventList(int movieId) {
        Observable<EventList> observable =
                mApiManager.getMTimeMovieApi().getEventList(movieId);
        subscribe(observable);
    }

    @Override
    public void loadCompanyList(int movieId) {
        Observable<CompanyList> observable =
                mApiManager.getMTimeMovieApi().getCompanyList(movieId);
        subscribe(observable);
    }

    @Override
    public void loadMediaReview(int movieId) {

        Observable<MediaReview> observable =
                mApiManager.getMTimeMovieApi().getMediaReview(movieId);
        subscribe(observable);
    }

    @Override
    public void loadMovieNews(int pageIndex, int movieId) {
        Observable<MovieNews> observable =
                mApiManager.getMTimeMovieApi().getNewsOfMovie(pageIndex, movieId);
        subscribe(observable);
    }

    @Override
    public void loadPersonImages(int personId) {
        Observable<Images> observable =
                mApiManager.getMTimeMovieApi().getPersonImages(personId);
        subscribe(observable);
    }

    @Override
    public void loadVideoList(int pageIndex, int movieId) {
        Observable<VideoList> observable =
                mApiManager.getMTimeMovieApi().getVideoList(pageIndex, movieId);
        subscribe(observable);
    }

    @Override
    public void loadMoviesOfCompany(int type, int companyId, int pageIndex) {
        Observable<MoviesOfCompany> observable =
                mApiManager.getMTimeMovieApi().getMoviesOfCompany(type, companyId, pageIndex);
        subscribe(observable);
    }

    @Override
    public void loadSearchResult(int searchType, String keyword, int pageIndex) {
        Observable<SearchResult> observable =
                mApiManager.getMTimeMovieApi().getSearchResult(searchType, keyword, pageIndex);
        subscribe(observable);
    }

    @Override
    public void loadDailyRecommendation() {
        Observable<DailyRecommendation> observable =
                mApiManager.getmTimeRefreshApi().getDailyRecommendation();
        subscribe(observable);
    }

    @Override
    public void loadCredits(int movieId) {
        Observable<Credits> observable = mApiManager.getMTimeMovieApi().getCredits(movieId);
        subscribe(observable);
    }


    @Override
    public void loadBasicDetail(int movieId) {

        Observable<MovieBasicDetail> observable =
                mApiManager.getMTimeTicketApi().getMovieBasicDetail(movieId);
        subscribe(observable);
    }

    @Override
    public void loadExtendDetail(int movieId) {

        Observable<MovieExtendDetail> observable =
                mApiManager.getMTimeMovieApi().getMovieExtendDetail(movieId);
        subscribe(observable);
    }

    @Override
    public void loadNewsDetail(int newsId) {

        Observable<NewsDetail> observable =
                mApiManager.getMTimeMovieApi().getNewsDetail(newsId);
        subscribe(observable);
    }

    @Override
    public void loadPersonDetail(int personId) {

        Observable<PersonsDetail> observable =
                mApiManager.getMTimeTicketApi().getPersonDetail(personId);
        subscribe(observable);
    }

    @Override
    public void loadPersonRelations(int personId, int pageIndex) {

        Observable<List<PersonsRelation>> observable =
                mApiManager.getMTimeMovieApi().getPersonRelations(personId,pageIndex);
        subscribe(observable);
    }

    @Override
    public void loadClassicLines(int movieId) {

        Observable<ResponseBody> observable =
                mApiManager.getMTimeMovieApi().getClassicLines(movieId);
        subscribe(observable);
    }

    @Override
    public void loadBehindMake(int movieId) {

        Observable<ResponseBody> observable =
                mApiManager.getMTimeMovieApi().getBehindMake(movieId);
        subscribe(observable);

    }

    @Override
    public void loadReviewDetail(int reviewId) {
        Observable<ReviewDetails> observable =
                mApiManager.getMTimeMovieApi().getReviewDetails(reviewId);
        subscribe(observable);
    }

}
