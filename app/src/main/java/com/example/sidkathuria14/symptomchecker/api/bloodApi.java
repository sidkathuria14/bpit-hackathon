package com.example.sidkathuria14.symptomchecker.api;

import com.example.sidkathuria14.symptomchecker.models.bloodPOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface bloodApi {
    @GET("{bloodpk}/{bankpk}")
    Call<bloodPOJO> getBlood(@Path("bloodpk") String bloodpk, @Path("bankpk") String bankpk);
}
