package com.example.androidtest.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static APIClient instance = null;
    private ApiInterface myApi;

    private APIClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.10.0.5:802")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiInterface.class);
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public ApiInterface getMyApi() {
        return myApi;
    }

}
