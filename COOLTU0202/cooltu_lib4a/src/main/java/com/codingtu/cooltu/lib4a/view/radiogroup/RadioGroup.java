package com.codingtu.cooltu.lib4a.view.radiogroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.codingtu.cooltu.lib4j.destory.Destroys;

public class RadioGroup {

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public static interface OnSetItem {

        public void setSelected(View view);

        public void setSelectno(View view);

    }

    public static interface OnClick {
        public boolean onClick(int index, View view);
    }

    public static interface OnSelectChange<RG extends RadioGroupBase> {
        public void onChange(RG rg, int selected);
    }

    public static interface CreateItemViews<E> {
        public View createItemView(Context context, E e);
    }

    public static interface InitItems<RG extends RadioGroupBase, E> {
        public E initItem(RG rg, int poistion, View view);
    }

    ///////////////////////////////////////////////////////
    //
    //
    //
    ///////////////////////////////////////////////////////
    public static <E> RadioGroup0<E> setViewItems(Destroys destroys, ViewGroup vp) {
        RadioGroup0<E> radioGroup = new RadioGroup0<>(vp);
        destroys.addOnDestroy(radioGroup);
        return radioGroup;
    }

    public static <E> RadioGroup0<E> setViewItems(Destroys destroys, View... itemViews) {
        RadioGroup0<E> radioGroup = new RadioGroup0<>(itemViews);
        destroys.addOnDestroy(radioGroup);
        return radioGroup;
    }

    public static <E> RadioGroup1<E> createViewItems(Destroys destroys, ViewGroup vp) {
        RadioGroup1<E> radioGroup = new RadioGroup1<>(vp);
        destroys.addOnDestroy(radioGroup);
        return radioGroup;
    }


}
