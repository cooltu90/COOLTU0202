package com.codingtu.cooltu.lib4a.tool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.codingtu.cooltu.lib4a.data.LTRB;
import com.codingtu.cooltu.lib4a.data.TextBox;

public class DrawTool {

    /***************************************
     *
     * 获取默认的Paint
     *
     ***************************************/
    public static Paint getDefaultPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    /***************************************
     *
     * 根据文字，文字大小，距上下左右的距离，获取包裹文字的区域参数
     *
     ***************************************/

    public static TextBox getOriginalTextBox(String text, int textSize, int paddingLR, int paddingTB) {
        return getTextBox(getDefaultPaint(), text, textSize, 0, 0, paddingLR, paddingTB);
    }

    public static TextBox getOriginalTextBox(Paint paint, String text, int textSize, int paddingLR, int paddingTB) {
        return getTextBox(paint, text, textSize, 0, 0, paddingLR, paddingTB);
    }

    public static TextBox getTextBox(String text, int textSize, int marginLeft, int marginTop, int paddingLR, int paddingTB) {
        return getTextBox(getDefaultPaint(), text, textSize, marginLeft, marginTop, paddingLR, paddingTB);
    }

    public static TextBox getTextBox(Paint paint, String text, int textSize, int marginLeft, int marginTop, int paddingLR, int paddingTB) {
        Rect bounds = getTextBounds(paint, textSize, text);

        LTRB ltrb = new LTRB();
        ltrb.lw(marginLeft, bounds.width() + paddingLR * 2);
        ltrb.th(marginTop, bounds.height() + paddingTB * 2);

        TextBox textBox = new TextBox();
        textBox.boxRect = ltrb.toRect();

        int left = paddingLR - bounds.left;
        int top = paddingTB - bounds.top;
        textBox.textTop(top);
        textBox.textLeft(left);

        return textBox;
    }

    /***************************************
     *
     * 指定区域和文字及大小。获取文字区域的参数
     *
     ***************************************/
    public static TextBox getTextBox(String text, int textSize, Rect boxRect) {
        return getTextBox(getDefaultPaint(), text, textSize, boxRect);
    }

    public static TextBox getTextBox(Paint paint, String text, int textSize, Rect boxRect) {
        Rect bounds = getTextBounds(paint, textSize, text);

        TextBox textBox = new TextBox();
        textBox.boxRect = boxRect;

        int left = (boxRect.width() - bounds.width()) / 2 - bounds.left;
        int top = (boxRect.height() - bounds.height()) / 2 - bounds.top;
        textBox.textTop(top);
        textBox.textLeft(left);

        return textBox;
    }

    /***************************************
     *
     * 画包裹text的区域
     *
     ***************************************/
    public static void drawTextWrap(
            Canvas canvas, String text, int textSize, int marginLeft, int marginTop, int paddingLR, int paddingTB,
            int bgColor, int textColor) {
        drawTextWrap(canvas, getDefaultPaint(), text, textSize, marginLeft, marginTop, paddingLR, paddingTB, bgColor, textColor);
    }

    public static void drawTextWrap(
            Canvas canvas, Paint paint, String text, int textSize, int marginLeft, int marginTop, int paddingLR, int paddingTB,
            int bgColor, int textColor) {
        drawTextBox(canvas, paint, text, getTextBox(paint, text, textSize, marginLeft, marginTop, paddingLR, paddingTB), bgColor, textColor);
    }

    public static void drawTextInBox(
            Canvas canvas, String text, int textSize, Rect box,
            int bgColor, int textColor) {
        drawTextInBox(canvas, getDefaultPaint(), text, textSize, box, bgColor, textColor);
    }

    public static void drawTextInBox(
            Canvas canvas, Paint paint, String text, int textSize, Rect box,
            int bgColor, int textColor) {
        drawTextBox(canvas, paint, text, getTextBox(paint, text, textSize, box), bgColor, textColor);
    }

    public static void drawTextBox(Canvas canvas, Paint paint, String text, TextBox textBox, int bgColor, int textColor) {
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(bgColor);
        canvas.drawRect(textBox.boxRect, paint);

        paint.setColor(textColor);
        canvas.drawText(text, textBox.textLeft(), textBox.textTop(), paint);
    }

    public static Rect getTextBounds(int textSize, String text) {
        return getTextBounds(getDefaultPaint(), textSize, text);
    }

    public static Rect getTextBounds(Paint paint, int textSize, String text) {
        Rect bounds = new Rect();
        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }

}
