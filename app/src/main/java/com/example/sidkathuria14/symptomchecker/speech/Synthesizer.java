package com.example.sidkathuria14.symptomchecker.speech;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.AsyncTask;

/**
 * Created by sidkathuria14 on 4/4/18.
 */

public class Synthesizer {

    private com.example.sidkathuria14.symptomchecker.speech.Voice m_serviceVoice;
    private com.example.sidkathuria14.symptomchecker.speech.Voice m_localVoice;

    public String m_audioOutputFormat = AudioOutputFormat.Raw16Khz16BitMonoPcm;
//            com.example.sidkathuria14.speech.AudioOutputFormat.Raw16Khz16BitMonoPcm;

    private AudioTrack audioTrack;

    private void playSound(final byte[] sound, final Runnable callback) {
        if (sound == null || sound.length == 0){
            return;
        }

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final int SAMPLE_RATE = 16000;

                audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, AudioTrack.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT), AudioTrack.MODE_STREAM);

                if (audioTrack.getState() == AudioTrack.STATE_INITIALIZED) {
                    audioTrack.play();
                    audioTrack.write(sound, 0, sound.length);
                    audioTrack.stop();
                    audioTrack.release();
                }

                if (callback != null) {
                    callback.run();
                }
            }
        });
    }

    //stop playing audio data
    // if use STREAM mode, will wait for the end of the last write buffer data will stop.
    // if you stop immediately, call the pause() method and then call the flush() method to discard the data that has not yet been played
    public void stopSound() {
        try {
            if (audioTrack != null && audioTrack.getState() == AudioTrack.STATE_INITIALIZED) {
                audioTrack.pause();
                audioTrack.flush();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public enum ServiceStrategy {
        AlwaysService//, WiFiOnly, WiFi3G4GOnly, NoService
    }

    public Synthesizer(String apiKey) {
        m_serviceVoice = new com.example.sidkathuria14.symptomchecker.speech.Voice("en-US");
        m_localVoice = null;
        m_eServiceStrategy = ServiceStrategy.AlwaysService;
        m_ttsServiceClient = new com.example.sidkathuria14.symptomchecker.speech.TtsServiceClient(apiKey);
    }

    public void SetVoice(com.example.sidkathuria14.symptomchecker.speech.Voice serviceVoice, com.example.sidkathuria14.symptomchecker.speech.Voice localVoice) {
        m_serviceVoice = serviceVoice;
        m_localVoice = localVoice;
    }

    public void SetServiceStrategy(ServiceStrategy eServiceStrategy) {
        m_eServiceStrategy = eServiceStrategy;
    }

    public byte[] Speak(String text) {
        String ssml = "<speak version='1.0' xml:lang='" + m_serviceVoice.lang + "'><voice xml:lang='" + m_serviceVoice.lang + "' xml:gender='" + m_serviceVoice.gender + "'";
        if (m_eServiceStrategy == ServiceStrategy.AlwaysService) {
            if (m_serviceVoice.voiceName.length() > 0) {
                ssml += " name='" + m_serviceVoice.voiceName + "'>";
            } else {
                ssml += ">";
            }
            ssml +=  text + "</voice></speak>";
        }
        return SpeakSSML(ssml);
    }

    public void SpeakToAudio(String text) {
        playSound(Speak(text), null);
    }

    public void SpeakSSMLToAudio(String ssml) {
        playSound(SpeakSSML(ssml), null);
    }

    public byte[] SpeakSSML(String ssml) {
        byte[] result = null;
        /*
         * check current network environment
         * to do...
         */
        if (m_eServiceStrategy == ServiceStrategy.AlwaysService) {
            result = m_ttsServiceClient.SpeakSSML(ssml);
            if (result == null || result.length == 0) {
                return null;
            }

        }
        return result;
    }

    private com.example.sidkathuria14.symptomchecker.speech.TtsServiceClient m_ttsServiceClient;
    private ServiceStrategy m_eServiceStrategy;

}
