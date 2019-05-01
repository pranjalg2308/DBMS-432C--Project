package com.kalabhedia.urbanclapclone.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrbanClapApiClient {

    //http://ff6906d6.ngrok.io
    private static final String BASE_URL = "http://65ef8301.ngrok.io";
    private static Retrofit retrofit = null;
    private static UrbanClapApi urbanClapApi = null;

    public synchronized static UrbanClapApi getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            urbanClapApi = retrofit.create(UrbanClapApi.class);
        }
        return urbanClapApi;
    }
}