package com.codingtu.cooltu.lib4a.task.task.cover;

public abstract class TaskCover2<P0, P1> extends TaskCover<P0, P1, Object, Object, Object, Object, Object, Object, Object> {

    @Override
    public final boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, Object co0, Object to0, Object co1, Object to1, Object co2, Object to2, Object co3, Object to3, Object co4, Object to4, Object co5, Object to5, Object co6, Object to6) {
        return cover(cp0, tp0, cp1, tp1);
    }

    public abstract boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1);
}
