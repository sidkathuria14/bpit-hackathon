package com.example.sidkathuria14.symptomchecker;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sidkathuria14.symptomchecker.speech.Synthesizer;
import com.example.sidkathuria14.symptomchecker.speech.Voice;

public class tts extends AppCompatActivity {

    private Synthesizer m_syn_male, m_syn_female;
    EditText etInput;
    String input;
    public String item = "male";
    Spinner language_spinner, voice_spinner;
    String voice = "male";
    public static final String TAG = "speech";
//    Button stop_btn,play_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        etInput = (EditText) findViewById(R.id.etinput);

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

            ((Button) findViewById(R.id.stop_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(tts.this, "stopped", Toast.LENGTH_SHORT).show();


                    m_syn_female.stopSound();
                }

            });
            ((Button) findViewById(R.id.play_btn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(tts.this, "playing!", Toast.LENGTH_SHORT).show();
                    input = etInput.getText().toString();

                    m_syn_female.SpeakToAudio(input);
                }


            });
        }


    }
}


//stop_btn = (Button)findViewById(R.id.stop_btn);
//play_btn = (Button)findViewById(R.id.play_btn);


//        ((Button)findViewById(R.id.stop_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(tts.this, "stopped", Toast.LENGTH_SHORT).show();
//
//
//                female_voice();
//                m_syn_female.stopSound();
//            }
//
//        });
//
//        ((Button)findViewById(R.id.play_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(tts.this, "playing!", Toast.LENGTH_SHORT).show();
//                input = etInput.getText().toString();
//
//                female_voice();
//                m_syn_female.SpeakToAudio(input);
//            }
//
//
//        });
//    }
//
//
//
//
//
//
//    public void female_voice(){
//        Log.d(TAG, "female_voice: ");
//        if (getString(R.string.api_key).startsWith("Please")) {
//            new AlertDialog.Builder(this)
//                    .setTitle(getString(R.string.add_subscription_key_tip_title))
//                    .setMessage(getString(R.string.add_subscription_key_tip))
//                    .setCancelable(false)
//                    .show();
//        } else {
//
//            if (m_syn_female == null) {
//                // Create Text To Speech Synthesizer.
//                m_syn_female = new com.example.sidkathuria14.symptomchecker.speech.Synthesizer(getString(R.string.api_key));
//            }
//
//            Toast.makeText(this, "If the wave is not played, please see the log for more information.", Toast.LENGTH_LONG).show();
//
//            m_syn_female.SetServiceStrategy(com.example.sidkathuria14.symptomchecker.speech.Synthesizer.ServiceStrategy.AlwaysService);
//
//            Voice v = new Voice("en-US", "Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)", Voice.Gender.Female, true);
//            //Voice v = new Voice("zh-CN", "Microsoft Server Speech Text to Speech Voice (zh-CN, HuihuiRUS)", Voice.Gender.Female, true);
//            m_syn_female.SetVoice(v, null);
//
//            // Use a string for speech.
////            m_syn_female.SpeakToAudio("Greetings, user!");
//            //getString(R.string.tts_text)
//
//            // Use SSML for speech.
//            String text = "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xmlns:mstts=\"http://www.w3.org/2001/mstts\" xml:lang=\"en-US\"><voice xml:lang=\"en-US\" name=\"Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)\">You can also use SSML markup for text to speech.</voice></speak>";
////            m_syn.SpeakSSMLToAudio(text);
//
//
//        }
//    }
//
//
//
//
//
//}
