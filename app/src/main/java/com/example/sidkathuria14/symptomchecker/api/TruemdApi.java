package com.example.sidkathuria14.symptomchecker.api;

import com.example.sidkathuria14.symptomchecker.models.Truemd_model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sidkathuria14 on 4/4/18.
 */

public interface TruemdApi {
    @GET("api/v2/medicines.json")
    Call<Truemd_model> getMedicines(@Query("search")String search);
}
