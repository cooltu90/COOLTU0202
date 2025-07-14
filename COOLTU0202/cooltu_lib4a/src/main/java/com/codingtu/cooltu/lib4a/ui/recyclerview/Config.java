package com.codingtu.cooltu.lib4a.ui.recyclerview;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.codingtu.cooltu.lib4j.callback.base.Supplier;


public interface Config {

    void config(Context context, RecyclerView rv, Supplier supplier);

}
