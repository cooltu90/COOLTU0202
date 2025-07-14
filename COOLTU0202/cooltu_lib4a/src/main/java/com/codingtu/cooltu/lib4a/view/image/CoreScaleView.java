package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.data.LTRB;
import com.codingtu.cooltu.lib4a.data.WH;
import com.codingtu.cooltu.lib4a.image.BitmapTool;
import com.codingtu.cooltu.lib4a.tool.DrawTool;
import com.codingtu.cooltu.lib4a.tool.HandlerTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.base.CoreView;
import com.codingtu.cooltu.lib4j.data.xy.FloatXY;

import java.util.ArrayList;
import java.util.List;

public class CoreScaleView extends CoreView {

    protected Paint paint;
    protected Bitmap drawBitmap;
    protected WH viewWH;
    protected float scale;
    private Integer fingers;
    private List<FloatXY> lastPoints;
    private FloatXY lastPoint;
    protected float maxScale;
    protected float minScale;
    protected WH adjustWH;
    protected LTRB locInView;
    protected LTRB showInView;
    protected LTRB showInBitmap;
    protected FloatXY scaleCenterPoint;
    protected WH oriBitmapWH;
    protected int bgColor;

    public CoreScaleView(Context context) {
        super(context);
    }

    public CoreScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoreScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        paint = DrawTool.getDefaultPaint();
        AttrsTools.getAttrs(context, attrs, R.styleable.CoreScaleView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                maxScale = attrs.getFloat(R.styleable.CoreScaleView_maxScale, 4);
                bgColor = attrs.getColor(R.styleable.CoreScaleView_android_background, Color.BLACK);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (viewWH == null) {
            viewWH = new WH(getWidth(), getHeight());
            onViewCompleted();
        } else if (viewWH.w != getWidth() || viewWH.h != getHeight()) {
            viewWH = new WH(getWidth(), getHeight());
            onViewCompleted();
        }
    }

    protected void onViewCompleted() {

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawBitmap != null && !drawBitmap.isRecycled()) {
            BitmapTool.drawBitmap(canvas, drawBitmap, bgColor);
        }
    }

    private long actionDownTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                fingers = null;
                lastPoints = null;
                lastPoint = null;
                actionDownTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                if (System.currentTimeMillis() - actionDownTime < 100) {
                    return true;
                }
                if (fingers == null) {

                    FloatXY p = getP(event);
                    if (lastPoint != null) {
                        onMoveSingle(event, p.x - lastPoint.x, p.y - lastPoint.y);
                    } else {
                        onMoveSingleStart(event);
                    }
                    lastPoint = p;
                } else if (fingers == 2 && event.getPointerCount() == 2) {
                    List<FloatXY> currentPs = getPs(event);
                    if (lastPoints != null) {
                        //处理
                        float scaleAdd = calculateScale(currentPs, lastPoints);
                        onMoveMulti(event, scaleAdd);
                    } else {
                        onMoveMultiStart(event);
                    }
                    lastPoints = currentPs;
                }
                break;
            case MotionEvent.ACTION_UP:
                long dt = System.currentTimeMillis() - actionDownTime;
                if (dt < 100) {
                    onSingleClickDeal(event);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (fingers == null) {
                    fingers = event.getPointerCount();
                } else if (fingers < event.getPointerCount()) {
                    fingers = event.getPointerCount();
                }
                lastPoints = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
        }
        return true;
    }


    private Long singleClickTime;

    private void onSingleClickDeal(MotionEvent event) {
        if (singleClickTime == null) {
            singleClickTime = System.currentTimeMillis();
            FloatXY p = getP(event);
            HandlerTool.getMainHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (singleClickTime != null) {
                        singleClickTime = null;
                        onSingleClick(p);
                    }
                }
            }, 200);
        } else {
            long l = System.currentTimeMillis() - singleClickTime;
            if (l < 200) {
                singleClickTime = null;
                onMultiClick(getP(event));
            }
        }
    }

    protected void onSingleClick(FloatXY p) {
    }

    protected void onMultiClick(FloatXY p) {
    }

    protected void onMoveSingleStart(MotionEvent event) {
    }

    protected void onMoveMultiStart(MotionEvent event) {
        scaleCenterPoint = getInAreaP(getScaleCenterP(event), showInView);
    }

    protected void onMoveSingle(MotionEvent event, float dx, float dy) {
        calculateMove(locInView.w(), locInView.h(), (int) (locInView.l + dx), (int) (locInView.t + dy));
        dealMove();
    }

    protected void onMoveMulti(MotionEvent event, float scaleAdd) {
        if (scaleCenterPoint == null)
            return;

        scale *= scaleAdd;
        if (scale > maxScale) {
            scale = maxScale;
        }

        if (scale < minScale) {
            scale = minScale;
        }

        float newW = oriBitmapWH.w * scale;
        float newH = oriBitmapWH.h * scale;

        if (newW < adjustWH.w || newH < adjustWH.h) {
            newW = adjustWH.w;
            newH = adjustWH.h;
        }

        int l = (int) (scaleCenterPoint.x - newW * (scaleCenterPoint.x - locInView.l) / locInView.w());
        int t = (int) (scaleCenterPoint.y - newH * (scaleCenterPoint.y - locInView.t) / locInView.h());

        calculateMove((int) newW, (int) newH, l, t);

        dealMove();

    }

    protected void dealMove() {

    }


    /**************************************************
     *
     * 一些计算方法
     *
     **************************************************/

    protected void calculateMove(int newW, int newH, int l, int t) {
        locInView.lw(l, newW);
        locInView.th(t, newH);

        if (locInView.w() <= viewWH.w) {
            locInView.l = (viewWH.w - locInView.w()) / 2;
            locInView.r = locInView.l + newW;
        } else if (locInView.l > 0 && locInView.r > viewWH.w) {
            locInView.l = 0;
            locInView.r = locInView.l + newW;
        } else if (locInView.l < 0 && locInView.r < viewWH.w) {
            locInView.r = viewWH.w;
            locInView.l = locInView.r - newW;
        }


        if (locInView.h() <= viewWH.h) {
            locInView.t = (viewWH.h - locInView.h()) / 2;
            locInView.b = locInView.t + newH;
        } else if (locInView.t > 0 && locInView.b > viewWH.h) {
            locInView.t = 0;
            locInView.b = locInView.t + newH;
        } else if (locInView.t < 0 && locInView.b < viewWH.h) {
            locInView.b = viewWH.h;
            locInView.t = locInView.b - newH;
        }

        showInView = locInView.copyOne();
        if (showInView.l < 0) {
            showInView.l = 0;
        }

        if (showInView.r > viewWH.w) {
            showInView.r = viewWH.w;
        }

        if (showInView.t < 0) {
            showInView.t = 0;
        }

        if (showInView.b > viewWH.h) {
            showInView.b = viewWH.h;
        }

        showInBitmap = new LTRB();
        if (locInView.l >= 0) {
            showInBitmap.l = 0;
        } else {
            showInBitmap.l = (int) (-locInView.l / scale);
        }


        showInBitmap.r = (int) (showInBitmap.l + showInView.w() / scale);


        if (locInView.t >= 0) {
            showInBitmap.t = 0;
        } else {
            showInBitmap.t = (int) (-locInView.t / scale);
        }

        showInBitmap.b = (int) (showInBitmap.t + showInView.h() / scale);
    }

    protected float calculateScale(List<FloatXY> currentPs, List<FloatXY> lastPs) {
        int size = currentPs.size();

        double d1 = 0;
        double d2 = 0;
        int num = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                d1 += getDistance(currentPs.get(i), currentPs.get(j));
                d2 += getDistance(lastPs.get(i), lastPs.get(j));
                num++;
            }
        }
        d1 = d1 / (double) num;
        d2 = d2 / (double) num;

        return (float) (d1 / d2);
    }

    protected double getDistance(FloatXY p1, FloatXY p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }


    protected FloatXY getP(MotionEvent event) {
        return new FloatXY(event.getX(), event.getY());
    }

    protected FloatXY getP(MotionEvent event, int index) {
        return new FloatXY(event.getX(index), event.getY(index));
    }

    protected List<FloatXY> getPs(MotionEvent event) {
        ArrayList<FloatXY> ps = new ArrayList<>();
        for (int i = 0; i < event.getPointerCount(); i++) {
            ps.add(getP(event, i));
        }
        return ps;
    }

    protected FloatXY getInAreaP(FloatXY p, LTRB ltrb) {
        return isInArea(p, ltrb) ? p : null;
    }

    protected boolean isInArea(FloatXY p, LTRB ltrb) {
        return p == null ? false : (ltrb.l <= p.x && p.x <= ltrb.r && ltrb.t <= p.y && p.y <= ltrb.b);
    }

    protected FloatXY getScaleCenterP(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        float x = 0;
        float y = 0;
        for (int i = 0; i < event.getPointerCount(); i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        return new FloatXY(x / (float) pointerCount, y / (float) pointerCount);
    }

//    public static class P extends CoreBean {
//        public float x;
//        public float y;
//
//        public P(float x, float y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
}
