package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover1;

public abstract class TaskRun1<P0> extends TaskRun<P0, Object, Object, Object, Object, Object, Object, Object, Object> {
    @Override
    public final void run(P0 p0, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
        run(p0);
    }

    public abstract void run(P0 p0);
    public TaskRun1<P0> cover(TaskCover1<P0> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
