# react-native-android-voice

[![DUB](https://img.shields.io/dub/l/vibe-d.svg?style=flat-square)](https://github.com/JoaoCnh/react-native-android-voice#license)
[![npm version](https://badge.fury.io/js/react-native-android-voice.svg)](https://badge.fury.io/js/react-native-android-voice)

react-native-android-voice is a speech-to-text library for [React Native](https://facebook.github.io/react-native/) for the Android Platform.

## Documentation
- [Install](https://github.com/JoaoCnh/react-native-android-voice#install)
- [Usage](https://github.com/JoaoCnh/react-native-android-voice#usage)
- [Example](https://github.com/JoaoCnh/react-native-android-voice#example)
- [Methods](https://github.com/JoaoCnh/react-native-android-voice#methods)
- [TODO](https://github.com/JoaoCnh/react-native-android-voice#todo)
- [License](https://github.com/JoaoCnh/react-native-android-voice#license)

## Install

```shell
npm install --save react-native-android-voice
```
## Usage
### Linking the Library
### Add it to your android project

* In `android/setting.gradle`

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
* Register Module (in MainActivity.java)

```java
import com.wmjmc.reactspeech.VoicePackage;  // <--- import

public class MainActivity extends ReactActivity {
...
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new VoicePackage()); // <--- added here
    }
}
```
## Example / Usage

```javascript
import SpeechAndroid from 'react-native-android-voice';

...
async _someFunction(){
    var spokenText = await SpeechAndroid.startSpeech();
    ...
}
...
```

## Methods

### startSpeech()
Initializes the voice recognition activity and returns what you spoke in text.

__Arguments__
- none ............ for now

## TODO

- [x] Change Package/Module Name: from SpeechPackage to VoicePackage
- [ ] Update ReadMe
- [ ] Develop customization options for the module such as Custom text for the native activity and Locales

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
