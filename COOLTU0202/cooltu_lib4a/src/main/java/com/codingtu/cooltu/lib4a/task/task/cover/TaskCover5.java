package com.codingtu.cooltu.lib4a.task.task.cover;

public abstract class TaskCover5<P0, P1, P2, P3, P4> extends TaskCover<P0, P1, P2, P3, P4, Object, Object, Object, Object> {

    @Override
    public final boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3, P4 cp4, P4 tp4, Object co0, Object to0, Object co1, Object to1, Object co2, Object to2, Object co3, Object to3) {
        return cover(cp0, tp0, cp1, tp1, cp2, tp2, cp3, tp3, cp4, tp4);
    }

    public abstract boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3, P4 cp4, P4 tp4);
}
