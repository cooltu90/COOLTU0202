package com.codingtu.cooltu.lib4a.data;

import android.graphics.Rect;
import android.graphics.RectF;

import com.codingtu.cooltu.lib4j.data.data.LibDataObj;

public class LTRB extends CoreDataObj {
    public int l;
    public int t;
    public int r;
    public int b;

    public LTRB() {
    }

    public LTRB(Rect rect) {
        this.l = rect.left;
        this.t = rect.top;
        this.r = rect.right;
        this.b = rect.bottom;
    }

    public LTRB(int l, int t, int r, int b) {
        this.l = l;
        this.t = t;
        this.r = r;
        this.b = b;
    }

    public void lw(int l, int w) {
        this.l = l;
        this.r = this.l + w;
    }

    public void rw(int r, int w) {
        this.r = r;
        this.l = this.r - w;
    }

    public void th(int t, int h) {
        this.t = t;
        this.b = this.t + h;
    }

    public void bh(int b, int h) {
        this.b = b;
        this.t = this.b - h;
    }

    public int w() {
        return r - l;
    }

    public int h() {
        return b - t;
    }

    public WH wh() {
        return new WH(w(), h());
    }

    public LTRB add(int num) {
        this.l += num;
        this.t += num;
        this.r += num;
        this.b += num;
        return this;
    }

    public LTRB moveL(int num) {
        this.l += num;
        this.r += num;
        return this;
    }

    public LTRB moveT(int num) {
        this.t += num;
        this.b += num;
        return this;
    }

    public LTRB sub(int num) {
        this.l -= num;
        this.t -= num;
        this.r -= num;
        this.b -= num;
        return this;
    }

    public LTRB multiply(int num) {
        this.l *= num;
        this.t *= num;
        this.r *= num;
        this.b *= num;
        return this;
    }

    public LTRB multiply(float num) {
        this.l = Math.round(this.l * num);
        this.t = Math.round(this.t * num);
        this.r = Math.round(this.r * num);
        this.b = Math.round(this.b * num);
        return this;
    }

    public LTRB divide(int num) {
        this.l /= num;
        this.t /= num;
        this.r /= num;
        this.b /= num;
        return this;
    }

    public LTRB roundDivide(int num) {
        return divide((float) num);
    }

    public LTRB divide(float num) {
        this.l = Math.round(this.l / num);
        this.t = Math.round(this.t / num);
        this.r = Math.round(this.r / num);
        this.b = Math.round(this.b / num);
        return this;
    }


    public LTRB copyOne() {
        return new LTRB(l, t, r, b);
    }

    public Rect toRect() {
        return new Rect(l, t, r, b);
    }

    public RectF toRectF() {
        return new RectF(l, t, r, b);
    }

    @Override
    public String toString() {
        return "LTRB{" +
                "l=" + l +
                ", r=" + r +
                ", t=" + t +
                ", b=" + b +
                ", w=" + w() +
                ", h=" + h() +
                '}';
    }

    public boolean isIn(float x, float y) {
        return x >= l && x <= r && y >= t && y <= b;
    }
}
