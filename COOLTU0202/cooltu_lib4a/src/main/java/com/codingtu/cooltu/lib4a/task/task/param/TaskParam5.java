package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun5;

public class TaskParam5<P0, P1, P2, P3, P4> extends TaskParam<P0, P1, P2, P3, P4, Object, Object, Object, Object> {

    public TaskParam5(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4) {
        paramEs.add(p0, p1, p2, p3, p4);
    }

    public TaskRun5<P0, P1, P2, P3, P4> run(TaskRun5<P0, P1, P2, P3, P4> taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
