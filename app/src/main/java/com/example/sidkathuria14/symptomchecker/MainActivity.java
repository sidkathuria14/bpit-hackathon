package com.example.sidkathuria14.symptomchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sidkathuria14.symptomchecker.models.Main_Object;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Retrofit retrofit;RecyclerView rv;MyAdapter adapter;ArrayList<Main_Object> arrayList;
public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFrc2hpdGFnb2VsODhAZ21haWwuY29tIiwicm9sZSI6IlVzZXIiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiIzMTQ2IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMjAwIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IlByZW1pdW0iLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL2xhbmd1YWdlIjoiZW4tZ2IiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL2V4cGlyYXRpb24iOiIyMDk5LTEyLTMxIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9tZW1iZXJzaGlwc3RhcnQiOiIyMDE4LTA0LTA0IiwiaXNzIjoiaHR0cHM6Ly9zYW5kYm94LWF1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1MjI5MTYyNDQsIm5iZiI6MTUyMjkwOTA0NH0.Jxlgo3VU658m1O3v7SUon9jU_8ffLPdh1VH_B9_EqO0";
public static final String TAG = "symptoms";
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView)findViewById(R.id.rv);
arrayList= new ArrayList<>();
ArrayList<Integer> intList = (ArrayList<Integer>)getIntent().getSerializableExtra("list");

for(int i=0;i<intList.size();i++){
    Log.d(TAG, "onCreate: " + intList.get(i));
}

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://sandbox-healthservice.priaid.ch/")
                .build();

        SymptomApi symptomApi = retrofit.create(SymptomApi.class);

//symptomApi.getSymp().enqueue(new Callback<ArrayList<Main_Object>>() {
//    @Override
//    public void onResponse(Call<ArrayList<Main_Object>> call, Response<ArrayList<Main_Object>> response) {
//        Log.d(TAG, "onResponse: "+ response.isSuccessful());
//        Log.d(TAG, "onResponse: " + String.valueOf(response.body().size()));
//        Log.d(TAG, "onResponse: " + String.valueOf(response.body().get(1).getIssue().getIcdName().toString()));
////        Log.d(TAG, "onResponse: " + response.body().get(0).getIssue().getName());
//        for(int i=0;i<response.body().size();i++){
//            arrayList.add(response.body().get(i));
//        }
//        Log.d(TAG, "onResponse: " + String.valueOf(arrayList.size()));
//        adapter = new MyAdapter(arrayList,MainActivity.this);
//
//        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        rv.setAdapter(adapter);
////        adapter.notifyDataSetChanged();
//
//
//    }
//
//    @Override
//    public void onFailure(Call<ArrayList<Main_Object>> call, Throwable t) {
//        Log.d(TAG, "onFailure: " + t.getCause());
//        Log.d(TAG, "onFailure: " + t.getMessage());
//    }
//});
//    ArrayList<Integer> arrayList1 = new ArrayList<>();

symptomApi.getSymptoms("male",
                "1998",
                "en-gb", Arrays.toString(intList.toArray()),
                "json",TOKEN).enqueue(new Callback<ArrayList<Main_Object>>() {
    @Override
    public void onResponse(Call<ArrayList<Main_Object>> call, Response<ArrayList<Main_Object>> response) {

        if(response.isSuccessful()) {
            Log.d(TAG, "onResponse: " + response.body().get(0).getIssue().getName());
            for(int i=0;i<response.body().size();i++){
            arrayList.add(response.body().get(i));
        }
        Log.d(TAG, "onResponse: " + String.valueOf(arrayList.size()));
        adapter = new MyAdapter(arrayList,MainActivity.this);

        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv.setAdapter(adapter);
        }else Log.d(TAG, "onResponse: " + response.isSuccessful());

    }


    @Override
    public void onFailure(Call<ArrayList<Main_Object>> call, Throwable t) {
        Log.d(TAG, "onFailure: ");
    }
});

    }
}
