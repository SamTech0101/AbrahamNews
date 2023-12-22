package com.example.abrahamnews;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.abrahamnews.base.BaseActivity;

public class Welcome extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(Welcome.this, Factory.class));

            }
        }, 4000);
        setUpViews();
    }


    private void setUpViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }


    @Override
    public void setProgressIndicator(boolean mustShow) {

    }




    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    System.exit(0);}
}
