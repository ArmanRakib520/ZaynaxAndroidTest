package com.zaynax.test.view.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zaynax.test.view.fragment.PaidFragment;
import com.zaynax.test.view.fragment.ActivatedFragment;

public class MyTabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyTabAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ActivatedFragment activatedFragment = new ActivatedFragment();
                return activatedFragment;
            case 1:
                PaidFragment paidFragment = new PaidFragment();
                return paidFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}