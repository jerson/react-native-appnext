//
//  ForensiqUtil.h
//  AppnextSDKCore
//
//  Created by shalom.b on 2/27/18.
//  Copyright © 2018 Appnext. All rights reserved.
//

#import <AppnextSDKCore/AppnextSDKCore.h>
#import "AppnextSettingsManager.h"
#import "AppnextAdData.h"

@interface ForensiqUtil : AppnextSettingsManager
- (instancetype) initWithSettingsManager:(AppnextSettingsManager*) settingsManager;
- (void) sendForensiqIfNeededWithAdData:(AppnextAdData *)adData withPlacementID:(NSString *) placementID  withTID:(NSString *) TID withAUID:(NSString *) AUID withVID:(NSString *) VID withResponseValidityPeriod:(time_t) responseValidityPeriod;
@end
