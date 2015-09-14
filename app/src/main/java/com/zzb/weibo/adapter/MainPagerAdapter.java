package com.zzb.weibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zzb.library.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity的PagerAdapter
 * Created by ZZB on 2015/9/9.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public MainPagerAdapter addFragment(Fragment fragment, String title){
        mFragments.add(fragment);
        mTitles.add(title);
        return this;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return ListUtils.getSize(mFragments);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
