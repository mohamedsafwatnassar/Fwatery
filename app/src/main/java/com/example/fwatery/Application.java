package com.example.fwatery;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.orhanobut.hawk.Hawk;

public class Application extends android.app.Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
    }

}
