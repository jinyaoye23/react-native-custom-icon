import { NativeModules, Platform } from 'react-native';

const CustomIconModule = NativeModules.CustomIcon;
const isIOS = Platform.OS === 'ios'
export default class CustomIcon {
  static registerIcon(iconNameList) {
    console.log('isIOS = ',isIOS)
    console.log('iconNameList = ',iconNameList)

    if (!isIOS) {
      CustomIconModule.registerIcon(iconNameList);
    }
  }
  // 更新图标
  static changeIcon(id) {
    let iconName = isIOS ? id + '-icon' : id
    CustomIconModule.changeIcon(iconName);
  }
  // 恢复默认图标
  static changeDefaultIcon() {
    if (isIOS) {
      CustomIconModule.changeDefaultIcon();
    } else {
      CustomIconModule.changeIcon('');
    }
  }
};
