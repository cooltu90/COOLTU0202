package com.codingtu.cooltu.lib4a.uicore;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.codingtu.cooltu.lib4a.permission.PermissionBack;
import com.codingtu.cooltu.lib4a.tool.ScreenAdaptationTool;
import com.codingtu.cooltu.lib4a.tool.StatusBarTool;
import com.codingtu.cooltu.lib4a.tool.ViewTool;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.Es;

import java.util.ArrayList;
import java.util.List;

public class CoreActBase {
    public void onCreate(CoreActInterface coreActInterface, PermissionBack back) {
        addPermissionBack(back);
        ScreenAdaptationTool.setCustomDensity(coreActInterface.getAct());
        coreActInterface.initStatusBar(coreActInterface.getAct());
        ActivityManager.getInstance().add(coreActInterface.getAct());
    }

    public void initStatusBar(Activity act) {
        StatusBarTool.translucentAndDark(act);
    }

    private ViewGroup rootView;
    public void setContentView(CoreActInterface coreActInterface) {
        rootView = ViewTool.getRootViewGroup(coreActInterface.getAct());
        rootView.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        coreActInterface.onViewInitComplete();
                        rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
    }

    ///////////////////////////////////////////////////////
    //
    // permissions
    //
    ///////////////////////////////////////////////////////
    protected List<PermissionBack> permissionBacks;

    public List<PermissionBack> getPermissionBacks() {
        if (permissionBacks == null)
            permissionBacks = new ArrayList<PermissionBack>();
        return permissionBacks;
    }

    public void addPermissionBack(PermissionBack back) {
        getPermissionBacks().add(back);

    }

    public void removePermissionBack(PermissionBack back) {
        getPermissionBacks().remove(back);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        Es.es(getPermissionBacks()).ls(new Es.EachEs<PermissionBack>() {
            @Override
            public boolean each(int position, PermissionBack back) {
                back.permissionBack(requestCode, permissions, grantResults);
                return false;
            }
        });
    }

    ///////////////////////////////////////////////////////
    //
    // OnDestroy
    //
    ///////////////////////////////////////////////////////
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    public void addOnDestroy(OnDestroy onDestroy) {
        getOnDestroys().add(onDestroy);
    }

    public void destroyAll() {
        Es.es(getOnDestroys()).ls(new Es.EachEs<OnDestroy>() {
            @Override
            public boolean each(int position, OnDestroy onDestroy) {
                onDestroy.onDestroy();
                return false;
            }
        });
    }

    public void onDestroy(CoreActInterface coreActInterface) {
        destroyAll();
        ActivityManager.getInstance().remove(coreActInterface.getAct());
    }

    ///////////////////////////////////////////////////////
    //
    // finish
    //
    ///////////////////////////////////////////////////////
    public void finish(CoreActInterface coreUi) {
        coreUi.beforeFinish();
        coreUi.superFinish();
        coreUi.setFinishAnimation();
        coreUi.afterFinish();
    }

    public void finishToNewPage(CoreActInterface coreUi) {
        coreUi.beforeFinish();
        coreUi.superFinish();
        coreUi.afterFinish();
    }
}
