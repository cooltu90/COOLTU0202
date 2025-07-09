package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.convert.ToInt;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.NumEs;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;

public class IntegerEs extends NumEs<Integer, IntegerEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public IntegerEs() {
    }

    public IntegerEs(List<Integer> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public IntegerEs add_int(int... ints) {
        int count = CountTool.count(ints);
        for (int i = 0; i < count; i++) {
            this.es.add(ints[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public IntegerEs createThis_int(int... ints) {
        IntegerEs integerVs = new IntegerEs();
        integerVs.add_int(ints);
        return integerVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Integer> maxMin() {
        return super.maxMin(new ToInt<Integer>() {
            @Override
            public int toInt(Integer integer) {
                return integer;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////

    public int[] to_ints() {
        int count = count();
        int[] arrs = new int[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.es.get(i);
        }
        return arrs;
    }
}
