package com.example.sidkathuria14.symptomchecker.api;

import com.example.sidkathuria14.symptomchecker.models.Main_Morse_Model;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sidkathuria14 on 5/4/18.
 */

public interface MorseApi {
@POST("translate/morse2english.json")
    Call<Main_Morse_Model> getMorse(@Query("text")String text );
}
