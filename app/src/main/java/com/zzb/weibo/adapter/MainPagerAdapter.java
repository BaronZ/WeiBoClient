package com.zzb.weibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zzb.weibo.fragment.MyHomePageFragment;

/**
 * MainActivity的PagerAdapter
 * Created by ZZB on 2015/9/9.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MyHomePageFragment.getInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "我的首页";
            case 1:
                return "热门微博";
            default:
                return super.getPageTitle(position);
        }

    }
}
