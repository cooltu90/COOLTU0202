package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.convert.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.NumEs;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;

public class LongEs extends NumEs<Long, LongEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public LongEs() {
    }

    public LongEs(List<Long> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public LongEs add_long(long... longs) {
        int count = CountTool.count(longs);
        for (int i = 0; i < count; i++) {
            this.es.add(longs[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public LongEs createThis_long(long... longs) {
        LongEs longVs = new LongEs();
        longVs.add_long(longs);
        return longVs;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////
    public MaxMin<Long> maxMin() {
        return super.maxMin(new ToLong<Long>() {
            @Override
            public long toLong(Long aLong) {
                return aLong;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////

    public long[] to_longs() {
        int count = count();
        long[] arrs = new long[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.es.get(i);
        }
        return arrs;
    }
}
