package com.codingtu.cooltu.lib4a.data;

import android.graphics.Rect;

public class TextBox {

    public Rect boxRect;
    private int textLeft;
    private int textTop;

    public void r(int r) {
        int width = boxRect.width();
        boxRect.right = r;
        boxRect.left = r - width;
    }

    public void l(int l) {
        int width = boxRect.width();
        boxRect.left = l;
        boxRect.right = l + width;
    }

    public void t(int t) {
        int height = boxRect.height();
        boxRect.top = t;
        boxRect.bottom = t + height;
    }

    public void b(int b) {
        int height = boxRect.height();
        boxRect.bottom = b;
        boxRect.top = b - height;
    }

    public int textLeft() {
        return boxRect.left + textLeft;
    }

    public void textLeft(int textLeft) {
        this.textLeft = textLeft;
    }

    public int textTop() {
        return boxRect.top + textTop;
    }

    public void textTop(int textTop) {
        this.textTop = textTop;
    }

}
