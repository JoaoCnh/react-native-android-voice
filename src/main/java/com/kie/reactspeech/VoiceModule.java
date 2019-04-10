package com.kie.reactspeech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

import java.util.ArrayList;
import java.util.Locale;

import javax.annotation.Nullable;

/**
 * Created by JMC on 14/01/2016.
 */
public class VoiceModule extends ReactContextBaseJavaModule implements RecognitionListener {

    final ReactApplicationContext reactContext;
    private static SpeechRecognizer speech = null;
    private String locale = null;

    private Promise voicePromise;
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
            stopListening();
        }

        speech = SpeechRecognizer.createSpeechRecognizer(this.reactContext);
        speech.setRecognitionListener(this);

        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, getLocale(this.locale));

        speech.startListening(intent);
    }

    private void stopListening() {
        speech.stopListening();
        speech.cancel();
        speech.destroy();
        speech = null;
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
        this.voicePromise = promise;


        Handler mainHandler = new Handler(this.reactContext.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    startListening();
                } catch (Exception e) {
                    voicePromise.reject(ErrorConstants.E_FAILED_TO_SHOW_VOICE, e.getMessage());
                    voicePromise = null;
                    stopListening();
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
//        Toast.makeText(getReactApplicationContext(), "EventName :: " + eventName + " , params: " + params, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);

        voicePromise.reject(String.valueOf(errorCode), errorMessage);
        voicePromise = null;
        stopListening();
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
        String matchingWord = "";
        for (String result : matches) {
            matchingWord = result;
            if (expectedText.trim().equalsIgnoreCase(result)) {
                foundMatch = true;
                break;
            }
            arr.pushString(result);
        }

        WritableMap event = Arguments.createMap();
        event.putArray("value", arr);
        sendEvent("onSpeechResults", event);

        if (voicePromise != null) {
            WritableMap resultEvent = Arguments.createMap();
            resultEvent.putBoolean("valid", foundMatch);
            resultEvent.putString("match", matchingWord);

            voicePromise.resolve(resultEvent);

            voicePromise = null;
            stopListening();
        }
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
