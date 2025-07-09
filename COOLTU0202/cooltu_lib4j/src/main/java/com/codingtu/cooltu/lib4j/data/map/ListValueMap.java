package com.codingtu.cooltu.lib4j.data.map;

import java.util.ArrayList;
import java.util.List;

public class ListValueMap<KEY, E> extends ValueMap<KEY, List<E>> {
    @Override
    protected List<E> newValue() {
        return new ArrayList<E>();
    }
}
