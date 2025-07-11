package com.codingtu.cooltu.lib4j.data.value;

import com.codingtu.cooltu.lib4j.tool.CountTool;

public class IntArrayValue {
    private int[] value;
    private boolean reverse;

    public static IntArrayValue obtain(int[] ints) {
        IntArrayValue value = new IntArrayValue();
        value.value = ints;
        return value;
    }

    public IntArrayValue reverse() {
        this.reverse = true;
        return this;
    }

    public float toFloat() {
        int num = 0;
        if (reverse) {
            num = num | (value[0] & 0xff);
            num = num | ((value[1] & 0xff) << 8);
            num = num | ((value[2] & 0xff) << 16);
            num = num | ((value[3] & 0xff) << 24);
        } else {
            num = num | ((value[0] & 0xff) << 24);
            num = num | ((value[1] & 0xff) << 16);
            num = num | ((value[2] & 0xff) << 8);
            num = num | (value[3] & 0xff);
        }
        return Float.intBitsToFloat(num);
    }

    public byte[] toByteArray() {
        int count = CountTool.count(value);
        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            bytes[i] = (byte) value[i];
        }
        return bytes;
    }
}
