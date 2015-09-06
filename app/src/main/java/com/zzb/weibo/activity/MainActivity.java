package com.zzb.weibo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zzb.weibo.R;
import com.zzb.weibo.http.api.WeiBoApi;
import com.zzb.weibo.http.base.RetrofitHelper;
import com.zzb.weibo.model.StatusList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitHelper.getApi(WeiBoApi.class).getFriendsTimeLine().subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<StatusList>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError ", e);
            }

            @Override
            public void onNext(StatusList statuses) {
                Log.i(TAG, "onNext " + statuses.statuses.size());
            }
        });
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
