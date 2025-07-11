package com.codingtu.cooltu.lib4j.data.value.xxx;

public class CharValue {
    public char value;

    public static CharValue obtain(char value) {
        CharValue boolValue = new CharValue();
        boolValue.value = value;
        return boolValue;
    }

    public boolean isUpper() {
        return value >= 'A' && value <= 'Z';
    }

    public boolean isLower() {
        return value >= 'a' && value <= 'z';
    }

    public boolean isLowerLine() {
        return value == '_';
    }

    public boolean isMiddleLine() {
        return value == '-';
    }

    public boolean isNumber() {
        return value >= '0' && value <= '9';
    }

    /**************************************************
     * 字母大小写的操作
     **************************************************/
    public char toUpper() {
        if (isLower()) {
            return (char) (value - 32);
        }
        return value;
    }

    public char toLower() {
        if (isUpper()) {
            return (char) (value + 32);
        }
        return value;
    }


}
