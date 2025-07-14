package com.codingtu.cooltu.lib4a.task.task;

import com.codingtu.cooltu.lib4a.task.service.TaskService;
import com.codingtu.cooltu.lib4a.task.task.result.TaskResult9;
import com.codingtu.cooltu.lib4a.task.task.run.TaskRun;
import com.codingtu.cooltu.lib4j.callback.OnCallBack;


public class ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, R0, R1, R2, R3, R4, R5, R6, R7, R8> {
    public int id;
    public int type;
    public boolean isBackgroundSub;
    public TaskRun<P0, P1, P2, P3, P4, P5, P6, P7, P8> taskRun;
    public TaskResult9<R0, R1, R2, R3, R4, R5, R6, R7, R8> taskResult;
    public OnCallBack.P1<Throwable> error;

    public ServiceTask(TaskRun<P0, P1, P2, P3, P4, P5, P6, P7, P8> taskRun, TaskResult9<R0, R1, R2, R3, R4, R5, R6, R7, R8> taskResult) {
        this.id = TaskService.obtainAppTaskId();
        this.taskRun = taskRun;
        this.taskResult = taskResult;
    }

    public ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, R0, R1, R2, R3, R4, R5, R6, R7, R8> type(int type) {
        this.type = type;
        return this;
    }

    public ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, R0, R1, R2, R3, R4, R5, R6, R7, R8> error(OnCallBack.P1<Throwable> onError) {
        this.error = onError;
        return this;
    }

    public ServiceTask<P0, P1, P2, P3, P4, P5, P6, P7, P8, R0, R1, R2, R3, R4, R5, R6, R7, R8> background() {
        this.isBackgroundSub = true;
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////

    public Object p(int index) {
        if (this.taskRun.param != null) {
            return this.taskRun.param.paramEs.getByIndex(index);
        }
        return null;
    }

    public P0 p0() {
        return (P0) p(0);
    }

    public P1 p1() {
        return (P1) p(1);
    }

    public P2 p2() {
        return (P2) p(2);
    }

    public P3 p3() {
        return (P3) p(3);
    }

    public P4 p4() {
        return (P4) p(4);
    }

    public P5 p5() {
        return (P5) p(5);
    }

    public P6 p6() {
        return (P6) p(6);
    }

    public P7 p7() {
        return (P7) p(7);
    }

    public P8 p8() {
        return (P8) p(8);
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////

    public Object r(int index) {
        if (this.taskRun.resultEs != null) {
            return this.taskRun.resultEs.getByIndex(index);
        }
        return null;
    }

    public R0 r0() {
        return (R0) r(0);
    }

    public R1 r1() {
        return (R1) r(1);
    }

    public R2 r2() {
        return (R2) r(2);
    }

    public R3 r3() {
        return (R3) r(3);
    }

    public R4 r4() {
        return (R4) r(4);
    }

    public R5 r5() {
        return (R5) r(5);
    }

    public R6 r6() {
        return (R6) r(6);
    }

    public R7 r7() {
        return (R7) r(7);
    }

    public R8 r8() {
        return (R8) r(8);
    }

    public void add() {
        TaskService.addTask(this);
    }
}
