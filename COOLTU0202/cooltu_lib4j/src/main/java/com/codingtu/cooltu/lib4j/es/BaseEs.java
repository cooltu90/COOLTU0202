package com.codingtu.cooltu.lib4j.es;

import java.util.List;

public class BaseEs<E> extends CoreEs<E, BaseEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public BaseEs() {
    }

    public BaseEs(List<E> list) {
        super(list);
    }
}
