package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover7;

public abstract class TaskRun7<P0, P1, P2, P3, P4, P5, P6> extends TaskRun<P0, P1, P2, P3, P4, P5, P6, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, Object o0, Object o1) {
        run(p0, p1, p2, p3, p4, p5, p6);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    public TaskRun7<P0, P1, P2, P3, P4, P5, P6> cover(TaskCover7<P0, P1, P2, P3, P4, P5, P6> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
