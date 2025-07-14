package com.codingtu.cooltu.lib4a.image;

import android.graphics.BitmapFactory;

import com.codingtu.cooltu.lib4a.data.WH;

import java.io.File;

public class BitmapWH {

    private String path;
    private BitmapFactory.Options options;
    private boolean notRotate;

    public static BitmapWH obtain(String path) {
        BitmapWH bitmapWH = new BitmapWH();
        bitmapWH.path = path;
        return bitmapWH;
    }

    public static BitmapWH obtain(File file) {
        return obtain(file.getAbsolutePath());
    }


    public BitmapWH options(BitmapFactory.Options options) {
        this.options = options;
        return this;
    }

    public BitmapWH notRotate() {
        this.notRotate = true;
        return this;
    }

    public WH wh() {
        if (options == null) {
            options = new BitmapFactory.Options();
        }
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;

        WH wh = new WH(options.outWidth, options.outHeight);
        if (notRotate) {
            return wh;
        }

        int digree = BitmapTool.getDigree(path);
        if (digree == 90 || digree == 270) {
            // 旋转图片
            wh = new WH(wh.h, wh.w);
        }
        return wh;
    }
}
