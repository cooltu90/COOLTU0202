package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun2;

public class TaskParam2<P0, P1> extends TaskParam<P0, P1, Object, Object, Object, Object, Object, Object, Object> {

    public TaskParam2(P0 p0, P1 p1) {
        paramEs.add(p0, p1);
    }

    public TaskRun2<P0, P1> run(TaskRun2<P0, P1> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
