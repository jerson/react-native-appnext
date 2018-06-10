
# react-native-appnext

Appnext support for **Android**


## Getting started

`$ npm install react-native-appnext --save`

### Mostly automatic installation

`$ react-native link react-native-appnext`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-appnext` and add `RNAppNext.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNAppNext.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import me.jerson.mobile.ads.RNAppNextPackage;` to the imports at the top of the file
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


## Usage
```javascript
import AppNext from 'react-native-appnext';

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

  AppNext.setupAd(placementId);
  AppNext.showRewardedVideo(placementId);
  AppNext.showInterstitial(placementId);
  AppNext.showFullScreenVideo(placementId);
  AppNext.loadAd(category = "Entertainment")
  AppNext.removeAd(adId);
  AppNext.adClicked(adId);
  AppNext.adImpression(adId);
  AppNext.videoStarted(adId);
  AppNext.videoEnded(adId);
  AppNext.privacyClicked(adId);
  AppNext.addEventListener(type, handler);
  AppNext.removeEventListener(type, handler);
  AppNext.removeAllListeners();
```
  