package com.codingtu.cooltu.lib4a.task.task.cover;

public abstract class TaskCover1<P0> extends TaskCover<P0, Object, Object, Object, Object, Object, Object, Object, Object> {

    @Override
    public final boolean cover(P0 cp0, P0 tp0, Object co0, Object to0, Object co1, Object to1, Object co2, Object to2, Object co3, Object to3, Object co4, Object to4, Object co5, Object to5, Object co6, Object to6, Object co7, Object to7) {
        return cover(cp0, tp0);
    }

    public abstract boolean cover(P0 cp0, P0 tp0);
}
