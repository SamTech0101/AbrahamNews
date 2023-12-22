package com.example.abrahamnews.data;

import android.content.Context;

public class NewsRepositoryInjector {
    public static NewsDataSource newsDataSourceInjector(Context context){

        return new NewsRepository(context) ;
    }
}
