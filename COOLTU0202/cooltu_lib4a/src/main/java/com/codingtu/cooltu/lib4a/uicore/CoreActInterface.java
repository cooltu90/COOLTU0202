package com.codingtu.cooltu.lib4a.uicore;

import android.app.Activity;
import android.content.Intent;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4j.destory.Destroys;

public interface CoreActInterface extends Destroys, PermissionBack {

    CoreActBase getBase();

    void setBase(CoreActBase base);

    Activity getAct();

    void initStatusBar(Activity act);

    void onCreateComplete();

    void onViewInitComplete();

    void beforeFinish();

    void superFinish();

    void setFinishAnimation();

    void afterFinish();

    void toast(String msg);

    void forbidKeyBack();

    void setResultOk();

    void setResultOk(Intent data);
}
