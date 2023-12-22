package com.example.abrahamnews.office;

import com.example.abrahamnews.base.BasePresenter;
import com.example.abrahamnews.base.BaseView;
import com.example.abrahamnews.data.Banner;
import com.example.abrahamnews.data.News;

import java.util.List;

public interface OfficePermit {


  public interface presenter extends BasePresenter<view> {
   void getBanners();

   void getConnectivity(boolean z);

   void getNewsList();
  }

  public interface view extends BaseView {
   void showBanners(List<Banner> list);
   void showEmptyBillboard(boolean isBannerViewRendered);
   void showNews(List<News> list);
   void checkConnection(boolean isConnected);

   void showNoInternet(boolean z);
  }
 }