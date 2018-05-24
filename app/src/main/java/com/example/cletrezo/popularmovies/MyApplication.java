package com.example.cletrezo.popularmovies;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }
    public  static MyApplication getmInstance(){
        return mInstance;
    }
    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
}
