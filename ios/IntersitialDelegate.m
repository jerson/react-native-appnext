#import "IntersitialDelegate.h"

@implementation IntersitialDelegate
{
    RNAppNext *appnext;
    RCTPromiseResolveBlock resolve;
    RCTPromiseRejectBlock reject;
}


- (IntersitialDelegate *)initWithAppNext: (RNAppNext *) instance{
    NSLog(@"IntersitialDelegate => initWithAppNext");

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

- (void) adLoaded:(AppnextAd *)ad{
    NSLog(@"IntersitialDelegate => adLoaded");

    if (ad.adIsLoaded)
    {
        [ad showAd];
    }
}
- (void) adOpened:(AppnextAd *)ad{
    NSLog(@"IntersitialDelegate => adOpened");

    resolve(@{@"auid": @"0"});
}
- (void) adClosed:(AppnextAd *)ad{
    [appnext sendEvent:@"onInterstitialClosed" body:@{ @"auid": @"0"  }];
}
- (void) adClicked:(AppnextAd *)ad{
    [appnext sendEvent:@"onInterstitialClicked" body:@{ @"auid": @"0" }];
}
- (void) adUserWillLeaveApplication:(AppnextAd *)ad{
    NSLog(@"IntersitialDelegate => adUserWillLeaveApplication");

}
- (void) adError:(AppnextAd *)ad error:(NSString *)error{
    reject(@"IntersitialDelegate", error, [NSError errorWithDomain:error code:100 userInfo:nil]);
}

@end
