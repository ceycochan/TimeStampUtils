package com.nshane.timestamputils;

import android.app.Application;

/**
 * Created by da on 2017-12-4.
 */

public class MainApplication extends Application {
    private static MainApplication mInstance;

    private MainApplication() {
    }

    public static synchronized MainApplication getInstance() {
        if (mInstance == null) {
            synchronized (MainApplication.class) {
                if (mInstance == null) {
                    mInstance = new MainApplication();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
