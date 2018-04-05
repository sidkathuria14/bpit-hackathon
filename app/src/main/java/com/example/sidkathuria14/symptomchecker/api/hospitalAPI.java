package com.example.sidkathuria14.symptomchecker.api;

import com.example.sidkathuria14.symptomchecker.models.hospitalPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface hospitalAPI {
    @GET("{a}")
    Call<hospitalPOJO> getHospital(@Path("a") String a);
}
