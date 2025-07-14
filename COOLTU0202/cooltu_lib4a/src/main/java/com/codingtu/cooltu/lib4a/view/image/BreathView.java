package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.data.WH;
import com.codingtu.cooltu.lib4a.tool.DrawTool;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

public class BreathView extends CoreView {

    private static final long BREATH_TIME = 1000; //动画执行时间/呼吸速率
    private Long startTime;

    private Paint paint;
    private Integer bgColor;
    private Bitmap bgBitmap;

    public BreathView(Context context) {
        super(context);
    }

    public BreathView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BreathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        paint = DrawTool.getDefaultPaint();

        Drawable background = getBackground();
        if (background != null) {
            if (background instanceof ColorDrawable) {
                bgColor = ((ColorDrawable) background).getColor();
                paint.setColor(bgColor);
            } else if (background instanceof BitmapDrawable) {
                bgBitmap = ((BitmapDrawable) background).getBitmap();
            }
        }
        setBackground(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long currentTimeMillis = System.currentTimeMillis();

        if (startTime == null) {
            startTime = currentTimeMillis;
        }

        long l = (currentTimeMillis - startTime) % 2000;
        if (l < 1000) {
            l = 255 * l / 999;
        } else {
            l = 255 - (255 * (l - 1000) / 999);
        }
        paint.setAlpha((int) l);
        if (bgColor != null) {
            canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);
        } else if (bgBitmap != null) {
            WH bitmapWH = new WH(bgBitmap.getWidth(), bgBitmap.getHeight());
            Rect rect = new Rect(0, 0, bitmapWH.w, bitmapWH.h);
            Rect rect1 = new Rect(0, 0, getWidth(), getHeight());
            canvas.drawBitmap(bgBitmap, rect, rect1, paint);
        }
        invalidate();
    }

}
