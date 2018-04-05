package com.example.sidkathuria14.symptomchecker;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidkathuria14.symptomchecker.api.MorseApi;
import com.example.sidkathuria14.symptomchecker.models.Main_Morse_Model;
import com.example.sidkathuria14.symptomchecker.speech.Synthesizer;
import com.example.sidkathuria14.symptomchecker.speech.Voice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Morse extends AppCompatActivity {

    public static final String TAG = "morse";
    String morse;
    Retrofit retrofit;
    TextView tvResponse;
    MorseApi morseApi;
    private Synthesizer m_syn_male, m_syn_female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse);

        tvResponse = (TextView) findViewById(R.id.tvResponse);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.funtranslations.com/")
                .build();

        morseApi = retrofit.create(MorseApi.class);


        ((Button) findViewById(R.id.dit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morse += ".";
            }
        });
        ((Button) findViewById(R.id.dah)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morse += "-";
            }
        });


        if (getString(R.string.api_key).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        } else {

            if (m_syn_female == null) {
                // Create Text To Speech Synthesizer.
                m_syn_female = new com.example.sidkathuria14.symptomchecker.speech.Synthesizer(getString(R.string.api_key));
            }
            Voice v = new Voice("en-US", "Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)", Voice.Gender.Female, true);
//            //Voice v = new Voice("zh-CN", "Microsoft Server Speech Text to Speech Voice (zh-CN, HuihuiRUS)", Voice.Gender.Female, true);
            m_syn_female.SetVoice(v, null);
            m_syn_female.SetServiceStrategy(com.example.sidkathuria14.symptomchecker.speech.Synthesizer.ServiceStrategy.AlwaysService);

            m_syn_female.SpeakToAudio("Greetings, user!");


        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this, "Menu key pressed", Toast.LENGTH_SHORT).show();
                morseApi.getMorse(morse).enqueue(new Callback<Main_Morse_Model>() {


                    @Override
                    public void onResponse(Call<Main_Morse_Model> call, Response<Main_Morse_Model> response) {
                        Log.d(TAG, "onResponse: " + response.body().getContents().getTranslated());
                        tvResponse.setText(response.body().getContents().getTranslated());
                        Log.d(TAG, "onResponse: " + response.body().getSuccess().getTotal());
                        Log.d(TAG, "onResponse: " + response.body().getContents().getText());
m_syn_female.SpeakToAudio(response.body().getContents().getTranslated());
                    }

                    @Override
                    public void onFailure(Call<Main_Morse_Model> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
                return true;
            case KeyEvent.KEYCODE_SEARCH:
//                Toast.makeText(this, "Search key pressed", Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_BACK:
//                onBackPressed();
                morse += " ";
                return true;
            case KeyEvent.KEYCODE_MENU:
//                event.startTracking();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                Toast.makeText(this,"Volumen Down pressed", Toast.LENGTH_SHORT).show();
                return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}