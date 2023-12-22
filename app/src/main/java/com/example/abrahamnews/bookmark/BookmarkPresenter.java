package com.example.abrahamnews.bookmark;



import com.example.abrahamnews.R;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsDataSource;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarkPresenter implements BookmarkContract.Presenter {
    private BookmarkContract.View view;

    public BookmarkPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    private NewsDataSource newsDataSource;
    private Disposable disposable;

    @Override
    public void loadBookmarkesNews() {
        view.setProgressIndicator(true);
        newsDataSource.getBookmarkedNews().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<News>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onSuccess(List<News> newsList) {
                        if (newsList.isEmpty()){
                            view.showEmptyView();
                        }else{
                            view.showBookmarkedNews(newsList);
                            view.hideEmtyView();

                        }
                        view.setProgressIndicator(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setProgressIndicator(false);
                        view.showError(view.context().getResources().getString(R.string.all_unknown_error));

                    }
                });

    }

    @Override
    public void attachView(BookmarkContract.View view) {
        this.view=view;
        loadBookmarkesNews();

    }

    @Override
    public void detachView() {
        this.view=null;
        if(!disposable.isDisposed()&&disposable!=null){
            disposable.dispose();
        }
    }
}
