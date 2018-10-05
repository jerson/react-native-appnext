#import <AppnextLib/AppnextLib.h>
#import <AppnextSDKCore/AppnextAd.h>
#import "RNAppNext.h"

#import <React/RCTBridgeModule.h>

@interface FullScreenVideoDelegate : NSObject<AppnextVideoAdDelegate>
- (FullScreenVideoDelegate *)initWithAppNext: (RNAppNext *) instance;
- (void)setResolver: (RCTPromiseResolveBlock) resolve;
- (void)setRejecter: (RCTPromiseRejectBlock) reject;
@end
