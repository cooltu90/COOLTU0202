package com.codingtu.cooltu.lib4a.tool;

import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.codingtu.cooltu.lib4a.config.CoreApp;


/**
 * 获取系统的服务
 */

public class SystemTool {

    private static Context getContext() {
        return CoreApp.APP;
    }

    public static WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
    }

    public static LocationManager getLocationManager() {
        return (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
    }

    public static ClipboardManager getClipboardManager() {
        return (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public static InputMethodManager getInputMethodManager() {
        return (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static AudioManager getAudioManager() {
        return (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    public static CameraManager getCameraManager() {
        return (CameraManager) getContext().getSystemService(Context.CAMERA_SERVICE);
    }

    public static NotificationManager getNotificationManager() {
        return (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    public static WifiManager getWifiManager() {
        return (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public static ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
