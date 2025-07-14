package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.ServiceTask;
import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam;
import com.codingtu.cooltu.lib4a.task.task.result.TaskResult9;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;

public abstract class TaskRun<P0, P1, P2, P3, P4, P5, P6, P7, P8> {

    public TaskParam<P0, P1, P2, P3, P4, P5, P6, P7, P8> param;
    public BaseEs resultEs = Es.es();
    public TaskCover<P0, P1, P2, P3, P4, P5, P6, P7, P8> taskCover;

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);

    public <R0, R1, R2, R3, R4, R5, R6, R7, R8> ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, R0, R1, R2, R3, R4, R5, R6, R7, R8> result(TaskResult9<R0, R1, R2, R3, R4, R5, R6, R7, R8> taskResult) {
        return new ServiceTask<>(this, taskResult);
    }

    public ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, Object, Object, Object, Object, Object, Object, Object, Object, Object> result() {
        return new ServiceTask<>(this, null);
    }

    protected void addResult(Object... objs) {
        resultEs.add(objs);
    }
}
