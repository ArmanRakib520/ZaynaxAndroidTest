package com.example.androidtest.Network;

import com.example.androidtest.model.ActivitionList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/activation-history/fetch-activation-history?")
    Call<List<ActivitionList>> getActivitionList(
            @Query("type") String type,
            @Query("page") String page);


}
