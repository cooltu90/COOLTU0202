package com.codingtu.cooltu.lib4a.task.task.run;


import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover2;

public abstract class TaskRun2<P0, P1> extends TaskRun<P0, P1, Object, Object, Object, Object, Object, Object, Object> {
    @Override
    public final void run(P0 p0, P1 p1, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
        run(p0, p1);
    }

    public abstract void run(P0 p0, P1 p1);
    public TaskRun2<P0, P1> cover(TaskCover2<P0, P1> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
