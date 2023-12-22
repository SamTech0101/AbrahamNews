package com.example.abrahamnews.category;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseFragment;


public class CategoriesFragment extends BaseFragment {


    @Override
    public void setUpView() {
        RecyclerView recyclerViewCategories =rootView.findViewById(R.id.rv_categories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewCategories.setAdapter(new CategoryAdapter(getContext()));


    }
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_categories;
    }

}
