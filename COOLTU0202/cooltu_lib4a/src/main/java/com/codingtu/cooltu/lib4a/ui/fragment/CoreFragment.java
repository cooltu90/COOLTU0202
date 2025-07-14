package com.codingtu.cooltu.lib4a.ui.fragment;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.codingtu.cooltu.lib4a.tool.ToastTool;
import com.codingtu.cooltu.lib4a.uicore.CoreFragmentInterface;
import com.codingtu.cooltu.lib4j.destory.Destroys;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;
import com.codingtu.cooltu.lib4j.es.Es;

import java.util.ArrayList;
import java.util.List;

public class CoreFragment extends Fragment implements CoreFragmentInterface, Destroys {

    @Override
    public void toast(String msg) {
        ToastTool.toast(msg);
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void onCreateComplete() {

    }

    //每次回到页面
    @Override
    public void onResume() {
        super.onResume();
        onFragmentShow();
    }

    //每次回到页面
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onFragmentShow();
        }
    }

    public void onFragmentShow() {

    }

    /**************************************************
     *
     * OnDestroy
     *
     **************************************************/
    protected List<OnDestroy> onDestroys;

    public List<OnDestroy> getOnDestroys() {
        if (onDestroys == null)
            onDestroys = new ArrayList<OnDestroy>();
        return onDestroys;
    }

    @Override
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

}
