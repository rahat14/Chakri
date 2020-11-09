package com.metacoders.cakri;

import android.app.Application;
import android.content.Intent;

import com.metacoders.cakri.Service.persistant_service;

public  class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       // startService(new Intent(this, persistant_service.class));

    }
}
