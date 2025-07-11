package com.codingtu.cooltu.lib4j.data.value.xxx;

public class IntValue {

    public int value;

    public static IntValue obtain() {
        return obtain(0);
    }

    public static IntValue obtain(int value) {
        IntValue intValue = new IntValue();
        intValue.value = value;
        return intValue;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
