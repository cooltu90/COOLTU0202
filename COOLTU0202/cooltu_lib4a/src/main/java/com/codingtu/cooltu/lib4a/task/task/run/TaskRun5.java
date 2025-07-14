package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover5;

public abstract class TaskRun5<P0, P1, P2, P3, P4> extends TaskRun<P0, P1, P2, P3, P4, Object, Object, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, Object o0, Object o1, Object o2, Object o3) {
        run(p0, p1, p2, p3, p4);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4);
    public TaskRun5<P0, P1, P2, P3, P4> cover(TaskCover5<P0, P1, P2, P3, P4> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
