package com.codingtu.cooltu.applib.ui.page;

import com.codingtu.cooltu.applib.data.User;
import com.codingtu.cooltu.applib.ui.page.base.BaseActivityInAppLib;
import com.codingtu.cooltu.processor.annotation.activity.ActBase;
import com.codingtu.cooltu.processor.annotation.base.BaseClass;
import com.codingtu.cooltu.processor.annotation.to.To;

import core.applib.actbase.BaseWelcomeActivityBase;
import core.applib.actconfig.BaseWelcomeActivityConfig;

@To(BaseWelcomeActivityConfig.class)
@ActBase
@BaseClass(BaseActivityInAppLib.class)
public class BaseWelcomeActivity<T extends User, E, F> extends BaseWelcomeActivityBase {
    private T t;
    private E e;
    private F f;

    protected T t() {
        return t;
    }

    protected E e() {
        return e;
    }

    protected F f() {
        return f;
    }

    protected void t(T t) {
        this.t = t;
    }

    protected void e(E e) {
        this.e = e;
    }

    protected void f(F f) {
        this.f = f;
    }
}
