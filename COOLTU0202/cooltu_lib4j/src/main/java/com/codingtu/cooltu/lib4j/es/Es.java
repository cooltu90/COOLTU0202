package com.codingtu.cooltu.lib4j.es;

import com.codingtu.cooltu.lib4j.data.data.LibDataObj;
import com.codingtu.cooltu.lib4j.es.impl.BooleanEs;
import com.codingtu.cooltu.lib4j.es.impl.DoubleEs;
import com.codingtu.cooltu.lib4j.es.impl.FloatEs;
import com.codingtu.cooltu.lib4j.es.impl.IntegerEs;
import com.codingtu.cooltu.lib4j.es.impl.LongEs;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.lib4j.es.map.BaseMap;
import com.codingtu.cooltu.lib4j.es.symbol.LongIdSymbol;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Es {

    /**************************************************
     *
     **************************************************/
    public static interface EachEs<E> {
        boolean each(int position, E e);
    }

    public static interface EachGetter<E> {
        int count();

        E get(int position);
    }

    public static interface IsThisOne<E> {
        boolean isThisOne(int position, E e);
    }

    public static class IsThisOneByLongId<E extends LongIdSymbol> implements IsThisOne<E> {

        private long longId;

        public IsThisOneByLongId(long longId) {
            this.longId = longId;
        }

        public IsThisOneByLongId(E e) {
            this.longId = e.obtainLongId();
        }

        @Override
        public boolean isThisOne(int position, E e) {
            return e.obtainLongId() == this.longId;
        }
    }

    public static interface Convert<SRC, TARGET> {
        TARGET convert(int index, SRC src);
    }

    public static interface NowMax<E> {
        boolean isNowMax(E last, E now);
    }

    public static interface IsNow<E> {
        boolean isNow(E last, E now);
    }

    public static interface Counter<E> {
        public int counter(int lastCount, int index, E e);
    }

    public static interface ToMap<K, V, TARGET> {
        void deal(Map<K, V> map, int i, TARGET target);
    }

    public interface MapEach<K, V> {
        public boolean each(K k, V v);
    }

    /**************************************************
     *
     **************************************************/
    public static class NearIndex extends LibDataObj {
        public int currentIndex;
        public int nearIndex;

        public boolean isNextOne() {
            return nearIndex > currentIndex;
        }
    }

    private static <ES extends CoreEs, E> ES es(Class<ES> esClass, Set<E> srcs) {
        ES es;
        try {
            es = esClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int count = CountTool.count(srcs);
        if (count >= 0) {
            es.es.addAll(srcs);
        }
        return es;
    }

    /**************************************************
     * BaseVs
     **************************************************/

    public static <E> BaseEs<E> es(EachGetter<E> getter) {
        return es(0, getter);
    }

    public static <E> BaseEs<E> es(int skip, EachGetter<E> getter) {
        BaseEs<E> ts = es();
        int count = getter.count();
        for (int i = skip; i < count; i++) {
            ts.add(getter.get(i));
        }
        return ts;
    }

    public static <E> BaseEs<E> es(E... srcs) {
        BaseEs<E> es = new BaseEs<>();
        es.add(srcs);
        return es;
    }

    public static <E> BaseEs<E> es(List<E> srcs) {
        return new BaseEs<>(srcs);
    }

    public static <E> BaseEs<E> es(Set<E> srcs) {
        return es(BaseEs.class, srcs);
    }

    public static <E> BaseEs<E> es(BaseEs<E> srcs) {
        BaseEs<E> es = new BaseEs<>();
        es.add(srcs);
        return es;
    }


    /**************************************************
     * StringVs
     **************************************************/
    public static StringEs strs(String... srcs) {
        StringEs es = new StringEs();
        es.add(srcs);
        return es;
    }

    public static StringEs strs(List<String> srcs) {
        return new StringEs(srcs);
    }

    public static StringEs strs(Set<String> srcs) {
        return es(StringEs.class, srcs);
    }

    public static StringEs strs(StringEs srcs) {
        StringEs es = new StringEs();
        es.add(srcs);
        return es;
    }

    /**************************************************
     * BooleanVs
     **************************************************/
    public static BooleanEs BOOLEANS(Boolean... srcs) {
        BooleanEs es = new BooleanEs();
        es.add(srcs);
        return es;
    }

    public static BooleanEs booleans(boolean... srcs) {
        BooleanEs es = new BooleanEs();
        es.add_boolean(srcs);
        return es;
    }

    public static BooleanEs booleans(List<Boolean> srcs) {
        return new BooleanEs(srcs);
    }

    public static BooleanEs booleans(Set<Boolean> srcs) {
        return es(BooleanEs.class, srcs);
    }

    public static BooleanEs booleans(BooleanEs srcVs) {
        BooleanEs es = new BooleanEs();
        es.add(srcVs);
        return es;
    }

    /**************************************************
     * DoubleVs
     **************************************************/
    public static DoubleEs DOUBLES(Double... srcs) {
        DoubleEs es = new DoubleEs();
        es.add(srcs);
        return es;
    }

    public static DoubleEs doubles(double... srcs) {
        DoubleEs es = new DoubleEs();
        es.add_double(srcs);
        return es;
    }

    public static DoubleEs doubles(List<Double> srcs) {
        return new DoubleEs(srcs);
    }

    public static DoubleEs doubles(Set<Double> srcs) {
        return es(DoubleEs.class, srcs);
    }

    public static DoubleEs doubles(DoubleEs srcs) {
        DoubleEs es = new DoubleEs();
        es.add(srcs);
        return es;
    }

    /**************************************************
     * FloatVs
     **************************************************/
    public static FloatEs FLOATS(Float... srcs) {
        FloatEs es = new FloatEs();
        es.add(srcs);
        return es;
    }

    public static FloatEs floats(float... srcs) {
        FloatEs es = new FloatEs();
        es.add_float(srcs);
        return es;
    }

    public static FloatEs floats(List<Float> srcs) {
        return new FloatEs(srcs);
    }

    public static FloatEs floats(Set<Float> srcs) {
        return es(FloatEs.class, srcs);
    }

    public static FloatEs floats(FloatEs srcs) {
        FloatEs es = new FloatEs();
        es.add(srcs);
        return es;
    }

    /**************************************************
     * IntegerVs
     **************************************************/
    public static IntegerEs INTS(Integer... srcs) {
        IntegerEs es = new IntegerEs();
        es.add(srcs);
        return es;
    }

    public static IntegerEs ints(int... srcs) {
        IntegerEs es = new IntegerEs();
        es.add_int(srcs);
        return es;
    }

    public static IntegerEs ints(List<Integer> srcs) {
        return new IntegerEs(srcs);
    }

    public static IntegerEs ints(Set<Integer> srcs) {
        return es(IntegerEs.class, srcs);
    }

    public static IntegerEs ints(IntegerEs srcs) {
        IntegerEs es = new IntegerEs();
        es.add(srcs);
        return es;
    }

    /**************************************************
     * LongVs
     **************************************************/
    public static LongEs LONGS(Long... srcs) {
        LongEs es = new LongEs();
        es.add(srcs);
        return es;
    }

    public static LongEs longs(long... srcs) {
        LongEs es = new LongEs();
        es.add_long(srcs);
        return es;
    }

    public static LongEs longs(List<Long> srcs) {
        return new LongEs(srcs);
    }

    public static LongEs longs(Set<Long> srcs) {
        return es(LongEs.class, srcs);
    }

    public static LongEs longs(LongEs srcs) {
        LongEs es = new LongEs();
        es.add(srcs);
        return es;
    }

    /**************************************************
     *
     **************************************************/
    public static <K, V> BaseMap<K, V> maps(Map<K, V> map) {
        return new BaseMap(map);
    }


    public static <TARGET> BaseEs<TARGET> convertList(BaseEs<List<TARGET>> es) {
        return es.convertList(new Convert<List<TARGET>, List<TARGET>>() {

            @Override
            public List<TARGET> convert(int index, List<TARGET> targets) {
                if (!CountTool.isNull(targets)) {
                    return targets;
                }
                return null;
            }
        });
    }

}
