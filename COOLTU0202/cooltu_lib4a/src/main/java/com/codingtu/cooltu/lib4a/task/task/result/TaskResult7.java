package com.codingtu.cooltu.lib4a.task.task.result;

public abstract class TaskResult7<R0, R1, R2, R3, R4, R5, R6> extends TaskResult9<R0, R1, R2, R3, R4, R5, R6, Object, Object> {

    @Override
    public final void result(R0 r0, R1 r1, R2 r2, R3 r3, R4 r4, R5 r5, R6 r6, Object o0, Object o1) {
        result(r0, r1, r2, r3, r4, r5, r6);
    }

    public abstract void result(R0 r0, R1 r1, R2 r2, R3 r3, R4 r4, R5 r5, R6 r6);
}
