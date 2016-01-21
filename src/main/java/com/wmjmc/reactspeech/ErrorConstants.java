package com.wmjmc.reactspeech;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JMC on 21/01/2016.
 */
public final class ErrorConstants {

    private ErrorConstants() { }

    public static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";

    public static final String E_VOICE_CANCELLED = "E_VOICE_CANCELLED";

    public static final String E_FAILED_TO_SHOW_VOICE = "E_FAILED_TO_SHOW_VOICE";

    public static final String E_AUDIO_ERROR = "E_AUDIO_ERROR";

    public static final String E_NETWORK_ERROR = "E_NETWORK_ERROR";

    public static final String E_NO_MATCH = "E_NO_MATCH";

    public static final String E_SERVER_ERROR = "E_SERVER_ERROR";

    public static Map<String, Object> getConstants()
    {
        final Map<String, Object> constants = new HashMap<>();

        constants.put(E_ACTIVITY_DOES_NOT_EXIST, "E_ACTIVITY_DOES_NOT_EXIST");
        constants.put(E_VOICE_CANCELLED, "E_VOICE_CANCELLED");
        constants.put(E_FAILED_TO_SHOW_VOICE, "E_FAILED_TO_SHOW_VOICE");
        constants.put(E_AUDIO_ERROR, "E_AUDIO_ERROR");
        constants.put(E_NO_MATCH, "E_NO_MATCH");
        constants.put(E_SERVER_ERROR, "E_SERVER_ERROR");

        return constants;
    }
}
