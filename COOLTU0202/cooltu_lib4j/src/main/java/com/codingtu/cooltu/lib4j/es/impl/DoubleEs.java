package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.convert.ToDouble;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.NumEs;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;

public class DoubleEs extends NumEs<Double, DoubleEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public DoubleEs() {
    }

    public DoubleEs(List<Double> list) {
        super(list);
    }


    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public DoubleEs add_double(double... doubles) {
        int count = CountTool.count(doubles);
        for (int i = 0; i < count; i++) {
            this.es.add(doubles[i]);
        }
        return this;
    }


    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public DoubleEs createThis_double(double... doubles) {
        DoubleEs doubleVs = new DoubleEs();
        doubleVs.add_double(doubles);
        return doubleVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Double> maxMin() {
        return super.maxMin(new ToDouble<Double>() {
            @Override
            public double toDouble(Double aDouble) {
                return aDouble;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public double[] to_doubles() {
        int count = count();
        double[] arrs = new double[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.es.get(i);
        }
        return arrs;
    }
}
