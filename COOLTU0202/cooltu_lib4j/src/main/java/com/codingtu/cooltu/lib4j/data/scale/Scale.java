package com.codingtu.cooltu.lib4j.data.scale;

public class Scale {

    private double scale;

    public Scale(double scale) {
        this.scale = scale;
    }

    public double getDoubleSize(double size) {
        return size * scale;
    }

    public int getIntSize(double size) {
        return (int) getDoubleSize(size);
    }

}
