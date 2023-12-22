package com.example.abrahamnews.search;


import com.example.abrahamnews.R;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchContract.Presenter {
    private Disposable disposable;
    private SearchContract.View view;

    public SearchPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    private NewsDataSource newsDataSource;


    @Override
    public void search(String keyWord) {
        newsDataSource.search(keyWord).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {
                    if (newsList.isEmpty()){
                        view.showNoResult();
                    }
                        else{
                        view.showResult(newsList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(view.context().getString(R.string.all_unknown_error));
                    }
                });

    }

    @Override
    public void attachView(SearchContract.View view) {
        this.view=view;

    }

    @Override
    public void detachView() {
        this.view=null;

        if (disposable!=null && !disposable.isDisposed())
            disposable.dispose();


    }

}
