package com.zzb.weibo.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zzb.weibo.R;
import com.zzb.weibo.adapter.MainPagerAdapter;
import com.zzb.weibo.http.api.WeiBoApi;
import com.zzb.weibo.http.base.RetrofitHelper;
import com.zzb.weibo.model.Status;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mViewPager;
    private MainPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //有网络，加载网络
        //加载完之后，删除数据库中存在的from id begin to id end;
        //再插入网络加载到的数据

        //如果没有网络，无法刷新
        //加载更多时，查数据库，以id排序拿，直至无数据
    }

    private void initViews() {
        initToolbar();
        mViewPager = $(R.id.viewpager);
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initToolbar() {
        Toolbar toolbar = $(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("My Title");
        toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon();
    }

    private void postWeiboWithPic() {
        try {
            String encodedWeibo = URLEncoder.encode("post weibo with pic", "UTF-8");
            TypedFile file = new TypedFile("multipart/form-data", new File(Environment.getExternalStorageDirectory().getPath() + "/1.jpg"));
            RetrofitHelper.getApi(WeiBoApi.class).postWeiboWithPic(encodedWeibo, file, new Callback<Status>() {
                @Override
                public void success(Status status, Response response) {
                    Log.d(TAG, "success " + status);
                }
                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, "failure ", error);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected String getClassName() {
        return getClass().getSimpleName();
    }
}
