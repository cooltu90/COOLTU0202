package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun3;

public class TaskParam3<P0, P1, P2> extends TaskParam<P0, P1, P2, Object, Object, Object, Object, Object, Object> {

    public TaskParam3(P0 p0, P1 p1, P2 p2) {
        paramEs.add(p0, p1, p2);
    }

    public TaskRun3<P0, P1, P2> run(TaskRun3<P0, P1, P2> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
