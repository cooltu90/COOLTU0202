package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.data.LTRB;
import com.codingtu.cooltu.lib4a.image.BitmapTool;
import com.codingtu.cooltu.lib4a.image.FileBitmap;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.tool.AdjustTool;

public class DefaultScaleImageView extends CoreScaleView {

    private Bitmap oriBitmap;

    public DefaultScaleImageView(Context context) {
        super(context);
    }

    public DefaultScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
    }

    @Override
    protected void onViewCompleted() {
        super.onViewCompleted();
        initBitmap();
    }

    public void setFile(String image) {
        initBitmap(FileBitmap.obtain(image).bitmap());
    }

    public void setImageUrl(String imageUrl) {
        ImageTools.getImage(imageUrl, new ImageTools.ImageBitmapGetter() {
            @Override
            public void callBack(Bitmap bitmap) {
                initBitmap(bitmap);
            }
        });
    }


    private void initBitmap(Bitmap bitmap) {
        BitmapTool.destoryBitmap(oriBitmap);
        oriBitmap = bitmap;
        oriBitmapWH = BitmapTool.getBitmapWH(oriBitmap);
        initBitmap();
    }

    private void initBitmap() {
        if (viewWH != null && oriBitmap != null) {
            adjustWH = AdjustTool.inBox(viewWH, oriBitmapWH);
            scale = (float) adjustWH.w / oriBitmapWH.w;
            minScale = scale;

            locInView = new LTRB();
            locInView.lw((viewWH.w - adjustWH.w) / 2, adjustWH.w);
            locInView.th((viewWH.h - adjustWH.h) / 2, adjustWH.h);

            showInView = locInView.copyOne();

            if (drawBitmap == null) {
                drawBitmap = BitmapTool.createBitmap(viewWH);
            }

            BitmapTool.drawBitmap(oriBitmap,
                    drawBitmap,
                    locInView.toRect(), bgColor);
            invalidate();
        }
    }

    @Override
    protected void dealMove() {
        super.dealMove();
        BitmapTool.drawBitmap(oriBitmap, showInBitmap.toRect(), drawBitmap, showInView.toRect(), bgColor);
        invalidate();
    }
}
