package com.example.sidkathuria14.symptomchecker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sidkathuria14.symptomchecker.api.TruemdApi;
import com.example.sidkathuria14.symptomchecker.models.Truemd_model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MedicinesActivity extends AppCompatActivity {

    Retrofit retrofit;Button btn;
    EditText etInput;
    String input;
    AutoCompleteTextView etAuto;
    public static final String TAG = "med";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        etAuto = (AutoCompleteTextView)findViewById(R.id.etAuto);







        etAuto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                input = etAuto.getText().toString();

                if ((input != null)) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.truemd.in/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    TruemdApi truemdApi = retrofit.create(TruemdApi.class);


                    Callback<Truemd_model> callback = new Callback<Truemd_model>() {
                        @Override
                        public void onResponse(Call<Truemd_model> call, final Response<Truemd_model> response) {
                            if (response.isSuccessful() && response.body().getSuggestions().size() !=0) {
                                Log.d("test", "onResponse: " + response.body().getSuggestions().get(0).getName());
                                final ArrayList<String> arrayList = new ArrayList<String>();
                                for(int i=0;i<response.body().getSuggestions().size();i++) {

                                    arrayList.add(response.body().getSuggestions().get(i).getName());
                                }

                                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MedicinesActivity.this,
                                        android.R.layout.simple_list_item_1, arrayList);

                                etAuto.setAdapter(adapter);
                                etAuto.setThreshold(3);
                                etAuto.setTextColor(Color.BLACK);
                                etAuto.showDropDown();
                                etAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Log.d("test", "onItemSelected: ");
                                        Intent intent = new Intent(MedicinesActivity.this,MedicineInfoActivity.class);
//                                intent.putExtra("name",adapterView.getItemAtPosition(i).getClass().getName());
//                                intent.putExtra("manu",adapterView.getItemAtPosition(i).getClass().);
//                                intent.putExtra("mrp",adapterView.getItemAtPosition(i).getClass().getName());
                                        Object item =  adapterView.getItemAtPosition(i);
                                        String name = (String)item;
                                        for(int j=0;j<arrayList.size();j++){
                                            if(response.body().getSuggestions().get(j).getName().equals(name)){
                                                intent.putExtra("mrp",response.body().getSuggestions().get(j).getMrp());
                                                intent.putExtra("manu",response.body().getSuggestions().get(j).getManufacturer());
                                            }
                                        }
//                                intent.putExtra("mrp",sug.getMrp());
//                                intent.putExtra("name",sug.getName());
//                                intent.putExtra("manu",sug.getManufacturer());
                                        intent.putExtra("name",name);

//                                Log.d("test", "onItemSelected: " +
//                                        (Suggestion)adapterView.getItemAtPosition(i).getClass().);
                                        startActivity(intent);
                                    }
                                });


//                        etAuto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                                Log.d("test", "onItemSelected: ");
//                                Intent intent = new Intent(TestActivity.this,MedicineInfoActivity.class);
//                                intent.putExtra("med",adapterView.getItemIdAtPosition(i));
//                                Log.d("test", "onItemSelected: " +adapterView.getItemAtPosition(i).toString() );
//                                startActivity(intent);
//
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> adapterView) {
//
//                            }
//                        });

                            } else Log.d("test", "onResponse: " + response.isSuccessful());
                        }

                        @Override
                        public void onFailure(Call<Truemd_model> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t.getMessage());
                            Log.d(TAG, "onFailure: ");
                        }
                    };
                    truemdApi.getMedicines(input).enqueue(callback);


                } else {
                    Toast.makeText(MedicinesActivity.this, "Please enter a medicine name", Toast.LENGTH_LONG);
                }



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        etInput.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });



    }

}
