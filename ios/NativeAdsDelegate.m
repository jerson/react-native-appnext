#import "NativeAdsDelegate.h"
#import <AppnextNativeAdsSDK/AppnextNativeAdsRequest.h>
#import <AppnextSDKCore/AppnextAdData.h>


@implementation NativeAdsDelegate
{
    RNAppNext *appnext;
    RCTPromiseResolveBlock resolve;
    RCTPromiseRejectBlock reject;
    NSMutableDictionary *ads;
}


- (NativeAdsDelegate *)initWithAppNext: (RNAppNext *) instance{
    NSLog(@"NativeAdsDelegate => initWithAppNext");
    
    self = [super init];
    if (self) {
        appnext = instance;
        ads = [[NSMutableDictionary alloc] init];
        
    }
    return self;
}

- (void)setResolver: (RCTPromiseResolveBlock) resolver{
    resolve = resolver;
}
- (void)setRejecter: (RCTPromiseRejectBlock) rejecter{
    reject = rejecter;
}
- (id)getAd: (NSString *) adID{
    NSLog(@"NativeAdsDelegate => getAd: %@",adID);
    return [ads objectForKey:adID];
}
- (void)removeAd: (NSString *) adID{
    NSLog(@"NativeAdsDelegate => removeAd: %@",adID);
    [ads removeObjectForKey:adID];
}
- (void) onAdsLoaded:(NSArray *)adsReponse forRequest:(AppnextNativeAdsRequest *)request{
    NSLog(@"NativeAdsDelegate => onAdsLoaded");

    [adsReponse enumerateObjectsUsingBlock:^(id object, NSUInteger idx, BOOL *stop) {
        AppnextAdData * ad = (AppnextAdData *) object;
        [ads setObject:ad forKey:ad.idx];
     
        [appnext sendEvent:@"onAdLoaded" body:@{
                                                @"categories":ad.categories,
                                                @"adDescription":ad.desc,
                                                @"adTitle":ad.title,
                                                @"imageURL":ad.urlImg,
                                                @"imageURLWide":ad.urlImgWide,
                                                @"adPackage":ad.iosPackage,
                                                @"idx":ad.idx,
                                                @"bannerId":ad.bannerId,
                                                @"campaignId":ad.campaignId,
                                                @"supportedVersion":ad.supportedVersion,
                                                @"storeRating":ad.storeRating,
                                                @"storeDownloads":@"0",
                                                @"appSize":ad.appSize,
                                                @"buttonText":@"",
                                                @"urlVideo":ad.urlVideo,
                                                @"urlVideo_high":ad.urlVideoHigh,
                                                @"urlVideo_30_sec":ad.urlVideo30Sec,
                                                @"urlVideo_30_sec_high":ad.urlVideo30SecHigh
                                                }];

    }];
    
}
- (void) onError:(NSString *)error forRequest:(AppnextNativeAdsRequest *)request {
    NSLog(@"NativeAdsDelegate => onError: %@",error);
}

@end
