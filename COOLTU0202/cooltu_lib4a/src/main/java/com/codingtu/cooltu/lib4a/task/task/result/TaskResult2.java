package com.codingtu.cooltu.lib4a.task.task.result;

public abstract class TaskResult2<R0, R1> extends TaskResult9<R0, R1, Object, Object, Object, Object, Object, Object, Object> {

    @Override
    public final void result(R0 r0, R1 r1, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
        result(r0, r1);
    }

    public abstract void result(R0 r0, R1 r1);
}
