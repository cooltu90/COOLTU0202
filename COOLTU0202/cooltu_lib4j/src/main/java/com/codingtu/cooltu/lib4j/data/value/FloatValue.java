package com.codingtu.cooltu.lib4j.data.value;

public class FloatValue {
    private float value;
    private boolean reverse;

    public static FloatValue obtain(float v) {
        FloatValue floatValue = new FloatValue();
        floatValue.value = v;
        return floatValue;
    }

    public FloatValue reverse() {
        this.reverse = true;
        return this;
    }


    public int[] toIntArray() {
        int num = Float.floatToIntBits(value);
        int[] arrs = new int[4];
        if (reverse) {
            arrs[0] = num & 0xff;
            arrs[1] = (num & (0xff << 8)) >> 8;
            arrs[2] = (num & (0xff << 16)) >> 16;
            arrs[3] = (num & (0xff << 24)) >> 24;
        } else {
            arrs[0] = (num & (0xff << 24)) >> 24;
            arrs[1] = (num & (0xff << 16)) >> 16;
            arrs[2] = (num & (0xff << 8)) >> 8;
            arrs[3] = num & 0xff;
        }
        return arrs;
    }

    public byte[] toByteArray() {
        int num = Float.floatToIntBits(value);
        byte[] arrs = new byte[4];
        if (reverse) {
            arrs[0] = (byte) (num & 0xff);
            arrs[1] = (byte) ((num & (0xff << 8)) >> 8);
            arrs[2] = (byte) ((num & (0xff << 16)) >> 16);
            arrs[3] = (byte) ((num & (0xff << 24)) >> 24);
        } else {
            arrs[0] = (byte) ((num & (0xff << 24)) >> 24);
            arrs[1] = (byte) ((num & (0xff << 16)) >> 16);
            arrs[2] = (byte) ((num & (0xff << 8)) >> 8);
            arrs[3] = (byte) (num & 0xff);
        }
        return arrs;
    }
}
