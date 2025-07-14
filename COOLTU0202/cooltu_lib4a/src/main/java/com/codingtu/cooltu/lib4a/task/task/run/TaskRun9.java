package com.codingtu.cooltu.lib4a.task.task.run;

import com.codingtu.cooltu.lib4a.task.task.cover.TaskCover9;

public abstract class TaskRun9<P0, P1, P2, P3, P4, P5, P6, P7, P8> extends TaskRun<P0, P1, P2, P3, P4, P5, P6, P7, P8> {
    public TaskRun9<P0, P1, P2, P3, P4, P5, P6, P7, P8> cover(TaskCover9<P0, P1, P2, P3, P4, P5, P6, P7, P8> taskCover) {
        this.taskCover = taskCover;
        return this;
    }
}
