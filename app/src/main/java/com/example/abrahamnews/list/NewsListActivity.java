package com.example.abrahamnews.list;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseActivity;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.office.PaperTrans;
import com.example.abrahamnews.search.SearchActivity;

import java.util.ArrayList;

public class NewsListActivity extends BaseActivity {
    private ArrayList<News> newsList;
    public static final String EXTRA_KEY_NEWS="news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        if(getIntent().getExtras()!=null){
            newsList=getIntent().getExtras().getParcelableArrayList(EXTRA_KEY_NEWS);
            if(newsList==null) {
                finish();
            }
        }else {
                finish();
            }

        setUpViews();
    }



    private void setUpViews(){
        setUpToolBar();
        RecyclerView recyclerView = findViewById(R.id.rv_newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
         recyclerView.setAdapter(new PaperTrans(newsList));
    }
    private void setUpToolBar(){
        View backButton =findViewById(R.id.iv_videoList_back);
        View searchButton =findViewById(R.id.iv_newsList_magnify);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsListActivity.this, SearchActivity.class));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void setProgressIndicator(boolean mustShow) {

    }
}
