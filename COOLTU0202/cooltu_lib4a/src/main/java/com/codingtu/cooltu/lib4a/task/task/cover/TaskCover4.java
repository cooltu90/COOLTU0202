package com.codingtu.cooltu.lib4a.task.task.cover;

public abstract class TaskCover4<P0, P1, P2, P3> extends TaskCover<P0, P1, P2, P3, Object, Object, Object, Object, Object> {

    @Override
    public final boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3, Object co0, Object to0, Object co1, Object to1, Object co2, Object to2, Object co3, Object to3, Object co4, Object to4) {
        return cover(cp0, tp0, cp1, tp1, cp2, tp2, cp3, tp3);
    }

    public abstract boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3);
}
