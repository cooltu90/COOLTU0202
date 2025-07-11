package com.codingtu.cooltu.lib4j.callback;

public interface OnCallBack {

    public static interface P0 {
        void onCallBack();
    }

    public static interface P1<P0> {
        void onCallBack(P0 p0);
    }

    public static interface P2<P0, P1> {
        void onCallBack(P0 p0, P1 p1);
    }

    public static interface P3<P0, P1, P2> {
        void onCallBack(P0 p0, P1 p1, P2 p2);
    }

    public static interface P4<P0, P1, P2, P3> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3);
    }

    public static interface P5<P0, P1, P2, P3, P4> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    public static interface P6<P0, P1, P2, P3, P4, P5> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    public static interface P7<P0, P1, P2, P3, P4, P5, P6> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    public static interface P8<P0, P1, P2, P3, P4, P5, P6, P7> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }

    public static interface P9<P0, P1, P2, P3, P4, P5, P6, P7, P8> {
        void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);
    }

    public static interface PR0<R> {
        R onCallBack();
    }

    public static interface PR1<P0, R> {
        R onCallBack(P0 p0);
    }

    public static interface PR2<P0, P1, R> {
        R onCallBack(P0 p0, P1 p1);
    }

    public static interface PR3<P0, P1, P2, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2);
    }

    public static interface PR4<P0, P1, P2, P3, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3);
    }

    public static interface PR5<P0, P1, P2, P3, P4, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    public static interface PR6<P0, P1, P2, P3, P4, P5, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    public static interface PR7<P0, P1, P2, P3, P4, P5, P6, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    public static interface PR8<P0, P1, P2, P3, P4, P5, P6, P7, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }

    public static interface PR9<P0, P1, P2, P3, P4, P5, P6, P7, P8, R> {
        R onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);
    }

}
