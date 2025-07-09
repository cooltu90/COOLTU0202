package com.codingtu.cooltu.lib4j.es.impl;

import com.codingtu.cooltu.lib4j.es.CoreEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;

public class BooleanEs extends CoreEs<Boolean, BooleanEs> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////

    public BooleanEs() {
    }

    public BooleanEs(List<Boolean> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取Symbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(Boolean aBoolean) {
        return aBoolean + "";
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////

    public BooleanEs add_boolean(boolean... booleans) {
        int count = CountTool.count(booleans);
        for (int i = 0; i < count; i++) {
            this.es.add(booleans[i]);
        }
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public BooleanEs createThis_boolean(boolean... booleans) {
        BooleanEs booleanVs = new BooleanEs();
        booleanVs.add_boolean(booleans);
        return booleanVs;
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
    public Boolean get(Es.IsThisOne<Boolean> isThisOne) {
        return super.get(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean get(Boolean aBoolean) {
        return super.get(aBoolean);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanEs getAll(Es.IsThisOne<Boolean> isThisOne) {
        return super.getAll(isThisOne);
    }

    @Deprecated
    @Override
    public BooleanEs getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanEs getAll(Boolean aBoolean) {
        return super.getAll(aBoolean);
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
    public boolean has(Boolean aBoolean) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (es.get(i) == aBoolean) {
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
        return super.indexBySymbol(symbol);
    }

    @Override
    public int index(Boolean aBoolean) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (aBoolean == this.es.get(i)) {
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
        return super.allIndexBySymbol(symbol);
    }

    @Override
    public IntegerEs allIndex(Boolean aBoolean) {
        int count = count();
        IntegerEs integerEs = new IntegerEs();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i) == aBoolean) {
                integerEs.add(i);
            }
        }
        return integerEs;
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
    public BooleanEs replaceBySymbol(String symbol, Boolean target) {
        return super.replaceBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replace(Boolean symbol, Boolean target) {
        return super.replace(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replace(Boolean target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public BooleanEs replace(Boolean... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replace(List<Boolean> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replace(BooleanEs targetEs) {
        return super.replace(targetEs);
    }


    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanEs replaceAllBySymbol(String symbol, Boolean target) {
        return super.replaceAllBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAll(Boolean symbolT, Boolean target) {
        return super.replaceAll(symbolT, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAll(Boolean target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAll(Boolean... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAll(List<Boolean> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAll(BooleanEs targetEs) {
        return super.replaceAll(targetEs);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/

    @Deprecated
    @Override
    public BooleanEs replaceOrAddBySymbol(String symbol, Boolean target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceOrAdd(Boolean symbol, Boolean target) {
        return super.replaceOrAdd(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceOrAdd(Boolean target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceOrAdd(Boolean... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceOrAdd(List<Boolean> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceOrAdd(BooleanEs targetEs) {
        return super.replaceOrAdd(targetEs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public BooleanEs replaceAllOrAddBySymbol(String symbol, Boolean target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAllOrAdd(Boolean symbole, Boolean target) {
        return super.replaceAllOrAdd(symbole, target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAllOrAdd(Boolean target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAllOrAdd(Boolean... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAllOrAdd(List<Boolean> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public BooleanEs replaceAllOrAdd(BooleanEs targetEs) {
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
    public BooleanEs deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanEs deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanEs deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanEs deleteBySymbol(StringEs symbolStringEs) {
        return super.deleteBySymbol(symbolStringEs);
    }

    @Deprecated
    @Override
    public BooleanEs delete(Boolean target) {
        int firstIndex = index(target);
        if (firstIndex >= 0) {
            this.es.remove(firstIndex);
        }
        return this;
    }

    @Deprecated
    @Override
    public BooleanEs delete(Boolean... targets) {
        return super.delete(targets);
    }

    @Deprecated
    @Override
    public BooleanEs delete(List<Boolean> targets) {
        return super.delete(targets);
    }

    @Deprecated
    @Override
    public BooleanEs delete(BooleanEs targetEs) {
        return super.delete(targetEs);
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public BooleanEs deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAllBySymbol(StringEs symbolStringVs) {
        return super.deleteAllBySymbol(symbolStringVs);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAll(Boolean target) {
        return super.deleteAll(target);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAll(Boolean... targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAll(List<Boolean> targets) {
        return super.deleteAll(targets);
    }

    @Deprecated
    @Override
    public BooleanEs deleteAll(BooleanEs targetEs) {
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
    public Es.NearIndex nearIndexWhenNextPriority(Es.IsThisOne<Boolean> isThisOne) {
        return super.nearIndexWhenNextPriority(isThisOne);
    }


    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return super.nearIndexWhenNextPriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenNextPriority(Boolean aBoolean) {
        return super.nearIndexWhenNextPriority(aBoolean);
    }


    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriority(Es.IsThisOne<Boolean> isThisOne) {
        return super.nearDataWhenNextPriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriorityBySymbol(String symbol) {
        return super.nearDataWhenNextPriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenNextPriority(Boolean aBoolean) {
        return super.nearDataWhenNextPriority(aBoolean);
    }


    /**************************************************
     * NearIndex上一个优先
     **************************************************/
    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenPrePriority(Es.IsThisOne<Boolean> isThisOne) {
        return super.nearIndexWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return super.nearIndexWhenPrePriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Es.NearIndex nearIndexWhenPrePriority(Boolean aBoolean) {
        return super.nearIndexWhenPrePriority(aBoolean);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriority(Es.IsThisOne<Boolean> isThisOne) {
        return super.nearDataWhenPrePriority(isThisOne);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
    }

    @Deprecated
    @Override
    public Boolean nearDataWhenPrePriority(Boolean aBoolean) {
        return super.nearDataWhenPrePriority(aBoolean);
    }

    ///////////////////////////////////////////////////////
    //
    // toArray
    //
    ///////////////////////////////////////////////////////
    public boolean[] to_booleans() {
        int count = count();
        boolean[] arrs = new boolean[count];
        for (int i = 0; i < count; i++) {
            arrs[i] = this.es.get(i);
        }
        return arrs;
    }
}
