package com.codingtu.cooltu.lib4a.view.attrs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;

import androidx.annotation.StyleableRes;

import com.codingtu.cooltu.lib4a.R;

public class Attrs {

    private final TypedArray ta;
    private SparseArray<Integer> map;

    public Attrs(Context context,
                 AttributeSet set, @StyleableRes int[] attrs) {

        map = new SparseArray<Integer>();

        ta = context.obtainStyledAttributes(set, attrs);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int attr = ta.getIndex(i);
            map.put(attr, i);
        }
    }

    public void getAttrs(GetAttrs getAttrs) {
        getAttrs.getAttrs(this);
        this.ta.recycle();
    }

    public int getInt(int index, int defaultValue) {
        if (map.get(index) != null) {
            return ta.getInt(index, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public Integer getInteger(int index, int defaultValue) {
        if (map.get(index) != null) {
            return ta.getInteger(index, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public float getFloat(int index, float defValue) {
        if (map.get(index) != null) {
            return ta.getFloat(index, defValue);
        }
        return defValue;
    }

    public String getString(int index) {
        if (map.get(index) != null) {
            return ta.getString(index);
        }
        return null;
    }

    public int getDimensionPixelSize(int index, int defaultValue) {
        if (map.get(index) != null) {
            return ta.getDimensionPixelSize(index, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public ColorStateList getColorStateList(int index) {
        if (map.get(index) != null) {
            return ta.getColorStateList(index);
        }
        return null;
    }

    public Drawable getDrawable(int index) {
        if (map.get(index) != null) {
            return ta.getDrawable(index);
        } else {
            return null;
        }
    }

    public int getColor(int index, int defaultValue) {
        ColorStateList colors = getColorStateList(index);
        if (colors == null) {
            return defaultValue;
        } else {
            return colors.getDefaultColor();
        }
    }

    public boolean getBoolean(int index, boolean defaultValue) {
        if (map.get(index) != null) {
            return ta.getBoolean(index, defaultValue);
        } else {
            return defaultValue;
        }
    }

    public int getResourceId(int index, int defaultValue) {
        if (map.get(index) != null) {
            return ta.getResourceId(index, defaultValue);
        } else {
            return defaultValue;
        }
    }
}
