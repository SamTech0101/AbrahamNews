package com.example.abrahamnews.hollywood;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.data.News;
import com.example.abrahamnews.detail.NewsDetailActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HollywoodTrans extends RecyclerView.Adapter<HollywoodTrans.NewsViewHolder> {


    private List<News> newsList=new ArrayList<>();
    private int pendingNewsPosition;

    public HollywoodTrans() {
    }

    public HollywoodTrans(List<News> newsList) {
        this.newsList = newsList;
    }

    public void setNewsList(List<News> newsList){

        this.newsList=newsList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new NewsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
      newsViewHolder.bindNews(newsList.get(i));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageNews;
        private TextView titleNews;
        private TextView dateNews;
        private View videoIndicator;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageNews=itemView.findViewById(R.id.iv_news_image);
            titleNews=itemView.findViewById(R.id.tv_news_title);
            dateNews=itemView.findViewById(R.id.tv_news_date);
            videoIndicator=itemView.findViewById(R.id.iv_news_video);
            setScaleAnimation(itemView);

        }
        public void bindNews(final News news){
            Picasso.get().load(news.getImage()).placeholder(R.drawable.label_abrahamnews).into(imageNews);
            titleNews.setText(news.getTitle());
            if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())== ViewCompat.LAYOUT_DIRECTION_RTL){
                titleNews.setGravity(Gravity.RIGHT);
            }else {
                titleNews.setGravity(Gravity.LEFT);
            }
            dateNews.setText(news.getDate());
            videoIndicator.setVisibility(news.isVideoNews()? View.VISIBLE: View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pendingNewsPosition=getAdapterPosition();
                    Intent intent=new Intent(itemView.getContext(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.EXTRA_KEY_DETAIL,news);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsBookmarked(News news){
        newsList.set(pendingNewsPosition,news);
        notifyItemChanged(pendingNewsPosition);


 }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }
}
