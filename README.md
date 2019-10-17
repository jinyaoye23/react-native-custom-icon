# react-native-custom-icon

## Getting started

`$ npm install https://github.com/jinyaoye23/react-native-custom-icon.git --save`

### Mostly automatic installation

`$ react-native link react-native-custom-icon`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-custom-icon` and add `CustomIcon.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libCustomIcon.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### iOS 更换图标配置[请参考](https://juejin.im/post/59395f9761ff4b006c6c204e)
##### 1.配置图标

动态修改的icon不能放在 Assets.xcassets 里， 需要创建文件如AppIcons, 文件夹里存放需要动态更换的app图标

![](https://upload-images.jianshu.io/upload_images/1928848-d47b7b71d7f512e0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/518/format/webp)

如果每一套图标有多个尺寸，需要统一名称前缀后面添加尺寸如前缀为 icon, 尺寸为20x20， 则名称为icon20x20@2x.png 和 icon20x20@3x.png，一种尺寸的图标包含2倍图和3倍图

![](https://upload-images.jianshu.io/upload_images/1928848-ab1ad81c5c35f0a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/508/format/webp)


##### 2.info.plist配置

在info.plist中右键 -> Add Row ：
输入Icon... 会有提示，选择Icon files（iOS 5）

![](https://upload-images.jianshu.io/upload_images/1928848-b37a04e25da7aada.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/636/format/webp)

```
这里的Icon files（iOS 5）是个字典，其中可包含的Key值有CFBundlePrimaryIcon         -> Primary Icon
CFBundleAlternateIcons ->这个是我们添加更换图片的字段，为Dictionary, 如果没有这个字段可以添加
UINewsstandIcon                 -> Newsstand Icon 暂时用不到可以删除

```
在 Icon files（iOS 5）内添加一个Key： CFBundleAlternateIcons ，类型为字典，在这个字典里配置我们所有需要动态修改的icon：键为icon的名称，值为一个字典（这个字典里包含两个键：CFBundleIconFiles，其值类型为Array，内容为icon的名称；UIPrerenderedIcon，其值类型为bool，内容为NO，也可以不加此key）

![](https://upload-images.jianshu.io/upload_images/1928848-e4285bb504e5f038.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

如果是添加了多个尺寸icon，也要在这里分别配置，以上面添加的sunshine图标为例：

![](https://upload-images.jianshu.io/upload_images/1928848-4bf57f3332d765f6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

使用的时候还是使用sunshine进行赋值即可！


#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.CustomIconPackage;` to the imports at the top of the file
  - Add `new CustomIconPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-custom-icon'
  	project(':react-native-custom-icon').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-custom-icon/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-custom-icon')
  	```
4.在AndroidManifest.xml里application节点下添加
```
<activity-alias
        android:name=".NAME"  //注意此处的NAME与Usage的NAME相对应，
        android:targetActivity=".MainActivity"
        android:launchMode="standard"
        android:label="@string/app_name"
        android:enabled="false"
        android:windowSoftInputMode="adjustResize"
        android:icon="@drawable/icon002"
        >
        <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
</activity-alias>
```
## Usage
### ios
```javascript
import CustomIcon from 'react-native-custom-icon';

CustomIcon.changeIcon(iconName); // iconName 为图标名称，如上面sunshine为例， iconName就是sunshine
```

### android
```javascript
import CustomIcon from 'react-native-custom-icon';

CustomIcon.registerIcon(iconNameList);//iconNameList为所有NAME的数组，如['001','002']，（不需要传默认图标）
CustomIcon.changeIcon(NAME); // NAME 为图标名称，如上面sunshine为例， iconName就是sunshine
```
