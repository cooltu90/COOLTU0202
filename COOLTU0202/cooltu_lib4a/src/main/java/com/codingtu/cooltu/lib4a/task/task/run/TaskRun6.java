package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover6;

public abstract class TaskRun6<P0, P1, P2, P3, P4, P5> extends TaskRun<P0, P1, P2, P3, P4, P5, Object, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, Object o0, Object o1, Object o2) {
        run(p0, p1, p2, p3, p4, p5);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    public TaskRun6<P0, P1, P2, P3, P4, P5> cover(TaskCover6<P0, P1, P2, P3, P4, P5> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
