package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.es.CoreEs;
import com.codingtu.cooltu.lib4j.es.Es;

import java.util.List;

public class StringEs extends CoreEs<String, StringEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public StringEs() {
    }

    public StringEs(List<String> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取Symbol
    //
    ///////////////////////////////////////////////////////

    @Override
    protected String obtainSymbol(String s) {
        return s;
    }

    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * get
     **************************************************/
    @Deprecated
    @Override
    public String getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public String get(String s) {
        return super.get(s);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public StringEs getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringEs getAll(String s) {
        return super.getAll(s);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////
    @Deprecated
    @Override
    public boolean hasBySymbol(String symbol) {
        return super.hasBySymbol(symbol);
    }

    @Override
    public boolean has(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i).equals(s)) {
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////
    //
    // index方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * index
     **************************************************/
    @Deprecated
    @Override
    public int indexBySymbol(String symbol) {
        return index(symbol);
    }

    @Override
    public int index(String s) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**************************************************
     * allIndex
     **************************************************/
    @Deprecated
    @Override
    public IntegerEs allIndexBySymbol(String symbol) {
        return allIndex(symbol);
    }

    @Override
    public IntegerEs allIndex(String s) {
        int count = count();
        IntegerEs integerVs = new IntegerEs();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i).equals(s)) {
                integerVs.add(i);
            }
        }
        return integerVs;
    }

    ///////////////////////////////////////////////////////
    //
    // replace方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * replace
     **************************************************/
    @Deprecated
    @Override
    public StringEs replaceBySymbol(String symbol, String target) {
        return replace(symbol, target);
    }

    @Override
    public StringEs replace(String symbol, String target) {
        int firstIndex = index(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return this;
    }

    @Deprecated
    @Override
    public StringEs replace(String target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public StringEs replace(String... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public StringEs replace(List<String> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public StringEs replace(StringEs targetEs) {
        return super.replace(targetEs);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public StringEs replaceAllBySymbol(String symbol, String target) {
        return super.replaceAllBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringEs replaceAll(String target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public StringEs replaceAll(String... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceAll(List<String> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceAll(StringEs targetEs) {
        return super.replaceAll(targetEs);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    @Deprecated
    @Override
    public StringEs replaceOrAddBySymbol(String symbol, String target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringEs replaceOrAdd(String target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public StringEs replaceOrAdd(String... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceOrAdd(List<String> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceOrAdd(StringEs targetEs) {
        return super.replaceOrAdd(targetEs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/

    @Deprecated
    @Override
    public StringEs replaceAllOrAddBySymbol(String symbol, String target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public StringEs replaceAllOrAdd(String target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public StringEs replaceAllOrAdd(String... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceAllOrAdd(List<String> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public StringEs replaceAllOrAdd(StringEs targetEs) {
        return super.replaceAllOrAdd(targetEs);
    }

    ///////////////////////////////////////////////////////
    //
    // delete方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * delete
     **************************************************/
    @Deprecated
    @Override
    public StringEs deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringEs deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringEs deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringEs deleteBySymbol(StringEs symbolStringEs) {
        return super.deleteBySymbol(symbolStringEs);
    }

    @Override
    public StringEs delete(String target) {
        return super.delete(target);
    }

    @Override
    public StringEs delete(String... targets) {
        return super.delete(targets);
    }

    @Override
    public StringEs delete(List<String> targets) {
        return super.delete(targets);
    }

    @Override
    public StringEs delete(StringEs targetEs) {
        return super.delete(targetEs);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public StringEs deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public StringEs deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringEs deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public StringEs deleteAllBySymbol(StringEs symbolStringVs) {
        return super.deleteAllBySymbol(symbolStringVs);
    }

    @Override
    public StringEs deleteAll(String target) {
        return super.deleteAll(target);
    }

    @Override
    public StringEs deleteAll(String... targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringEs deleteAll(List<String> targets) {
        return super.deleteAll(targets);
    }

    @Override
    public StringEs deleteAll(StringEs targetEs) {
        return super.deleteAll(targetEs);
    }

    ///////////////////////////////////////////////////////
    //
    // near方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * NearIndex下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return super.nearIndexWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String nearDataWhenNextPriorityBySymbol(String symbol) {
        return super.nearDataWhenNextPriorityBySymbol(symbol);
    }

    /**************************************************
     * NearIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return super.nearIndexWhenPrePriorityBySymbol(symbol);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public String nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
    }
}
