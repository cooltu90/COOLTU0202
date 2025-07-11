package com.codingtu.cooltu.lib4j.data.xy;

/**************************************************
 *
 * 坐标
 *
 **************************************************/
public class FloatXY {
    public float x;
    public float y;

    public FloatXY() {
    }

    public FloatXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "XY{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
