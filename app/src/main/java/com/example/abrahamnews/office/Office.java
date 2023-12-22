package com.example.abrahamnews.office;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseFragment;
import com.example.abrahamnews.data.Banner;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.data.NewsRepositoryInjector;
import com.example.abrahamnews.list.NewsListActivity;
import com.example.abrahamnews.search.SearchActivity;
import com.example.abrahamnews.setting.Setting;
import com.example.abrahamnews.wiFi.ConnectivityReceiver;
import com.example.abrahamnews.wiFi.No_item_internet_image;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Office extends BaseFragment implements OfficePermit.view , SwipeRefreshLayout.OnRefreshListener {
private OfficePermit.presenter presenter;
    private boolean isConnected = ConnectivityReceiver.isConnected();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new MainOffice(NewsRepositoryInjector.newsDataSourceInjector(getContext()));


    }

    @Override
    public void showNews(final List<News> newsList) {
        RecyclerView recyclerViewNews =rootView.findViewById(R.id.rv_home_news);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(context(),LinearLayoutManager.VERTICAL,false));

        recyclerViewNews.setAdapter(new PaperTrans(newsList));

        recyclerViewNews.setNestedScrollingEnabled(false);

        View buttonViewAll =rootView.findViewById(R.id.tv_home_viewAll);
        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NewsListActivity.class);
                intent.putParcelableArrayListExtra(NewsListActivity.EXTRA_KEY_NEWS, (ArrayList<? extends Parcelable>) newsList);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showNoInternet(boolean z) {
        startActivity(new Intent(getActivity(), No_item_internet_image.class));

    }

    @Override
    public void showBanners(List<Banner> bannersList) {
         SliderAdapterExample slider =new SliderAdapterExample(context(),bannersList);
        SliderView sliderViewBanner =rootView.findViewById(R.id.imageSlider);
        sliderViewBanner.setSliderAdapter(slider);
        sliderViewBanner.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderViewBanner.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderViewBanner.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderViewBanner.setIndicatorSelectedColor(Color.WHITE);
        sliderViewBanner.setIndicatorUnselectedColor(Color.GRAY);
        sliderViewBanner.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderViewBanner.startAutoCycle();



   /*     binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION)
        binding.imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_LEFT
        binding.imageSlider.indicatorSelectedColor = Color.WHITE
        binding.imageSlider.indicatorUnselectedColor = Color.GRAY
        binding.imageSlider.scrollTimeInSec = 3
        binding.imageSlider.isAutoCycle = true
        binding.imageSlider.startAutoCycle()*/


      /*  RecyclerView recyclerViewBanner =rootView.findViewById(R.id.rv_home_banners);
        recyclerViewBanner.setLayoutManager(new LinearLayoutManager(context(),LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewBanner);
        recyclerViewBanner.setAdapter(new BoardTrans(bannersList));
        recyclerViewBanner.setNestedScrollingEnabled(false);*/

    }

    @Override
    public void showEmptyBillboard(boolean isBannerViewRendered) {
        View emptyBanner=rootView.findViewById(R.id.iv_banner_empty);
        if (isBannerViewRendered){
            emptyBanner.setVisibility(View.GONE);
        }else {
            emptyBanner.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void setProgressIndicator(boolean mustShow) {
      getBaseActivity().setProgressIndicator(mustShow);
    }

    @Override
    public void showError(String errorMessage) {
        makeText(getContext(), errorMessage, 2000).show();

    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);



    }
    @Override
    public void checkConnection(boolean isConnected) {

       if (!isConnected)
            makeText(getContext(), context().getString(R.string.tv_noInternet_tutnOn_title), 2000).show();

        }


    @Override
    public void onStop() {
        super.onStop();
    presenter.detachView();

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void setUpView() {
        swipeRefreshLayout=rootView.findViewById(R.id.swip_home);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            swipeRefreshLayout.setColorSchemeColors(context().getColor(R.color.colorAccent));
        }


        View searchButton=rootView.findViewById(R.id.iv_home_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        View settingView=rootView.findViewById(R.id.iv_home_setting);
        settingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Setting.class));
            }
        });


        Toast toast = new Toast(Objects.requireNonNull(getActivity()).getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(getLayoutInflater().inflate(R.layout.toast_custom_error, (ViewGroup) getActivity().findViewById(R.id.custom_toast_layout_error)));
    }



    public static Snackbar makeText(Context context, String message, int duration) {
        Activity activity = (Activity) context;
        Snackbar snackbar = Snackbar
                .make(activity.findViewById(android.R.id.content), message, duration);
        View layout = snackbar.getView();
        if (Build.VERSION.SDK_INT >= 21) {
            layout.setBackground(context.getDrawable(R.drawable.gradient_video));
        }
        android.widget.TextView text = (android.widget.TextView) layout.findViewById(R.id.snackbar_text);
        text.setTextColor(context.getResources().getColor(R.color.overlay_light_90));
        text.setTypeface(ResourcesCompat.getFont(context, R.font.iran_yekan));
        text.setTextSize(18.0f);
        return snackbar;
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onStart();
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);


    }
}
