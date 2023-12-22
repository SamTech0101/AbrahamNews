package com.example.abrahamnews.hollywood;



import com.example.abrahamnews.R;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HollywoodOffice implements HollywoodPermit.presenter {
    private NewsDataSource newsDataSource;
    private HollywoodPermit.view view;
    private Disposable disposable;
    private boolean isViewRendered;

    public HollywoodOffice(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;

    }

    @Override
    public void loadVideosList() {
        view.setProgressIndicator(true);
        newsDataSource.getVideoNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;

                    }

                    @Override
                    public void onSuccess(List<News> newsList) {
                        view.showVideosList(newsList);
                        view.setProgressIndicator(false);
                        isViewRendered=true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setProgressIndicator(false);
                        view.showError(view.context().getResources().getString(R.string.all_unknown_error));
                        view.showNOInternet(false);

                    }
                });

    }


    @Override
    public void attachView(HollywoodPermit.view view) {
        this.view=view;
        if(!isViewRendered){
            loadVideosList();

        }
    }

    @Override
    public void detachView() {
        this.view=null;
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();

        }

    }
}
