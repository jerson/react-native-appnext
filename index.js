import { Platform, NativeModules, NativeEventEmitter } from "react-native";

const { RNAppNext } = NativeModules;

const TAG = "[Appnext]";
export default class Appnext {
  static emitter = RNAppNext ?  new NativeEventEmitter(RNAppNext) : null;

  static eventHandlers = {
    onAdLoaded: new Map(),
    onRewardedVideoEnded: new Map(),
    onRewardedVideoClicked: new Map(),
    onRewardedVideoClosed: new Map(),
    onInterstitialClicked: new Map(),
    onInterstitialClosed: new Map(),
    onFullScreenVideoEnded: new Map(),
    onFullScreenVideoClicked: new Map(),
    onFullScreenVideoClosed: new Map()
  };

  static setupAd(placementId) {
    __DEV__ && console.debug(TAG, "setupAd", placementId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.setupAd(placementId);
  }

  static showRewardedVideo(placementId) {
    __DEV__ && console.debug(TAG, "showRewardedVideo", placementId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.showRewardedVideo(placementId);
  }

  static showInterstitial(placementId) {
    __DEV__ && console.debug(TAG, "showInterstitial", placementId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.showInterstitial(placementId);
  }

  static showFullScreenVideo(placementId) {
    __DEV__ && console.debug(TAG, "showFullScreenVideo", placementId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.showFullScreenVideo(placementId);
  }

  static loadAd(category = "Entertainment") {
    __DEV__ && console.debug(TAG, "loadAd", category);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.loadAd(category);
  }

  static removeAd(adId) {
    __DEV__ && console.debug(TAG, "removeAd", adId);
    if(Platform.OS!=='android'){
      return
    }
    return  RNAppNext.removeAd(adId);
  }

  static adClicked(adId) {
    __DEV__ && console.debug(TAG, "adClicked", adId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.adClicked(adId);
  }

  static adImpression(adId) {
    __DEV__ && console.debug(TAG, "adImpression", adId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.adImpression(adId);
  }

  static videoStarted(adId) {
    __DEV__ && console.debug(TAG, "videoStarted", adId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.videoStarted(adId);
  }

  static videoEnded(adId) {
    __DEV__ && console.debug(TAG, "videoEnded", adId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.videoEnded(adId);
  }

  static privacyClicked(adId) {
    __DEV__ && console.debug(TAG, "privacyClicked", adId);
    if(Platform.OS!=='android'){
      return
    }
    return RNAppNext.privacyClicked(adId);
  }

  static addEventListener(type, handler) {
    if (this.eventHandlers[type]) {
      this.emitter &&  this.eventHandlers[type].set(
        handler,
        this.emitter.addListener(type, handler)
      );
    } else {
      console.warn(`Event with type ${type} does not exist.`);
    }
  }

  static removeEventListener(type, handler) {
    if (!this.eventHandlers[type].has(handler)) {
      return;
    }
    this.eventHandlers[type].get(handler).remove();
    this.eventHandlers[type].delete(handler);
  } 

  static removeAllListeners() {
    let names = Object.keys(eventHandlers);
    for (const name of names) {
      this.emitter && this.emitter.removeAllListeners(name);
    }
  }
}
