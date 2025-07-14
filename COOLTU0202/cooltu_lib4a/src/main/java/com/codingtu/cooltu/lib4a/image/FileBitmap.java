package com.codingtu.cooltu.lib4a.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;

import com.codingtu.cooltu.lib4a.data.WH;

import java.io.File;

public class FileBitmap {
    private String path;

    private int size;
    private WH box;

    private Rect cut;
    private boolean notRotate;

    public static FileBitmap obtain(String path) {
        FileBitmap cutFileImage = new FileBitmap();
        cutFileImage.path = path;
        return cutFileImage;
    }

    public static FileBitmap obtain(File file) {
        return obtain(file.getAbsolutePath());
    }

    public FileBitmap size(int size) {
        this.size = size;
        return this;
    }

    public FileBitmap size(int w, int h) {
        this.box = new WH(w, h);
        return this;
    }

    public FileBitmap size(WH wh) {
        this.box = wh;
        return this;
    }

    public FileBitmap cut(Rect rect) {
        this.cut = rect;
        return this;
    }

    public FileBitmap notRotate() {
        this.notRotate = true;
        return this;
    }


    public Bitmap bitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false;

        int digree = 0;
        if (!notRotate) {
            digree = BitmapTool.getDigree(path);
        }

        if (box == null) {
            options.inSampleSize = getSize();
        } else {
            options.inSampleSize = (digree == 0 || digree == 180) ? getSize(options.outWidth, options.outHeight) : getSize(options.outHeight, options.outWidth);
        }

        Bitmap bitmap = null;
        if (cut == null) {
            bitmap = BitmapFactory.decodeFile(path, options);
        } else {
            Rect newCut = cut;
            switch (digree) {
                case 90:
                    newCut = new Rect(cut.top, options.outHeight - cut.right, cut.bottom, options.outHeight - cut.left);
                    break;
                case 180:
                    newCut = new Rect(options.outWidth - cut.right, options.outHeight - cut.bottom, options.outWidth - cut.left, options.outHeight - cut.top);
                    break;
                case 270:
                    newCut = new Rect(options.outWidth - cut.bottom, cut.left, options.outWidth - cut.top, cut.right);
                    break;
            }
            bitmap = cut(options, newCut);
        }

        if (digree == 0) {
            return bitmap;
        }

        Bitmap rotate = BitmapTool.rotate(bitmap, digree);
        if (bitmap != rotate) {
            BitmapTool.destoryBitmap(bitmap);
        }
        return rotate;
    }

    private int getSize() {
        return this.size <= 1 ? 1 : this.size;
    }

    private int getSize(int w, int h) {
        if (h > box.h || w > box.w) {
            if (w * box.h > h * box.w) {
                return Math.round(w / box.w);
            } else {
                return Math.round(h / box.h);
            }
        }
        return getSize();
    }

    private Bitmap cut(BitmapFactory.Options options, Rect cut) {
        try {
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(path, false);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return decoder.decodeRegion(cut, options);
        } catch (Exception e) {

        }
        return null;
    }

}
