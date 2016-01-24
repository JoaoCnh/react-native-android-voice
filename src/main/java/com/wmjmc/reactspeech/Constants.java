package com.wmjmc.reactspeech;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JMC on 21/01/2016.
 */
public final class Constants {

    private Constants() {}

    public static Map<String, Object> getConstants(){
        final Map<String, Object> constants = new HashMap<>();

        constants.putAll(ErrorConstants.getConstants());
        constants.putAll(LocaleConstants.getConstants());

        return constants;
    }
}
