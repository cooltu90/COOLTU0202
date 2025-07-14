package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;


import com.codingtu.cooltu.lib4a.data.WH;

import java.util.Map;

public class BigScaleImageView extends CoreScaleView {

    protected Bitmap drawBitmap;
    private BigImage bigImage;

    public static class BigImageLevel {
        private int level;
        private String[][] images;
        private WH imageWH;
        private WH fileWH;
        private WH ltWH;

        public void setImage(int row, int column, String url) {
            images[row][column] = url;
        }
    }

    public static class BigImage {
        private Map<Integer, BigImageLevel> levels;

        public void addLevel(int level, BigImageLevel bigImageLevel) {
            bigImageLevel.level = level;
            levels.put(level, bigImageLevel);
        }
    }

    public BigScaleImageView(Context context) {
        super(context);
    }

    public BigScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BigScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onViewCompleted() {
        super.onViewCompleted();
        initBitmap();
    }

    public void setBigImage(BigImage bigImage) {
        this.bigImage = bigImage;
        initBitmap();
    }

    private void initBitmap() {
        if (viewWH == null || bigImage == null) {
            return;
        }

    }
}
