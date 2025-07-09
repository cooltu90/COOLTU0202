package com.codingtu.cooltu.lib4j.es;

import com.codingtu.cooltu.lib4j.convert.ToDouble;
import com.codingtu.cooltu.lib4j.convert.ToFloat;
import com.codingtu.cooltu.lib4j.convert.ToInt;
import com.codingtu.cooltu.lib4j.convert.ToLong;
import com.codingtu.cooltu.lib4j.data.map.ListValueMap;
import com.codingtu.cooltu.lib4j.data.maxmin.MaxMin;
import com.codingtu.cooltu.lib4j.es.impl.BooleanEs;
import com.codingtu.cooltu.lib4j.es.impl.DoubleEs;
import com.codingtu.cooltu.lib4j.es.impl.FloatEs;
import com.codingtu.cooltu.lib4j.es.impl.IntegerEs;
import com.codingtu.cooltu.lib4j.es.impl.LongEs;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.lib4j.es.symbol.Symbol;
import com.codingtu.cooltu.lib4j.log.LibLogs;
import com.codingtu.cooltu.lib4j.tool.CountTool;
import com.codingtu.cooltu.lib4j.json.JsonTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************************************************
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  add方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #add(Object)}
 * {@link #add(Object[])}
 * {@link #add(List)}
 * {@link #add(CoreEs)}
 * {@link #addn(int, Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  get方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【通过索引值获取】
 * {@link #getByIndex(int)}
 *
 * 【get】
 * {@link #get(Es.IsThisOne)}
 * {@link #getBySymbol(String)}
 * {@link #get(Object)}
 *
 * 【getAll】
 * {@link #getAll(Es.IsThisOne)}
 * {@link #getAllBySymbol(String)}
 * {@link #getAll(Object)}
 *
 * 【getLast】
 * {@link #getLast()}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  has方法                ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #has(Es.IsThisOne)}
 * {@link #hasBySymbol(String)}
 * {@link #has(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  index方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【index】
 * {@link #index(Es.IsThisOne)}
 * {@link #indexBySymbol(String)}
 * {@link #index(Object)}
 *
 * 【allIndex】
 * {@link #allIndex(Es.IsThisOne)}
 * {@link #allIndexBySymbol(String)}
 * {@link #allIndex(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  replace方法            ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【replaceByIndex】
 * {@link #replaceByIndex(int, Object)}
 *
 * 【replace】
 * {@link #replace(Object, Es.IsThisOne)}
 * {@link #replaceBySymbol(String, Object)}
 * {@link #replace(Object, Object)}
 * {@link #replace(Object)}
 * {@link #replace(Object[])}
 * {@link #replace(List)}
 * {@link #replace(CoreEs)}
 *
 * 【replaceAll】
 * {@link #replaceAll(Object, Es.IsThisOne)}
 * {@link #replaceAllBySymbol(String, Object)}
 * {@link #replaceAll(Object, Object)}
 * {@link #replaceAll(Object)}
 * {@link #replaceAll(Object[])}
 * {@link #replaceAll(List)}
 * {@link #replaceAll(CoreEs)}
 *
 * 【replaceOrAdd】
 * {@link #replaceOrAdd(Object, Es.IsThisOne)}
 * {@link #replaceOrAddBySymbol(String, Object)}
 * {@link #replaceOrAdd(Object, Object)}
 * {@link #replaceOrAdd(Object)}
 * {@link #replaceOrAdd(Object[])}
 * {@link #replaceOrAdd(List)}
 * {@link #replaceOrAdd(CoreEs)}
 *
 * 【replaceAllOrAdd】
 * {@link #replaceAllOrAdd(Object, Es.IsThisOne)}
 * {@link #replaceAllOrAddBySymbol(String, Object)}
 * {@link #replaceAllOrAdd(Object, Object)}
 * {@link #replaceAllOrAdd(Object)}
 * {@link #replaceAllOrAdd(Object[])}
 * {@link #replaceAllOrAdd(List)}
 * {@link #replaceAllOrAdd(CoreEs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  delete方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【deleteByIndex】
 * {@link #deleteByIndex(int)}
 *
 * 【delete】
 * {@link #delete(Es.IsThisOne)}
 * {@link #deleteBySymbol(String)}
 * {@link #deleteBySymbol(String...)}
 * {@link #deleteBySymbol(List)}
 * {@link #deleteBySymbol(StringEs)}
 * {@link #delete(Object)}
 * {@link #delete(Object[])}
 * {@link #delete(List)}
 * {@link #delete(CoreEs)}
 *
 * 【deleteAll】
 * {@link #deleteAll(Es.IsThisOne)}
 * {@link #deleteAllBySymbol(String)}
 * {@link #deleteAllBySymbol(String...)}
 * {@link #deleteAllBySymbol(List)}
 * {@link #deleteAllBySymbol(StringEs)}
 * {@link #deleteAll(Object)}
 * {@link #deleteAll(Object[])}
 * {@link #deleteAll(List)}
 * {@link #deleteAll(CoreEs)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  conver方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #convert(Es.Convert)}
 * {@link #convert(Class, Es.Convert)}
 * {@link #convertToString(Es.Convert)}
 * {@link #convertToBoolean(Es.Convert)}
 * {@link #convertToInteger(Es.Convert)}
 * {@link #convertToLong(Es.Convert)}
 * {@link #convertToDouble(Es.Convert)}
 * {@link #convertToFloat(Es.Convert)}
 * {@link #convertList(Es.Convert)}
 * {@link #convertList(Class, Es.Convert)}
 * {@link #converToStringList(Es.Convert)}
 * {@link #converToBooleanList(Es.Convert)}
 * {@link #converToIntegerList(Es.Convert)}
 * {@link #converToLongList(Es.Convert)}
 * {@link #converToDoubleList(Es.Convert)}
 * {@link #converToFloatList(Es.Convert)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  maxMin方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #maxMin(Es.NowMax)}
 * {@link #maxMin(ToInt)}
 * {@link #maxMin(ToLong)}
 * {@link #maxMin(ToDouble)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  near方法               ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 *
 * 【基础方法】
 * {@link #nearIndexByIndex(int, boolean)}
 * {@link #nearIndex(Es.IsThisOne, boolean)}
 * {@link #nearIndexBySymbol(String, boolean)}
 * {@link #nearIndex(Object, boolean)}
 * {@link #nearData(Es.NearIndex)}
 *
 * 【NeighborIndex下一个优先】
 * {@link #nearIndexWhenNextPriority(Es.IsThisOne)}
 * {@link #nearIndexWhenNextPriorityBySymbol(String)}
 * {@link #nearIndexWhenNextPriority(Object)}
 *
 * 【NeighborData下一个优先】
 * {@link #nearDataWhenNextPriority(Es.IsThisOne)}
 * {@link #nearDataWhenNextPriorityBySymbol(String)}
 * {@link #nearDataWhenNextPriority(Object)}
 *
 * 【NeighborIndex上一个优先】
 * {@link #nearIndexWhenPrePriority(Es.IsThisOne)}
 * {@link #nearIndexWhenPrePriorityBySymbol(String)}
 * {@link #nearIndexWhenPrePriority(Object)}
 *
 * 【NeighborData上一个优先】
 * {@link #nearDataWhenPrePriority(Es.IsThisOne)}
 * {@link #nearDataWhenPrePriorityBySymbol(String)}
 * {@link #nearDataWhenPrePriority(Object)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  toList方法             ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #toList()}
 * {@link #toArray()}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  findFinal方法          ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #findFinal(Es.IsNow)}
 *
 * ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
 * ┃  toMap方法              ┃
 * ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
 * {@link #toMap(Es.ToMap)}
 * {@link #toMap()}
 *
 **************************************************/
public abstract class CoreEs<E, THIS extends CoreEs> {

    ///////////////////////////////////////////////////////
    //
    // 数据
    //
    ///////////////////////////////////////////////////////

    protected List<E> es;
    private Boolean isSymbol;

    ///////////////////////////////////////////////////////
    //
    // 构造函数
    //
    ///////////////////////////////////////////////////////
    public CoreEs() {
        this.es = new ArrayList<>();
    }

    public CoreEs(List<E> es) {
        if (es == null) {
            this.es = new ArrayList<>();
        } else {
            this.es = es;
        }
    }

    ///////////////////////////////////////////////////////
    //
    // 抽象方法，获取valueSymbol
    //
    ///////////////////////////////////////////////////////

    protected String obtainSymbol(E e) {
        if (isSymbol == null) {
            isSymbol = e instanceof Symbol;
        }
        if (isSymbol) {
            return ((Symbol) e).obtainSymbol();
        } else {
            return e.toString();
        }
    }

    ///////////////////////////////////////////////////////
    //
    // 计数
    //
    ///////////////////////////////////////////////////////
    public int count() {
        return CountTool.count(es);
    }

    public int count(Es.Counter<E> counter) {
        if (counter == null)
            return 0;

        int total = 0;
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                E e = this.es.get(i);
                total = counter.counter(total, i, e);
            }
        }
        return total;
    }

    public boolean isNull() {
        return count() <= 0;
    }

    ///////////////////////////////////////////////////////
    //
    // 遍历
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 正向遍历
     **************************************************/
    public THIS ls(int step, Es.EachEs<E> eachEs) {
        if (eachEs == null || step <= 0)
            return (THIS) this;

        int count = count();
        for (int i = 0; i < count; i += step) {
            if (eachEs.each(i, this.es.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS ls(Es.EachEs<E> eachEs) {
        return ls(1, eachEs);
    }

    /**************************************************
     * 反向遍历
     **************************************************/
    public THIS rls(int step, Es.EachEs<E> eachEs) {
        if (eachEs == null || step <= 0)
            return (THIS) this;

        int count = count();
        for (int i = count - 1; i >= 0; i -= step) {
            if (eachEs.each(i, this.es.get(i))) {
                return (THIS) this;
            }
        }
        return (THIS) this;
    }

    public THIS rls(Es.EachEs<E> eachEs) {
        return rls(1, eachEs);
    }

    ///////////////////////////////////////////////////////
    //
    // 打印
    //
    ///////////////////////////////////////////////////////
    public THIS log() {
        ls(new Es.EachEs<E>() {
            @Override
            public boolean each(int position, E e) {
                LibLogs.i(JsonTool.toJson(e));
                return false;
            }
        });
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // 排序
    //
    ///////////////////////////////////////////////////////
    public THIS sort(Comparator<E> comparator) {
        if (count() > 0) {
            Collections.sort(es, comparator);
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // 清除
    //
    ///////////////////////////////////////////////////////
    public THIS clear() {
        this.es.clear();
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // add方法
    //
    ///////////////////////////////////////////////////////
    public THIS add(E e) {
        this.es.add(e);
        return (THIS) this;
    }

    public THIS add(E... es) {
        int count = CountTool.count(es);
        for (int i = 0; i < count; i++) {
            this.es.add(es[i]);
        }
        return (THIS) this;
    }

    public THIS add(List<E> es) {
        if (!CountTool.isNull(es)) {
            this.es.addAll(es);
        }
        return (THIS) this;
    }

    public THIS add(THIS es) {
        if (!CountTool.isNull(es)) {
            this.es.addAll(es.es);
        }
        return (THIS) this;
    }

    public THIS addAllKindsOfEs(CoreEs<E, ?> addEs) {
        if (!CountTool.isNull(addEs)) {
            this.es.addAll(addEs.es);
        }
        return (THIS) this;
    }

    public THIS addn(int n, E e) {
        for (int i = 0; i < n; i++) {
            this.es.add(e);
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // createThis
    //
    ///////////////////////////////////////////////////////
    public THIS createThis() {
        try {
            return (THIS) this.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public THIS createThis(E... es) {
        THIS aThis = createThis();
        aThis.add(es);
        return aThis;
    }

    public THIS createThis(List<E> list) {
        try {
            return (THIS) this.getClass().getConstructor(List.class).newInstance(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    ///////////////////////////////////////////////////////
    //
    // get方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 通过索引值获取
     **************************************************/
    public E getByIndex(int index) {
        if (index < count() && index >= 0) {
            return es.get(index);
        }
        return null;
    }

    /**************************************************
     * get
     **************************************************/
    public E get(Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            E e = null;
            for (int i = 0; i < count; i++) {
                e = this.es.get(i);
                if (isThisOne.isThisOne(i, e)) {
                    return e;
                }
            }
        }
        return null;
    }

    public E getBySymbol(String symbol) {
        int count = count();
        E e = null;
        for (int i = 0; i < count; i++) {
            e = this.es.get(i);
            if (symbol.equals(obtainSymbol(e))) {
                return e;
            }
        }
        return null;
    }

    public E get(E e) {
        return getBySymbol(obtainSymbol(e));
    }

    /**************************************************
     * getAll
     **************************************************/
    public THIS getAll(Es.IsThisOne<E> isThisOne) {
        THIS ThisObj = createThis();
        if (isThisOne != null) {
            int count = count();
            E e = null;
            for (int i = 0; i < count; i++) {
                e = this.es.get(i);
                if (isThisOne.isThisOne(i, e)) {
                    ThisObj.add(e);
                }
            }
        }
        return ThisObj;
    }

    public THIS getAllBySymbol(String symbol) {
        THIS ThisObj = createThis();
        int count = count();
        E e = null;
        for (int i = 0; i < count; i++) {
            e = this.es.get(i);
            if (symbol.equals(obtainSymbol(e))) {
                ThisObj.add(e);
            }
        }
        return ThisObj;
    }

    public THIS getAll(E e) {
        return getAllBySymbol(obtainSymbol(e));
    }

    /**************************************************
     * getLast
     **************************************************/
    public E getLast() {
        if (count() <= 0) {
            return null;
        }
        return this.es.get(count() - 1);
    }

    ///////////////////////////////////////////////////////
    //
    // has方法
    //
    ///////////////////////////////////////////////////////
    public boolean has(Es.IsThisOne<E> isThisOne) {
        return get(isThisOne) != null;
    }

    public boolean hasBySymbol(String symbol) {
        return getBySymbol(symbol) != null;
    }

    public boolean has(E e) {
        return hasBySymbol(obtainSymbol(e));
    }

    ///////////////////////////////////////////////////////
    //
    // index方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * index
     **************************************************/
    public int index(Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.es.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int indexBySymbol(String symbol) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (symbol.equals(obtainSymbol(this.es.get(i)))) {
                return i;
            }
        }
        return -1;
    }

    public int index(E e) {
        return indexBySymbol(obtainSymbol(e));
    }

    /**************************************************
     * allIndex
     **************************************************/
    public IntegerEs allIndex(Es.IsThisOne<E> isThisOne) {
        IntegerEs integerEs = new IntegerEs();
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.es.get(i))) {
                    integerEs.add(i);
                }
            }
        }
        return integerEs;
    }

    public IntegerEs allIndexBySymbol(String symbol) {
        IntegerEs integerEs = new IntegerEs();
        int count = count();
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.es.get(i)).equals(symbol)) {
                integerEs.add(i);
            }
        }
        return integerEs;
    }

    public IntegerEs allIndex(E e) {
        return allIndexBySymbol(obtainSymbol(e));
    }

    ///////////////////////////////////////////////////////
    //
    // replace方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * replaceByIndex
     **************************************************/
    public THIS replaceByIndex(int index, E e) {
        this.es.set(index, e);
        return (THIS) this;
    }

    /**************************************************
     * replace
     **************************************************/
    public THIS replace(E target, Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceBySymbol(String symbol, E target) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        }
        return (THIS) this;
    }

    public THIS replace(E symbol, E target) {
        if (symbol != null && target != null) {
            replaceBySymbol(obtainSymbol(symbol), target);
        }
        return (THIS) this;
    }

    public THIS replace(E target) {
        return replace(target, target);
    }

    public THIS replace(E... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replace(List<E> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replace(THIS targetEs) {
        int count = targetEs.count();
        if (count > 0) {
            E target;
            for (int i = 0; i < count; i++) {
                target = (E) targetEs.es.get(i);
                if (target != null) {
                    replaceBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAll
     **************************************************/
    public THIS replaceAll(E target, Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.es.get(i))) {
                    replaceByIndex(i, target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllBySymbol(String symbol, E target) {
        int count = count();
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.es.get(i)).equals(symbol)) {
                replaceByIndex(i, target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(E symbolE, E target) {
        if (symbolE != null && target != null) {
            replaceAllBySymbol(obtainSymbol(symbolE), target);
        }
        return (THIS) this;
    }

    public THIS replaceAll(E target) {
        return replaceAll(target, target);
    }

    public THIS replaceAll(E... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(List<E> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAll(THIS targetEs) {
        int count = targetEs.count();
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = (E) targetEs.es.get(i);
                if (target != null) {
                    replaceAllBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceOrAdd
     **************************************************/
    public THIS replaceOrAdd(E target, Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                replaceByIndex(firstIndex, target);
            } else if (target != null) {
                es.add(target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAddBySymbol(String symbol, E target) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            replaceByIndex(firstIndex, target);
        } else if (target != null) {
            es.add(target);
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(E symbol, E target) {
        if (symbol != null && target != null) {
            replaceOrAddBySymbol(obtainSymbol(symbol), target);
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(E target) {
        return replaceOrAdd(target, target);
    }

    public THIS replaceOrAdd(E... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(List<E> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceOrAdd(THIS targetEs) {
        int count = targetEs.count();
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = (E) targetEs.es.get(i);
                if (target != null) {
                    replaceOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    /**************************************************
     * replaceAllOrAdd
     **************************************************/
    public THIS replaceAllOrAdd(E target, Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int count = count();
            boolean isReplace = false;
            for (int i = 0; i < count; i++) {
                if (isThisOne.isThisOne(i, this.es.get(i))) {
                    replaceByIndex(i, target);
                    isReplace = true;
                }
            }
            if (!isReplace && target != null) {
                this.es.add(target);
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAddBySymbol(String symbol, E target) {
        int count = count();
        boolean isReplace = false;
        for (int i = 0; i < count; i++) {
            if (obtainSymbol(this.es.get(i)).equals(symbol)) {
                replaceByIndex(i, target);
                isReplace = true;
            }
        }
        if (!isReplace && target != null) {
            this.es.add(target);
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(E symbole, E target) {
        if (symbole != null && target != null) {
            replaceAllOrAddBySymbol(obtainSymbol(symbole), target);
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(E target) {
        return replaceAllOrAdd(target, target);
    }

    public THIS replaceAllOrAdd(E... targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets[i];
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(List<E> targets) {
        int count = CountTool.count(targets);
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = targets.get(i);
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    public THIS replaceAllOrAdd(THIS targetEs) {
        int count = targetEs.count();
        if (count > 0) {
            E target = null;
            for (int i = 0; i < count; i++) {
                target = (E) targetEs.es.get(i);
                if (target != null) {
                    replaceAllOrAddBySymbol(obtainSymbol(target), target);
                }
            }
        }
        return (THIS) this;
    }

    ///////////////////////////////////////////////////////
    //
    // delete方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * deleteByIndex
     **************************************************/
    public THIS deleteByIndex(int position) {
        this.es.remove(position);
        return (THIS) this;
    }

    /**************************************************
     * delete
     **************************************************/
    //删除第一个
    public THIS delete(Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            int firstIndex = index(isThisOne);
            if (firstIndex >= 0) {
                this.es.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(String symbol) {
        int firstIndex = indexBySymbol(symbol);
        if (firstIndex >= 0) {
            this.es.remove(firstIndex);
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(String... symbols) {
        int count = CountTool.count(symbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbols[i]);
            if (firstIndex >= 0) {
                this.es.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(List<String> symbols) {
        int count = CountTool.count(symbols);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbols.get(i));
            if (firstIndex >= 0) {
                this.es.remove(firstIndex);
            }
        }
        return (THIS) this;
    }

    public THIS deleteBySymbol(StringEs symbolStringEs) {
        int count = CountTool.count(symbolStringEs);
        for (int i = 0; i < count; i++) {
            int firstIndex = indexBySymbol(symbolStringEs.es.get(i));
            if (firstIndex >= 0) {
                this.es.remove(firstIndex);
            }
        }
        return (THIS) this;
    }


    public THIS delete(E target) {
        if (target != null) {
            deleteBySymbol(obtainSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS delete(Es.EachGetter<E> getter) {
        if (getter != null) {
            int count = getter.count();
            if (count > 0) {
                E target = null;
                for (int i = 0; i < count; i++) {
                    target = getter.get(i);
                    if (target != null) {
                        deleteBySymbol(obtainSymbol(target));
                    }
                }
            }
        }
        return (THIS) this;
    }

    public THIS delete(E... targets) {
        return delete(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public E get(int position) {
                return targets[position];
            }
        });
    }

    public THIS delete(List<E> targets) {
        return delete(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public E get(int position) {
                return targets.get(position);
            }
        });
    }

    public THIS delete(THIS targetEs) {
        return delete(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targetEs);
            }

            @Override
            public E get(int position) {
                return (E) targetEs.es.get(position);
            }
        });
    }

    /**************************************************
     * deleteAll
     **************************************************/
    public THIS deleteAll(Es.IsThisOne<E> isThisOne) {
        if (isThisOne != null) {
            List<E> newEs = new ArrayList<>();
            int count = count();
            E e;
            for (int i = 0; i < count; i++) {
                e = this.es.get(i);
                if (!isThisOne.isThisOne(i, e)) {
                    newEs.add(e);
                }
            }
            this.es.clear();
            this.es.addAll(newEs);
        }
        return (THIS) this;
    }

    public THIS deleteAllBySymbol(String symbol) {
        List<E> newEs = new ArrayList<>();
        int count = count();
        E e;
        for (int i = 0; i < count; i++) {
            e = this.es.get(i);
            if (!obtainSymbol(e).equals(symbol)) {
                newEs.add(e);
            }
        }
        this.es.clear();
        this.es.addAll(newEs);
        return (THIS) this;
    }

    private THIS deleteAllBySymbol(Es.EachGetter<String> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<E> newEs = new ArrayList<>();

                E e;
                String tValueSymbol;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    e = this.es.get(i);
                    tValueSymbol = obtainSymbol(e);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (tValueSymbol.equals(getter.get(j))) {
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

    public THIS deleteAllBySymbol(String... symbols) {
        return deleteAllBySymbol(new Es.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbols);
            }

            @Override
            public String get(int position) {
                return symbols[position];
            }
        });
    }

    public THIS deleteAllBySymbol(List<String> symbols) {
        return deleteAllBySymbol(new Es.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbols);
            }

            @Override
            public String get(int position) {
                return symbols.get(position);
            }
        });
    }

    public THIS deleteAllBySymbol(StringEs symbolStringVs) {
        return deleteAllBySymbol(new Es.EachGetter<String>() {
            @Override
            public int count() {
                return CountTool.count(symbolStringVs);
            }

            @Override
            public String get(int position) {
                return symbolStringVs.es.get(position);
            }
        });
    }

    public THIS deleteAll(E target) {
        if (target != null) {
            deleteAllBySymbol(obtainSymbol(target));
        }
        return (THIS) this;
    }

    protected THIS deleteAll(Es.EachGetter<E> getter) {
        if (getter != null) {
            int valueSymbolCount = getter.count();
            int tCount = count();
            if (valueSymbolCount > 0 && tCount > 0) {
                List<E> newEs = new ArrayList<>();

                E e;
                String tValueSymbol;
                boolean isSame;
                for (int i = 0; i < tCount; i++) {
                    e = this.es.get(i);
                    tValueSymbol = obtainSymbol(e);

                    isSame = false;
                    for (int j = 0; j < valueSymbolCount; j++) {
                        if (tValueSymbol.equals(obtainSymbol(getter.get(j)))) {
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


    public THIS deleteAll(E... targets) {
        return deleteAll(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public E get(int position) {
                return targets[position];
            }
        });
    }

    public THIS deleteAll(List<E> targets) {
        return deleteAll(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targets);
            }

            @Override
            public E get(int position) {
                return targets.get(position);
            }
        });
    }

    public THIS deleteAll(THIS targetEs) {
        return deleteAll(new Es.EachGetter<E>() {
            @Override
            public int count() {
                return CountTool.count(targetEs);
            }

            @Override
            public E get(int position) {
                return (E) targetEs.es.get(position);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // 转换
    //
    ///////////////////////////////////////////////////////
    public <TARGET> BaseEs<TARGET> convert(Es.Convert<E, TARGET> convert) {
        BaseEs baseEs = new BaseEs();
        if (convert != null) {
            int count = count();
            TARGET target;
            for (int i = 0; i < count; i++) {
                target = convert.convert(i, this.es.get(i));
                if (target != null) {
                    baseEs.add(target);
                }
            }
        }
        return baseEs;
    }

    public <TARGET, ES extends CoreEs> ES convert(Class<ES> vsClass, Es.Convert<E, TARGET> convert) {
        ES targetEs;
        try {
            targetEs = vsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int count = count();
        TARGET target;
        for (int i = 0; i < count; i++) {
            target = convert.convert(i, this.es.get(i));
            if (target != null) {
                targetEs.add(target);
            }
        }
        return targetEs;
    }

    public StringEs convertToString(Es.Convert<E, String> convert) {
        return convert(StringEs.class, convert);
    }

    public BooleanEs convertToBoolean(Es.Convert<E, Boolean> convert) {
        return convert(BooleanEs.class, convert);
    }

    public IntegerEs convertToInteger(Es.Convert<E, Integer> convert) {
        return convert(IntegerEs.class, convert);
    }

    public LongEs convertToLong(Es.Convert<E, Long> convert) {
        return convert(LongEs.class, convert);
    }

    public DoubleEs convertToDouble(Es.Convert<E, Double> convert) {
        return convert(DoubleEs.class, convert);
    }

    public FloatEs convertToFloat(Es.Convert<E, Float> convert) {
        return convert(FloatEs.class, convert);
    }

    public <TARGET> BaseEs<TARGET> convertList(Es.Convert<E, List<TARGET>> convert) {
        BaseEs<TARGET> baseEs = new BaseEs<>();
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.es.get(i));
                if (!CountTool.isNull(list)) {
                    baseEs.add(list);
                }
            }
        }
        return baseEs;
    }

    public <TARGET> BaseEs<TARGET> convertList() {
        return convertList(new Es.Convert<E, List<TARGET>>() {
            @Override
            public List<TARGET> convert(int index, E e) {
                if (e != null) {
                    return (List<TARGET>) e;
                }
                return null;
            }
        });
    }

    public <ES extends CoreEs, TARGET> ES convertList(Class<ES> vsClass, Es.Convert<E, List<TARGET>> convert) {
        ES targetEs;
        try {
            targetEs = vsClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (convert != null) {
            int count = count();
            List<TARGET> list;
            for (int i = 0; i < count; i++) {
                list = convert.convert(i, this.es.get(i));
                if (!CountTool.isNull(list)) {
                    targetEs.add(list);
                }
            }
        }
        return targetEs;
    }

    public StringEs converToStringList(Es.Convert<E, List<String>> convert) {
        return convertList(StringEs.class, convert);
    }

    public BooleanEs converToBooleanList(Es.Convert<E, List<Boolean>> convert) {
        return convertList(BooleanEs.class, convert);
    }

    public IntegerEs converToIntegerList(Es.Convert<E, List<Integer>> convert) {
        return convertList(IntegerEs.class, convert);
    }

    public LongEs converToLongList(Es.Convert<E, List<Long>> convert) {
        return convertList(LongEs.class, convert);
    }

    public DoubleEs converToDoubleList(Es.Convert<E, List<Double>> convert) {
        return convertList(DoubleEs.class, convert);
    }

    public FloatEs converToFloatList(Es.Convert<E, List<Float>> convert) {
        return convertList(FloatEs.class, convert);
    }

    ///////////////////////////////////////////////////////
    //
    // 获取最大最小值
    //
    ///////////////////////////////////////////////////////

    public MaxMin<E> maxMin(Es.NowMax<E> nowMax) {
        if (nowMax == null)
            return null;

        int count = count();
        MaxMin<E> maxMin = null;
        for (int i = 0; i < count; i++) {
            E now = this.es.get(i);
            if (i == 0) {
                maxMin = new MaxMin<>();
                maxMin.max = now;
                maxMin.min = now;
            } else {
                maxMin.max = nowMax.isNowMax(maxMin.max, now) ? now : maxMin.max;
                maxMin.min = nowMax.isNowMax(maxMin.min, now) ? maxMin.min : now;
            }
        }

        return maxMin;
    }

    public MaxMin<E> maxMin(ToInt<E> toInt) {
        return maxMin(new Es.NowMax<E>() {
            @Override
            public boolean isNowMax(E last, E now) {
                return toInt.toInt(now) > toInt.toInt(last);
            }
        });
    }

    public MaxMin<E> maxMin(ToLong<E> toLong) {
        return maxMin(new Es.NowMax<E>() {
            @Override
            public boolean isNowMax(E last, E now) {
                return toLong.toLong(now) > toLong.toLong(last);
            }
        });
    }

    public MaxMin<E> maxMin(ToDouble<E> toDouble) {
        return maxMin(new Es.NowMax<E>() {
            @Override
            public boolean isNowMax(E last, E now) {
                return toDouble.toDouble(now) > toDouble.toDouble(last);
            }
        });
    }

    public MaxMin<E> maxMin(ToFloat<E> toFloat) {
        return maxMin(new Es.NowMax<E>() {
            @Override
            public boolean isNowMax(E last, E now) {
                return toFloat.toFloat(now) > toFloat.toFloat(last);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // near方法
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 基础方法
     **************************************************/
    protected Es.NearIndex nearIndexByIndex(int index, boolean isNext) {
        int count = count();
        if (count == 1) {
            return null;
        }
        if (index < 0) {
            return null;
        }

        int step = isNext ? 1 : -1;

        Es.NearIndex nearIndex = new Es.NearIndex();
        nearIndex.currentIndex = index;
        if (nearIndex.currentIndex == (isNext ? (count - 1) : 0)) {
            nearIndex.nearIndex = nearIndex.currentIndex - step;
        } else {
            nearIndex.nearIndex = nearIndex.currentIndex + step;
        }
        return nearIndex;
    }

    protected Es.NearIndex nearIndex(Es.IsThisOne<E> isThisOne, boolean isNext) {
        return nearIndexByIndex(index(isThisOne), isNext);
    }

    protected Es.NearIndex nearIndexBySymbol(String symbol, boolean isNext) {
        return nearIndexByIndex(indexBySymbol(symbol), isNext);
    }

    protected Es.NearIndex nearIndex(E e, boolean isNext) {
        return nearIndexByIndex(index(e), isNext);
    }

    protected E nearData(Es.NearIndex nearIndex) {
        if (nearIndex == null)
            return null;
        return getByIndex(nearIndex.nearIndex);
    }

    /**************************************************
     * NearIndex下一个优先
     **************************************************/
    public Es.NearIndex nearIndexWhenNextPriority(Es.IsThisOne<E> isThisOne) {
        return nearIndex(isThisOne, true);
    }

    public Es.NearIndex nearIndexWhenNextPriorityBySymbol(String symbol) {
        return nearIndexBySymbol(symbol, true);
    }

    public Es.NearIndex nearIndexWhenNextPriority(E e) {
        return nearIndex(e, true);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    public E nearDataWhenNextPriority(Es.IsThisOne<E> isThisOne) {
        return nearData(nearIndexWhenNextPriority(isThisOne));
    }

    public E nearDataWhenNextPriorityBySymbol(String symbol) {
        return nearData(nearIndexWhenNextPriorityBySymbol(symbol));
    }

    public E nearDataWhenNextPriority(E e) {
        return nearData(nearIndexWhenNextPriority(e));
    }

    /**************************************************
     * NearIndex上一个优先
     **************************************************/
    public Es.NearIndex nearIndexWhenPrePriority(Es.IsThisOne<E> isThisOne) {
        return nearIndex(isThisOne, false);
    }

    public Es.NearIndex nearIndexWhenPrePriorityBySymbol(String symbol) {
        return nearIndexBySymbol(symbol, false);
    }

    public Es.NearIndex nearIndexWhenPrePriority(E e) {
        return nearIndex(e, false);
    }

    /**************************************************
     * NearData下一个优先
     **************************************************/
    public E nearDataWhenPrePriority(Es.IsThisOne<E> isThisOne) {
        return nearData(nearIndexWhenPrePriority(isThisOne));
    }

    public E nearDataWhenPrePriorityBySymbol(String symbol) {
        return nearData(nearIndexWhenPrePriorityBySymbol(symbol));
    }

    public E nearDataWhenPrePriority(E e) {
        return nearData(nearIndexWhenPrePriority(e));
    }

    ///////////////////////////////////////////////////////
    //
    // toList
    //
    ///////////////////////////////////////////////////////
    public List<E> toList() {
        return this.es;
    }

    public E[] toArray() {
        int count = count();
        if (count == 0) {
            return null;
        }

        E[] newArray = (E[]) java.lang.reflect.Array.newInstance(getByIndex(0).getClass(), count);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                newArray[i] = this.es.get(i);
            }
        }
        return newArray;
    }

    ///////////////////////////////////////////////////////
    //
    // findFinal
    //
    ///////////////////////////////////////////////////////

    /**************************************************
     * 查找最后一个符合条件的元素
     **************************************************/
    public E findFinal(Es.IsNow<E> isNow) {
        if (isNow == null)
            return null;

        int count = count();
        E last = null;
        for (int i = 0; i < count; i++) {
            E now = this.es.get(i);
            last = last == null ? now : (isNow.isNow(last, now) ? now : last);
        }
        return last;
    }

    ///////////////////////////////////////////////////////
    //
    // ToMap
    //
    ///////////////////////////////////////////////////////
    public <K, V> Map<K, V> toMap(Es.ToMap<K, V, E> toMap) {
        Map<K, V> map = new HashMap<>();
        int count = count();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                E e = this.es.get(i);
                toMap.deal(map, i, e);
            }
        }
        return map;
    }

    public Map<String, E> toMap() {
        return toMap(new Es.ToMap<String, E, E>() {
            @Override
            public void deal(Map<String, E> map, int i, E e) {
                map.put(obtainSymbol(e), e);
            }
        });
    }

    public Map<E, E> toTMap() {
        return toMap(new Es.ToMap<E, E, E>() {
            @Override
            public void deal(Map<E, E> map, int i, E e) {
                map.put(e, e);
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public interface GroupSortGetter<T> {
        String getGroup(int level, T t);

        int getLevels();

        int compare(T o1, T o2);

    }

    public THIS groupSort(GroupSortGetter<E> getter) {
        ListValueMap<String, String> totalMap = new ListValueMap<>();
        Map<String, E> tMap = new HashMap<>();

        Collections.sort(this.es, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return getter.compare(o1, o2);
            }
        });

        ls(new Es.EachEs<E>() {
            @Override
            public boolean each(int i, E e) {
                tMap.put(getter.getGroup(getter.getLevels() - 1, e), e);

                String[] gs = new String[getter.getLevels()];
                for (int j = 0; j < gs.length; j++) {
                    gs[j] = getter.getGroup(j, e);
                }

                List<String> list = totalMap.get(getRootGroupKey());

                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < gs.length; j++) {
                    if (j < gs.length - 1) {
                        sb.append(gs[j]);
                        List<String> subList = totalMap.get(sb.toString());
                        if (CountTool.isNull(subList)) {
                            list.add(gs[j]);
                        }
                        list = subList;
                    } else {
                        list.add(gs[j]);
                    }

                }
                return false;
            }
        });

        List<E> as = new ArrayList<>();
        groupSort(as, getter.getLevels(), 0, totalMap, tMap, getRootGroupKey());
        this.es = as;
        return (THIS) this;
    }

    private void groupSort(List<E> container, int levels, int level, ListValueMap<String, String> categorgMap, Map<String, E> tMap, String key) {
        Es.strs(categorgMap.get(key)).ls(new Es.EachEs<String>() {
            @Override
            public boolean each(int i, String s) {
                if (level < levels - 1) {
                    groupSort(container, levels, level + 1, categorgMap, tMap, (getRootGroupKey().equals(key) ? "" : key) + s);
                } else {
                    container.add(tMap.get(s));
                }
                return false;
            }
        });
    }

    private String getRootGroupKey() {
        return "root";
    }

}
