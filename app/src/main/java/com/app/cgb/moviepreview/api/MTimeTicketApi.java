package com.app.cgb.moviepreview.api;

import com.app.cgb.moviepreview.entity.MovieBasicDetail;
import com.app.cgb.moviepreview.entity.PersonsDetail;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MTimeTicketApi {

    //    https://ticket-api-m.mtime.cn/movie/detail.api?locationId=290&movieId=  影片详情-基本资料
    @GET("movie/detail.api?locationId=290")
    Observable<MovieBasicDetail> getMovieBasicDetail(@Query("movieId") int movieId);

    //    https://ticket-api-m.mtime.cn/person/detail.api?personId=911757&cityId=290  人物详情
    @GET("person/detail.api?cityId=290")
    Observable<PersonsDetail> getPersonDetail(@Query("personId") int personId);

}
