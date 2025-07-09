package com.codingtu.cooltu.ui.page;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.applib.ui.page.BaseWelcomeActivity;
import com.codingtu.cooltu.applib.data.User;
import com.codingtu.cooltu.processor.annotation.activity.ActBase;
import com.codingtu.cooltu.processor.annotation.base.BaseClass;
import com.codingtu.cooltu.processor.annotation.base.Genericity;
import com.codingtu.cooltu.processor.annotation.to.To;

import core.app.actbase.WelcomeActivityBase;
import core.app.actconfig.WelcomeActivityConfig;

@To(WelcomeActivityConfig.class)
@ActBase
@BaseClass(
        value = BaseWelcomeActivity.class,
        genericities = {
                @Genericity(name = "T", extendsClass = User.class),
                @Genericity(String.class),
                @Genericity(name = "F"),
        })
public class WelcomeActivity extends WelcomeActivityBase<User, User> {

}
