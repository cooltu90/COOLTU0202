package com.codingtu.cooltu.lib4a.tool;

import android.os.Build;

import com.codingtu.cooltu.lib4a.exception.VersionException;
import com.codingtu.cooltu.lib4j.tool.CountTool;
import com.codingtu.cooltu.lib4j.tool.StringTool;

public class VersionTool {

    public static final int A4 = Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    public static final int A4_0_3 = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    public static final int A4_1 = Build.VERSION_CODES.JELLY_BEAN;
    public static final int A4_2 = Build.VERSION_CODES.JELLY_BEAN_MR1;
    public static final int A4_3 = Build.VERSION_CODES.JELLY_BEAN_MR2;
    public static final int A4_4 = Build.VERSION_CODES.KITKAT;
    public static final int A4_4W = Build.VERSION_CODES.KITKAT_WATCH;
    public static final int A5 = Build.VERSION_CODES.LOLLIPOP;
    public static final int A5_1 = Build.VERSION_CODES.LOLLIPOP_MR1;
    public static final int A6 = Build.VERSION_CODES.M;
    public static final int A7 = Build.VERSION_CODES.N;
    public static final int A7_1 = Build.VERSION_CODES.N_MR1;
    public static final int A8 = Build.VERSION_CODES.O;
    public static final int A8_1 = Build.VERSION_CODES.O_MR1;
    public static final int A9 = Build.VERSION_CODES.P;
    public static final int A10 = Build.VERSION_CODES.Q;
    public static final int A11 = Build.VERSION_CODES.R;
    public static final int A12 = Build.VERSION_CODES.S;
    public static final int A12L = Build.VERSION_CODES.S_V2;


    public static boolean isLess(int version) {
        return Build.VERSION.SDK_INT < version;
    }

    public static boolean isLessOrEqual(int version) {
        return Build.VERSION.SDK_INT <= version;
    }

    public static boolean isGreater(int version) {
        return Build.VERSION.SDK_INT > version;
    }

    public static boolean isGreaterOrEqual(int version) {
        return Build.VERSION.SDK_INT >= version;
    }

    public static boolean equal(int version) {
        return Build.VERSION.SDK_INT == version;
    }

    public static boolean aIsLessThanB(String aVersion, String bVersion) throws VersionException {
        return isNewVersion(aVersion, bVersion);
    }

    public static boolean aIsLessOrEqualsThanB(String aVersion, String bVersion) throws VersionException {
        return !isNewVersion(bVersion, aVersion);
    }


    public static boolean aIsGreaterThanB(String aVersion, String bVersion) throws VersionException {
        return isNewVersion(bVersion, aVersion);
    }

    public static boolean aIsGreaterOrEqualsThanB(String aVersion, String bVersion) throws VersionException {
        return !isNewVersion(aVersion, bVersion);
    }

    private static boolean isNewVersion(String myVersion, String getVersion) throws VersionException {
        if (StringTool.isBlank(myVersion) || StringTool.isBlank(getVersion)) {
            throw new VersionException("版本号不能为空");
        }
        try {
            String[] myVersions = myVersion.split("\\.");
            String[] getVersions = getVersion.split("\\.");
            int myCount = CountTool.count(myVersions);
            int getCount = CountTool.count(getVersions);
            for (int i = 0; i < myCount; i++) {
                int my = Integer.parseInt(myVersions[i]);
                if (i < getCount) {
                    int get = Integer.parseInt(getVersions[i]);
                    if (my > get) {
                        return false;
                    } else if (my < get) {
                        return true;
                    }
                } else {
                    return false;
                }
            }
            if (getCount > myCount) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new VersionException("版本号格式错误");
        }
    }

}
