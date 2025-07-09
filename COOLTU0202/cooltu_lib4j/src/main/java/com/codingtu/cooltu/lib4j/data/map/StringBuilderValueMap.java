package com.codingtu.cooltu.lib4j.data.map;

public class StringBuilderValueMap<KEY> extends ValueMap<KEY, StringBuilder> {

    @Override
    protected StringBuilder newValue() {
        return new StringBuilder();
    }
}
