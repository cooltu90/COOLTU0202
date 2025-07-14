package com.codingtu.cooltu.lib4a.ui.activity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.es.Es;

import java.util.HashMap;

public abstract class CoreIndexActivity extends CoreActivity {

    private int currentTag = -1;
    private Fragment currentFragment;
    private int[] allTags;
    protected HashMap<Integer, View> btMap = new HashMap<>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        allTags = allTag();
        createClick();
        int defaultTag = allTags[0];
        clickTag(defaultTag);
    }

    private void createClick() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTag(v.getId());
            }
        };
        Es.ints(allTags).ls(new Es.EachEs<Integer>() {
            @Override
            public boolean each(int position, Integer id) {
                View bt = findViewById(id);
                btMap.put(id, bt);
                createClick(position, id);
                bt.setOnClickListener(onClickListener);
                return false;
            }
        });
    }

    protected void createClick(int position, Integer id) {

    }

    public void clickTag(int tag) {
        if (currentTag == tag) {
            return;
        }
        currentTag = tag;
        changeBt(currentTag);
        showFragment(tag);
    }

    protected abstract void changeBt(int currentTag);

    protected abstract int[] allTag();

    private void showFragment(int tag) {
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

    protected Fragment getFragmentByTag(int tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        return manager.findFragmentByTag(getFragmentTagName(tag));
    }

    protected abstract Fragment initFragment(int tag);

    protected String getFragmentTagName(int tag) {
        return "tag_" + tag;
    }

}
