package com.example.abrahamnews.data;



import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class LocalDataSource implements NewsDataSource {
    @Query("SELECT*FROM TBL_NEWS")
   @Override
   public abstract Flowable<List<News>> getNews();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void savedNewsList(List<News> newsList);

    @Override
   public Single<List<Banner>> getBanners() {
       return null;
   }

   @Override
   @Query("SELECT*FROM tbl_news WHERE is_bookmerked LIKE 1")
   public abstract Single<List<News>> getBookmarkedNews();

   @Override
   public Single<List<News>> getVideoNews() {
       return null;
   }
   @Query("SELECT * FROM TBL_NEWS WHERE title LIKE '%'||:keyWord||'%' ")
   @Override
   public abstract Single<List<News>> search(String keyWord);

   @Override
   @Update
   public abstract void bookmark(News news);
   @Query("DELETE FROM tbl_news")
   public abstract void removeAllRows();
}
