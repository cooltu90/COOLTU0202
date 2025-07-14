package com.codingtu.cooltu.lib4a.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.core.content.FileProvider;

import com.codingtu.cooltu.lib4a.CoreRequestCode;
import com.codingtu.cooltu.lib4a.config.CoreConfigs;

import java.io.File;

public class AppJumpTool {

    /**************************************************
     *
     * 跳转设置页面
     *
     **************************************************/
    public static void setting(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }

    /**************************************************
     *
     * 跳转未知应用安装页面
     *
     **************************************************/
    public static void manageUnknownApp(Activity act) {
        manageUnknownApp(act, false);
    }

    public static void manageUnknownApp(Activity act, boolean isNewTask) {
        Uri packageURI = Uri.parse("package:" + act.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        if (isNewTask)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        act.startActivityForResult(intent, CoreRequestCode.MANAGE_UNKNOWN_APP_SOURCES);
    }

    /**************************************************
     *
     * 跳转打开管理所有文件页面
     *
     **************************************************/
    public static void mangeAppAllFiles(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, CoreRequestCode.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
    }

    /**************************************************
     *
     * 安装app
     *
     **************************************************/
    public static void install(Context context, File apk) {
        if (VersionTool.isLess(VersionTool.A7)) {
            //开始安装
            Uri uri = Uri.fromFile(apk);
            Intent intent = new Intent();
            intent.setClassName("com.android.packageinstaller", "com.android.packageinstaller.PackageInstallerActivity");
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri uri = FileProvider.getUriForFile(context, CoreConfigs.configs().getImageGetterFileProvider(), apk);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    public static void manageWindow(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent, CoreRequestCode.ACTION_MANAGE_OVERLAY_PERMISSION);
    }
}
