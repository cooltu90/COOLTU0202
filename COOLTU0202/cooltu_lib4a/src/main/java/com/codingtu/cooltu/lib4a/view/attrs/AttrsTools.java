package com.codingtu.cooltu.lib4a.view.attrs;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.StyleableRes;

public class AttrsTools {
    public static void getAttrs(Context context,
                                AttributeSet set, @StyleableRes int[] attrs, GetAttrs getAttrs) {
        new Attrs(context, set, attrs).getAttrs(getAttrs);
    }
}
