import { NativeModules } from 'react-native';

const CustomIconModule  = NativeModules.CustomIcon;

export default class CustomIcon {
  static registerIcon(iconNameList) {
    CustomIconModule.registerIcon(iconNameList);
  }
  // 更新图标
  static changeIcon(iconName) {
    CustomIconModule.changeIcon(iconName);
  }
  // 恢复默认图标
  static changeDefaultIcon() {
    CustomIconModule.changeDefaultIcon();
  }
};
