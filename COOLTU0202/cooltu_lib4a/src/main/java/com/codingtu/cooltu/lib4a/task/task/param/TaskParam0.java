package com.codingtu.cooltu.lib4a.task.task.param;

import com.codingtu.cooltu.lib4a.task.task.run.TaskRun0;

public class TaskParam0 extends TaskParam<Object, Object, Object, Object, Object, Object, Object, Object, Object> {

    public TaskParam0() {
        paramEs.add();
    }

    public TaskRun0 run(TaskRun0 taskRun) {
        taskRun.param = this;
        return taskRun;
    }
}
