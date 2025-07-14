package com.codingtu.cooltu.lib4a.task.task.result;

public abstract class TaskResult3<R0, R1, R2> extends TaskResult9<R0, R1, R2, Object, Object, Object, Object, Object, Object> {

    @Override
    public final void result(R0 r0, R1 r1, R2 r2, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5) {
        result(r0, r1, r2);
    }

    public abstract void result(R0 r0, R1 r1, R2 r2);
}
