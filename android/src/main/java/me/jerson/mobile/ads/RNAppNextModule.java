package me.jerson.mobile.ads;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.appnext.ads.fullscreen.FullScreenVideo;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.appnextsdk.API.AppnextAPI;
import com.appnext.appnextsdk.API.AppnextAd;
import com.appnext.appnextsdk.API.AppnextAdRequest;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.ArrayList;
import java.util.HashMap;

public class RNAppnextModule extends ReactContextBaseJavaModule
        implements AppnextAPI.AppnextAdListener, LifecycleEventListener, AppnextAPI.OnAdOpened {

    private static final String TAG = "RNAppnext";
    private ReactApplicationContext context;
    private boolean isLoaded;
    private AppnextAPI api;
    private HashMap<String, AppnextAd> ads;

    public RNAppnextModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        context.addLifecycleEventListener(this);
    }

    @Override
    public String getName() {
        return "RNAppNext";
    }

    @ReactMethod
    public void setupAd(String placementId) {
        Log.i(TAG, "loadAd:" + placementId);

        if (api == null && context.getCurrentActivity() != null) {
            api = new AppnextAPI(context.getCurrentActivity(), placementId);
            api.setAdListener(this);
            api.setOnAdOpenedListener(this);
            api.setCreativeType(AppnextAPI.TYPE_STATIC);

            ads = new HashMap<>();

        } else {
            Log.w(TAG, "[ERROR] init");
        }

    }

    @ReactMethod
    public void loadAd(String category, final Promise promise) {
        Log.i(TAG, "loadAd:" + category);

        try {
            AppnextAdRequest request = new AppnextAdRequest();

            if (!category.isEmpty()) {
                request.setCategory(category);
            }
            request.setCount(1);
            api.loadAds(request);
            promise.resolve(true);

        } catch (Exception e) {
            promise.reject("500", e.getMessage());
        }

    }

    @ReactMethod
    public void showFullScreenVideo(String placementId, final Promise promise) {
        final FullScreenVideo fullscreenAd = new FullScreenVideo(context, placementId);
        fullscreenAd.loadAd();
        fullscreenAd.setOnVideoEndedCallback(new OnVideoEnded() {
            @Override
            public void videoEnded() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", fullscreenAd.getAUID());
                sendEvent("onFullScreenVideoEnded", data);
            }
        });
        fullscreenAd.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", fullscreenAd.getAUID());
                sendEvent("onFullScreenVideoClicked", data);

            }
        });
        fullscreenAd.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", fullscreenAd.getAUID());
                sendEvent("onFullScreenVideoClosed", data);

            }
        });
        fullscreenAd.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Log.w(TAG, "[ERROR] onError: " + error);
                promise.reject("500", error);
            }
        });
        fullscreenAd.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                promise.resolve(fullscreenAd.getAUID());

            }
        });
        fullscreenAd.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                if (fullscreenAd.isAdLoaded()) {
                    fullscreenAd.showAd();
                }

            }
        });
    }

    @ReactMethod
    public void showInterstitial(String placementId, final Promise promise) {
        final Interstitial interstitialAd = new Interstitial(context, placementId);
        interstitialAd.loadAd();
        interstitialAd.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", interstitialAd.getAUID());
                sendEvent("onInterstitialClicked", data);

            }
        });
        interstitialAd.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", interstitialAd.getAUID());
                sendEvent("onInterstitialClosed", data);

            }
        });
        interstitialAd.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Log.w(TAG, "[ERROR] onError: " + error);
                promise.reject("500", error);
            }
        });
        interstitialAd.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                promise.resolve(interstitialAd.getAUID());

            }
        });
        interstitialAd.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.showAd();
                }
            }
        });
    }

    @ReactMethod
    public void showRewardedVideo(String placementId, final Promise promise) {
        final RewardedVideo rewardedAd = new RewardedVideo(context, placementId);
        rewardedAd.loadAd();
        rewardedAd.setOnVideoEndedCallback(new OnVideoEnded() {
            @Override
            public void videoEnded() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", rewardedAd.getAUID());
                sendEvent("onRewardedVideoEnded", data);
            }
        });
        rewardedAd.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", rewardedAd.getAUID());
                sendEvent("onRewardedVideoClicked", data);

            }
        });
        rewardedAd.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                WritableMap data = new WritableNativeMap();
                data.putString("auid", rewardedAd.getAUID());
                sendEvent("onRewardedVideoClosed", data);

            }
        });
        rewardedAd.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Log.w(TAG, "[ERROR] onError: " + error);
                promise.reject("500", error);
            }
        });
        rewardedAd.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                promise.resolve(rewardedAd.getAUID());

            }
        });
        rewardedAd.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                if (rewardedAd.isAdLoaded()) {
                    rewardedAd.showAd();
                }

            }
        });
    }

    @ReactMethod
    public void removeAd(String adId) {
        if (ads.containsKey(adId)) {
            ads.remove(adId);
        } else {
            Log.w(TAG, "[ERROR] removeAd:" + adId);
        }
    }

    @ReactMethod
    public void adClicked(final String adId) {

        if (context.getCurrentActivity() != null) {
            context.getCurrentActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (ads.containsKey(adId)) {
                            AppnextAd ad = ads.get(adId);
                            api.adClicked(ad);
                        } else {
                            Log.w(TAG, "[ERROR] adClicked:" + adId);
                        }
                    } catch (Exception e) {
                        Log.w(TAG, "[ERROR] adClicked:" + e.getMessage());

                    }
                }
            });
        }

    }

    @ReactMethod
    public void adImpression(final String adId) {

        /*
         * if (context.getCurrentActivity() != null) {
         * context.getCurrentActivity().runOnUiThread(new Runnable() {
         * 
         * @Override public void run() { try { if (ads.containsKey(adId)) { AppnextAd ad
         * = ads.get(adId); api.adImpression(ad); } else { Log.w(TAG,
         * "[ERROR] adImpression:" + adId); } } catch (Exception e) { Log.w(TAG,
         * "[ERROR] adImpression2:" + e.getMessage());
         * 
         * } } }); }
         */

        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (ads.containsKey(adId)) {
                        AppnextAd ad = ads.get(adId);
                        api.adImpression(ad);
                    } else {
                        Log.w(TAG, "[ERROR] adImpression:" + adId);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "[ERROR] adImpression2:" + e.getMessage());

                }
            }
        };
        mainHandler.post(myRunnable);

    }

    @ReactMethod
    public void videoStarted(final String adId) {

        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (ads.containsKey(adId)) {
                        AppnextAd ad = ads.get(adId);
                        api.videoStarted(ad);
                    } else {
                        Log.w(TAG, "[ERROR] videoStarted:" + adId);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "[ERROR] videoStarted:" + e.getMessage());

                }
            }
        };
        mainHandler.post(myRunnable);

    }

    @ReactMethod
    public void videoEnded(final String adId) {
        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (ads.containsKey(adId)) {
                        AppnextAd ad = ads.get(adId);
                        api.videoEnded(ad);
                    } else {
                        Log.w(TAG, "[ERROR] videoEnded:" + adId);
                    }

                } catch (Exception e) {
                    Log.w(TAG, "[ERROR] videoEnded:" + e.getMessage());

                }
            }
        };
        mainHandler.post(myRunnable);

    }

    @ReactMethod
    public void privacyClicked(final String adId) {
        Handler mainHandler = new Handler(context.getMainLooper());
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (ads.containsKey(adId)) {
                        AppnextAd ad = ads.get(adId);
                        api.privacyClicked(ad);
                    } else {
                        Log.w(TAG, "[ERROR] privacyClicked:" + adId);
                    }

                } catch (Exception e) {
                    Log.w(TAG, "[ERROR] privacyClicked:" + e.getMessage());

                }
            }
        };
        mainHandler.post(myRunnable);

    }

    @Override
    public void storeOpened() {

    }

    @Override
    public void onError(String error) {
        Log.w(TAG, "[ERROR] onError: " + error);
    }

    @Override
    public void onAdsLoaded(ArrayList<AppnextAd> adsLoaded) {

        for (AppnextAd ad : adsLoaded) {

            String categories = ad.getCategories();
            String adDescription = ad.getAdDescription();
            String adTitle = ad.getAdTitle();
            String imageURL = ad.getImageURL();
            String imageURLWide = ad.getImageURLWide();
            String adPackage = ad.getAdPackage();
            String idx = ad.getIdx();
            String bannerId = ad.getBannerID();
            String campaignId = ad.getCampaignID();
            String supportedVersion = ad.getSupportedVersion();
            String storeRating = ad.getStoreRating();
            String storeDownloads = ad.getStoreDownloads();
            String appSize = ad.getAppSize();
            String buttonText = ad.getButtonText();
            String urlVideo = ad.getVideoUrl();
            String urlVideo_high = ad.getVideoUrlHigh();
            String urlVideo_30_sec = ad.getVideoUrl30Sec();
            String urlVideo_30_sec_high = ad.getVideoUrlHigh30Sec();

            WritableMap data = new WritableNativeMap();
            data.putString("categories", categories);
            data.putString("adDescription", adDescription);
            data.putString("adTitle", adTitle);
            data.putString("imageURL", imageURL);
            data.putString("imageURLWide", imageURLWide);
            data.putString("adPackage", adPackage);
            data.putString("idx", idx);
            data.putString("bannerId", bannerId);
            data.putString("campaignId", campaignId);
            data.putString("supportedVersion", supportedVersion);
            data.putString("storeRating", storeRating);
            data.putString("storeDownloads", storeDownloads);
            data.putString("appSize", appSize);
            data.putString("buttonText", buttonText);
            data.putString("urlVideo", urlVideo);
            data.putString("urlVideo_high", urlVideo_high);
            data.putString("urlVideo_30_sec", urlVideo_30_sec);
            data.putString("urlVideo_30_sec_high", urlVideo_30_sec_high);
            sendEvent("onAdLoaded", data);
            ads.put(idx, ad);

        }

    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        try {
            context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
        } catch (Exception e) {
            Log.e(TAG, "error", e);
        }
    }

    @Override
    public void onHostResume() {
        isLoaded = true;
        Log.d(TAG, "onHostResume");
    }

    @Override
    public void onHostPause() {
        isLoaded = true;
        Log.d(TAG, "onHostPause");

    }

    @Override
    public void onHostDestroy() {
        isLoaded = false;
        Log.d(TAG, "onHostDestroy");
        // api.finish();

    }

    @Override
    public void onConnect() {
        Log.d(TAG, "onConnect");

    }

    @Override
    public void onDisconnect() {
        Log.d(TAG, "onDisconnect");

    }

}