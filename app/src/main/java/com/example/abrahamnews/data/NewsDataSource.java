package com.example.abrahamnews.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface NewsDataSource {

 Flowable<List<News>> getNews();
 Single<List<Banner>> getBanners();
 Single<List<News>> getBookmarkedNews();
 Single<List<News>> getVideoNews();
 Single<List<News>> search(String keyWord);
 void bookmark(News news);

}
