package com.codingtu.cooltu.lib4a.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4j.callback.OnCallBack;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;

public class OnCallBackInUiThread {


    public static abstract class P0
            extends P<Object, Object, Object, Object, Object, Object, Object, Object, Object>
            implements OnCallBack.P0 {
        @Override
        protected void callBack(Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7, Object o8) {
            callBack();
        }

        protected abstract void callBack();

        @Override
        public void onCallBack() {
            sendMessage(null, null, null, null, null, null, null, null, null);
        }
    }

    public static abstract class P1<P0>
            extends P<P0, Object, Object, Object, Object, Object, Object, Object, Object>
            implements OnCallBack.P1<P0> {
        @Override
        protected void callBack(P0 p0, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6, Object o7) {
            callBack(p0);
        }

        protected abstract void callBack(P0 p0);

        @Override
        public void onCallBack(P0 p0) {
            sendMessage(p0, null, null, null, null, null, null, null, null);
        }
    }

    public static abstract class P2<P0, P1>
            extends P<P0, P1, Object, Object, Object, Object, Object, Object, Object>
            implements OnCallBack.P2<P0, P1> {
        @Override
        protected void callBack(P0 p0, P1 p1, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5, Object o6) {
            callBack(p0, p1);
        }

        protected abstract void callBack(P0 p0, P1 p1);

        @Override
        public void onCallBack(P0 p0, P1 p1) {
            sendMessage(p0, p1, null, null, null, null, null, null, null);
        }
    }

    public static abstract class P3<P0, P1, P2>
            extends P<P0, P1, P2, Object, Object, Object, Object, Object, Object>
            implements OnCallBack.P3<P0, P1, P2> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, Object o0, Object o1, Object o2, Object o3, Object o4, Object o5) {
            callBack(p0, p1, p2);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2) {
            sendMessage(p0, p1, p2, null, null, null, null, null, null);
        }
    }

    public static abstract class P4<P0, P1, P2, P3>
            extends P<P0, P1, P2, P3, Object, Object, Object, Object, Object>
            implements OnCallBack.P4<P0, P1, P2, P3> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, P3 p3, Object o0, Object o1, Object o2, Object o3, Object o4) {
            callBack(p0, p1, p2, p3);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3) {
            sendMessage(p0, p1, p2, p3, null, null, null, null, null);
        }
    }

    public static abstract class P5<P0, P1, P2, P3, P4>
            extends P<P0, P1, P2, P3, P4, Object, Object, Object, Object>
            implements OnCallBack.P5<P0, P1, P2, P3, P4> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, Object o0, Object o1, Object o2, Object o3) {
            callBack(p0, p1, p2, p3, p4);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4) {
            sendMessage(p0, p1, p2, p3, p4, null, null, null, null);
        }
    }

    public static abstract class P6<P0, P1, P2, P3, P4, P5>
            extends P<P0, P1, P2, P3, P4, P5, Object, Object, Object>
            implements OnCallBack.P6<P0, P1, P2, P3, P4, P5> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, Object o0, Object o1, Object o2) {
            callBack(p0, p1, p2, p3, p4, p5);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
            sendMessage(p0, p1, p2, p3, p4, p5, null, null, null);
        }
    }

    public static abstract class P7<P0, P1, P2, P3, P4, P5, P6>
            extends P<P0, P1, P2, P3, P4, P5, P6, Object, Object>
            implements OnCallBack.P7<P0, P1, P2, P3, P4, P5, P6> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, Object o0, Object o1) {
            callBack(p0, p1, p2, p3, p4, p5, p6);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) {
            sendMessage(p0, p1, p2, p3, p4, p5, p6, null, null);
        }
    }

    public static abstract class P8<P0, P1, P2, P3, P4, P5, P6, P7>
            extends P<P0, P1, P2, P3, P4, P5, P6, P7, Object>
            implements OnCallBack.P8<P0, P1, P2, P3, P4, P5, P6, P7> {
        @Override
        protected void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, Object o0) {
            callBack(p0, p1, p2, p3, p4, p5, p6, p7);
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) {
            sendMessage(p0, p1, p2, p3, p4, p5, p6, p7, null);
        }
    }

    public static abstract class P9<P0, P1, P2, P3, P4, P5, P6, P7, P8>
            extends P<P0, P1, P2, P3, P4, P5, P6, P7, P8>
            implements OnCallBack.P9<P0, P1, P2, P3, P4, P5, P6, P7, P8> {

        @Override
        public void onCallBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) {
            sendMessage(p0, p1, p2, p3, p4, p5, p6, p7, p8);
        }
    }

    public static abstract class P<P0, P1, P2, P3, P4, P5, P6, P7, P8> extends Handler {

        public P() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            BaseEs<Object> es = (BaseEs<Object>) msg.obj;
            callBack(
                    (P0) es.getByIndex(0),
                    (P1) es.getByIndex(1),
                    (P2) es.getByIndex(2),
                    (P3) es.getByIndex(3),
                    (P4) es.getByIndex(4),
                    (P5) es.getByIndex(5),
                    (P6) es.getByIndex(6),
                    (P7) es.getByIndex(7),
                    (P8) es.getByIndex(8)
            );
        }

        protected abstract void callBack(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);

        protected void sendMessage(P0 p0, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8) {
            Message msg = Message.obtain();
            msg.obj = Es.es(p0, p1, p2, p3, p4, p5, p6, p7, p8);
            sendMessage(msg);
        }
    }

}
