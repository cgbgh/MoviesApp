package com.app.cgb.moviepreview.api;

import com.app.cgb.moviepreview.entity.CompanyList;
import com.app.cgb.moviepreview.entity.Credits;
import com.app.cgb.moviepreview.entity.DailyRecommendation;
import com.app.cgb.moviepreview.entity.EventList;
import com.app.cgb.moviepreview.entity.Images;
import com.app.cgb.moviepreview.entity.MTTopLists;
import com.app.cgb.moviepreview.entity.MediaReview;
import com.app.cgb.moviepreview.entity.MovieComingNew;
import com.app.cgb.moviepreview.entity.MovieComment;
import com.app.cgb.moviepreview.entity.MovieExtendDetail;
import com.app.cgb.moviepreview.entity.MovieInTheater;
import com.app.cgb.moviepreview.entity.MovieNews;
import com.app.cgb.moviepreview.entity.MoviesOfCompany;
import com.app.cgb.moviepreview.entity.NewsDetail;
import com.app.cgb.moviepreview.entity.PersonsRelation;
import com.app.cgb.moviepreview.entity.ReviewDetails;
import com.app.cgb.moviepreview.entity.SearchResult;
import com.app.cgb.moviepreview.entity.TopListDetails;
import com.app.cgb.moviepreview.entity.TrailerList;
import com.app.cgb.moviepreview.entity.VideoList;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface MTimeMovieApi {

    @GET("Showtime/LocationMovies.api?locationId=290")
    Observable<MovieInTheater> getMovieInThread();

    @GET("Movie/MovieComingNew.api?locationId=290")
    Observable<MovieComingNew> getMovieComingNew();

    //    https://api-m.mtime.cn/News/NewsList.api?pageIndex=1  //新闻页
    @GET("News/NewsList.api")
    Observable<MovieNews> getMovieNews(@Query("pageIndex") int pageIndex);

    //    https://api-m.mtime.cn/News/Detail.api?newsId=1566695  //新闻详情页
    @GET("/News/Detail.api")
    Observable<NewsDetail> getNewsDetail(@Query("newsId") int newsId);

    //    https://api-m.mtime.cn/MobileMovie/Review.api?needTop=false  //资讯 影评页
    @GET("MobileMovie/Review.api?needTop=false")
    Observable<List<MovieComment>> getMovieReview();

    //    https://api-m.mtime.cn/Review/Detail.api?reviewId=7768063  影评详情
    @GET("Review/Detail.api")
    Observable<ReviewDetails> getReviewDetails(@Query("reviewId") int reviewId);

    //    https://api-m.mtime.cn/TopList/TopListOfAll.api?pageIndex=1  排行榜
    @GET("TopList/TopListOfAll.api")
    Observable<MTTopLists> getMTTopLists(@Query("pageIndex") int pageIndex);

    //    https://api-m.mtime.cn/TopList/TopListDetails.api?topListId=1469&pageIndex=1 排行榜详情
    @GET("TopList/TopListDetails.api?")
    Observable<TopListDetails> getTopListDetails(@Query("topListId") int topListId,
                                                 @Query("pageIndex") int pageIndex);

    //    https://api-m.mtime.cn/TopList/TopListFixedNew.api   排行榜
//    @GET("TopList/TopListFixedNew.api")
//    https://api-m.mtime.cn/Person/ImageAll.api?personId=1287527  人物照片
    @GET("Person/ImageAll.api")
    Observable<Images> getPersonImages(@Query("personId") int personId);

    //    https://api-m.mtime.cn/PageSubArea/TrailerList.api   预告片
    @GET("PageSubArea/TrailerList.api")
    Observable<TrailerList> getTrailerList();

    //    https://api-m.mtime.cn/Movie/extendMovieDetail.api?MovieId=209688  //影片详情—扩展资料
    @GET("Movie/extendMovieDetail.api")
    Observable<MovieExtendDetail> getMovieExtendDetail(@Query("MovieId") int movieId);

    //    https://api-m.mtime.cn/Movie/ImageAll.api?movieId=17418  电影照片
    @GET("Movie/ImageAll.api")
    Observable<Images> getImages(@Query("movieId") int movieId);


    //    https://api-m.mtime.cn/Person/Movie.api?personId=911757&pageIndex=1  人物相关作品
    @GET("Person/Movie.api")
    Observable<List<PersonsRelation>> getPersonRelations(@Query("personId") int personId,
                                                         @Query("pageIndex") int pageIndex);


    //    https://api-m.mtime.cn/Movie/events.api?MovieId=209688
    @GET("Movie/events.api")
    Observable<EventList> getEventList(@Query("MovieId") int movieId);

    //    https://api-m.mtime.cn/Movie/ClassicLines.api?MovieId=209688  经典台词
    @GET("Movie/ClassicLines.api")
    Observable<ResponseBody> getClassicLines(@Query("MovieId") int movieId);

    //    https://api-m.mtime.cn/Movie/CompanyList.api?MovieId=209688
    @GET("Movie/CompanyList.api")
    Observable<CompanyList> getCompanyList(@Query("MovieId") int movieId);

    //    https://api-m.mtime.cn/Movie/MediaComments.api?MovieId=209688 媒体评论
    @GET("Movie/MediaComments.api")
    Observable<MediaReview> getMediaReview(@Query("MovieId") int movieId);

    //https://api-m.mtime.cn/Movie/BehindMake.api?MovieId=175637  制作幕后
    @GET("Movie/BehindMake.api")
    Observable<ResponseBody> getBehindMake(@Query("MovieId") int MovieId);

    //https://api-m.mtime.cn/Movie/News.api?pageIndex=1&movieId=209688  电影解读
    @GET("Movie/News.api")
    Observable<MovieNews> getNewsOfMovie(@Query("pageIndex") int pageIndex,
                                         @Query("movieId") int movieId);

//    https://api-m.mtime.cn/Movie/Video.api?pageIndex=1&movieId=209688  //电影全部视频
    @GET("Movie/Video.api")
    Observable<VideoList> getVideoList(@Query("pageIndex") int pageIndex,
                                       @Query("movieId") int movieId);

//    https://api-m.mtime.cn/Company/MakeMovies.api?type=0&companyId=38176&pageIndex=1  公司其他作品
    @GET("Company/MakeMovies.api")
    Observable<MoviesOfCompany> getMoviesOfCompany(@Query("type") int type,
                                                   @Query("companyId") int companyId,
                                                   @Query("pageIndex") int pageIndex);

//    https://api-m.mtime.cn/Showtime/SearchVoice.api?searchType=3&keyword=%E8%83%A1%E6%AD%8C&pageIndex=1  搜索
    @GET("Showtime/SearchVoice.api")
    Observable<SearchResult> getSearchResult(@Query("searchType") int searchType,
                                             @Query("keyword") String keyword,
                                             @Query("pageIndex") int pageIndex);

//    https://api-m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api  每日推荐
    @GET("PageSubArea/GetRecommendationIndexInfo.api")
    Observable<DailyRecommendation> getDailyRecommendation();



//    https://api-m.mtime.cn/Movie/MovieCreditsWithTypes.api?movieId=216008
    @GET("Movie/MovieCreditsWithTypes.api")
    Observable<Credits> getCredits(@Query("movieId") int movieId);

//    https://api-m.mtime.cn/Movie/RelatedMovies.api?MovieId=216008
}
