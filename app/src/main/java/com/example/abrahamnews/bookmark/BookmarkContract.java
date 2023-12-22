package com.example.abrahamnews.bookmark;



import com.example.abrahamnews.base.BasePresenter;
import com.example.abrahamnews.base.BaseView;
import com.example.abrahamnews.data.News;

import java.util.List;

public interface BookmarkContract  {
    interface View extends BaseView {
        void showBookmarkedNews(List<News> newsList);
        void showEmptyView();
        void hideEmtyView();

    }
    interface Presenter extends BasePresenter<View> {
        void loadBookmarkesNews();
    }
}
