package com.example.abrahamnews.search;

import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseActivity;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsRepositoryInjector;
import com.example.abrahamnews.office.PaperTrans;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SearchActivity extends BaseActivity implements TextWatcher,SearchContract.View {
    private RecyclerView recyclerViewSearch;
    private PaperTrans paperTrans;
    private ImageView back;
    private ImageView clear;
    private View textViewNoResult;
    private EditText searchET;
    private CoordinatorLayout root;
    private SearchContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
         setUpViews();

    }


    private void setUpViews(){
        recyclerViewSearch=findViewById(R.id.rv_search);
        recyclerViewSearch.setLayoutManager( new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        paperTrans =new PaperTrans();
        recyclerViewSearch.setAdapter(paperTrans);
        searchET=findViewById(R.id.et_search);
        searchET.addTextChangedListener(this);
        textViewNoResult=findViewById(R.id.tv_search_noResultMessage);
        root=findViewById(R.id.coordinator_search);
        presenter=new SearchPresenter(NewsRepositoryInjector.newsDataSourceInjector(context()));
        presenter.attachView(this);
        clear=findViewById(R.id.iv_search_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchET.setText("");
            }
        });
        back=findViewById(R.id.iv_search_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length()>0){
            clear.setVisibility(View.VISIBLE);
            presenter.search(s.toString());
        }
        else {
            clear.setVisibility(View.GONE);
            textViewNoResult.setVisibility(View.GONE);
            recyclerViewSearch.setVisibility(View.GONE);

        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void showResult(List<News> newsList) {
        paperTrans.setNewsList(newsList);
        recyclerViewSearch.setVisibility(View.VISIBLE);
        textViewNoResult.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showNoResult() {
        recyclerViewSearch.setVisibility(View.INVISIBLE);
        textViewNoResult.setVisibility(View.VISIBLE);

    }

    @Override
    public void setProgressIndicator(boolean mustShow) {

    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(root,errorMessage, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public Context context() {
        return this;
    }
}
