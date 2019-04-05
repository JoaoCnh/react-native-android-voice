package com.wmjmc.reactspeech;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.os.Bundle;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.wmjmc.reactspeech.LocaleConstants;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

/**
 * Created by JMC on 14/01/2016.
 */
public class VoiceModule extends ReactContextBaseJavaModule implements RecognitionListener {

    final ReactApplicationContext reactContext;
    private SpeechRecognizer speech = null;
    private boolean isRecognizing = false;
    private String locale = null;

    private Promise mVoicepromise;
    private String expectedText;

    public VoiceModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    private String getLocale(String locale) {
        if (locale != null && !locale.equals("")) {
            return locale;
        }

        return Locale.getDefault().toString();
    }

    private void startListening() {
        if (speech != null) {
            speech.destroy();
            speech = null;
        }

        speech = SpeechRecognizer.createSpeechRecognizer(this.reactContext);
        speech.setRecognitionListener(this);

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getLocale(this.locale));

        speech.startListening(intent);
    }

    @Override
    public String getName() {
        return "SpeechAndroid";
    }

    @ReactMethod
    public void startSpeech(String expectedText, String locale, final Promise promise) {
        if (!isPermissionGranted()) {
            String[] PERMISSIONS = {Manifest.permission.RECORD_AUDIO};
            if (this.getCurrentActivity() != null) {
                ((PermissionAwareActivity) this.getCurrentActivity()).requestPermissions(PERMISSIONS, 1, new PermissionListener() {
                    public boolean onRequestPermissionsResult(final int requestCode,
                                                              @NonNull final String[] permissions,
                                                              @NonNull final int[] grantResults) {
                        boolean permissionsGranted = true;
                        for (int i = 0; i < permissions.length; i++) {
                            final boolean granted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                            permissionsGranted = permissionsGranted && granted;
                        }

                        return permissionsGranted;
                    }
                });
            }
            return;
        }

        this.expectedText = expectedText;
        this.locale = locale;
        this.mVoicepromise = promise;


        Handler mainHandler = new Handler(this.reactContext.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    startListening();
                    isRecognizing = true;
                } catch (Exception e) {
                    mVoicepromise.reject(ErrorConstants.E_FAILED_TO_SHOW_VOICE + e.getMessage());
                    mVoicepromise = null;
                }
            }
        });
    }

    private boolean isPermissionGranted() {
        String permission = Manifest.permission.RECORD_AUDIO;
        int res = getReactApplicationContext().checkCallingOrSelfPermission(permission);
        return res == PackageManager.PERMISSION_GRANTED;
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        Toast.makeText(getReactApplicationContext(), "eventName : " + eventName + " , params: " + params, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
    }

    @Override
    public void onEndOfSpeech() {
        WritableMap event = Arguments.createMap();
        event.putBoolean("error", false);
        sendEvent("onSpeechEnd", event);
        Log.d("ASR", "onEndOfSpeech()");
        mVoicepromise.resolve(event);
        mVoicepromise = null;
        isRecognizing = false;
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = String.format("%d/%s", errorCode, getErrorText(errorCode));

        mVoicepromise.reject(errorMessage);
        mVoicepromise = null;
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
    }

    @Override
    public void onPartialResults(Bundle results) {
        WritableArray arr = Arguments.createArray();

        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        for (String result : matches) {
            arr.pushString(result);
        }

        WritableMap event = Arguments.createMap();
        event.putArray("value", arr);
        sendEvent("onSpeechPartialResults", event);
        Log.d("ASR", "onPartialResults()");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
    }

    @Override
    public void onResults(Bundle results) {
        WritableArray arr = Arguments.createArray();

        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        boolean foundMatch = false;
        String gtResult = null;
        for (String result : matches) {
            if (result.equalsIgnoreCase(expectedText)) {
                foundMatch = true;
                gtResult = result;
            }
            arr.pushString(result);
        }

        WritableMap event = Arguments.createMap();
        event.putArray("value", arr);
        sendEvent("onSpeechResults", event);

        Toast.makeText(getReactApplicationContext(), "FOUUUUUNDDD : " + foundMatch + " - " + matches, Toast.LENGTH_LONG).show();

        Log.d("ASR", "onResults()");
    }

    @Override
    public void onRmsChanged(float rmsdB) {
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }
}
