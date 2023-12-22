package com.example.abrahamnews.data;

import android.content.Context;


import com.example.abrahamnews.setting.SettingSharedPrefManager;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository implements NewsDataSource {
    private CloudDataSource cloudDataSource;
    private LocalDataSource localDataSource;

    public NewsRepository(Context context) {
        localDataSource=AppDatabase.getInstance(context).getLocalDataSource();
if (SettingSharedPrefManager.getInstance(context).getDefaultLanguage().equalsIgnoreCase("fa"))
        {
            cloudDataSource=new FarsiCloudDataSource();
        }else{
            cloudDataSource=new EnglishCloudDataSource();
        }

            }

    @Override
    public Flowable<List<News>> getNews() {
        cloudDataSource.getNews().subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<List<News>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<News> newsList) {
                        localDataSource.savedNewsList(newsList);

                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return localDataSource.getNews();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return cloudDataSource.getBanners();
    }

    @Override
    public Single<List<News>> getBookmarkedNews() {
        return localDataSource.getBookmarkedNews();
    }

    @Override
    public Single<List<News>> getVideoNews() {
        return cloudDataSource.getVideoNews();
    }

    @Override
    public Single<List<News>> search(String keyWord) {
        return localDataSource.search(keyWord);
    }

    @Override
    public void bookmark(News news) {
        localDataSource.bookmark(news);
           }
           public void clearCache(){
        localDataSource.removeAllRows();
           }
}
