package com.zaynax.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.zaynax.test.databinding.ActivityMainBinding;
import com.zaynax.test.view.adapter.MyTabAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        initVariables();
        initView();
        initFunction();
        initListener();

    }

    private void initVariables() {

    }

    private void initView() {
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

    }

    private void initFunction() {

        activityMainBinding.tabLayout.addTab( activityMainBinding.tabLayout.newTab().setText("Activated"));
        activityMainBinding.tabLayout.addTab( activityMainBinding.tabLayout.newTab().setText("Paid"));
        activityMainBinding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final MyTabAdapter adapter = new MyTabAdapter(this,getSupportFragmentManager(), activityMainBinding.tabLayout.getTabCount());
        activityMainBinding.viewPager.setAdapter(adapter);

        activityMainBinding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(activityMainBinding.tabLayout));
    }

    private void initListener() {
        activityMainBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                activityMainBinding.viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
}