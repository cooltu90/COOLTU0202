package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun1;

public class TaskParam1<P0> extends TaskParam<P0, Object, Object, Object, Object, Object, Object, Object, Object> {

    public TaskParam1(P0 p0) {
        paramEs.add(p0);
    }

    public TaskRun1<P0> run(TaskRun1<P0> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
