package com.codingtu.cooltu.lib4j.es;

import com.codingtu.cooltu.lib4j.convert.ToDouble;
import com.codingtu.cooltu.lib4j.convert.ToFloat;
import com.codingtu.cooltu.lib4j.convert.ToInt;
import com.codingtu.cooltu.lib4j.convert.ToLong;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.impl.IntegerEs;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NumEs<E, THIS extends NumEs> extends CoreEs<E, THIS> {

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public NumEs() {
    }

    public NumEs(List<E> list) {
        super(list);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取valueSymbol
    //
    ///////////////////////////////////////////////////////
    @Override
    protected String obtainSymbol(E e) {
        return e + "";
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
    public E getBySymbol(String symbol) {
        return super.getBySymbol(symbol);
    }

    @Deprecated
    @Override
    public E get(E e) {
        return super.get(e);
    }

    /**************************************************
     * getAll
     **************************************************/
    @Deprecated
    @Override
    public THIS getAllBySymbol(String symbol) {
        return super.getAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public THIS getAll(E e) {
        return super.getAll(e);
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
    public boolean has(E e) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i) == e) {
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
    public int index(E e) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (e == this.es.get(i)) {
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
    public IntegerEs allIndex(E e) {
        int count = count();
        IntegerEs integerVs = new IntegerEs();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i) == e) {
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
    public THIS replaceBySymbol(String symbol, E target) {
        return super.replaceBySymbol(symbol, target);
    }

    @Override
    public THIS replace(E symbol, E target) {
        int firstIndex = index(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replace(E target) {
        return super.replace(target);
    }

    @Deprecated
    @Override
    public THIS replace(E... targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public THIS replace(List<E> targets) {
        return super.replace(targets);
    }

    @Deprecated
    @Override
    public THIS replace(THIS targetEs) {
        return super.replace(targetEs);
    }

    /**************************************************
     * replaceAll
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllBySymbol(String symbol, E target) {
        return super.replaceAllBySymbol(symbol, target);
    }

    @Override
    public THIS replaceAll(E symbol, E target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (this.es.get(i) == symbol) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceAll(E target) {
        return super.replaceAll(target);
    }

    @Deprecated
    @Override
    public THIS replaceAll(E... targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAll(List<E> targets) {
        return super.replaceAll(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAll(THIS targetEs) {
        return super.replaceAll(targetEs);
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceOrAddBySymbol(String symbol, E target) {
        return super.replaceOrAddBySymbol(symbol, target);
    }

    @Override
    public THIS replaceOrAdd(E symbol, E target) {
        int firstIndex = index(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            es.add(target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(E target) {
        return super.replaceOrAdd(target);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(E... targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(List<E> targets) {
        return super.replaceOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceOrAdd(THIS targetEs) {
        return super.replaceOrAdd(targetEs);
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    @Deprecated
    @Override
    public THIS replaceAllOrAddBySymbol(String symbol, E target) {
        return super.replaceAllOrAddBySymbol(symbol, target);
    }

    @Override
    public THIS replaceAllOrAdd(E symbole, E target) {
        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (this.es.get(i) == symbole) {
                replaceByIndex(i, target);
                isReplace = true;
            }
        }
        if (!isReplace && target != null) {
            this.es.add(target);
        }
        return (THIS) this;
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(E target) {
        return super.replaceAllOrAdd(target);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(E... targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(List<E> targets) {
        return super.replaceAllOrAdd(targets);
    }

    @Deprecated
    @Override
    public THIS replaceAllOrAdd(THIS targetEs) {
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
    public THIS deleteBySymbol(String symbol) {
        return super.deleteBySymbol(symbol);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(String... symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(List<String> symbols) {
        return super.deleteBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteBySymbol(StringEs symbolStringEs) {
        return super.deleteBySymbol(symbolStringEs);
    }

    @Override
    public THIS delete(E target) {
        int firstIndex = index(target);
        if (firstIndex >= 0) {
            this.es.remove(firstIndex);
        }
        return (THIS) this;
    }

    @Override
    protected THIS delete(Es.EachGetter<E> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                E target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        delete(target);
                    }
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * deleteAll
     **************************************************/
    @Deprecated
    @Override
    public THIS deleteAllBySymbol(String symbol) {
        return super.deleteAllBySymbol(symbol);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(String... symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(List<String> symbols) {
        return super.deleteAllBySymbol(symbols);
    }

    @Deprecated
    @Override
    public THIS deleteAllBySymbol(StringEs symbolStringVs) {
        return super.deleteAllBySymbol(symbolStringVs);
    }

    @Override
    public THIS deleteAll(E target) {
        List<E> newEs = new ArrayList<>();
        int count = count();
        E e;
        for (int i = 0; i < count; i++) {
            e = this.es.get(i);
            if (e != target) {
                newEs.add(e);
            }
        }
        this.es.clear();
        this.es.addAll(newEs);
        return (THIS) this;
    }

    @Override
    protected THIS deleteAll(Es.EachGetter<E> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<E> newEs = new ArrayList<>();

                E e;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    e = this.es.get(i);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (e == getter.get(j)) {
                            isSame = true;
                            break;
                        }
                    }
                    if (!isSame) {
                        newEs.add(e);
                    }
                }
                this.es.clear();
                this.es.addAll(newEs);
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // maxMin
    //
    ///////////////////////////////////////////////////////

    @Deprecated
    @Override
    public MaxMin<E> maxMin(Es.NowMax<E> nowMax) {
        return super.maxMin(nowMax);
    }

    @Deprecated
    @Override
    public MaxMin<E> maxMin(ToInt<E> toInt) {
        return super.maxMin(toInt);
    }

    @Deprecated
    @Override
    public MaxMin<E> maxMin(ToLong<E> toLong) {
        return super.maxMin(toLong);
    }

    @Deprecated
    @Override
    public MaxMin<E> maxMin(ToDouble<E> toDouble) {
        return super.maxMin(toDouble);
    }

    @Deprecated
    @Override
    public MaxMin<E> maxMin(ToFloat<E> toFloat) {
        return super.maxMin(toFloat);
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
    public E nearDataWhenNextPriorityBySymbol(String symbol) {
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
    public E nearDataWhenPrePriorityBySymbol(String symbol) {
        return super.nearDataWhenPrePriorityBySymbol(symbol);
    }

    ///////////////////////////////////////////////////////
    //
    // ToMap
    //
    ///////////////////////////////////////////////////////
    @Deprecated
    @Override
    public Map<String, E> toMap() {
        return super.toMap();
    }
}
