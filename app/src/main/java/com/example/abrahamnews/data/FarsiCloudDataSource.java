package com.example.abrahamnews.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class FarsiCloudDataSource extends CloudDataSource {
    private FarsiApiService farsiApiService;
    public FarsiCloudDataSource(){
            super();
                farsiApiService =retrofit.create(FarsiApiService.class);


    }


    @Override
    public Flowable<List<News>> getNews() {
        return farsiApiService.getNews();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return farsiApiService.getBanners();
    }

    @Override
    public Single<List<News>> getBookmarkedNews() {
        return null;
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return farsiApiService.getVideoNews();
    }

    @Override
    public Single<List<News>> search(String keyWord) {
        return null;
    }

    @Override
    public void bookmark(News news) {

    }
}
