package com.codingtu.cooltu.lib4j.tool;

import com.codingtu.cooltu.lib4j.data.xy.XY;

public class MathTool {

    /**************************************************
     *
     * 根据总数和每行的个数，来求需要多少行
     *
     **************************************************/
    public static int row(int count, int perRow) {
        return (count / perRow) + (count % perRow == 0 ? 0 : 1);
    }


    /**************************************************
     *
     * 最大值和最小值
     *
     **************************************************/
    public static <T extends Number> T max(T t1, T t2) {
        return t1.doubleValue() > t2.doubleValue() ? t1 : t2;
    }

    public static <T extends Number> T min(T t1, T t2) {
        return t1.doubleValue() > t2.doubleValue() ? t2 : t1;
    }

    /**************************************************
     *
     * 最高位最大：最高位比输入值大1，其他位为0。
     * 比如输入341，获得的值为400。
     *
     **************************************************/
    public static <T extends Number> int highestBitMax(T t) {
        int num = 1;
        int perValue = t.intValue();
        while ((perValue / 10f) > 1) {
            perValue /= 10;
            num *= 10;
        }
        return ((perValue + 1) * num);
    }

    /**************************************************
     *
     * 按比例计算新的数
     *
     **************************************************/
    public static <T extends Number> double adjust(T from, T to, T now) {
        return to.doubleValue() * now.doubleValue() / from.doubleValue();
    }


    /**************************************************
     *
     * 获得偶数，输入数字本身是偶数则返回此数，否则加1返回
     *
     **************************************************/
    public static int toEven(int num) {
        return num % 2 == 0 ? num : (num + 1);
    }

    /**************************************************
     *
     * 检测所给数字是否在范围内
     *
     **************************************************/
    public static <T extends Number> boolean inRange(T num, T range1, T range2) {
        double n = num.doubleValue();
        double r1 = range1.doubleValue();
        double r2 = range2.doubleValue();
        if (r1 > r2) {
            return n >= r2 && n <= r1;
        } else {
            return n >= r1 && n <= r2;
        }
    }


    /**************************************************
     *
     * 获取点到直线的距离
     * x1,y1 x2,y2 为直线上的两点坐标
     * x,y 为所求点的x,y坐标
     *
     **************************************************/
    public static double getDistanceFromPointToLine(double x1, double y1, double x2, double y2, double x, double y) {
        double y3 = getYInLine(x1, y1, x2, y2, x);
        double x3 = getXInLine(x1, y1, x2, y2, y);
        double delatX = Math.abs(x3 - x);
        double delatY = Math.abs(y3 - y);
        return delatX * delatY / getDistance(x, y3, x3, y);
    }

    /**************************************************
     *
     * 两点之间的距离
     *
     **************************************************/
    public static double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**************************************************
     *
     * 点到直线的垂线与直线交点的x坐标
     * x1,y1 x2,y2为直线上两点的坐标
     * y为所求点的y坐标
     *
     **************************************************/
    public static double getXInLine(double x1, double y1, double x2, double y2, double y) {
        return (x1 * y - x2 * y + x2 * y1 - x1 * y2) / (y1 - y2);
    }

    /**************************************************
     *
     * 点到直线的垂线与直线交点的y坐标
     * x1,y1 x2,y2为直线上两点的坐标
     * x为所求点的x坐标
     *
     **************************************************/
    public static double getYInLine(double x1, double y1, double x2, double y2, double x) {
        return (x * y1 - x * y2 + x1 * y2 - x2 * y1) / (x1 - x2);
    }

    /**************************************************
     *
     * 获得两直线的交点
     * x1,y1 x2,y2 为直线1的两点坐标
     * x3,y3 x4,y4 为直线2的两点坐标
     *
     **************************************************/
    public static XY getIntersectionPoint(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double denominator01 = (x3 * y4 - x4 * y3) * (x1 - x2) - (x1 * y2 - x2 * y1) * (x3 - x4);
        double denominator02 = (y1 - y2) * (x3 * y4 - x4 * y3) - (y3 - y4) * (x1 * y2 - x2 * y1);
        double molecular = (y1 - y2) * (x3 - x4) - (y3 - y4) * (x1 - x2);
        XY xy = new XY();
        xy.x = denominator01 / molecular;
        xy.y = denominator02 / molecular;
        return xy;
    }

    /**************************************************
     *
     * 获得两直线的交点
     * l1p1 l1p2 为直线1的两点坐标
     * l2p1 l2p2 为直线2的两点坐标
     *
     **************************************************/
    public static XY getIntersectionPoint(XY l1p1, XY l1p2, XY l2p1, XY l2p2) {
        return getIntersectionPoint(
                l1p1.x, l1p1.y,
                l1p2.x, l1p2.y,
                l2p1.x, l2p1.y,
                l2p2.x, l2p2.y);
    }

    /**************************************************
     *
     * 获得两个线段的交点(这个交点在两个线段中则返回，否则返回空)
     * l1p1 l1p2 为线段1的两点坐标
     * l2p1 l2p2 为线段2的两点坐标
     *
     **************************************************/
    public static XY getIntersectionPointInSegment(XY l1p1, XY l1p2, XY l2p1, XY l2p2) {
        return getIntersectionPointInSegment(l1p1.x, l1p1.y, l1p2.x, l1p2.y, l2p1.x, l2p1.y, l2p2.x, l2p2.y);
    }

    /**************************************************
     *
     * 获得两个线段的交点(这个交点在两个线段中则返回，否则返回空)
     * x1,y1 x2,y2 为线段1的两点坐标
     * x3,y3 x4,y4 为线段2的两点坐标
     *
     **************************************************/
    public static XY getIntersectionPointInSegment(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        XY xy = getIntersectionPoint(x1, y1, x2, y2, x3, y3, x4, y4);
        if (inRange(x1, x2, xy.x) && inRange(y1, y2, xy.y) && inRange(x3, x4, xy.x) && inRange(y3, y4, xy.y)) {
            return xy;
        }
        return null;
    }
}
