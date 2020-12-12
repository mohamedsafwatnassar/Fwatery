package com.example.fwatery;

import android.content.Context;


import androidx.appcompat.app.AppCompatDelegate;

import com.orhanobut.hawk.Hawk;

public class Application extends android.app.Application {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
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
