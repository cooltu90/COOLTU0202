package com.codingtu.cooltu.lib4a.task.task.result;

public abstract class TaskResult5<R0, R1, R2, R3, R4> extends TaskResult9<R0, R1, R2, R3, R4, Object, Object, Object, Object> {

    @Override
    public final void result(R0 r0, R1 r1, R2 r2, R3 r3, R4 r4, Object o0, Object o1, Object o2, Object o3) {
        result(r0, r1, r2, r3, r4);
    }

    public abstract void result(R0 r0, R1 r1, R2 r2, R3 r3, R4 r4);
}
