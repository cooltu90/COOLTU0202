package com.codingtu.cooltu.lib4a.view.radiogroup;

import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.es.Es;

public class RadioGroup0<E> extends RadioGroupBase<E, RadioGroup0<E>> {

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public RadioGroup0(ViewGroup vp) {
        if (vp.getChildCount() > 0) {
            this.itemViewEs = Es.es(new Es.EachGetter<View>() {
                @Override
                public int count() {
                    return vp.getChildCount();
                }

                @Override
                public View get(int i) {
                    View childAt = vp.getChildAt(i);
                    childAt.setTag(R.id.tag_0, i);
                    childAt.setOnClickListener(RadioGroup0.this);
                    return childAt;
                }
            });
        }
    }

    public RadioGroup0(View... itemViews) {
        this.itemViewEs = Es.es(itemViews).ls(new Es.EachEs<View>() {
            @Override
            public boolean each(int position, View childAt) {
                childAt.setTag(R.id.tag_0, position);
                childAt.setOnClickListener(RadioGroup0.this);
                return false;
            }
        });
    }


    public RadioGroup0<E> initItems(RadioGroup.InitItems<RadioGroup0<E>, E> initItems) {
        if (this.itemViewEs != null) {
            this.itemEs = this.itemViewEs.convert(new Es.Convert<View, E>() {
                @Override
                public E convert(int index, View view) {
                    return initItems.initItem(RadioGroup0.this, index, view);
                }
            });
        }
        return this;
    }
}
