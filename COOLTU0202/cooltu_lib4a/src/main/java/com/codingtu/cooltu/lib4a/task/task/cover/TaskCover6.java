package com.codingtu.cooltu.lib4a.task.task.cover;

public abstract class TaskCover6<P0, P1, P2, P3, P4, P5> extends TaskCover<P0, P1, P2, P3, P4, P5, Object, Object, Object> {

    @Override
    public final boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3, P4 cp4, P4 tp4, P5 cp5, P5 tp5, Object co0, Object to0, Object co1, Object to1, Object co2, Object to2) {
        return cover(cp0, tp0, cp1, tp1, cp2, tp2, cp3, tp3, cp4, tp4, cp5, tp5);
    }

    public abstract boolean cover(P0 cp0, P0 tp0, P1 cp1, P1 tp1, P2 cp2, P2 tp2, P3 cp3, P3 tp3, P4 cp4, P4 tp4, P5 cp5, P5 tp5);
}
