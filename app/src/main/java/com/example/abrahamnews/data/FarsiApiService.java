package com.example.abrahamnews.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface FarsiApiService {
    @GET("newsfa")
    Flowable<List<News>> getNews();
    @GET("bannerfa")
    Single<List<Banner>> getBanners();
    @GET("videofa")
    Single<List<News>> getVideoNews();
}
