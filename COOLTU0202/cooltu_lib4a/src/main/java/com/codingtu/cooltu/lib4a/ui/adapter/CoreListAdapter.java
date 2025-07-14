package com.codingtu.cooltu.lib4a.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.codingtu.cooltu.lib4a.ui.adapter.viewholder.CoreAdapterVH;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.CoreEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tool.CountTool;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class CoreListAdapter<VH extends CoreAdapterVH, E> extends CoreAdapter<VH> {
    protected BaseEs<E> es;
    private Class<VH> vhClass;

    @Override
    public int getItemCount() {
        return CountTool.count(es);
    }

    public void updateItems(List<E> items) {
        this.es = Es.es(items);
        notifyDataSetChanged();
    }

    public void updateItems(E... items) {
        this.es = Es.es(items);
        notifyDataSetChanged();
    }

    public void updateItems(CoreEs<E, ?> items) {
        this.es = Es.es();
        if (items != null) {
            this.es.addAllKindsOfEs(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {
            Constructor<VH> constructor = this.vhClass.getConstructor(ViewGroup.class);
            return (VH) constructor.newInstance(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setVH(Class vhClass) {
        this.vhClass = vhClass;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindVH(holder, position, this.es.getByIndex(position));
    }

    protected abstract void onBindVH(@NonNull VH holder, int position, E e);

    public BaseEs<E> getItems() {
        return this.es;
    }

    public void addItem(E e) {
        if (this.es == null) {
            this.es = new BaseEs<>();
        }
        this.es.add(e);
        notifyDataSetChanged();
    }
}
