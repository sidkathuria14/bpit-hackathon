package com.example.sidkathuria14.symptomchecker;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ((Button) findViewById(R.id.medicines)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, MedicinesActivity.class));
            }
        });
        ((Button) findViewById(R.id.symptoms)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, SymptomSelectorActivity.class));
            }
        });
        ((Button) findViewById(R.id.blind)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, Morse.class));
            }
        });


    }
}
