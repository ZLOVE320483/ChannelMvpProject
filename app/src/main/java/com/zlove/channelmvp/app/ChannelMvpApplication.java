package com.zlove.channelmvp.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by ZLOVE on 2016/10/28.
 */
public class ChannelMvpApplication extends Application {

    private static final String LOG_TAG = ChannelMvpApplication.class.getSimpleName();

    private static ChannelMvpApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ChannelMvpApplication getInstance() {
        if (instance == null) {
            Log.e(LOG_TAG, "Application is null");
        }
        return instance;
    }
}
