package com.codingtu.cooltu.lib4j.tool;

public class DoubleTool {

    public static String toString(double num, int bit, boolean trim) {
        return toString(Double.valueOf(num), bit, trim);
    }

    public static String toString(Double num, int bit, boolean trim) {
        if (bit < 0) {
            throw new RuntimeException("bit必须大于等于0");
        }

        if (num == null
                || num == 0
                || Double.isInfinite(num)
                || !Double.isFinite(num)
                || Double.isNaN(num)) {
            if (bit == 0 || trim) {
                return "0";
            } else {
                return "0." + StringTool.repeatString(bit, "0");
            }
        }

        boolean isNegative = num < 0;
        if (isNegative) {
            num = Math.abs(num);
        }
        String absStr = absDoubleToString(num, bit, trim);
        if (isNegative) {
            return "-" + absStr;
        }
        return absStr;
    }

    private static String absDoubleToString(Double num, int bit, boolean trim) {
        String numStr = String.valueOf(num);
        int dotIndex = numStr.indexOf(".");
        if (dotIndex < 0) {
            if (bit == 0 || trim) {
                return numStr;
            } else {
                return numStr + "." + StringTool.repeatString(bit, "0");
            }
        }

        String zhengshuStr = numStr.substring(0, dotIndex);
        String xiaoshuStr = numStr.substring(dotIndex + 1);

        int xiaoshuLen = xiaoshuStr.length();
        if (xiaoshuLen < bit) {
            if (trim) {
                return String.valueOf(num);
            } else {
                return String.valueOf(num) + StringTool.repeatString(bit - xiaoshuLen, "0");
            }
        } else if (xiaoshuLen == bit) {
            return String.valueOf(num);
        }

        if (bit == 0) {
            int zhengshu = Integer.parseInt(zhengshuStr);
            if (Integer.parseInt(xiaoshuStr.substring(0, 1)) >= 5) {
                zhengshu += 1;
            }
            return String.valueOf(zhengshu);
        }

        int xiaoshu1 = Integer.parseInt(xiaoshuStr.substring(0, bit));
        int xiaoshu2 = Integer.parseInt(xiaoshuStr.substring(bit, bit + 1));

        xiaoshuStr = String.valueOf(xiaoshu1);
        if (xiaoshu2 >= 5) {
            xiaoshu1 += 1;
        }
        String newXiaoshuStr = String.valueOf(xiaoshu1);
        if (xiaoshuStr.length() == newXiaoshuStr.length()) {
            xiaoshuStr = newXiaoshuStr;
        } else {
            xiaoshuStr = newXiaoshuStr.substring(1);
            zhengshuStr = String.valueOf(Integer.parseInt(zhengshuStr) + 1);
        }

        if (trim) {
            xiaoshuStr = StringTool.trimRight(xiaoshuStr, '0');
            if (StringTool.isBlank(xiaoshuStr)) {
                return zhengshuStr;
            } else {
                return zhengshuStr + "." + xiaoshuStr;
            }
        } else {
            return zhengshuStr + "." + xiaoshuStr;
        }
    }


}
