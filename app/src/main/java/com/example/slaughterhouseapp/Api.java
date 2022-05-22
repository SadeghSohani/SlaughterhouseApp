package com.example.slaughterhouseapp;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface Api {

    @GET("allchickens")
    Call<List<ChickenAsset>> getAllChickens();

    @PUT("updatechicken")
    Call<RequestResponse> updateChicken(@Body RequestBody body);

}