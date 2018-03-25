package com.example.sidkathuria14.symptomchecker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sidkathuria14.symptomchecker.models.Main_Object;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Retrofit retrofit;RecyclerView rv;MyAdapter adapter;ArrayList<Main_Object> arrayList;
public static final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImthdGh1cmlhLnNpZGRoYXJ0aEB5YWhvby5pbiIsInJvbGUiOiJVc2VyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvc2lkIjoiMzA2NyIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6IjIwMCIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbGltaXQiOiI5OTk5OTk5OTkiLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL21lbWJlcnNoaXAiOiJQcmVtaXVtIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9sYW5ndWFnZSI6ImVuLWdiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjA5OS0xMi0zMSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcHN0YXJ0IjoiMjAxOC0wMy0yMyIsImlzcyI6Imh0dHBzOi8vc2FuZGJveC1hdXRoc2VydmljZS5wcmlhaWQuY2giLCJhdWQiOiJodHRwczovL2hlYWx0aHNlcnZpY2UucHJpYWlkLmNoIiwiZXhwIjoxNTIyMDExMTg4LCJuYmYiOjE1MjIwMDM5ODh9.4vu4hZq7CRW5K2eiCONPUjO8UPQnvuhi-PrnyxPelt8";
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

symptomApi.getSymp().enqueue(new Callback<ArrayList<Main_Object>>() {
    @Override
    public void onResponse(Call<ArrayList<Main_Object>> call, Response<ArrayList<Main_Object>> response) {
        Log.d(TAG, "onResponse: "+ response.isSuccessful());
        Log.d(TAG, "onResponse: " + String.valueOf(response.body().size()));
        Log.d(TAG, "onResponse: " + String.valueOf(response.body().get(1).getIssue().getIcdName().toString()));
//        Log.d(TAG, "onResponse: " + response.body().get(0).getIssue().getName());
        for(int i=0;i<response.body().size();i++){
            arrayList.add(response.body().get(i));
        }
        Log.d(TAG, "onResponse: " + String.valueOf(arrayList.size()));
        adapter = new MyAdapter(arrayList,MainActivity.this);

        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


    }

    @Override
    public void onFailure(Call<ArrayList<Main_Object>> call, Throwable t) {
        Log.d(TAG, "onFailure: " + t.getCause());
        Log.d(TAG, "onFailure: " + t.getMessage());
    }
});
//symptomApi.getSymptoms("male",
//                "1998",
//                "en-gb",new String []  {"13"},
//                "json",TOKEN).enqueue(new Callback<ArrayList<Main_Object>>() {
//    @Override
//    public void onResponse(Call<ArrayList<Main_Object>> call, Response<ArrayList<Main_Object>> response) {
//        Log.d(TAG, "onResponse: " + response.isSuccessful());
//        Log.d(TAG, "onResponse: " + response.message());
//        Log.d(TAG, "onResponse: " + response.errorBody());
//    }
//
//    @Override
//    public void onFailure(Call<ArrayList<Main_Object>> call, Throwable t) {
//        Log.d(TAG, "onFailure: ");
//    }
//});

    }
}
