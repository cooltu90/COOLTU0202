package com.codingtu.cooltu.lib4a.task.service;

import com.codingtu.cooltu.lib4a.task.task.ServiceTask;

public interface TaskServiceMsThreadInterface {

    void mainSubThreadStart();

    void backgroundSubThreadStart();

    void dealMainSubThread();

    void dealBackgroundSubThread();

    void taskResult(ServiceTask task);

    void taskError(ServiceTask task, Exception e);

}
