package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun6;

public class TaskParam6<P0, P1, P2, P3, P4, P5> extends TaskParam<P0, P1, P2, P3, P4, P5, Object, Object, Object> {

    public TaskParam6(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        paramEs.add(p0, p1, p2, p3, p4, p5);
    }

    public TaskRun6<P0, P1, P2, P3, P4, P5> run(TaskRun6<P0, P1, P2, P3, P4, P5> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
