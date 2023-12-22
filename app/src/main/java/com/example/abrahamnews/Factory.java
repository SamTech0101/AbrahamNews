package com.example.abrahamnews;

import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.abrahamnews.base.BaseActivity;
import com.example.abrahamnews.bookmark.BookmarkFragment;
import com.example.abrahamnews.category.CategoriesFragment;
import com.example.abrahamnews.hollywood.Hollywood;
import com.example.abrahamnews.office.Office;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import java.util.Stack;

public class Factory extends BaseActivity {

    private long exitTime = 0;
    private Fragment homeFragment;
    private View progressBar;
    private Toast toast;

    private Fragment categoriesFragment;
    private Fragment bookmarkFragment;
    private Fragment videoFragment;
    private BottomNavigation bottomNavigation;
    private Stack<Integer> horizontalStack = new Stack<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(getLayoutInflater().inflate(R.layout.toast_custom, (ViewGroup) findViewById(R.id.custom_toast_layout)));

    }


    public void setUpViews() {

        progressBar = findViewById(R.id.frame_main_progressBar);
        bottomNavigation = findViewById(R.id.bn_home);


        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {

                horizontalStack.add(i);
                switch (i){


                    case  R.id.tab_home:
                        if(homeFragment==null)
                            homeFragment=new Office();
                        replaceTransaction(homeFragment);
                        bottomNavigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_700));

                        break;

                    case R.id.tab_category:
                        if(categoriesFragment ==null)
                            categoriesFragment=new CategoriesFragment();
                        replaceTransaction(categoriesFragment);
                        bottomNavigation.setBackgroundColor(getResources().getColor(R.color.teal_800));
                        break;

                    case R.id.tab_bookmarks:
                        if(bookmarkFragment==null)
                            bookmarkFragment=new BookmarkFragment();
                        replaceTransaction(bookmarkFragment);
                        bottomNavigation.setBackgroundColor(getResources().getColor(R.color.indigo_500));

                        break;

                    case R.id.tab_videos:
                        if(videoFragment==null)
                            videoFragment=new Hollywood();
                        replaceTransaction(videoFragment);
                        bottomNavigation.setBackgroundColor(getResources().getColor(R.color.cyan_700));
                        break;

                }
            }
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }


    @Override
    public void setProgressIndicator(boolean mustShow) {
        progressBar.setVisibility(mustShow ? View.VISIBLE : View.GONE);

    }


    private void replaceTransaction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_fragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//
//    @Override
//    public void onBackPressed() {
//       if(horizontalStack.size()>1){
//           horizontalStack.pop();
//           switch(horizontalStack.peek()){
//               case R.id.tab_home:
//                   replaceTransaction(homeFragment);
//                   bottomNavigation.setSelectedItemWithoutNotifyListener(0);
//                   bottomNavigation.setBackgroundColor(getResources().getColor(R.color.blue_grey_700));
//
//
//                   break;
//               case R.id.tab_category:
//                   replaceTransaction(categoriesFragment);
//                   bottomNavigation.setSelectedItemWithoutNotifyListener(1);
//                   bottomNavigation.setBackgroundColor(getResources().getColor(R.color.darkSlate));
//
//                   break;
//               case R.id.tab_bookmarks:
//                   replaceTransaction(bookmarkFragment);
//                   bottomNavigation.setSelectedItemWithoutNotifyListener(2);
//                   bottomNavigation.setBackgroundColor(getResources().getColor(R.color.indigo_500));
//
//                   break;
//               case R.id.tab_videos:
//                   replaceTransaction(videoFragment);
//                   bottomNavigation.setSelectedItemWithoutNotifyListener(3);
//                   bottomNavigation.setBackgroundColor(getResources().getColor(R.color.purple_700));
//
//           }
//       }
//       else{
//
//          finish();
//       }
//    }


    public void onBackPressed() {
        if (bottomNavigation.getSelectedItem() != 0) {
            bottomNavigation.setSelectedItem(0);
        } else {
            exitApp();
        }
    }


    public void exitApp() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            toast.show();
            exitTime = System.currentTimeMillis();
            return;
        }
        finish();
    }



}