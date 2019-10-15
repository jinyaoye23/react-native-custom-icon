#import "CustomIcon.h"
#import <UIKit/UIKit.h>


@implementation CustomIcon

RCT_EXPORT_MODULE()


RCT_EXPORT_METHOD(changeIcon:(NSString *)iconName) {
    UIApplication *app = [UIApplication sharedApplication];
    dispatch_sync(dispatch_get_main_queue(), ^{
        if (![[UIApplication sharedApplication] supportsAlternateIcons]) {
            return;
        }
        
    });
    
    if ([iconName isEqualToString:@""]) {
        iconName = nil;
    }
    dispatch_sync(dispatch_get_main_queue(), ^{
        [[UIApplication sharedApplication] setAlternateIconName:iconName completionHandler:^(NSError * _Nullable error) {
            if (error) {
                NSLog(@"更换app图标发生错误了 ： %@",error);
            }
        }];
    });
}

// 恢复默认图标
RCT_EXPORT_METHOD(changeDefaultIcon) {
    UIApplication *app = [UIApplication sharedApplication];
    dispatch_sync(dispatch_get_main_queue(), ^{
        if (![[UIApplication sharedApplication] supportsAlternateIcons]) {
            return;
        }
        
    });
    
    dispatch_sync(dispatch_get_main_queue(), ^{
        [[UIApplication sharedApplication] setAlternateIconName:nil completionHandler:^(NSError * _Nullable error) {
            if (error) {
                NSLog(@"更换app图标发生错误了 ： %@",error);
            }
        }];
    });
}




@end
