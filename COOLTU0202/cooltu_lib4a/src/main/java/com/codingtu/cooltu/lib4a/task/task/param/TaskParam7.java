package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun7;

public class TaskParam7<P0, P1, P2, P3, P4, P5, P6> extends TaskParam<P0, P1, P2, P3, P4, P5, P6, Object, Object> {

    public TaskParam7(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
        paramEs.add(p0, p1, p2, p3, p4, p5, p6);
    }

    public TaskRun7<P0, P1, P2, P3, P4, P5, P6> run(TaskRun7<P0, P1, P2, P3, P4, P5, P6> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
