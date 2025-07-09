package com.codingtu.cooltu.lib4a.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codingtu.cooltu.lib4a.tool.ActTool;
import com.codingtu.cooltu.lib4a.uicore.CoreActBase;
import com.codingtu.cooltu.lib4a.uicore.CoreActInterface;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class CoreActivity extends AppCompatActivity implements CoreActInterface {

    ///////////////////////////////////////////////////////
    //
    // base创建获取
    //
    ///////////////////////////////////////////////////////

    protected CoreActBase BASE;

    @Override
    public CoreActBase getBase() {
        return this.BASE;
    }

    @Override
    public void setBase(CoreActBase base) {
        this.BASE = base;
    }

    ///////////////////////////////////////////////////////
    //
    // 获取act
    //
    ///////////////////////////////////////////////////////
    @Override
    public Activity getAct() {
        return this;
    }

    ///////////////////////////////////////////////////////
    //
    // StatusBar
    //
    ///////////////////////////////////////////////////////
    @Override
    public void initStatusBar(Activity act) {
        getBase().initStatusBar(act);
    }

    ///////////////////////////////////////////////////////
    //
    // create
    //
    ///////////////////////////////////////////////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBase(new CoreActBase());
        getBase().onCreate(this, this);
    }

    @Override
    public void onCreateComplete() {

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getBase().setContentView(this);
    }

    @Override
    public void onViewInitComplete() {

    }

    ///////////////////////////////////////////////////////
    //
    //  Destroy
    //
    ///////////////////////////////////////////////////////
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBase().onDestroy(this);
    }


    @Override
    public void addOnDestory(OnDestroy onDestroy) {
        getBase().addOnDestroy(onDestroy);
    }

    @Override
    public void destroyAll() {
        getBase().destroyAll();
    }

    ///////////////////////////////////////////////////////
    //
    // permission
    //
    ///////////////////////////////////////////////////////
    @Override
    public void permissionBack(int requestCode, String[] permissions, int[] grantResults) {

    }

    ///////////////////////////////////////////////////////
    //
    // 对finish方法做扩展
    //
    ///////////////////////////////////////////////////////
    @Override
    public void finish() {
        getBase().finish(this);
    }

    protected void finishToNewPage() {
        getBase().finishToNewPage(this);
    }

    //finish之前调用
    @Override
    public void beforeFinish() {

    }

    @Override
    public void superFinish() {
        super.finish();
    }

    //设置finish动画
    @Override
    public void setFinishAnimation() {
        ActTool.actRightOut(this);
    }

    //finish之后调用
    @Override
    public void afterFinish() {

    }

    ///////////////////////////////////////////////////////
    //
    // toast
    //
    ///////////////////////////////////////////////////////

    @Override
    public void toast(String str) {
        getBase().toast(str);
    }

    ///////////////////////////////////////////////////////
    //
    // whenkeydown
    //
    ///////////////////////////////////////////////////////
    @Override
    public void forbidKeyBack() {
        getBase().forbidKeyBack();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean b = getBase().onKeyDown(keyCode, event);
        return b ? b : super.onKeyDown(keyCode, event);
    }

    ///////////////////////////////////////////////////////
    //
    // actback
    //
    ///////////////////////////////////////////////////////
    @Override
    public void setResultOk() {
        getBase().setResultOk(this);
    }

    @Override
    public void setResultOk(Intent data) {
        getBase().setResultOk(this, data);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getBase().onActivityResult(requestCode, resultCode, data);
    }


}
