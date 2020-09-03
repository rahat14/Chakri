package com.metacoders.cakri.Service;

import android.content.Context;


import com.metacoders.cakri.Api.ChakriApi;
import com.metacoders.cakri.Utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private  Context context ;





    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }


    public ChakriApi getApi() {
        return retrofit.create(ChakriApi.class);
    }
}
