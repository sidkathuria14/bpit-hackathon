package com.example.sidkathuria14.symptomchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.sidkathuria14.symptomchecker.models.SymptomsObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SymptomSelectorActivity extends AppCompatActivity {
ArrayList<Integer> arrayList;
public static final String TAG = "selector";
CheckBox[] ch;
int size,count = 0;
ArrayList<SymptomsObject> symptomList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_selector);
arrayList = new ArrayList<>();
symptomList = new ArrayList<>();

        ScrollView sv = new ScrollView(this);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        ll.setOrientation(LinearLayout.VERTICAL);
        ((Button)findViewById(R.id.btnSubmit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(SymptomSelectorActivity.this,MainActivity.class);

                for(int i=0;i<size;i++) {
                  if(ch[i].isChecked()){
//                      arrayList.add(i);
                      for(int j=0;j<symptomList.size();j++){
                          if(symptomList.get(j).getName().equals(ch[i].getText().toString())){
                              arrayList.add(symptomList.get(j).getId());
                              Log.d(TAG, "onClick: " + symptomList.get(j).getName() + " " + symptomList.get(j).getId());
                          }
                      }

                  }
                }
                intent.putExtra("list",arrayList);
                startActivity(intent);



            }
        });

//arrayList.add("Abdominal pain");
//        "Anxiety","Back pain","Burning eyes","Burning in the throat","Cheek swelling","Chest pain","Chest tightness",
//                "Chills","Cold sweats","Cough","Dizziness","Drooping eyelid","Dry eyes","Earache",
//        "Early satiety","Eye pain"

        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl("https://sandbox-healthservice.priaid.ch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SymptomsSelectorApi api = retrofit.create(SymptomsSelectorApi.class);
        api.callSymptoms().enqueue(new Callback<ArrayList<SymptomsObject>>() {
            @Override
            public void onResponse(Call<ArrayList<SymptomsObject>> call, Response<ArrayList<SymptomsObject>> response) {

              if(response.isSuccessful()) {
                  Log.d(TAG, "onResponse: " + response.body().size() + response.body().get(0).getName());
                  ch = new CheckBox[response.body().size()];
//LinearLayout li = new LinearLayout(SymptomSelectorActivity.this);
size = response.body().size();
                      for (int i = 0; i < response.body().size(); i++) {
                          symptomList.add(response.body().get(i));
ch[i] = new CheckBox(SymptomSelectorActivity.this);
                          ch[i].setId(i);
                          ch[i].setText(response.body().get(i).getName());
                          ll.addView(ch[i]);
                      }




              } else Log.d(TAG, "onResponse: " + response.isSuccessful() + " " + response.message());
            }

            @Override
            public void onFailure(Call<ArrayList<SymptomsObject>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
