package com.codingtu.cooltu.lib4a.tool;

import android.view.View;

/**************************************************
 *
 * View的Padding操作
 *
 **************************************************/
public class Paddings {

    //获取padding

    public static int l(View v) {
        if (v == null)
            return -1;
        return v.getPaddingLeft();
    }

    public static int t(View v) {
        if (v == null)
            return -1;
        return v.getPaddingTop();
    }

    public static int r(View v) {
        if (v == null)
            return -1;
        return v.getPaddingRight();
    }

    public static int b(View v) {
        if (v == null)
            return -1;
        return v.getPaddingBottom();
    }

    //设置padding基础方法
    private static void p(View v, int l, int t, int r, int b) {
        if (v == null)
            return;
        v.setPadding(l, t, r, b);
    }

    private static int px(View v, int dp) {
        return MobileTool.dpToPx(v.getContext(), dp);
    }

    //设置padding，单位px
    public static void l(View v, int l) {
        p(v, l, t(v), r(v), b(v));
    }

    public static void lt(View v, int l, int t) {
        p(v, l, t, r(v), b(v));
    }

    public static void ltr(View v, int l, int t, int r) {
        p(v, l, t, r, b(v));
    }

    public static void ltrb(View v, int l, int t, int r, int b) {
        p(v, l, t, r, b);
    }

    public static void ltb(View v, int l, int t, int b) {
        p(v, l, t, r(v), b);
    }

    public static void lr(View v, int l, int r) {
        p(v, l, t(v), r, b(v));
    }

    public static void lrb(View v, int l, int r, int b) {
        p(v, l, t(v), r, b);
    }

    public static void lb(View v, int l, int b) {
        p(v, l, t(v), r(v), b);
    }

    public static void t(View v, int t) {
        p(v, l(v), t, r(v), b(v));
    }

    public static void tr(View v, int t, int r) {
        p(v, l(v), t, r, b(v));
    }

    public static void trb(View v, int t, int r, int b) {
        p(v, l(v), t, r, b);
    }

    public static void tb(View v, int t, int b) {
        p(v, l(v), t, r(v), b);
    }

    public static void r(View v, int r) {
        p(v, l(v), t(v), r, b(v));
    }

    public static void rb(View v, int r, int b) {
        p(v, l(v), t(v), r, b);
    }

    public static void b(View v, int b) {
        p(v, l(v), t(v), r(v), b);
    }

    //设置padding，单位dp

    public static void l_dp(View v, int l) {
        p(v, px(v, l), t(v), r(v), b(v));
    }

    public static void lt_dp(View v, int l, int t) {
        p(v, px(v, l), px(v, t), r(v), b(v));
    }

    public static void ltr_dp(View v, int l, int t, int r) {
        p(v, px(v, l), px(v, t), px(v, r), b(v));
    }

    public static void ltrb_dp(View v, int l, int t, int r, int b) {
        p(v, px(v, l), px(v, t), px(v, r), px(v, b));
    }

    public static void ltb_dp(View v, int l, int t, int b) {
        p(v, px(v, l), px(v, t), r(v), px(v, b));
    }

    public static void lr_dp(View v, int l, int r) {
        p(v, px(v, l), t(v), px(v, r), b(v));
    }

    public static void lrb_dp(View v, int l, int r, int b) {
        p(v, px(v, l), t(v), px(v, r), px(v, b));
    }

    public static void lb_dp(View v, int l, int b) {
        p(v, px(v, l), t(v), r(v), px(v, b));
    }

    public static void t_dp(View v, int t) {
        p(v, l(v), px(v, t), r(v), b(v));
    }

    public static void tr_dp(View v, int t, int r) {
        p(v, l(v), px(v, t), px(v, r), b(v));
    }

    public static void trb_dp(View v, int t, int r, int b) {
        p(v, l(v), px(v, t), px(v, r), px(v, b));
    }

    public static void tb_dp(View v, int t, int b) {
        p(v, l(v), px(v, t), r(v), px(v, b));
    }

    public static void r_dp(View v, int r) {
        p(v, l(v), t(v), px(v, r), b(v));
    }

    public static void rb_dp(View v, int r, int b) {
        p(v, l(v), t(v), px(v, r), px(v, b));
    }

    public static void b_dp(View v, int b) {
        p(v, l(v), t(v), r(v), px(v, b));
    }

}
