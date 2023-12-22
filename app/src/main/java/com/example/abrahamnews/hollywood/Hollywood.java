package com.example.abrahamnews.hollywood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseFragment;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsRepository;
import com.example.abrahamnews.office.PaperTrans;
import com.example.abrahamnews.wiFi.No_item_internet_image;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Hollywood extends BaseFragment implements HollywoodPermit.view {
  private HollywoodPermit.presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new HollywoodOffice( new NewsRepository(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detachView();
        setProgressIndicator(false);

    }

    @Override
    public void showVideosList(List<News> newsList) {
        RecyclerView recyclerView=rootView.findViewById(R.id.rv_video);
        recyclerView.setLayoutManager(new LinearLayoutManager(context(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PaperTrans(newsList));

    }

    @Override
    public void showNOInternet(boolean isConnect) {
        if (!isConnect) {
            startActivity(new Intent(getActivity(), No_item_internet_image.class));
        }


    }

    @Override
    public void setProgressIndicator(boolean mustShow) {
        getBaseActivity().setProgressIndicator(mustShow);

    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootView,errorMessage,Snackbar.LENGTH_SHORT);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_video_news_list;
    }

    @Override
    public void setUpView() {

    }
}
