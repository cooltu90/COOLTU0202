package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.codingtu.cooltu.lib4a.data.LTRB;
import com.codingtu.cooltu.lib4a.data.WH;
import com.codingtu.cooltu.lib4a.image.BitmapTool;
import com.codingtu.cooltu.lib4a.image.ImageTools;
import com.codingtu.cooltu.lib4a.tool.AdjustTool;
import com.codingtu.cooltu.lib4a.tool.HandlerTool;
import com.codingtu.cooltu.lib4a.tool.MobileTool;
import com.codingtu.cooltu.lib4a.view.base.CoreView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ScaleImageView extends CoreView {

    protected int maxScale = 4;
    private int minWidth;
    private int maxWidth;

    private String imageUrl;
    protected Bitmap bgBitmap;
    protected Bitmap drawBitmap;
    protected WH viewWH;
    private WH scaleWH;

    protected PicManager pic;
    private OnClickListener onClick;
    protected boolean startImage;

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected class PicManager {
        public LTRB locInView;
        public LTRB showInView;

        public PicManager() {
        }

        public void setLocInView(LTRB locInView) {
            this.locInView = locInView;
            obtainShowInView();
        }

        private void obtainShowInView() {
            this.showInView = locInView.copyOne();
            if (showInView.l < 0)
                showInView.l = 0;
            if (showInView.t < 0)
                showInView.t = 0;
            if (showInView.r > viewWH.w)
                showInView.r = viewWH.w;
            if (showInView.b > viewWH.h)
                showInView.b = viewWH.h;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawBitmap != null && !drawBitmap.isRecycled()) {
            BitmapTool.drawBitmap(canvas, drawBitmap, Color.WHITE);
        }
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
//        ViewTool.completeView(this, new ViewTool.ViewComplete() {
//            @Override
//            public void viewComplete() {
//                startImage = true;
//                initView();
//            }
//        });
        if (getWidth() > 0) {
            startImage = true;
            initView();
        } else {
            HandlerTool.getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    startImage = true;
                    initView();
                }
            });
        }
    }

    private void initView() {
        if (viewWH == null)
            viewWH = new WH(getWidth(), getHeight());
        if (pic == null)
            pic = new PicManager();
        drawBitmap = BitmapTool.createBitmap(viewWH);
        File file = new File(imageUrl);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageUrl);
            initBitmap(bitmap);
        } else {
            ImageTools.getImage(imageUrl, new ImageTools.ImageBitmapGetter() {
                @Override
                public void callBack(Bitmap bitmap) {
                    initBitmap(bitmap);
                }
            });
        }
    }

    protected void initBitmap(Bitmap bitmap) {
        bgBitmap = dealLoadBitmap(bitmap);
        WH bitmapWH = BitmapTool.getBitmapWH(bgBitmap);
        scaleWH = AdjustTool.inBox(viewWH, bitmapWH);

        minWidth = scaleWH.w;

        if (bgBitmap.getWidth() < MobileTool.getScreenWidth() || bgBitmap.getHeight() < MobileTool.getScreenHight()) {
            maxWidth = AdjustTool.outBox(new WH(MobileTool.getScreenWidth(), MobileTool.getScreenHight()), bitmapWH).w * maxScale;
        } else {
            maxWidth = bitmapWH.w * maxScale;
        }

        LTRB ltrb = new LTRB();
        ltrb.lw((viewWH.w - scaleWH.w) / 2, scaleWH.w);
        ltrb.th((viewWH.h - scaleWH.h) / 2, scaleWH.h);

        pic.setLocInView(ltrb);
        drawBitmap = BitmapTool.getBitmap(drawBitmap, ltrb.toRect(), bgBitmap);
        startImage = false;
        invalidate();
    }

    protected Bitmap dealLoadBitmap(Bitmap bitmap) {
        return bitmap;
    }

    protected boolean isScale;
    private P focusP;
    protected float scale;
    private P lastP;
    private List<P> lastPs;
    //单击
    private boolean isClick = false;
    private P clickP;
    private long clickTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (startImage || bgBitmap == null) {
            return false;
        }

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //单击
                isClick = true;
                clickP = getP(event);
                clickTime = System.currentTimeMillis();
                //
                isScale = false;
                lastP = getP(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isScale) {
                    moveSingle(event);
                } else {
                    moveMulti(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                lastP = null;
                this.lastPs = null;
                if (this.onClick != null && isClick) {
                    clickTime = System.currentTimeMillis() - clickTime;
                    if (clickTime <= 500) {
                        double distance = getDistance(getP(event), clickP);
                        if (distance <= 20) {
                            this.onClick.onClick(this);
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (!isScale) {
                    onScaleBegin(event);
                }
                isScale = true;
                this.lastPs = getPs(event);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() <= 2) {
                    lastP = null;
                    onScaleEnd();
                } else {
                    this.lastPs = getPs(event);
                }
                break;
        }
        return true;
    }


    private void moveSingle(MotionEvent event) {
        if (lastP != null) {
            P p = getP(event);
            float dx = lastP.x - p.x;
            float dy = lastP.y - p.y;
            onSroll(dx, dy);
        }
        lastP = getP(event);
    }

    private void moveMulti(MotionEvent event) {

        List<P> ps = getPs(event);

        int size = ps.size();

        double d1 = 0;
        double d2 = 0;
        int num = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                d1 += getDistance(ps.get(i), ps.get(j));
                d2 += getDistance(this.lastPs.get(i), this.lastPs.get(j));
                num++;
            }
        }

        d1 = d1 / (double) num;
        d2 = d2 / (double) num;
        scale *= (float) (d1 / d2);
        dealScale();

        this.lastPs = ps;
    }

    private void onScaleBegin(MotionEvent event) {
        isScale = true;
        initScale(event);
    }

    private void onScaleEnd() {
        if (focusP != null) {
            pic.setLocInView(fitLtrbForScale());
            scaleWH = pic.locInView.wh();
        }
        isScale = false;
    }

    private void initScale(MotionEvent event) {
        scale = (focusP = getInAreaP(getFocusP(event), pic.showInView)) != null ? 1f : 0f;
    }

    private void dealScale() {
        fitScale();
        if (scale != 0f) {
            LTRB fitLtrb = fitLtrbForScale();
            Bitmap bitmap = BitmapTool.createBitmap(viewWH);
            drawBitmap.recycle();
            drawBitmap = BitmapTool.getBitmap(bitmap, fitLtrb.toRect(), bgBitmap);
            onScale(fitLtrb);
            invalidate();
        }

    }

    protected void onScale(LTRB ltrb) {

    }

    private void fitScale() {
        int w = pic.locInView.w();
        float maxScale = (float) maxWidth / (float) w;
        float minScale = (float) minWidth / (float) w;
        if (scale > maxScale) {
            scale = maxScale;
        } else if (scale < minScale) {
            scale = minScale;
        }
    }

    private LTRB fitLtrbForScale() {
        LTRB ltrb = pic.locInView.copyOne();
        WH newWH = ltrb.wh().copyOne();

        newWH.w = Math.round(newWH.w * scale);
        newWH.h = Math.round(newWH.h * scale);

        ltrb.l -= Math.round(((focusP.x - ltrb.l)) * (scale - 1));
        ltrb.t -= Math.round(((focusP.y - ltrb.t)) * (scale - 1));
        ltrb.r = ltrb.l + newWH.w;
        ltrb.b = ltrb.t + newWH.h;

        if (ltrb.w() <= viewWH.w) {
            ltrb.l = (viewWH.w - ltrb.w()) / 2;
            ltrb.r = ltrb.l + newWH.w;
        } else if (ltrb.l > 0 && ltrb.r > viewWH.w) {
            ltrb.l = 0;
            ltrb.r = ltrb.l + newWH.w;
        } else if (ltrb.l < 0 && ltrb.r < viewWH.w) {
            ltrb.r = viewWH.w;
            ltrb.l = ltrb.r - newWH.w;
        }

        if (ltrb.h() <= viewWH.h) {
            ltrb.t = (viewWH.h - ltrb.h()) / 2;
            ltrb.b = ltrb.t + newWH.h;
        } else if (ltrb.t > 0 && ltrb.b > viewWH.h) {
            ltrb.t = 0;
            ltrb.b = ltrb.t + newWH.h;
        } else if (ltrb.t < 0 && ltrb.b < viewWH.h) {
            ltrb.b = viewWH.h;
            ltrb.t = ltrb.b - newWH.h;
        }
        return ltrb;
    }

    /************************************************
     *
     *
     *
     ************************************************/
    private void onSroll(float distanceX, float distanceY) {
        LTRB np = fitLtrbForScroll(distanceX, distanceY);
        LTRB ltrb = new LTRB();
        ltrb.l = np.l - pic.locInView.l;
        ltrb.t = np.t - pic.locInView.t;
        ltrb.r = ltrb.l + viewWH.w;
        ltrb.b = ltrb.t + viewWH.h;
        pic.setLocInView(np);
        Bitmap bitmap = BitmapTool.getBitmap(BitmapTool.createBitmap(viewWH), np.toRect(), bgBitmap);
        drawBitmap = BitmapTool.getBitmap(bitmap, ltrb.toRect(), drawBitmap);
        onSroll(np);
        invalidate();
    }

    protected void onSroll(LTRB ltrb) {

    }

    private LTRB fitLtrbForScroll(float distanceX, float distanceY) {
        LTRB ltrb = pic.locInView.copyOne();

        if (pic.locInView.w() > viewWH.w) {
            ltrb.l = Math.round(ltrb.l - distanceX);
            ltrb.r = ltrb.l + pic.locInView.w();
            if (ltrb.l > 0) {
                ltrb.l = 0;
                ltrb.r = ltrb.l + pic.locInView.w();
            } else if (ltrb.r < viewWH.w) {
                ltrb.r = viewWH.w;
                ltrb.l = ltrb.r - pic.locInView.w();
            }
        }

        if (pic.locInView.h() > viewWH.h) {
            ltrb.t = Math.round(ltrb.t - distanceY);
            ltrb.b = ltrb.t + pic.locInView.h();
            if (ltrb.t > 0) {
                ltrb.t = 0;
                ltrb.b = ltrb.t + pic.locInView.h();
            } else if (ltrb.b < viewWH.h) {
                ltrb.b = viewWH.h;
                ltrb.t = ltrb.b - pic.locInView.h();
            }
        }

        return ltrb;
    }

    /************************************************
     *
     * 点的操作
     *
     ************************************************/

    private double getDistance(P p1, P p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    private List<P> getPs(MotionEvent event) {
        List<P> ps = new ArrayList<P>();
        for (int i = 0; i < event.getPointerCount(); i++) {
            ps.add(getP(event, i));
        }
        return ps;
    }

    private P getFocusP(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        float x = 0;
        float y = 0;
        for (int i = 0; i < event.getPointerCount(); i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        return new P(x / (float) pointerCount, y / (float) pointerCount);
    }

    private P getInAreaP(P p, LTRB ltrb) {
        return isInArea(p, ltrb) ? p : null;
    }

    private boolean isInArea(P p, LTRB ltrb) {
        return p == null ? false : (ltrb.l <= p.x && p.x <= ltrb.r && ltrb.t <= p.y && p.y <= ltrb.b);
    }

    private P getP(MotionEvent event) {
        return new P(event.getX(), event.getY());
    }

    private P getP(MotionEvent event, int index) {
        return new P(event.getX(index), event.getY(index));
    }

    private class P {
        private float x;
        private float y;

        public P() {
        }

        public P(float x, float y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public void setClick(OnClickListener onClick) {
        this.onClick = onClick;
    }

    public LTRB getLtrb() {
        return pic.locInView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onClick = null;
    }
}
