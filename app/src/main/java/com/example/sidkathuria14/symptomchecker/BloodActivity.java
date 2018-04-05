package com.example.sidkathuria14.symptomchecker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BloodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("htps://api-bpit.herokuapp.com/blood/")
//                .addConverterFactory(GsonConverterFactory.create()).build();



    }
}
