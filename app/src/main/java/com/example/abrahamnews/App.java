package com.example.abrahamnews;

import android.app.Application;
import android.content.Context;

import com.example.abrahamnews.setting.LocaleManager;
import com.example.abrahamnews.wiFi.ConnectivityReceiver;


public class App extends Application {
    private static App instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.updateResources(base,LocaleManager.getLanguage(base)));
    }
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized App getInstance() {
        return instance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
