package com.example.abrahamnews.category;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.list.NewsListActivity;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Category[] categories=new Category[6];

    private Context context;
    public CategoryAdapter(Context context) {
        this.context = context;

        Category topStories = new Category();
        topStories.setId(0);
        topStories.setTitle(context.getString(R.string.category_topStories));
        topStories.setIcon(R.drawable.newspaper_wlcome);
        categories[0] = topStories;

        Category world = new Category();
        world.setId(1);
        world.setTitle(context.getString(R.string.category_World));
        world.setIcon(R.drawable.earth);
        categories[1] = world;

        Category business = new Category();
        business.setId(2);
        business.setTitle(context.getString(R.string.category_business));
        business.setIcon(R.drawable.domain);
        categories[2] = business;

        Category iran = new Category();
        iran.setId(3);
        iran.setTitle(context.getString(R.string.category_iran));
        iran.setIcon(R.drawable.flag);
        categories[3] = iran;

        Category health = new Category();
        health.setId(4);
        health.setTitle(context.getString(R.string.category_health));
        health.setIcon(R.drawable.heart_pulse);
        categories[4] = health;

        Category technology = new Category();
        technology.setId(5);
        technology.setTitle(context.getString(R.string.category_technology));
        technology.setIcon(R.drawable.chip);
        categories[5] = technology;


    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_categories,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.bindCategories(categories[i]);

    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewIcon;
        private TextView textViewTitle;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewIcon=itemView.findViewById(R.id.iv_categories_icon);
            textViewTitle =itemView.findViewById(R.id.tv_categories_title);
        }
        public void bindCategories(Category category){

            imageViewIcon.setImageResource(category.getIcon());
            textViewTitle.setText(category.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent=new Intent(context , NewsListActivity.class);
//                    // TODO: 8/27/2019  txt1
//                    context.startActivity(intent);
                    context.startActivity(new Intent(context, NewsListActivity.class));

                }
            });
        }
    }



    }

