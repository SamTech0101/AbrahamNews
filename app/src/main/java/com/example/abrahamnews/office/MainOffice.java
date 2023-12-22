package com.example.abrahamnews.office;

import com.example.abrahamnews.data.Banner;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsDataSource;
import com.example.abrahamnews.wiFi.ConnectivityReceiver;

import org.reactivestreams.Subscription;


import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainOffice implements OfficePermit.presenter , ConnectivityReceiver.ConnectivityReceiverListener {
    private OfficePermit.view view;
    private boolean isViewRendered;
    private Subscription newsSubscription;
    private NewsDataSource newsDataSource;
    private boolean isBannerRendered;
    private boolean isConnected = ConnectivityReceiver.isConnected();

    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    public MainOffice(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void getBanners() {
        view.setProgressIndicator(true);
        newsDataSource.getBanners().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        try {
                            compositeDisposable.add(d);
                        } catch (Exception e) {
                            isViewRendered=false;
                            isBannerRendered=false;
                            view.showEmptyBillboard(false);


                        }
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {
                        view.showBanners(banners);
                        view.setProgressIndicator(false);
                       isViewRendered = true;
                       isBannerRendered=true;
                        view.showEmptyBillboard(true);


                    }

                    @Override

                    public void onError(Throwable e) {
                        view.setProgressIndicator(false);
                        isViewRendered = false;
                        isBannerRendered=false;
                        view.checkConnection(false);
                        view.showEmptyBillboard(false);


//                        view.showError(view.context().getString(R.string.tv_noInternet_tutnOn_title));


//                       view.showError(view.context().getString(R.string.tv_noInternet_tutnOn_title));
                    }
                });
//        newsDataSource.getBanners().subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<List<Banner>>() {
//                    @Override
//                    public void accept(List<Banner> banners) throws Exception {
//                        view.showBanners(banners);
//                        isViewRendered=true;
//
//                    }
//                }).doOnError(throwable -> {
//                    view.setProgressIndicator(false);
//                    view.showError("There is any data in Banners !");
//                    MainOffice.this.detachView();
//
//                }).doOnSubscribe(new Consumer<Subscription>() {
//            @Override
//            public void accept(Subscription subscription) throws Exception {
//                MainOffice.this.bannerSubscription=subscription;
//
//            }
//        }).subscribe();

    }

    @Override
    public void getConnectivity(boolean z) {
       view.showNoInternet(z);
    }

    @Override
    public void getNewsList() {
    newsDataSource.getNews().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(new Consumer<List<News>>() {
                @Override
                public void accept(List<News> news) throws Exception {
                    view.showNews(news);
                    isViewRendered=true;
                }
            })
            .doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
//                    view.showError(view.context().getString(R.string.all_unknown_error));
//                    view.showError(view.context().getString(R.string.tv_noInternet_tutnOn_title));
                    isViewRendered=false;
                    view.checkConnection(false);





                }
            })
            .doOnSubscribe(new Consumer<Subscription>() {
                @Override
                public void accept(Subscription subscription) throws Exception {
                    MainOffice.this.newsSubscription =subscription;
                    isViewRendered=false;
                }
            })
            .subscribe();
    }

    @Override
    public void attachView(OfficePermit.view view) {
     this.view=view;
     if(!isViewRendered){
         getNewsList();
         getBanners();
//         view.showEmptyBillboard(false);

     }
     else{
         view.checkConnection(true);
//         view.showEmptyBillboard(true);
     }

    }

    @Override
    public void detachView() {
     this.view=null;
     if (compositeDisposable.size()>0&&compositeDisposable !=null){
         compositeDisposable.clear();}
         if(newsSubscription !=null){
            newsSubscription.cancel();
         }


     }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        getConnectivity(isConnected);

    }
}

