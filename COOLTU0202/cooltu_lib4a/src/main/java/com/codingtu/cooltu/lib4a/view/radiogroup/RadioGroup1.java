package com.codingtu.cooltu.lib4a.view.radiogroup;

import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tool.MobileTool;
import com.codingtu.cooltu.lib4a.tool.ViewTool;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;

public class RadioGroup1<E> extends RadioGroupBase<E, RadioGroup1<E>> {
    private ViewGroup vp;
    private RadioGroup.CreateItemViews<E> createItemViews;

    public RadioGroup1(ViewGroup vp) {
        this.vp = vp;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        vp = null;
        createItemViews = null;
    }

    @Override
    public RadioGroup1<E> setItems(BaseEs<E> items) {
        RadioGroup1<E> rg = super.setItems(items);
        if (createItemViews != null) {
            createItemViewsReal();
        }
        return rg;
    }

    public RadioGroup1<E> createItemViews(RadioGroup.CreateItemViews<E> createItemViews) {
        this.createItemViews = createItemViews;
        if (itemEs != null && !itemEs.isNull()) {
            createItemViewsReal();
        }
        return this;
    }

    private void createItemViewsReal() {
        itemEs.ls(new Es.EachEs<E>() {
            @Override
            public boolean each(int position, E e) {
                View view = createItemViews.createItemView(vp.getContext(), e);
                vp.addView(view, ViewTool.MATCH_PARENT, MobileTool.dpToEvenPx(30));
                view.setTag(R.id.tag_0, position);
                view.setOnClickListener(RadioGroup1.this);
                return false;
            }
        });
    }

}
