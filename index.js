import { NativeModules, NativeEventEmitter } from "react-native";

const { RNAppNext } = NativeModules;

const TAG = "[Appnext]";
export default class Appnext {
  static emitter = new NativeEventEmitter(RNAppNext);

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
    __DEV__ && console.info(TAG, "setupAd", placementId);
    return RNAppNext.setupAd(placementId);
  }

  static showRewardedVideo(placementId) {
    __DEV__ && console.info(TAG, "showRewardedVideo", placementId);
    return RNAppNext.showRewardedVideo(placementId);
  }

  static showInterstitial(placementId) {
    __DEV__ && console.info(TAG, "showInterstitial", placementId);
    return RNAppNext.showInterstitial(placementId);
  }

  static showFullScreenVideo(placementId) {
    __DEV__ && console.info(TAG, "showFullScreenVideo", placementId);
    return RNAppNext.showFullScreenVideo(placementId);
  }

  static loadAd(category = "Entertainment") {
    __DEV__ && console.info(TAG, "loadAd", category);
    RNAppNext && RNAppNext.loadAd(category);
  }

  static removeAd(adId) {
    __DEV__ && console.info(TAG, "removeAd", adId);
    RNAppNext && RNAppNext.removeAd(adId);
  }

  static adClicked(adId) {
    __DEV__ && console.info(TAG, "adClicked", adId);
    RNAppNext && RNAppNext.adClicked(adId);
  }

  static adImpression(adId) {
    __DEV__ && console.info(TAG, "adImpression", adId);
    RNAppNext && RNAppNext.adImpression(adId);
  }

  static videoStarted(adId) {
    __DEV__ && console.info(TAG, "videoStarted", adId);
    RNAppNext && RNAppNext.videoStarted(adId);
  }

  static videoEnded(adId) {
    __DEV__ && console.info(TAG, "videoEnded", adId);
    RNAppNext && RNAppNext.videoEnded(adId);
  }

  static privacyClicked(adId) {
    __DEV__ && console.info(TAG, "privacyClicked", adId);
    RNAppNext && RNAppNext.privacyClicked(adId);
  }

  static addEventListener(type, handler) {
    if (this.eventHandlers[type]) {
      this.eventHandlers[type].set(
        handler,
        this.emitter.addListener(type, handler)
      );
    } else {
      console.log(`Event with type ${type} does not exist.`);
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
      this.emitter.removeAllListeners(name);
    }
  }
}
