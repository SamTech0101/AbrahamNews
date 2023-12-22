package com.example.abrahamnews.base;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.abrahamnews.Factory;


public abstract class BaseFragment extends Fragment {
    protected View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            rootView=inflater.inflate(getLayoutRes(),container,false);
            setUpView();
        }
        return rootView;

    }
    public abstract int getLayoutRes();
    public abstract void setUpView();
    public BaseActivity getBaseActivity(){
        return (Factory) getActivity();
    }
}
