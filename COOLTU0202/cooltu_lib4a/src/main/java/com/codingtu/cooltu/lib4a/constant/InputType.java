package com.codingtu.cooltu.lib4a.constant;

public class InputType {

    public static final int TEXT = android.text.InputType.TYPE_CLASS_TEXT;
    public static final int PASSWORD = TEXT + android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
    public static final int INT = android.text.InputType.TYPE_CLASS_NUMBER;
    public static final int SIGNED_INT = android.text.InputType.TYPE_NUMBER_FLAG_SIGNED + INT;
    public static final int DOUBLE = android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL + INT;
    public static final int SIGNED_DOUBLE = android.text.InputType.TYPE_NUMBER_FLAG_SIGNED + DOUBLE;

}
