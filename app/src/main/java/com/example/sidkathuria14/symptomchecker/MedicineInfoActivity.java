package com.example.sidkathuria14.symptomchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MedicineInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_info);




        ((TextView)findViewById(R.id.tvName)).setText(getIntent().getStringExtra("name"));
        ((TextView)findViewById(R.id.tvManufacturer)).setText(getIntent().getStringExtra("manu"));
        ((TextView)findViewById(R.id.tvMRP)).setText(getIntent().getStringExtra("mrp"));


    }
}
