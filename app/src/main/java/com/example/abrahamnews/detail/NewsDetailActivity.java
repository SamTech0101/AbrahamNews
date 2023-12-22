package com.example.abrahamnews.detail;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.abrahamnews.R;
import com.example.abrahamnews.SepehrJustifyTextView;
import com.example.abrahamnews.base.BaseActivity;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsDataSource;
import com.example.abrahamnews.data.NewsRepositoryInjector;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import icegroup.textviewjustify.TextViewJustify;

public class NewsDetailActivity extends BaseActivity {
    private News news;
    public static final String EXTRA_KEY_DETAIL = "news";
    private static final int VIDEO_WIDTH =254;
    private static final int VIDEO_HEIGHT=400;
    private JzvdStd videoPlayer;
    private NewsDataSource newsDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        newsDataSource= NewsRepositoryInjector.newsDataSourceInjector(this);
        news=getIntent().getParcelableExtra(EXTRA_KEY_DETAIL);

        setUpView();
    }

    private void setUpView(){
        Toolbar toolbar =findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout =findViewById(R.id.collapsingToolbar_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            collapsingToolbarLayout.setCollapsedTitleTextColor(Objects.requireNonNull(ContextCompat.getColorStateList(this, android.R.color.white)));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            collapsingToolbarLayout.setExpandedTitleTextColor(Objects.requireNonNull(ContextCompat.getColorStateList(this, android.R.color.transparent)));
        }
        Typeface typeface = ResourcesCompat.getFont(this, R.font.iran_yekan);
        collapsingToolbarLayout.setCollapsedTitleTypeface(typeface);
        collapsingToolbarLayout.setExpandedTitleTypeface(typeface);

        collapsingToolbarLayout.setTitle(news.getTitle());

        if(news.isVideoNews()){
            final FrameLayout frameLayout=findViewById(R.id.frame_detail_videoConteiner);
            frameLayout.post(new Runnable() {
                @Override
                public void run() {
                    ViewGroup.LayoutParams layoutParams=frameLayout.getLayoutParams();
                    layoutParams.height=frameLayout.getWidth()*VIDEO_WIDTH/VIDEO_HEIGHT;
                    frameLayout.setLayoutParams(layoutParams);

                }
            });
            frameLayout.setVisibility(View.VISIBLE);
            videoPlayer=findViewById(R.id.videoPlayer_detail);
            //  title ? title location is top left
            videoPlayer.setUp(news.getVideo(),"", Jzvd.SCREEN_NORMAL);
            Picasso.get().load(news.getImage()).into(videoPlayer.thumbImageView);

            /*
            because our video is low quality.We said : don't show fullScreen icon on video
             */
             videoPlayer.fullscreenButton.setVisibility(View.GONE);

        }else {
            ImageView imageView=findViewById(R.id.iv_detail);
            Picasso.get().load(news.getImage()).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }
        TextView title=findViewById(R.id.tv_detail_title);
        title.setText(news.getTitle());
        TextView date =findViewById(R.id.tv_detail_date);
        date.setText(news.getDate());
        TextViewJustify content=findViewById(R.id.tv_detail_content);
        content.setText(news.getContent(),true);
        final ImageView bookmarkImageView = findViewById(R.id.iv_bookmark);
        bookmarkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news.setBookmarked(!news.isBookmarked());
                newsDataSource.bookmark(news);
                bookmarkImageView.setImageResource(news.isBookmarked() ? R.drawable.bookmark_check : R.drawable.bookmark_outline);
                EventBus.getDefault().post(news);
            }
        });

        bookmarkImageView.setImageResource(news.isBookmarked() ? R.drawable.bookmark_check : R.drawable.bookmark_outline);
    }

    @Override
    public void onBackPressed() {
        if(JzvdStd.backPress()){
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
    JzvdStd.releaseAllVideos();
    }

    @Override
    public void setProgressIndicator(boolean mustShow) {

    }
}
