package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.convert.ToFloat;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.NumEs;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;

public class FloatEs extends NumEs<Float, FloatEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public FloatEs() {
    }

    public FloatEs(List<Float> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public FloatEs add_float(float... floats) {
        int count = CountTool.count(floats);
        for (int i = 0; i < count; i++) {
            this.es.add(floats[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public FloatEs createThis_float(float... floats) {
        FloatEs floatVs = new FloatEs();
        floatVs.add_float(floats);
        return floatVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Float> maxMin() {
        return super.maxMin(new ToFloat<Float>() {
            @Override
            public double toFloat(Float aFloat) {
                return aFloat;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public float[] to_floats() {
        int count = count();
        float[] arrs = new float[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.es.get(i);
        }
        return arrs;
    }
}
