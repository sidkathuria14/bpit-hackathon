package com.example.sidkathuria14.symptomchecker;

import com.example.sidkathuria14.symptomchecker.models.SymptomsObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.sidkathuria14.symptomchecker.MainActivity.TOKEN;

/**
 * Created by sidkathuria14 on 25/3/18.
 */

public interface SymptomsSelectorApi {
    @GET("symptoms?language=en-gb&format=json&token=" + TOKEN)
    Call<ArrayList<SymptomsObject>> callSymptoms();

}
