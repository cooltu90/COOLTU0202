package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover8;

public abstract class TaskRun8<P0, P1, P2, P3, P4, P5, P6, P7> extends TaskRun<P0, P1, P2, P3, P4, P5, P6, P7, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, Object o0) {
        run(p0, p1, p2, p3, p4, p5, p6, p7);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    public TaskRun8<P0, P1, P2, P3, P4, P5, P6, P7> cover(TaskCover8<P0, P1, P2, P3, P4, P5, P6, P7> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
