package com.codingtu.cooltu.lib4a.data;

import com.codingtu.cooltu.lib4j.tool.MathTool;

public class WH extends CoreDataObj {
    public int w;
    public int h;

    public WH() {
    }

    public WH(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public WH copyOne() {
        return new WH(this.w, this.h);
    }

    public WH add(int num) {
        this.w += num;
        this.h += num;
        return this;
    }

    public WH sub(int num) {
        this.w -= num;
        this.h -= num;
        return this;
    }

    public WH multiply(int num) {
        this.w *= num;
        this.h *= num;
        return this;
    }

    public WH multiply(float num) {
        this.w = Math.round(this.w * num);
        this.h = Math.round(this.h * num);
        return this;
    }

    public WH divide(int num) {
        this.w /= num;
        this.h /= num;
        return this;
    }

    public WH roundDivide(int num) {
        return divide((float) num);
    }

    public WH divide(float num) {
        this.w = Math.round(this.w / num);
        this.h = Math.round(this.h / num);
        return this;
    }

    public WH toEven() {
        this.w = MathTool.toEven(this.w);
        this.h = MathTool.toEven(this.h);
        return this;
    }

    @Override
    public String toString() {
        return "WH{" +
                "x=" + w +
                ", y=" + h +
                '}';
    }
}
