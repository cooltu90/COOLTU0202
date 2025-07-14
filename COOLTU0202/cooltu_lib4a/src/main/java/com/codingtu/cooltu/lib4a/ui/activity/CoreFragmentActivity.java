package com.codingtu.cooltu.lib4a.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup0;

public abstract class CoreFragmentActivity extends CoreActivity {

    protected Fragment currentFragment;
    protected RadioGroup0 radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        radioGroup = RadioGroup.setViewItems(this,getBts())
                .setOnSetItem(new RadioGroup.OnSetItem() {
                    @Override
                    public void setSelected(View view) {
                        setTagSelected(view);
                        showFragment(view.getId());
                    }

                    @Override
                    public void setSelectno(View view) {
                        setTagSelectno(view);
                    }
                }).setSelected(0);
    }

    protected void showFragment(int tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(getFragmentTagName(tag));
        if (fragment != null) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.core_containerView, fragment = initFragment(tag), getFragmentTagName(tag));
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        currentFragment = fragment;

        transaction.commitNow();
    }

    protected abstract Fragment initFragment(int tag);

    protected String getFragmentTagName(int tag) {
        return "tag_" + tag;
    }

    protected abstract void setTagSelectno(View view);

    protected abstract void setTagSelected(View view);

    protected abstract View[] getBts();

}
