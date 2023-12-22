package com.example.abrahamnews.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class EnglishCloudDataSource extends CloudDataSource {
    private EnglishApiService englishApiService;
    public EnglishCloudDataSource(){
        super();

                englishApiService=retrofit.create(EnglishApiService.class);
    }


    @Override
    public Flowable<List<News>> getNews() {
        return englishApiService.getNews();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return englishApiService.getBanners();
    }

    @Override
    public Single<List<News>> getBookmarkedNews() {
        return null;
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return englishApiService.getVideoNews();
    }

    @Override
    public Single<List<News>> search(String keyWord) {
        return null;
    }

    @Override
    public void bookmark(News news) {

    }
}
