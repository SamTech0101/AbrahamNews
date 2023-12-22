package com.example.abrahamnews.office;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.abrahamnews.R;
import com.example.abrahamnews.data.Banner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BoardTrans extends RecyclerView.Adapter<BoardTrans.BannerViewHolder> {
    public BoardTrans(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }
   private View view;
    private List<Banner> bannerList=new ArrayList<>();
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BannerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.banner,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder bannerViewHolder, int i) {
        bannerViewHolder.bindViewHolder(bannerList.get(i));

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
       private TextView sourceTextView;
       private TextView titleTextView;
       private ImageView bannerImageView;
       public BannerViewHolder(@NonNull View itemView) {
           super(itemView);
           sourceTextView=itemView.findViewById(R.id.tv_banner_source);
           titleTextView=itemView.findViewById(R.id.tv_banner_title);
           bannerImageView=itemView.findViewById(R.id.iv_banner);
       }
       public void bindViewHolder(Banner banner){
//           Picasso.get().load(banner.getImage()).into(bannerImageView);
           Glide.with(itemView).load(banner.getImage()).into(bannerImageView);

           titleTextView.setText(banner.getTitle());
           sourceTextView.setText(banner.getSource());

       }
   }
}
