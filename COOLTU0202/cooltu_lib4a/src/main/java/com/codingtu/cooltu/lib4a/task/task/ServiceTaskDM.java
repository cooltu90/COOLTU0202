package com.codingtu.cooltu.lib4a.task.task;

import com.codingtu.cooltu.lib4a.task.task.param.TaskParam0;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam1;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam2;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam3;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam4;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam5;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam6;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam7;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam8;
import com.codingtu.cooltu.lib4a.task.task.param.TaskParam9;

public class ServiceTaskDM {

    public static TaskParam0 task() {
        TaskParam0 taskParam = new TaskParam0();
        return taskParam;
    }

    public static <P0> TaskParam1<P0> task(P0 p0) {
        TaskParam1<P0> taskParam = new TaskParam1<>(p0);
        return taskParam;
    }

    public static <P0, P1> TaskParam2<P0, P1> task(P0 p0, P1 p1) {
        TaskParam2<P0, P1> taskParam = new TaskParam2<>(p0, p1);
        return taskParam;
    }

    public static <P0, P1, P2> TaskParam3<P0, P1, P2> task(P0 p0, P1 p1, P2 p2) {
        TaskParam3<P0, P1, P2> taskParam = new TaskParam3<>(p0, p1, p2);
        return taskParam;
    }

    public static <P0, P1, P2, P3> TaskParam4<P0, P1, P2, P3> task(P0 p0, P1 p1, P2 p2, P3 p3) {
        TaskParam4<P0, P1, P2, P3> taskParam = new TaskParam4<>(p0, p1, p2, p3);
        return taskParam;
    }

    public static <P0, P1, P2, P3, P4> TaskParam5<P0, P1, P2, P3, P4> task(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4) {
        TaskParam5<P0, P1, P2, P3, P4> taskParam = new TaskParam5<>(p0, p1, p2, p3, p4);
        return taskParam;
    }

    public static <P0, P1, P2, P3, P4, P5> TaskParam6<P0, P1, P2, P3, P4, P5> task(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        TaskParam6<P0, P1, P2, P3, P4, P5> taskParam = new TaskParam6<>(p0, p1, p2, p3, p4, p5);
        return taskParam;
    }

    public static <P0, P1, P2, P3, P4, P5, P6> TaskParam7<P0, P1, P2, P3, P4, P5, P6> task(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
        TaskParam7<P0, P1, P2, P3, P4, P5, P6> taskParam = new TaskParam7<>(p0, p1, p2, p3, p4, p5, p6);
        return taskParam;
    }

    public static <P0, P1, P2, P3, P4, P5, P6, P7> TaskParam8<P0, P1, P2, P3, P4, P5, P6, P7> task(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) {
        TaskParam8<P0, P1, P2, P3, P4, P5, P6, P7> taskParam = new TaskParam8<>(p0, p1, p2, p3, p4, p5, p6, p7);
        return taskParam;
    }

    public static <P0, P1, P2, P3, P4, P5, P6, P7, P8> TaskParam9<P0, P1, P2, P3, P4, P5, P6, P7, P8> task(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) {
        TaskParam9<P0, P1, P2, P3, P4, P5, P6, P7, P8> taskParam = new TaskParam9<>(p0, p1, p2, p3, p4, p5, p6, p7, p8);
        return taskParam;
    }
}
