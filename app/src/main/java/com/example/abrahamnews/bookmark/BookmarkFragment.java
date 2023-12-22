package com.example.abrahamnews.bookmark;



import android.content.Context;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseFragment;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsRepositoryInjector;
import com.example.abrahamnews.office.PaperTrans;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class BookmarkFragment extends BaseFragment implements BookmarkContract.View{
private BookmarkContract.Presenter presenter;
private View emtyView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    presenter=new BookmarkPresenter(NewsRepositoryInjector.newsDataSourceInjector(getContext()));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bookmark;
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
    }

    @Override
    public void setUpView() {
        emtyView=rootView.findViewById(R.id.frame_empty_state);


    }

    @Override
    public void showBookmarkedNews(List<News> newsList) {
        RecyclerView recyclerView=rootView.findViewById(R.id.rv_bookmark);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(new PaperTrans(newsList));

    }

    @Override
    public void showEmptyView() {
        emtyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmtyView() {
        emtyView.setVisibility(View.GONE);
    }

    @Override
    public void setProgressIndicator(boolean mustShow) {
        getBaseActivity().setProgressIndicator(mustShow);

    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(rootView,errorMessage,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public Context context() {
        return getContext();
    }
}
