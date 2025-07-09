package com.codingtu.cooltu.lib4j.data.value;

public class TValue<T> {
    public T value;

    public static <T> TValue<T> obtain() {
        return obtain(null);
    }

    public static <T> TValue<T> obtain(T t) {
        TValue<T> tValue = new TValue<>();
        tValue.value = t;
        return tValue;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
