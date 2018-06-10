
# react-native-appnext

Appnext support for **Android** and comming soon to **iOS**


## Getting started

`$ npm install react-native-appnext --save`

### Mostly automatic installation

`$ react-native link react-native-appnext`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-appnext` and add `RNAppnext.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAppnext.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android


1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import me.jerson.mobile.ads.appnext.RNAppNextPackage;` to the imports at the top of the file
  - Add `new RNAppNextPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-appnext'
  	project(':react-native-appnext').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-appnext/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-appnext')
  	```
4. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.appnext.base.Appnext;` to the imports at the top of the file
  - Add `Appnext.init(getApplicationContext());` to the the `onCreate()` method
    ```
    public void onCreate() {
        //...
        Appnext.init(getApplicationContext());
        //...
    }
    ```
    

## Usage
```javascript
import Appnext from 'react-native-appnext';

   eventHandlers = [ 
    onAdLoaded,
    onRewardedVideoEnded,
    onRewardedVideoClicked,
    onRewardedVideoClosed,
    onInterstitialClicked,
    onInterstitialClosed,
    onFullScreenVideoEnded,
    onFullScreenVideoClicked,
    onFullScreenVideoClosed
   ];

  Appnext.setupAd(placementId);
  Appnext.showRewardedVideo(placementId);
  Appnext.showInterstitial(placementId);
  Appnext.showFullScreenVideo(placementId);
  Appnext.loadAd(category = "Entertainment")
  Appnext.removeAd(adId);
  Appnext.adClicked(adId);
  Appnext.adImpression(adId);
  Appnext.videoStarted(adId);
  Appnext.videoEnded(adId);
  Appnext.privacyClicked(adId);
  Appnext.addEventListener(type, handler);
  Appnext.removeEventListener(type, handler);
  Appnext.removeAllListeners();
```
  