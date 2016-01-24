package com.wmjmc.reactspeech;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by JMC on 20/01/2016.
 */
public final class LocaleConstants {

    private LocaleConstants() { }

    public static final String LOCALE_DEFAULT = "DEFAULT";

    /****** MEU LINDO PORTUGAL *******/
    public static final String LOCALE_PT = "PT";
    public static final String LOCALE_PORTUGUESE = "PORTUGUESE";
    /*********************************/

    /****** ZUEIRA *******/
    public static final String LOCALE_BR = "BR";
    public static final String LOCALE_PORTUGUESE_BRAZIL = "PORTUGUESE_BRAZIL";
    /*********************************/

    public static final String LOCALE_BULGARIAN = "BULGARIAN";

    public static final String LOCALE_CANADA = "CANADA";
    public static final String LOCALE_CANADA_FRENCH = "CANADA_FRENCH";
    public static final String LOCALE_CZECH = "CZECH";
    public static final String LOCALE_CROATIAN = "CROATIAN";
    public static final String LOCALE_CHINA = "CHINA";
    public static final String LOCALE_CHINESE = "CHINESE";
    public static final String LOCALE_SIMPLIFIED_CHINESE = "SIMPLIFIED_CHINESE";
    public static final String LOCALE_TRADITIONAL_CHINESE = "TRADITIONAL_CHINESE";

    public static final String LOCALE_DUTCH = "DUTCH";
    public static final String LOCALE_DUTCH_BELGIUM = "DUTCH_BELGIUM";

    public static final String LOCALE_UK = "UK";
    public static final String LOCALE_US = "US";
    public static final String LOCALE_ENGLISH = "ENGLISH";
    public static final String LOCALE_AUSTRALIA = "AUSTRALIA";
    public static final String LOCALE_NEW_ZEALAND = "NEW_ZEALAND";
    public static final String LOCALE_SINGAPORE = "SINGAPORE";
    public static final String LOCALE_ENGLISH_INDIA = "ENGLISH_INDIA";
    public static final String LOCALE_ENGLISH_IRELAND = "ENGLISH_IRELAND";
    public static final String LOCALE_ENGLISH_ZIMBABWE = "ENGLISH_ZIMBABWE";

    public static final String LOCALE_ARABIC_EGYPT = "ARABIC_EGYPT";
    public static final String LOCALE_ARABIC_ISRAEL = "ARABIC_ISRAEL";

    public static final String LOCALE_FRANCE = "FRANCE";
    public static final String LOCALE_FRENCH = "FRENCH";
    public static final String LOCALE_FRENCH_BELGIUM = "FRENCH_BELGIUM";
    public static final String LOCALE_FRENCH_SWITZERLAND = "SWITZERLAND";

    public static final String LOCALE_FINNISH = "FINNISH";

    public static final String LOCALE_DANISH = "DANISH";

    public static final String LOCALE_GERMANY = "GERMANY";
    public static final String LOCALE_GERMAN = "GERMAN";
    public static final String LOCALE_GERMAN_SWITZERLAND = "GERMAN_SWITZERLAND";
    public static final String LOCALE_GREEK = "GREEK";

    public static final String LOCALE_HEBREW = "HEBREW";
    public static final String LOCALE_HINDI = "HINDI";
    public static final String LOCALE_HUNGARIAN = "HUNGARIAN";

    public static final String LOCALE_ITALY = "ITALY";
    public static final String LOCALE_ITALIAN = "ITALIAN";
    public static final String LOCALE_ITALIAN_SWITZERLAND = "ITALIAN_SWITZERLAND";
    public static final String LOCALE_INDONESIAN = "INDONESIAN";

    public static final String LOCALE_LATVIAN = "LATVIAN";
    public static final String LOCALE_LITHUANIAN = "LITHUANIAN";

    public static final String LOCALE_NORWEGIAN = "NORWEGIAN";

    public static final String LOCALE_JAPAN = "JAPAN";
    public static final String LOCALE_JAPANESE = "JAPANESE";

    public static final String LOCALE_POLISH = "POLISH";

    public static final String LOCALE_RUSSIAN = "RUSSIAN";
    public static final String LOCALE_ROMANIAN = "ROMANIAN";

    public static final String LOCALE_SPANISH = "SPANISH";
    public static final String LOCALE_CATALAN = "CATALAN";
    public static final String LOCALE_SPANISH_US = "SPANISH_US";

    public static final String LOCALE_SERBIAN = "SERBIAN";
    public static final String LOCALE_SLOVAK = "SLOVAK";
    public static final String LOCALE_SLOVENIAN = "SLOVENIAN";
    public static final String LOCALE_SWEDISH = "SWEDISH";

    public static final String LOCALE_KOREA = "KOREA";
    public static final String LOCALE_KOREAN = "KOREAN";

    public static final String LOCALE_TAIWAN = "TAIWAN";
    public static final String LOCALE_TAGALOG_PHILIPPINES = "TAGALOG_PHILIPPINES";
    public static final String LOCALE_THAI = "THAI";
    public static final String LOCALE_TURKISH = "TURKISH";

    public static final String LOCALE_UKRAINIAN = "UKRAINIAN";

    public static final String LOCALE_VIETNAMESE = "VIETNAMESE";

    public static Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        constants.put(LOCALE_DEFAULT, Locale.getDefault().toString());

        /****** MEU LINDO PORTUGAL *******/
        constants.put(LOCALE_PT, "pt_PT");
        constants.put(LOCALE_PORTUGUESE, "pt_PT");
        /*********************************/

        /****** ZUEIRA *******/
        constants.put(LOCALE_BR, "pt_BR");
        constants.put(LOCALE_PORTUGUESE_BRAZIL, "pt_BR");
        /*********************************/

        constants.put(LOCALE_BULGARIAN, "bg_BG");

        constants.put(LOCALE_CZECH, "cs_CZ");
        constants.put(LOCALE_CROATIAN, "hr_HR");

        constants.put(LOCALE_CANADA, Locale.CANADA.toString());
        constants.put(LOCALE_CANADA_FRENCH, Locale.CANADA_FRENCH.toString());

        constants.put(LOCALE_CHINA, Locale.CHINA.toString());
        constants.put(LOCALE_CHINESE, Locale.CHINESE.toString());
        constants.put(LOCALE_SIMPLIFIED_CHINESE, Locale.SIMPLIFIED_CHINESE.toString());
        constants.put(LOCALE_TRADITIONAL_CHINESE, Locale.TRADITIONAL_CHINESE.toString());

        constants.put(LOCALE_DUTCH, "nl_NL");
        constants.put(LOCALE_DUTCH_BELGIUM, "nl_BE");

        constants.put(LOCALE_UK, Locale.UK.toString());
        constants.put(LOCALE_US, Locale.US.toString());
        constants.put(LOCALE_ENGLISH, Locale.ENGLISH.toString());
        constants.put(LOCALE_AUSTRALIA, "en_AU");
        constants.put(LOCALE_NEW_ZEALAND, "en_NZ");
        constants.put(LOCALE_SINGAPORE, "en_SG");
        constants.put(LOCALE_ENGLISH_INDIA, "en_IN");
        constants.put(LOCALE_ENGLISH_IRELAND, "en_IE");
        constants.put(LOCALE_ENGLISH_ZIMBABWE, "en_ZA");

        constants.put(LOCALE_ARABIC_EGYPT, "ar_EG");
        constants.put(LOCALE_ARABIC_ISRAEL, "ar_IL");

        constants.put(LOCALE_FRANCE, Locale.FRANCE.toString());
        constants.put(LOCALE_FRENCH, Locale.FRENCH.toString());
        constants.put(LOCALE_FRENCH_BELGIUM, "fr_BE");
        constants.put(LOCALE_FRENCH_SWITZERLAND, "fr_CH");
        constants.put(LOCALE_FINNISH, "fi_FI");

        constants.put(LOCALE_DANISH, "da_DK");

        constants.put(LOCALE_GERMANY, Locale.GERMANY.toString());
        constants.put(LOCALE_GERMAN, Locale.GERMAN.toString());
        constants.put(LOCALE_GERMAN_SWITZERLAND, "de_CH");
        constants.put(LOCALE_GREEK, "el_GR");

        constants.put(LOCALE_HEBREW, "iw_IL");
        constants.put(LOCALE_HINDI, "hi_IN");
        constants.put(LOCALE_HUNGARIAN, "hu_HU");

        constants.put(LOCALE_ITALY, Locale.ITALY.toString());
        constants.put(LOCALE_ITALIAN, Locale.ITALIAN.toString());
        constants.put(LOCALE_ITALIAN_SWITZERLAND, "it_CH");
        constants.put(LOCALE_INDONESIAN, "in_ID");

        constants.put(LOCALE_LATVIAN, "lv_LV");
        constants.put(LOCALE_LITHUANIAN, "lt_LT");

        constants.put(LOCALE_NORWEGIAN, "nb_NO");

        constants.put(LOCALE_JAPAN, Locale.JAPAN.toString());
        constants.put(LOCALE_JAPANESE, Locale.JAPANESE.toString());

        constants.put(LOCALE_POLISH, "pl_PL");
        constants.put(LOCALE_RUSSIAN, "ru_RU");
        constants.put(LOCALE_ROMANIAN, "ro_RO");

        constants.put(LOCALE_SPANISH, "es_ES");
        constants.put(LOCALE_CATALAN, "ca_ES");
        constants.put(LOCALE_SPANISH_US, "es_US");
        constants.put(LOCALE_SERBIAN, "sr_RS");
        constants.put(LOCALE_SLOVAK, "sk_SK");
        constants.put(LOCALE_SLOVENIAN, "sl_SI");
        constants.put(LOCALE_SWEDISH, "sv_SE");

        constants.put(LOCALE_KOREA, Locale.KOREA.toString());
        constants.put(LOCALE_KOREAN, Locale.KOREAN.toString());

        constants.put(LOCALE_TAIWAN, Locale.TAIWAN.toString());
        constants.put(LOCALE_TAGALOG_PHILIPPINES, "tl_PH");
        constants.put(LOCALE_THAI, "th_TH");
        constants.put(LOCALE_TURKISH, "tr_TR");

        constants.put(LOCALE_UKRAINIAN, "uk_UA");

        constants.put(LOCALE_VIETNAMESE, "vi_VN");

        return constants;
    }
}
