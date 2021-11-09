package com.zaynax.test.repository;

import android.content.Context;
import android.util.Log;

import com.zaynax.test.Network.APIService;
import com.zaynax.test.Network.ApiUtil.ApiUtils;
import com.zaynax.test.Network.HttpParams;
import com.zaynax.test.Network.callback.ActivationShow;
import com.zaynax.test.Network.callback.RequestCompleteListener;
import com.zaynax.test.model.ActivationList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivatedApiCall implements ActivationShow {
    @Override
    public void getActivation(Context context, String page, RequestCompleteListener callback) {
        APIService apiService = ApiUtils.getApiService(HttpParams.URL);
        apiService.getActivationList(page).enqueue(new Callback<ActivationList>() {
            @Override
            public void onResponse(Call<ActivationList> call, Response<ActivationList> response) {
                if (response.isSuccessful()) {
                    callback.onRequestSuccess(response.body());
                } else {
                    callback.onRequestFailed("Activated list Failed!");
                }
            }

            @Override
            public void onFailure(Call<ActivationList> call, Throwable t) {
                callback.onRequestFailed("Activated Failed!");
                Log.e("Activated", t.getMessage());
            }
        });
    }
}
