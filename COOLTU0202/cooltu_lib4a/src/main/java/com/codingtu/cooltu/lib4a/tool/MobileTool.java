package com.codingtu.cooltu.lib4a.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

import com.codingtu.cooltu.lib4a.config.CoreApp;
import com.codingtu.cooltu.lib4j.tool.MathTool;

import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MobileTool {

    /**************************************************
     *
     **************************************************/
    public static String getMacAddress() {
        String macAddress = null;
        try {
            // For Android 6.0 and above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Use the new getMacAddress() method for API 23 and above
                macAddress = getMacAddressByNetworkInterface();
                if (macAddress != null && !macAddress.isEmpty()) {
                    return macAddress;
                }
            }
            // For Android 5.0 to 5.1.1
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                macAddress = getMacAddressByWifiInfo();
                if (macAddress != null && !macAddress.isEmpty()) {
                    return macAddress;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddress;
    }

    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("MissingPermission")
    private static String getMacAddressByWifiInfo() {
        try {
            WifiManager wifi = SystemTool.getWifiManager();
            if (wifi != null) {
                WifiInfo winfo = wifi.getConnectionInfo();
                if (winfo != null) {
                    return winfo.getMacAddress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**************************************************
     * 获取androidId
     **************************************************/
    public static String getAndroidID() {
        return Settings.Secure.getString(CoreApp.APP.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /************************************************
     *
     * 获取版本号
     *
     ************************************************/

    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }

    public static String getAppVersionName(Context context) {
        String appVersionCode = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionCode = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("", e.getMessage());
        }
        return appVersionCode;
    }


    /****************************************************************
     *
     * 获取屏幕宽度
     *
     ****************************************************************/

    private static Point point;

    private static int screenWidth = -1;
    /****************************************************************
     *
     * 获取屏幕高度
     *
     ****************************************************************/

    private static int screenHight = -1;
    /****************************************************************
     *
     * 获取屏幕密度
     *
     ****************************************************************/

//    private static float density = -1;

    /****************************************************************
     *
     * 获取屏幕密度
     *
     ****************************************************************/
    private static int statusBarHeight = -1;

    public static int getScreenWidth() {
        if (screenWidth < 0) {
            screenWidth = getScreenPoint().x;
        }
        return screenWidth;
    }

    public static int getScreenHight() {
        if (screenHight < 0) {
            screenHight = getScreenPoint().y;
        }
        return screenHight;
    }

    public static int getScreenMinWidth() {
        int screenWidth = getScreenWidth();
        int screenHight = getScreenHight();
        return screenWidth > screenHight ? screenHight : screenWidth;
    }


//    public static float getDensity() {
//        if (density < 0) {
//            density = getDisplayMetrics().density;
//        }
//        return density;
//    }

    private static Point getScreenPoint() {
        if (point == null) {
            point = new Point();
            SystemTool.getWindowManager().getDefaultDisplay().getRealSize(point);
        }
        return point;
    }

    public static int getStatusBarHeight() {

        if (statusBarHeight < 0) {

            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = CoreApp.APP.getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                statusBarHeight = 0;
            }
        }

        return statusBarHeight;
    }

    public static int dpToEvenPx(float dp) {
        return dpToEvenPx(CoreApp.APP, dp);
    }

    public static int dpToEvenPx(Context context, float dp) {
        return MathTool.toEven(dpToPx(context, dp));
    }

    public static int dpToPx(float dp) {
        return dpToPx(CoreApp.APP, dp);
    }

    public static int dpToPx(Context context, float dp) {
        int v = (int) dpToPxFloat(context, dp);
        return v < 1 ? 1 : v;
    }

    public static float dpToPxFloat(float dp) {
        return dpToPxFloat(CoreApp.APP, dp);
    }

    public static float dpToPxFloat(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getDisplayMetrics(context));
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /****************************************************************
     *
     * 获取输入法高度
     *
     ****************************************************************/

    public static void getRestKeyBoardHeight(Activity act, View view,
                                             OnGetRestKeyBoardHeight onGetRestKeyBoardHeight) {
        view.getViewTreeObserver()
                .addOnGlobalLayoutListener(new OnKeyBoardGlobalLayoutListener(act,
                        view,
                        onGetRestKeyBoardHeight));
    }

    private static class OnKeyBoardGlobalLayoutListener
            implements ViewTreeObserver.OnGlobalLayoutListener {

        private OnGetRestKeyBoardHeight onGetRestKeyBoardHeight;
        private View view;
        private Activity act;

        public OnKeyBoardGlobalLayoutListener(Activity act, View view,
                                              OnGetRestKeyBoardHeight onGetRestKeyBoardHeight) {
            this.view = view;
            this.act = act;
            this.onGetRestKeyBoardHeight = onGetRestKeyBoardHeight;
        }

        @Override
        public void onGlobalLayout() {
            int windowVisibleDisplayH = getWindowVisibleDisplayH(act);
            if (windowVisibleDisplayH != getScreenHight()) {
                onGetRestKeyBoardHeight.onGetRestKeyBoardHeight(windowVisibleDisplayH);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                deleteData();
            }
        }

        public void deleteData() {
            view = null;
            act = null;
            onGetRestKeyBoardHeight = null;
        }
    }

    public static interface OnGetRestKeyBoardHeight {
        public void onGetRestKeyBoardHeight(int height);
    }

    public static int getWindowVisibleDisplayH(Activity act) {
        Rect rect = new Rect();
        act.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

}
