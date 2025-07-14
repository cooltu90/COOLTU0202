package com.codingtu.cooltu.lib4a.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup0;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.CountTool;

public abstract class CoreTagActivity extends CoreActivity {

    private View[] bts;
    private RadioGroup0 radioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateComplete() {
        super.onCreateComplete();
        bts = getBts();

        ViewPager2 vp = getViewPager();
        vp.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return CoreTagActivity.this.createFragment(position);
            }

            @Override
            public int getItemCount() {
                return CoreTagActivity.this.getItemCount();
            }
        });
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (radioGroup != null) {
                    if (position != radioGroup.getSelected()) {
                        radioGroup.setSelected(position);
                    }
                }
            }
        });

        Es.es(bts).ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int position, View view) {
                view.setTag(R.id.tag_0, position);
                return false;
            }
        });

        radioGroup = RadioGroup.setViewItems(this, bts)
                .setOnSetItem(new RadioGroup.OnSetItem() {
                    @Override
                    public void setSelected(View view) {
                        onSelected((Integer) view.getTag(R.id.tag_0), view);
                    }

                    @Override
                    public void setSelectno(View view) {
                        onSelectno((Integer) view.getTag(R.id.tag_0), view);
                    }
                })
                .addOnSelectChange(new RadioGroup.OnSelectChange<RadioGroup0<Object>>() {
                    @Override
                    public void onChange(RadioGroup0<Object> objectRadioGroup0, int selected) {
                        if (vp.getCurrentItem() != selected) {
                            vp.setCurrentItem(selected);
                        }
                    }
                }).setSelected(getDefaultSelected());
    }

    protected int getDefaultSelected() {
        return 0;
    }

    protected int getItemCount() {
        return CountTool.count(bts);
    }

    protected abstract void onSelectno(int position, View view);

    protected abstract void onSelected(int position, View view);

    protected abstract View[] getBts();

    protected abstract Fragment createFragment(int position);

    protected abstract ViewPager2 getViewPager();
}
