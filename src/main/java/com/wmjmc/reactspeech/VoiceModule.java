package com.wmjmc.reactspeech;

import android.app.Activity;
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
    private Promise mVoicepromise;

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
        return Constants.getConstants();
    }

    @ReactMethod
    public void startSpeech(String prompt, String locale, final Promise promise) {
        Activity currentActivity = getCurrentActivity();

        if (currentActivity == null) {
            promise.reject(ErrorConstants.E_ACTIVITY_DOES_NOT_EXIST);
            return;
        }

        mVoicepromise = promise;

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getLocale(locale));
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getPrompt(prompt));
        if (intent.resolveActivity(this.reactContext.getPackageManager()) != null) {
            try{
                this.reactContext.startActivityForResult(intent, REQUEST_SPEECH_ACTIVITY, null);
            }catch(Exception ex){
                mVoicepromise.reject(ErrorConstants.E_FAILED_TO_SHOW_VOICE);
                mVoicepromise = null;
            }
        }
    }

    @Override
    public void onActivityResult(
        Activity activity,
        int requestCode,
        int resultCode,
        Intent data
    ) {
        this.onActivityResult(requestCode, resultCode, data);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mVoicepromise == null) {
            return;
        }

        switch (resultCode){
            case Activity.RESULT_OK:
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                mVoicepromise.resolve(result.get(0));
                mVoicepromise = null;
                break;
            case Activity.RESULT_CANCELED:
                mVoicepromise.reject(ErrorConstants.E_VOICE_CANCELLED);
                mVoicepromise = null;
                break;
            case RecognizerIntent.RESULT_AUDIO_ERROR:
                mVoicepromise.reject(ErrorConstants.E_AUDIO_ERROR);
                mVoicepromise = null;
                break;
            case RecognizerIntent.RESULT_NETWORK_ERROR:
                mVoicepromise.reject(ErrorConstants.E_NETWORK_ERROR);
                mVoicepromise = null;
                break;
            case RecognizerIntent.RESULT_NO_MATCH:
                mVoicepromise.reject(ErrorConstants.E_NO_MATCH);
                mVoicepromise = null;
                break;
            case RecognizerIntent.RESULT_SERVER_ERROR:
                mVoicepromise.reject(ErrorConstants.E_SERVER_ERROR);
                mVoicepromise = null;
                break;
        }
    }

    public void onNewIntent(Intent intent) {
        // no-op
    }

    private String getPrompt(String prompt){
        if(prompt != null && !prompt.equals("")){
            return prompt;
        }

        return "Say something";
    }

    private String getLocale(String locale){
        if(locale != null && !locale.equals("")){
            return locale;
        }

        return Locale.getDefault().toString();
    }
}
