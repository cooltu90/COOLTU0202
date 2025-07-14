package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover3;

public abstract class TaskRun3<P0, P1, P2> extends TaskRun<P0, P1, P2, Object, Object, Object, Object, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, P2 p2, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5) {
        run(p0, p1, p2);
    }

    public abstract void run(P0 p0, P1 p1, P2 p2);
    public TaskRun3<P0, P1, P2> cover(TaskCover3<P0, P1, P2> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
