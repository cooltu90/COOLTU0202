package com.codingtu.cooltu.lib4a.task.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.codingtu.cooltu.lib4a.msthread.CoreMultiMsThread;
import com.codingtu.cooltu.lib4a.task.task.ServiceTask;

public class TaskServiceMsThread extends CoreMultiMsThread {

    ///////////////////////////////////////////////////////
    //
    // 创建方法
    //
    ///////////////////////////////////////////////////////
    private Handler mainHandler;
    private Handler subHandler0;
    private Handler subHandler1;

    public void start() {
        createMainHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler0();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                createSubHandler1();
            }
        }).start();

    }

    private void createMainHandler() {
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInMain(msg);
            }
        };
    }

    private void createSubHandler0() {
        Looper.prepare();
        subHandler0 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread0(msg);
            }
        };
        sendMessage(subHandler0, subThread0StartType(), 0l);
        Looper.loop();
    }

    private void createSubHandler1() {
        Looper.prepare();
        subHandler1 = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMessageInThread1(msg);
            }
        };
        sendMessage(subHandler1, subThread1StartType(), 0l);
        Looper.loop();
    }

    public void stop() {
        if (subHandler0 != null) {
            subHandler0.getLooper().quitSafely();
            subHandler0 = null;
        }
        mainHandler = null;

        if (subHandler0 != null) {
            subHandler0.getLooper().quitSafely();
            subHandler0 = null;
        }
        mainHandler = null;


    }
    ///////////////////////////////////////////////////////
    //
    // 初始化方法
    //
    ///////////////////////////////////////////////////////
    private TaskServiceMsThreadInterface dealer;

    public static TaskServiceMsThread obtain() {
        return new TaskServiceMsThread();
    }

    public TaskServiceMsThread dealer(TaskServiceMsThreadInterface dealer) {
        this.dealer = dealer;
        return this;
    }

    private int type(TaskServiceMsThreadType type) {
        return type.ordinal();
    }

    protected boolean isSubThread0() {
        return Thread.currentThread() == subHandler0.getLooper().getThread();
    }

    protected boolean isSubThread1() {
        return Thread.currentThread() == subHandler1.getLooper().getThread();
    }


    ///////////////////////////////////////////////////////
    //
    // 主线程的消息处理
    //
    ///////////////////////////////////////////////////////
    private void handleMessageInMain(Message msg) {
        if (msg.what == type(TaskServiceMsThreadType.TASK_RESULT_0)) {
            dealer.taskResult((ServiceTask) msg.obj);
            return;
        }
        if (msg.what == type(TaskServiceMsThreadType.TASK_ERROR_0)) {
            Object[] objects = (Object[]) msg.obj;
            dealer.taskError((ServiceTask) objects[0], (Exception) objects[1]);
            return;
        }

    }

    public boolean sendMessageForTaskResult(ServiceTask task) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TaskServiceMsThreadType.TASK_RESULT_0), 0l, task);
            return true;
        }
        return false;
    }

    public void sendMessageForTaskResultForce(ServiceTask task) {
        sendMessage(mainHandler, type(TaskServiceMsThreadType.TASK_RESULT_0), 0l, task);
    }

    public boolean sendMessageForTaskError(ServiceTask task, Exception e) {
        if (!isMainThread()) {
            sendMessage(mainHandler, type(TaskServiceMsThreadType.TASK_ERROR_0), 0l, task, e);
            return true;
        }
        return false;
    }

    public void sendMessageForTaskErrorForce(ServiceTask task, Exception e) {
        sendMessage(mainHandler, type(TaskServiceMsThreadType.TASK_ERROR_0), 0l, task, e);
    }


    ///////////////////////////////////////////////////////
    //
    // 线程0的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread0StartType() {
        return type(TaskServiceMsThreadType.MAIN_SUB_THREAD_START_0);
    }

    private void handleMessageInThread0(Message msg) {
        if (msg.what == type(TaskServiceMsThreadType.DEAL_MAIN_SUB_THREAD_0)) {
            dealer.dealMainSubThread();
            return;
        }
        if (msg.what == type(TaskServiceMsThreadType.MAIN_SUB_THREAD_START_0)) {
            dealer.mainSubThreadStart();
            return;
        }
    }

    public boolean sendMessageForDealMainSubThread() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(TaskServiceMsThreadType.DEAL_MAIN_SUB_THREAD_0), 0l);
            return true;
        }
        return false;
    }

    public void sendMessageForDealMainSubThreadForce() {
        sendMessage(subHandler0, type(TaskServiceMsThreadType.DEAL_MAIN_SUB_THREAD_0), 0l);
    }

    public boolean sendMessageForMainSubThreadStart() {
        if (!isSubThread0()) {
            sendMessage(subHandler0, type(TaskServiceMsThreadType.MAIN_SUB_THREAD_START_0), 0l);
            return true;
        }
        return false;
    }

    public void sendMessageForMainSubThreadStartForce() {
        sendMessage(subHandler0, type(TaskServiceMsThreadType.MAIN_SUB_THREAD_START_0), 0l);
    }


    ///////////////////////////////////////////////////////
    //
    // 线程1的消息处理
    //
    ///////////////////////////////////////////////////////
    private int subThread1StartType() {
        return type(TaskServiceMsThreadType.BACKGROUND_SUB_THREAD_START_0);
    }

    private void handleMessageInThread1(Message msg) {
        if (msg.what == type(TaskServiceMsThreadType.DEAL_BACKGROUND_SUB_THREAD_0)) {
            dealer.dealBackgroundSubThread();
            return;
        }
        if (msg.what == type(TaskServiceMsThreadType.BACKGROUND_SUB_THREAD_START_0)) {
            dealer.backgroundSubThreadStart();
            return;
        }
    }

    public boolean sendMessageForDealBackgroundSubThread() {
        if (!isSubThread1()) {
            sendMessage(subHandler1, type(TaskServiceMsThreadType.DEAL_BACKGROUND_SUB_THREAD_0), 0l);
            return true;
        }
        return false;
    }

    public void sendMessageForDealBackgroundSubThreadForce() {
        sendMessage(subHandler1, type(TaskServiceMsThreadType.DEAL_BACKGROUND_SUB_THREAD_0), 0l);
    }

    public boolean sendMessageForBackgroundSubThreadStart() {
        if (!isSubThread1()) {
            sendMessage(subHandler1, type(TaskServiceMsThreadType.BACKGROUND_SUB_THREAD_START_0), 0l);
            return true;
        }
        return false;
    }

    public void sendMessageForBackgroundSubThreadStartForce() {
        sendMessage(subHandler1, type(TaskServiceMsThreadType.BACKGROUND_SUB_THREAD_START_0), 0l);
    }


}

