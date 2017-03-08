# react-native-android-voice

[![DUB](https://img.shields.io/dub/l/vibe-d.svg?style=flat-square)](https://github.com/JoaoCnh/react-native-android-voice#license)
[![npm version](https://badge.fury.io/js/react-native-android-voice.svg)](https://badge.fury.io/js/react-native-android-voice)

react-native-android-voice is a speech-to-text library for [React Native](https://facebook.github.io/react-native/) for the Android Platform.

##Any Feedback and Ideas are welcome! please use the [Issues](https://github.com/JoaoCnh/react-native-android-voice/issues) section above

## Documentation
- [Complete Docs](https://github.com/JoaoCnh/react-native-android-voice/wiki)
- [Install](https://github.com/JoaoCnh/react-native-android-voice#install)
- [Usage](https://github.com/JoaoCnh/react-native-android-voice#usage)
- [Example](https://github.com/JoaoCnh/react-native-android-voice#example)
- [Methods](https://github.com/JoaoCnh/react-native-android-voice#methods)
- [Available Locales](https://github.com/JoaoCnh/react-native-android-voice#locales)
- [Error Handling](https://github.com/JoaoCnh/react-native-android-voice#errors)
- [TODO](https://github.com/JoaoCnh/react-native-android-voice#todo)
- [License](https://github.com/JoaoCnh/react-native-android-voice#license)

## Install

```shell
npm install --save react-native-android-voice
```
## Usage
### Linking the Library
### Add it to your android project

* In `android/settings.gradle`

```gradle
...
include ':VoiceModule', ':app'
project(':VoiceModule').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-android-voice')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':VoiceModule')
}
```
* Register Module (in MainApplication.java)

```java
import com.wmjmc.reactspeech.VoicePackage;  // <--- import

public class MainApplication extends Application implements ReactApplication {
...
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new VoicePackage()); // <--- added here
    }
}
```
## Example

```javascript
import SpeechAndroid from 'react-native-android-voice';

...
async _buttonClick(){
    try{
        //More Locales will be available upon release.
        var spokenText = await SpeechAndroid.startSpeech("Speak yo", SpeechAndroid.GERMAN);
        ToastAndroid.show(spokenText , ToastAndroid.LONG);
    }catch(error){
        switch(error){
            case SpeechAndroid.E_VOICE_CANCELLED:
                ToastAndroid.show("Voice Recognizer cancelled" , ToastAndroid.LONG);
                break;
            case SpeechAndroid.E_NO_MATCH:
                ToastAndroid.show("No match for what you said" , ToastAndroid.LONG);
                break;
            case SpeechAndroid.E_SERVER_ERROR:
                ToastAndroid.show("Google Server Error" , ToastAndroid.LONG);
                break;
            /*And more errors that will be documented on Docs upon release*/
        }
    }
}
...
```

This will automatically start recognizing and adjusting for the German Language.
On release I'll update these docs with every single Locale available.

## Methods

### startSpeech(prompt, locale)
Initializes the voice recognition activity and returns what you spoke in text.

__Arguments__
- prompt: String for the text to be displayed by the SpeechRecognizer dialog;
- locale: String for the SpeechRecognizer to set itself for the given Locale; (Accessible through constants. see below available locales)

## Locales:

### DEFAULT
```javascript
SpeechAndroid.DEFAULT
```
This will set the SpeechRecognizer to the default locale of your Android smartphone.
***
### Portugal / Portuguese
```javascript
SpeechAndroid.PT /*or*/ SpeechAndroid.PORTUGUESE
```
***
### Brazil / Portuguese
```javascript
SpeechAndroid.BR /*or*/ SpeechAndroid.PORTUGUESE_BRAZIL
```
***
### Bulgarian
```javascript
SpeechAndroid.BULGARIAN
```
***
### Canada / English
```javascript
SpeechAndroid.CANADA
```
***
### Canada / French
```javascript
SpeechAndroid.CANADA_FRENCH
```
***
### Czech
```javascript
SpeechAndroid.CZECH
```
***
### Croatian
```javascript
SpeechAndroid.CROATIAN
```
***
### Chinese
```javascript
SpeechAndroid.CHINA /*or*/ SpeechAndroid.CHINESE /*or*/
SpeechAndroid.SIMPLIFIED_CHINESE /*or*/ SpeechAndroid.TRADITIONAL_CHINESE
```
***
### Netherlands / Dutch
```javascript
SpeechAndroid.DUTCH
```
***
### Belgium / Dutch
```javascript
SpeechAndroid.DUTCH_BELGIUM
```
***
### English
```javascript
SpeechAndroid.ENGLISH
```
***
### UK / English
```javascript
SpeechAndroid.UK
```
***
### US / English
```javascript
SpeechAndroid.US
```
***
### Australia / English
```javascript
SpeechAndroid.AUSTRALIA
```
***
### New Zealand / English
```javascript
SpeechAndroid.NEW_ZEALAND
```
***
### Singapore / English
```javascript
SpeechAndroid.SINGAPORE
```
***
### India / English
```javascript
SpeechAndroid.ENGLISH_INDIA
```
***
### Ireland / English
```javascript
SpeechAndroid.ENGLISH_IRELAND
```
***
### Zimbabwe / English
```javascript
SpeechAndroid.ENGLISH_ZIMBABWE
```
***
### Egypt / Arabic
```javascript
SpeechAndroid.ARABIC_EGYPT
```
***
### Israel / Arabic
```javascript
SpeechAndroid.ARABIC_ISRAEL
```
***
### France / French
```javascript
SpeechAndroid.FRANCE /*or*/ SpeechAndroid.FRENCH
```
***
### Belgium / French
```javascript
SpeechAndroid.FRENCH_BELGIUM
```
***
### Switzerland / French
```javascript
SpeechAndroid.FRENCH_SWITZERLAND
```
***
### Finnish
```javascript
SpeechAndroid.FINNISH
```
***
### Danish
```javascript
SpeechAndroid.DANISH
```
***
### Germany / German
```javascript
SpeechAndroid.GERMANY /*or*/ SpeechAndroid.GERMAN
```
***
### Switzerland / German
```javascript
SpeechAndroid.GERMAN_SWITZERLAND
```
***
### Greek
```javascript
SpeechAndroid.GREEK
```
***
### Hebrew
```javascript
SpeechAndroid.HEBREW
```
***
### Hindi
```javascript
SpeechAndroid.HINDI
```
***
### Hungarian
```javascript
SpeechAndroid.HUNGARIAN
```
***
### Italy / Italian
```javascript
SpeechAndroid.ITALY /*or*/ SpeechAndroid.ITALIAN
```
***
### Switzerland / Italian
```javascript
SpeechAndroid.ITALIAN_SWITZERLAND
```
***
### Indonesian
```javascript
SpeechAndroid.INDONESIAN
```
***
### Latvian
```javascript
SpeechAndroid.LATVIAN
```
***
### Lithuanian
```javascript
SpeechAndroid.LITHUANIAN
```
***
### Norwegian
```javascript
SpeechAndroid.NORWEGIAN
```
***
### Japan
```javascript
SpeechAndroid.JAPAN /*or*/ SpeechAndroid.JAPANESE
```
***
### Polish
```javascript
SpeechAndroid.POLISH
```
***
### Russian
```javascript
SpeechAndroid.RUSSIAN
```
***
### Romanian
```javascript
SpeechAndroid.ROMANIAN
```
***
### Spanish
```javascript
SpeechAndroid.SPANISH
```
***
### Catalan
```javascript
SpeechAndroid.CATALAN
```
***
### US / Spanish
```javascript
SpeechAndroid.SPANISH_US
```
***
### Serbian
```javascript
SpeechAndroid.SERBIAN
```
***
### Slovak
```javascript
SpeechAndroid.SLOVAK
```
***
### Slovenian
```javascript
SpeechAndroid.SLOVENIAN
```
***
### Swedish
```javascript
SpeechAndroid.SWEDISH
```
***
### Korea
```javascript
SpeechAndroid.KOREA /*or*/ SpeechAndroid.KOREAN
```
***
### Taiwan
```javascript
SpeechAndroid.TAIWAN
```
***
### Philippines / Tagalog
```javascript
SpeechAndroid.TAGALOG_PHILIPPINES
```
***
### Thai
```javascript
SpeechAndroid.THAI
```
***
### Turkish
```javascript
SpeechAndroid.TURKISH
```
***
### Ukrainian
```javascript
SpeechAndroid.UKRAINIAN
```
***
### Vietnamese
```javascript
SpeechAndroid.VIETNAMESE
```
***

## Errors

### E_ACTIVITY_DOES_NOT_EXIST
```javascript
SpeechAndroid.E_ACTIVITY_DOES_NOT_EXIST
```
Generic error on current Activity not existing.
***
### E_VOICE_CANCELLED
```javascript
SpeechAndroid.E_VOICE_CANCELLED
```
Voice Recognizer was cancelled
***
### E_FAILED_TO_SHOW_VOICE
```javascript
SpeechAndroid.E_FAILED_TO_SHOW_VOICE
```
Voice Recognizer failed to initialize
***
### E_AUDIO_ERROR
```javascript
SpeechAndroid.E_AUDIO_ERROR
```
Voice Recognizer encountered some error with the Audio received
***
### E_NETWORK_ERROR
```javascript
SpeechAndroid.E_NETWORK_ERROR
```
Network error while attempting connection with Google's Servers
***
### E_NO_MATCH
```javascript
SpeechAndroid.E_NO_MATCH
```
Voice Recognizer did not find any match
***
### E_SERVER_ERROR
```javascript
SpeechAndroid.E_SERVER_ERROR
```
Google's Servers encountered an error while processing the request
***

## License

The MIT License (MIT)
=====================

Copyright © `2015` [João Cunha](https://github.com/JoaoCnh)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
