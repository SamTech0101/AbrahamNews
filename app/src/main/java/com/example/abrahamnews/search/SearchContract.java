package com.example.abrahamnews.search;


import com.example.abrahamnews.base.BasePresenter;
import com.example.abrahamnews.base.BaseView;
import com.example.abrahamnews.data.News;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView
    {
        void showResult(List<News> newsList);
        void showNoResult();
    }
    interface Presenter extends BasePresenter<View> {
        void search(String keyWord);
    }

}
