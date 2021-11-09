package com.zaynax.test.Network;


import com.zaynax.test.model.ActivationList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET(HttpParams.END_POINT)
    Call<ActivationList> getActivationList(
            @Query("page") String page);
}
