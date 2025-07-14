package com.codingtu.cooltu.lib4a.tool;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.config.CoreApp;


public class TextViewSetter {

    private TextView textView;

    public static TextViewSetter obtain(View view) {
        if (view instanceof TextView) {
            return obtain((TextView) view);
        }
        throw new RuntimeException("view不是TextView");
    }

    public static TextViewSetter obtain(TextView textView) {
        TextViewSetter tvs = new TextViewSetter();
        tvs.textView = textView;
        return tvs;
    }

    public TextViewSetter setTextSizeBySp(int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        return this;
    }

    public TextViewSetter setTextSizeByPx(int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        return this;
    }

    public TextViewSetter setNormalStyle() {
        textView.setTypeface(null, Typeface.NORMAL);
        return this;
    }

    public TextViewSetter setBoldStyle() {
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        return this;
    }

    public TextViewSetter setItalicStyle() {
        textView.setTypeface(textView.getTypeface(), Typeface.ITALIC);
        return this;
    }

    public TextViewSetter setBoldItalicStyle() {
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD_ITALIC);
        return this;
    }

    public TextViewSetter setTextColor(String color) {
        textView.setTextColor(Color.parseColor(color));
        return this;
    }

    public TextViewSetter setTextColor(int color) {
        textView.setTextColor(color);
        return this;
    }

    public TextViewSetter setTextColorByRes(int color) {
        textView.setTextColor(CoreApp.APP.getResources().getColor(color));
        return this;
    }

    public TextViewSetter setGravity(int gravity) {
        textView.setGravity(gravity);
        return this;
    }

    public TextViewSetter setBackgroundColor(String color) {
        textView.setBackgroundColor(Color.parseColor(color));
        return this;
    }

    public TextViewSetter setBackgroundColor(int color) {
        textView.setBackgroundColor(color);
        return this;
    }

    public TextViewSetter setBackgroundResource(int resId) {
        textView.setBackgroundResource(resId);
        return this;
    }

    public TextViewSetter setDeleteLine() {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    public TextViewSetter removeDeleteLine() {
        textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        return this;
    }

    public void set() {
        textView = null;
    }

}
