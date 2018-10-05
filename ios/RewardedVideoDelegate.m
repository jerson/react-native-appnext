#import "RewardedVideoDelegate.h"

@implementation RewardedVideoDelegate
{
    RNAppNext *appnext;
    RCTPromiseResolveBlock resolve;
    RCTPromiseRejectBlock reject;
}


- (RewardedVideoDelegate *)initWithAppNext: (RNAppNext *) instance{
    
    self = [super init];
    if (self) {
        NSLog(@"init RewardedVideoDelegate");
        appnext = instance;
        
    }
    return self;
}

- (void)setResolver: (RCTPromiseResolveBlock) resolver{
    resolve = resolver;
}
- (void)setRejecter: (RCTPromiseRejectBlock) rejecter{
    reject = rejecter;
}

- (void) videoEnded:(AppnextAd *)ad{
    [appnext sendEvent:@"onRewardedVideoEnded" body:@{ @"auid": @"0"  }];
}
- (void) adLoaded:(AppnextAd *)ad{
    if (ad.adIsLoaded)
    {
        [ad showAd];
    }
}
- (void) adOpened:(AppnextAd *)ad{
    resolve(@{@"auid": @"0"});
}
- (void) adClosed:(AppnextAd *)ad{
    [appnext sendEvent:@"onRewardedVideoClosed" body:@{ @"auid": @"0"  }];
}
- (void) adClicked:(AppnextAd *)ad{
    [appnext sendEvent:@"onRewardedVideoClicked" body:@{ @"auid": @"0" }];
}
- (void) adUserWillLeaveApplication:(AppnextAd *)ad{
}
- (void) adError:(AppnextAd *)ad error:(NSString *)error{
    reject(@"RewardedVideoDelegate", error, [NSError errorWithDomain:error code:100 userInfo:nil]);
}

@end
