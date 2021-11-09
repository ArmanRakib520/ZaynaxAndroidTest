package com.zaynax.test.Network.ApiUtil;

import android.util.Log;

import com.zaynax.test.Network.APIService;
import com.zaynax.test.Network.RetroClient;


public class ApiUtils {
    public static APIService getApiService(String baseURL){
        return RetroClient.getClient(baseURL).create(APIService.class);
    }
}
