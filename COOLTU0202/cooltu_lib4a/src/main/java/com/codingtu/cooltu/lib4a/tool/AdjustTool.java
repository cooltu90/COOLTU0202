package com.codingtu.cooltu.lib4a.tool;


import com.codingtu.cooltu.lib4a.data.WH;

public class AdjustTool {

    public static WH inBox(int boxW, int boxH, int objW, int objH) {
        if (boxW * objH > boxH * objW) {
            boxW = Math.round(((float) (boxH * objW)) / ((float) objH));
        } else if (boxW * objH < boxH * objW) {
            boxH = Math.round(((float) (boxW * objH)) / ((float) objW));
        }
        return new WH(boxW, boxH);
    }

    public static WH inBox(WH box, WH obj) {
        WH adjustWh = box.copyOne();
        if (adjustWh.w * obj.h > adjustWh.h * obj.w) {
            adjustWh.w = Math.round(((float) (adjustWh.h * obj.w)) / ((float) obj.h));
        } else if (adjustWh.w * obj.h < adjustWh.h * obj.w) {
            adjustWh.h = Math.round(((float) (adjustWh.w * obj.h)) / ((float) obj.w));
        }
        return adjustWh;
    }

    public static WH outBox(WH box, WH obj) {
        WH adjustWh = box.copyOne();
        if (adjustWh.w * obj.h > adjustWh.h * obj.w) {
            adjustWh.h = Math.round(((float) (adjustWh.w * obj.h)) / ((float) obj.w));
        } else if (adjustWh.w * obj.h < adjustWh.h * obj.w) {
            adjustWh.w = Math.round(((float) (adjustWh.h * obj.w)) / ((float) obj.h));
        }
        return adjustWh;
    }

}
