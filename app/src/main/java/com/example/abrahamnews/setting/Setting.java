package com.example.abrahamnews.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.abrahamnews.R;
import com.example.abrahamnews.base.BaseActivity;
import com.example.abrahamnews.data.NewsRepository;


public class Setting extends BaseActivity {
    private RadioGroup radioGroup;
    private LottieAnimationView lottieAnimationView;

    private TextView restartMessage;
    private boolean isLocateChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setUpViews();

    }
    private void setUpViews(){
        this.lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie_setting);
        this.lottieAnimationView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent restartIntent = Setting.this.getBaseContext().getPackageManager().getLaunchIntentForPackage(Setting.this.getBaseContext().getPackageName());
                if (restartIntent != null) {
                    restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                Setting.this.startActivity(restartIntent);
            }
        });

        View backBtn=findViewById(R.id.iv_setting_back);
        restartMessage=findViewById(R.id.tv_setting_restartMessage);
        radioGroup=findViewById(R.id.radio_group_setting);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                    case R.id.rb_setting_en:
                        SettingSharedPrefManager.getInstance(Setting.this).savedDefaultLanguage("en");


                        break;
                    case R.id.rb_setting_fa:
                        SettingSharedPrefManager.getInstance(Setting.this).savedDefaultLanguage("fa");

                        break;

                }
                if (restartMessage.getAlpha()==0){
                    restartMessage.animate().alpha(1).setDuration(300);
                    isLocateChange=true;
                }

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isLocateChange){
            NewsRepository newsRepository=new NewsRepository(this);
            newsRepository.clearCache();
        }

    }

    @Override
    public void setProgressIndicator(boolean mustShow) {

    }
}
