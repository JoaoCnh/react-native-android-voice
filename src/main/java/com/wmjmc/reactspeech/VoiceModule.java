package com.wmjmc.reactspeech;

import android.content.Intent;
import android.speech.RecognizerIntent;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.ArrayList;
import java.util.Locale;

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

    @ReactMethod
    public void startSpeech(Promise promise) {
        this.promise = promise;

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something");
        if (intent.resolveActivity(this.reactContext.getPackageManager()) != null) {
            this.reactContext.startActivityForResult(intent, REQUEST_SPEECH_ACTIVITY, null);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
        this.promise.resolve(result.get(0));
    }

}
