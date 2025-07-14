package com.codingtu.cooltu.lib4a.path;

import android.graphics.Bitmap;

import com.codingtu.cooltu.lib4a.data.WH;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.tool.SDCardTool;
import com.codingtu.cooltu.lib4j.path.base.BaseFile;

import java.io.File;

public class ImageFile extends BaseFile {
    public ImageFile(String root) {
        super(root);
    }

    public ImageFile(File rootFile) {
        super(rootFile);
    }

    public Bitmap bitmap() {
        return FileBitmap.obtain(rootFile()).bitmap();
    }

    public Bitmap bitmap(int w, int h) {
        return FileBitmap.obtain(rootFile()).size(w, h).bitmap();
    }

    public Bitmap bitmap(WH wh) {
        return FileBitmap.obtain(rootFile()).size(wh).bitmap();
    }

    public Bitmap bitmap(int size) {
        return FileBitmap.obtain(rootFile()).size(size).bitmap();
    }


    public void bitmap(Bitmap bitmap, int percent) {
        SDCardTool.bitMapToFile(bitmap, rootFile(), percent);
    }

    public void bitmap(Bitmap bitmap) {
        bitmap(bitmap, 100);
    }

}
