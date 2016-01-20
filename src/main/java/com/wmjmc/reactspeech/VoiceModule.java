package com.wmjmc.reactspeech;

import android.content.Intent;
import android.speech.RecognizerIntent;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.wmjmc.reactspeech.LocaleConstants;

/**
 * Created by JMC on 14/01/2016.
 */
public class VoiceModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    static final int REQUEST_SPEECH_ACTIVITY = 1;

    final ReactApplicationContext reactContext;
    Promise promise;

    public VoiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.reactContext.addActivityEventListener(this);
    }

    @Override
    public String getName() {
        return "SpeechAndroid";
    }

    @Override
    public Map<String, Object> getConstants() {
        return LocaleConstants.getConstants();
    }

    @ReactMethod
    public void startSpeech(String prompt, Promise promise) {
        this.promise = promise;

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getPrompt(prompt));
        if (intent.resolveActivity(this.reactContext.getPackageManager()) != null) {
            try{
                this.reactContext.startActivityForResult(intent, REQUEST_SPEECH_ACTIVITY, null);
            }catch(Exception ex){
                this.promise.reject(ex.getMessage());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        this.promise.resolve(result.get(0));
    }

    private String getPrompt(String prompt){
        if(prompt != null && !prompt.equals("")){
            return prompt;
        }

        return "Say something";
    }

    private Locale getLocale(Locale locale){
        if(locale != null){
            return locale;
        }

        return Locale.getDefault();
    }
}
