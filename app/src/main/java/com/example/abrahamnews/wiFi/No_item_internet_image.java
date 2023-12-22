
package com.example.sepehrnews.wiFi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.sepehrnews.R;
import com.example.sepehrnews.base.BaseActivity;


public class No_item_internet_image extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_no_item_internet_image);
        initComponent();
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        AppCompatButton bt_retry = (AppCompatButton) findViewById(R.id.button_noIntenrnet_retry);
        ImageView imageView = (ImageView) findViewById(R.id.iv_noInternet);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
        translateAnimation.setDuration(1100);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new BounceInterpolator());
        imageView.startAnimation(translateAnimation);
        bt_retry.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent restartIntent = No_item_internet_image.this.getBaseContext().getPackageManager().getLaunchIntentForPackage(No_item_internet_image.this.getBaseContext().getPackageName());
                if (restartIntent != null) {
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                No_item_internet_image.this.startActivity(restartIntent);
            }
        });

    }


    @Override
    public void onBackPressed() {

    }

    public void setProgressIndicator(boolean mustShow) {
    }

    protected void onRestart() {
        super.onRestart();
        finish();
    }
}