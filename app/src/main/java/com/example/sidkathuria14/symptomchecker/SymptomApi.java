package com.example.sidkathuria14.symptomchecker;

import com.example.sidkathuria14.symptomchecker.models.Main_Object;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.sidkathuria14.symptomchecker.MainActivity.TOKEN;

/**
 * Created by sidkathuria14 on 24/3/18.
 */

public interface SymptomApi {
    @Headers("Accept: application/json, text/plain, */*")
@GET("diagnosis")
Call<ArrayList<Main_Object>> getSymptoms(@Query("gender")String gender,
                                        @Query("year_of_birth")String year_of_birth,
                                        @Query("language")String lang,
                                        @Query("symptoms") String [] ids ,
                                        @Query("format") String format,
                                        @Query("token")String token);

    @GET("diagnosis?symptoms=[" + "13" + "]&gender=male&year_of_birth=1988&language=en-gb&format=json&token=" + TOKEN)
    Call<ArrayList<Main_Object>> getSymp();

}
