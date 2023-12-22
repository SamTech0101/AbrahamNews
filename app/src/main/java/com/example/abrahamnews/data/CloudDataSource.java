package com.example.abrahamnews.data;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class CloudDataSource implements NewsDataSource {
    protected Retrofit retrofit;
    public CloudDataSource() {
         retrofit= new Retrofit.Builder()
                .baseUrl("http://185.4.30.234:5000/api/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
