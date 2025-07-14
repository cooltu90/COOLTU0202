package com.codingtu.cooltu.lib4a.tool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**************************************************
 *
 * 备份
 *
 **************************************************/
public class AppJumpSettingTool {

    public final class ANDROIDSETTINGS {
        public static final String ACTION_ACCESSIBILITY_SETTINGS = "android.settings.ACCESSIBILITY_SETTINGS";
        public static final String ACTION_ADD_ACCOUNT = "android.settings.ADD_ACCOUNT_SETTINGS";
        public static final String ACTION_AIRPLANE_MODE_SETTINGS = "android.settings.AIRPLANE_MODE_SETTINGS";
        public static final String ACTION_APN_SETTINGS = "android.settings.APN_SETTINGS";
        public static final String ACTION_APPLICATION_DETAILS_SETTINGS = "android.settings" +
                ".APPLICATION_DETAILS_SETTINGS";
        public static final String ACTION_APPLICATION_DEVELOPMENT_SETTINGS = "android.settings" +
                ".APPLICATION_DEVELOPMENT_SETTINGS";
        public static final String ACTION_APPLICATION_SETTINGS = "android.settings.APPLICATION_SETTINGS";
        public static final String ACTION_BATTERY_SAVER_SETTINGS = "android.settings.BATTERY_SAVER_SETTINGS";
        public static final String ACTION_BLUETOOTH_SETTINGS = "android.settings.BLUETOOTH_SETTINGS";
        public static final String ACTION_CAPTIONING_SETTINGS = "android.settings.CAPTIONING_SETTINGS";
        public static final String ACTION_CAST_SETTINGS = "android.settings.CAST_SETTINGS";
        public static final String ACTION_DATA_ROAMING_SETTINGS = "android.settings.DATA_ROAMING_SETTINGS";
        public static final String ACTION_DATE_SETTINGS = "android.settings.DATE_SETTINGS";
        public static final String ACTION_DEVICE_INFO_SETTINGS = "android.settings.DEVICE_INFO_SETTINGS";
        public static final String ACTION_DISPLAY_SETTINGS = "android.settings.DISPLAY_SETTINGS";
        public static final String ACTION_DREAM_SETTINGS = "android.settings.DREAM_SETTINGS";
        public static final String ACTION_HARD_KEYBOARD_SETTINGS = "android.settings.HARD_KEYBOARD_SETTINGS";
        public static final String ACTION_HOME_SETTINGS = "android.settings.HOME_SETTINGS";
        public static final String ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS = "android.settings" +
                ".IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS";
        public static final String ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS = "android.settings" +
                ".IGNORE_BATTERY_OPTIMIZATION_SETTINGS";
        public static final String ACTION_INPUT_METHOD_SETTINGS = "android.settings.INPUT_METHOD_SETTINGS";
        public static final String ACTION_INPUT_METHOD_SUBTYPE_SETTINGS = "android.settings" +
                ".INPUT_METHOD_SUBTYPE_SETTINGS";
        public static final String ACTION_INTERNAL_STORAGE_SETTINGS = "android.settings.INTERNAL_STORAGE_SETTINGS";
        public static final String ACTION_LOCALE_SETTINGS = "android.settings.LOCALE_SETTINGS";
        public static final String ACTION_LOCATION_SOURCE_SETTINGS = "android.settings.LOCATION_SOURCE_SETTINGS";
        public static final String ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS = "android.settings" +
                ".MANAGE_ALL_APPLICATIONS_SETTINGS";
        public static final String ACTION_MANAGE_APPLICATIONS_SETTINGS = "android.settings" +
                ".MANAGE_APPLICATIONS_SETTINGS";
        public static final String ACTION_MANAGE_DEFAULT_APPS_SETTINGS = "android.settings" +
                ".MANAGE_DEFAULT_APPS_SETTINGS";
        public static final String ACTION_MANAGE_OVERLAY_PERMISSION = "android.settings.action" +
                ".MANAGE_OVERLAY_PERMISSION";
        public static final String ACTION_MANAGE_WRITE_SETTINGS = "android.settings.action.MANAGE_WRITE_SETTINGS";
        public static final String ACTION_MEMORY_CARD_SETTINGS = "android.settings.MEMORY_CARD_SETTINGS";
        public static final String ACTION_NETWORK_OPERATOR_SETTINGS = "android.settings.NETWORK_OPERATOR_SETTINGS";
        public static final String ACTION_NFCSHARING_SETTINGS = "android.settings.NFCSHARING_SETTINGS";
        public static final String ACTION_NFC_PAYMENT_SETTINGS = "android.settings.NFC_PAYMENT_SETTINGS";
        public static final String ACTION_NFC_SETTINGS = "android.settings.NFC_SETTINGS";
        public static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings" +
                ".ACTION_NOTIFICATION_LISTENER_SETTINGS";
        public static final String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS = "android.settings" +
                ".NOTIFICATION_POLICY_ACCESS_SETTINGS";
        public static final String ACTION_PRINT_SETTINGS = "android.settings.ACTION_PRINT_SETTINGS";
        public static final String ACTION_PRIVACY_SETTINGS = "android.settings.PRIVACY_SETTINGS";
        public static final String ACTION_QUICK_LAUNCH_SETTINGS = "android.settings.QUICK_LAUNCH_SETTINGS";
        public static final String ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.settings" +
                ".REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";
        public static final String ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
        public static final String ACTION_SECURITY_SETTINGS = "android.settings.SECURITY_SETTINGS";
        public static final String ACTION_SETTINGS = "android.settings.SETTINGS";
        public static final String ACTION_SHOW_REGULATORY_INFO = "android.settings.SHOW_REGULATORY_INFO";
        public static final String ACTION_SOUND_SETTINGS = "android.settings.SOUND_SETTINGS";
        public static final String ACTION_SYNC_SETTINGS = "android.settings.SYNC_SETTINGS";
        public static final String ACTION_USAGE_ACCESS_SETTINGS = "android.settings.USAGE_ACCESS_SETTINGS";
        public static final String ACTION_USER_DICTIONARY_SETTINGS = "android.settings.USER_DICTIONARY_SETTINGS";
        public static final String ACTION_VOICE_CONTROL_AIRPLANE_MODE = "android.settings.VOICE_CONTROL_AIRPLANE_MODE";
        public static final String ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE = "android.settings" +
                ".VOICE_CONTROL_BATTERY_SAVER_MODE";
        public static final String ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE = "android.settings" +
                ".VOICE_CONTROL_DO_NOT_DISTURB_MODE";
        public static final String ACTION_VOICE_INPUT_SETTINGS = "android.settings.VOICE_INPUT_SETTINGS";
        public static final String ACTION_VPN_SETTINGS = "android.settings.VPN_SETTINGS";
        public static final String ACTION_VR_LISTENER_SETTINGS = "android.settings.VR_LISTENER_SETTINGS";
        public static final String ACTION_WEBVIEW_SETTINGS = "android.settings.WEBVIEW_SETTINGS";
        public static final String ACTION_WIFI_IP_SETTINGS = "android.settings.WIFI_IP_SETTINGS";
        public static final String ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS";
        public static final String ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS";
        public static final String AUTHORITY = "settings";
        public static final String EXTRA_ACCOUNT_TYPES = "account_types";
        public static final String EXTRA_AIRPLANE_MODE_ENABLED = "airplane_mode_enabled";
        public static final String EXTRA_AUTHORITIES = "authorities";
        public static final String EXTRA_BATTERY_SAVER_MODE_ENABLED = "android.settings.extra" +
                ".battery_saver_mode_enabled";
        public static final String EXTRA_DO_NOT_DISTURB_MODE_ENABLED = "android.settings.extra" +
                ".do_not_disturb_mode_enabled";
        public static final String EXTRA_DO_NOT_DISTURB_MODE_MINUTES = "android.settings.extra" +
                ".do_not_disturb_mode_minutes";
        public static final String EXTRA_INPUT_METHOD_ID = "input_method_id";
        public static final String INTENT_CATEGORY_USAGE_ACCESS_CONFIG = "android.intent.category.USAGE_ACCESS_CONFIG";
        public static final String METADATA_USAGE_ACCESS_REASON = "android.settings.metadata.USAGE_ACCESS_REASON";
    }

    private AppJumpSettingTool() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void jumpSystemActivity(Context context, String action) {
        switch (action) {
            case ANDROIDSETTINGS.ACTION_ACCESSIBILITY_SETTINGS:    // 跳转系统的辅助功能界面
                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_ADD_ACCOUNT:               // 显示添加帐户创建一个新的帐户屏幕。【测试跳转到微信登录界面】
                intent = new Intent(Settings.ACTION_ADD_ACCOUNT);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_AIRPLANE_MODE_SETTINGS:       // 飞行模式，无线网和网络设置界面
                intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_WIRELESS_SETTINGS:
                intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_APN_SETTINGS:                 //  跳转 APN设置界面
                intent = new Intent(Settings.ACTION_APN_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_APPLICATION_DETAILS_SETTINGS:
                intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                }
                context.startActivity(intent);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_APPLICATION_DEVELOPMENT_SETTINGS:  // 跳转开发人员选项界面
                intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_APPLICATION_SETTINGS:      // 跳转应用程序列表界面
                intent = new Intent(Settings.ACTION_APPLICATION_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS:   // 跳转到应用程序界面【所有的】
                intent = new Intent(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_MANAGE_APPLICATIONS_SETTINGS://  跳转 应用程序列表界面【已安装的】
                intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_BLUETOOTH_SETTINGS:      // 跳转系统的蓝牙设置界面
                intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_DATA_ROAMING_SETTINGS:   //  跳转到移动网络设置界面
                intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_DATE_SETTINGS:           //  跳转日期时间设置界面
                intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_DEVICE_INFO_SETTINGS:    // 跳转手机状态界面
                intent = new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_DISPLAY_SETTINGS:        // 跳转手机显示界面
                intent = new Intent(Settings.ACTION_DISPLAY_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_DREAM_SETTINGS:   // 【API 18 及以上 没测试】
                intent = new Intent(Settings.ACTION_DREAM_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_INPUT_METHOD_SETTINGS:    // 跳转语言和输入设备
                intent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS://  【API 11 及以上】  //  跳转 语言选择界面 【多国语言选择】
                intent = new Intent(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_INTERNAL_STORAGE_SETTINGS:     // 跳转存储设置界面【内部存储】
                intent = new Intent(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_MEMORY_CARD_SETTINGS:   // 跳转 存储设置 【记忆卡存储】
                intent = new Intent(Settings.ACTION_MEMORY_CARD_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_LOCALE_SETTINGS:         // 跳转语言选择界面【仅有English 和 中文两种选择】
                intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_LOCATION_SOURCE_SETTINGS:    //  跳转位置服务界面【管理已安装的应用程序。】
                intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_NETWORK_OPERATOR_SETTINGS: // 跳转到 显示设置选择网络运营商。
                intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_NFCSHARING_SETTINGS:       // 显示NFC共享设置。【API 14及以上】
                intent = new Intent(Settings.ACTION_NFCSHARING_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_NFC_SETTINGS:           // 显示NFC设置。这显示了用户界面,允许NFC打开或关闭。【API 16及以上】
                intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_PRIVACY_SETTINGS:       //  跳转到备份和重置界面
                intent = new Intent(Settings.ACTION_PRIVACY_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_QUICK_LAUNCH_SETTINGS: // 跳转快速启动设置界面
                intent = new Intent(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_SEARCH_SETTINGS:    // 跳转到 搜索设置界面
                intent = new Intent(Settings.ACTION_SEARCH_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_SECURITY_SETTINGS:     // 跳转到安全设置界面
                intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_SETTINGS:                // 跳转到设置界面
                intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_SOUND_SETTINGS:             // 跳转到声音设置界面
                intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_SYNC_SETTINGS:             // 跳转账户同步界面
                intent = new Intent(Settings.ACTION_SYNC_SETTINGS);
                context.startActivity(intent);
                break;
            case ANDROIDSETTINGS.ACTION_USER_DICTIONARY_SETTINGS:  //  跳转用户字典界面
                intent = new Intent(Settings.ACTION_USER_DICTIONARY_SETTINGS);
                context.startActivity(intent);
                break;

            case ANDROIDSETTINGS.ACTION_WIFI_IP_SETTINGS:         // 跳转到IP设定界面
                intent = new Intent(Settings.ACTION_WIFI_IP_SETTINGS);
                context.startActivity(intent);
                break;

            case ANDROIDSETTINGS.ACTION_WIFI_SETTINGS:            //  跳转Wifi列表设置
                intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
                break;
        }
    }
}
