package com.codingtu.cooltu.lib4j.callback.base;

import java.util.Objects;

@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    @SuppressWarnings("unchecked")
    static <T> Predicate<T> not(Predicate<? super T> target) {
        Objects.requireNonNull(target);
        return (Predicate<T>) target.negate();
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? object -> Objects.isNull(object)
                : object -> targetRef.equals(object);
    }
}
