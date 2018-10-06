#import "RewardedVideoDelegate.h"

@implementation RewardedVideoDelegate
{
    RNAppNext *appnext;
    RCTPromiseResolveBlock resolve;
    RCTPromiseRejectBlock reject;
}


- (RewardedVideoDelegate *)initWithAppNext: (RNAppNext *) instance{
    NSLog(@"RewardedVideoDelegate => initWithAppNext");

    self = [super init];
    if (self) {
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
    NSLog(@"RewardedVideoDelegate => adLoaded");

    if (ad.adIsLoaded)
    {
        [ad showAd];
    }
}
- (void) adOpened:(AppnextAd *)ad{
    NSLog(@"RewardedVideoDelegate => adOpened");

    resolve(@{@"auid": @"0"});
}
- (void) adClosed:(AppnextAd *)ad{
    [appnext sendEvent:@"onRewardedVideoClosed" body:@{ @"auid": @"0"  }];
}
- (void) adClicked:(AppnextAd *)ad{
    [appnext sendEvent:@"onRewardedVideoClicked" body:@{ @"auid": @"0" }];
}
- (void) adUserWillLeaveApplication:(AppnextAd *)ad{
    NSLog(@"RewardedVideoDelegate => adUserWillLeaveApplication");

}
- (void) adError:(AppnextAd *)ad error:(NSString *)error{
    reject(@"RewardedVideoDelegate", error, [NSError errorWithDomain:error code:100 userInfo:nil]);
}

@end
