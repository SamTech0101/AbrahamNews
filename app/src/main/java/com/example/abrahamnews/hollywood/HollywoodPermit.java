package com.example.abrahamnews.hollywood;



import com.example.abrahamnews.base.BasePresenter;
import com.example.abrahamnews.base.BaseView;
import com.example.abrahamnews.data.News;

import java.util.List;

public interface HollywoodPermit {
    interface view extends BaseView {
        void showVideosList(List<News> newsList);
        public void showNOInternet(boolean isConnect) ;

    }

    interface presenter extends BasePresenter<view> {
        void loadVideosList();
    }
}
