package com.codingtu.cooltu.lib4a.task.service;

import com.codingtu.cooltu.lib4a.task.task.ServiceTask;

public class TaskServiceBaseForMsThread<THIS extends TaskServiceBaseForMsThread> extends BaseTaskService<THIS> implements TaskServiceMsThreadInterface {

    protected TaskServiceMsThread taskServiceMsThread;

    public void start() {
        taskServiceMsThread = TaskServiceMsThread.obtain().dealer(this);
        taskServiceMsThread.start();
    }

    @Override
    public void mainSubThreadStart() {
    }

    protected boolean sendMessageForMainSubThreadStart() {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForMainSubThreadStart();
        }
        return true;
    }

    protected void sendMessageForMainSubThreadStartForce() {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForMainSubThreadStartForce();
        }
    }

    @Override
    public void backgroundSubThreadStart() {
    }

    protected boolean sendMessageForBackgroundSubThreadStart() {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForBackgroundSubThreadStart();
        }
        return true;
    }

    protected void sendMessageForBackgroundSubThreadStartForce() {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForBackgroundSubThreadStartForce();
        }
    }

    @Override
    public void dealMainSubThread() {
    }

    protected boolean sendMessageForDealMainSubThread() {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForDealMainSubThread();
        }
        return true;
    }

    protected void sendMessageForDealMainSubThreadForce() {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForDealMainSubThreadForce();
        }
    }

    @Override
    public void dealBackgroundSubThread() {
    }

    protected boolean sendMessageForDealBackgroundSubThread() {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForDealBackgroundSubThread();
        }
        return true;
    }

    protected void sendMessageForDealBackgroundSubThreadForce() {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForDealBackgroundSubThreadForce();
        }
    }

    @Override
    public void taskResult(ServiceTask task) {
    }

    protected boolean sendMessageForTaskResult(ServiceTask task) {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForTaskResult(task);
        }
        return true;
    }

    protected void sendMessageForTaskResultForce(ServiceTask task) {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForTaskResultForce(task);
        }
    }

    @Override
    public void taskError(ServiceTask task, Exception e) {
    }

    protected boolean sendMessageForTaskError(ServiceTask task, Exception e) {
        if (taskServiceMsThread != null) {
            return taskServiceMsThread.sendMessageForTaskError(task, e);
        }
        return true;
    }

    protected void sendMessageForTaskErrorForce(ServiceTask task, Exception e) {
        if (taskServiceMsThread != null) {
            taskServiceMsThread.sendMessageForTaskErrorForce(task, e);
        }
    }



    protected void stopMsThread() {
        if (taskServiceMsThread != null)
            taskServiceMsThread.stop();
        taskServiceMsThread = null;
    }

}
