package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun4;

public class TaskParam4<P0, P1, P2, P3> extends TaskParam<P0, P1, P2, P3, Object, Object, Object, Object, Object> {

    public TaskParam4(P0 p0, P1 p1, P2 p2, P3 p3) {
        paramEs.add(p0, p1, p2, p3);
    }

    public TaskRun4<P0, P1, P2, P3> run(TaskRun4<P0, P1, P2, P3> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
