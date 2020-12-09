package com.customIcon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.List;

public class CustomIconModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private PackageManager packageManager = null;
    private ComponentName defaultComponent;
    private ComponentName testComponent;
    private List<ComponentName> componentNames = new ArrayList<>();
    private String pkName;

    public CustomIconModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        pkName = reactContext.getPackageName();
    }

    @Override
    public String getName() {
        return "CustomIcon";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    /**
     * 所有的图标都新建ComponentName对象
     *
     * @param iconNameList
     */
    @ReactMethod
    public void registerIcon(ReadableArray iconNameList) {

        if (componentNames.isEmpty()) {

            ComponentName defaultComponent = new ComponentName(reactContext.getBaseContext(), pkName + ".MainAliasActivity");
            componentNames.add(defaultComponent);
            for (int i = 0; i < iconNameList.size(); i++) {
                Log.i("iconNameList = ", iconNameList.getString(i));
                ComponentName component = new ComponentName(reactContext.getBaseContext(), pkName + ".icon" + iconNameList.getString(i));
                componentNames.add(component);
            }

        } else {
            Log.i("registerIcon = ", "重复注册");
        }

    }

    /**
     * 改变logo
     *
     * @param id 租户id
     */
    @ReactMethod
    public void changeIcon(String id) {
        //拿到当前activity注册的组件名称

        packageManager = reactContext.getApplicationContext().getPackageManager();
        String ComponentName = pkName + ".icon" + id;
        if (id.isEmpty()) {
            ComponentName = pkName + ".MainAliasActivity";
        }

        //遍历componentNames，若为该id则启动组件，否则关闭组件
        for (ComponentName name : componentNames) {

            if (ComponentName.equals(name.getClassName())) {
                enableComponent(name);

            } else {
                disableComponent(name);
            }
        }
    }

    /**
     * 启用组件
     *
     * @param componentName
     */
    private void enableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            //已经启用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName
     */
    private void disableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            //已经禁用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
