package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover4;

public abstract class TaskRun4<P0, P1, P2, P3> extends TaskRun<P0, P1, P2, P3, Object, Object, Object, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, P3 p3, Object o0, Object o1, Object o2, Object o3, Object o4) {
        run(p0, p1, p2, p3);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2, P3 p3);
    public TaskRun4<P0, P1, P2, P3> cover(TaskCover4<P0, P1, P2, P3> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
