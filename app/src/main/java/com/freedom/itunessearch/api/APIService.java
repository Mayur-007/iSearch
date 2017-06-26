package com.freedom.itunessearch.api;

import com.freedom.itunessearch.api.model.TrackModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {

    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<TrackModel> getTracks(@Query("term") String term);
}