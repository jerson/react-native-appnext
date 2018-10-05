#import <AppnextNativeAdsSDK/AppnextNativeAdsSDK.h>
#import "RNAppNext.h"
#import <React/RCTBridgeModule.h>

@interface NativeAdsDelegate : NSObject<AppnextNativeAdsRequestDelegate>
- (NativeAdsDelegate *)initWithAppNext: (RNAppNext *) instance;
- (id)getAd: (NSString *) adID;
- (void)removeAd: (NSString *) adID;
- (void)setResolver: (RCTPromiseResolveBlock) resolve;
- (void)setRejecter: (RCTPromiseRejectBlock) reject;
@end
