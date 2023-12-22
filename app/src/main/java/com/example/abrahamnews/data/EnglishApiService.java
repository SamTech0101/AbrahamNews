package com.example.abrahamnews.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface EnglishApiService {
//    @GET("p5gwn")
@GET("news")
Flowable<List<News>> getNews();
    @GET("banner")
    Single<List<Banner>> getBanners();
    @GET("video")
    Single<List<News>> getVideoNews();



}
