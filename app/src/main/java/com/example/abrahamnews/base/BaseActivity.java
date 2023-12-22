package com.example.abrahamnews.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abrahamnews.setting.LocaleManager;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.updateResources(newBase,LocaleManager.getLanguage(newBase)));
    }

    public abstract void setProgressIndicator(boolean mustShow);
}
