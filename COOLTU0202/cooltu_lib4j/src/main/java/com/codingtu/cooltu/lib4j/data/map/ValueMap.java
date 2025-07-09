package com.codingtu.cooltu.lib4j.data.map;

import java.util.HashMap;

/**************************************************
 *
 * 扩展HashMap
 * 修改get方法，如果get的值为空，则创建一个存在map中
 *
 **************************************************/
public abstract class ValueMap<KEY, VALUE> extends HashMap<KEY, VALUE> {

    @Override
    public VALUE get(Object key) {
        VALUE value = super.get(key);
        if (value == null) {
            value = newValue();
            put((KEY) key, value);
        }
        return value;
    }

    protected abstract VALUE newValue();

}
