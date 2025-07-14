package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

public class LargeImageView extends AppCompatImageView {

    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;
    private int mTileSize;
    private int mMaxLevel;
    private int mLevel;
    private Rect mVisibleRect;
    private Paint mPaint;

    public LargeImageView(Context context) {
        super(context);
        init();
    }

    public LargeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LargeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTileSize = 256;
        mMaxLevel = 0;
        mLevel = 0;
        mVisibleRect = new Rect();
        mPaint = new Paint();
        mPaint.setFilterBitmap(true);
        mPaint.setAntiAlias(true);
    }

    public void setImageBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        mMaxLevel = (int) Math.ceil(Math.log(Math.max(mBitmapWidth, mBitmapHeight)) / Math.log(2));
        invalidate();
    }

    public void setLevel(int level) {
        mLevel = Math.max(0, Math.min(mMaxLevel, level));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null) {
            return;
        }

        int tileSize = mTileSize << mLevel;
        int visibleLeft = mVisibleRect.left >> mLevel;
        int visibleTop = mVisibleRect.top >> mLevel;
        int visibleRight = mVisibleRect.right >> mLevel;
        int visibleBottom = mVisibleRect.bottom >> mLevel;

        for (int x = visibleLeft; x < visibleRight; x++) {
            for (int y = visibleTop; y < visibleBottom; y++) {
                int tileLeft = x * tileSize;
                int tileTop = y * tileSize;
                int tileRight = tileLeft + tileSize;
                int tileBottom = tileTop + tileSize;

                int srcLeft = 0;
                int srcTop = 0;
                int srcRight = tileSize;
                int srcBottom = tileSize;

                if (tileLeft < 0) {
                    srcLeft = -tileLeft;
                    tileLeft = 0;
                }

                if (tileTop < 0) {
                    srcTop = -tileTop;
                    tileTop = 0;
                }

                if (tileRight > mBitmapWidth) {
                    srcRight = tileSize - (tileRight - mBitmapWidth);
                    tileRight = mBitmapWidth;
                }

                if (tileBottom > mBitmapHeight) {
                    srcBottom = tileSize - (tileBottom - mBitmapHeight);
                    tileBottom = mBitmapHeight;
                }

                Rect srcRect = new Rect(srcLeft, srcTop, srcRight, srcBottom);
                Rect dstRect = new Rect(tileLeft, tileTop, tileRight, tileBottom);
                canvas.drawBitmap(mBitmap, srcRect, dstRect, mPaint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mVisibleRect.set(0, 0, width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int dx = (int) event.getX() - mVisibleRect.centerX();
            int dy = (int) event.getY() - mVisibleRect.centerY();
            mVisibleRect.offset(-dx, -dy);
            invalidate();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
