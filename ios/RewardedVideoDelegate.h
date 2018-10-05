#import <AppnextLib/AppnextLib.h>
#import <AppnextSDKCore/AppnextAd.h>
#import "RNAppNext.h"

#import <React/RCTBridgeModule.h>

@interface RewardedVideoDelegate : NSObject<AppnextVideoAdDelegate>
- (RewardedVideoDelegate *)initWithAppNext: (RNAppNext *) instance;
- (void)setResolver: (RCTPromiseResolveBlock) resolve;
- (void)setRejecter: (RCTPromiseRejectBlock) reject;
@end
