package com.codingtu.cooltu.lib4j.file.read.parse;

public class ParseInt implements Parse<Integer> {
    @Override
    public Integer parse(String line) {
        return Integer.parseInt(line);
    }
}
