
#import <React/RCTEventEmitter.h>



@interface RNAppNext : RCTEventEmitter <RCTBridgeModule>
- (void)sendEvent:(NSString *)name body:(id)body;
@end
  
