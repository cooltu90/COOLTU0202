package com.codingtu.cooltu.lib4a.view.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.base.CoreImageView;

public class RoundImageView extends CoreImageView {
    private int bgRadius;
    private int topLeftbgRadius;
    private int topRightbgRadius;
    private int bottomLeftbgRadius;
    private int bottomRightbgRadius;

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        AttrsTools.getAttrs(context, attrs, R.styleable.RoundImageView, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                bgRadius = attrs.getDimensionPixelSize(R.styleable.RoundImageView_android_radius, 0);
                topLeftbgRadius = attrs.getDimensionPixelSize(R.styleable.RoundImageView_android_topLeftRadius, bgRadius);
                topRightbgRadius = attrs.getDimensionPixelSize(R.styleable.RoundImageView_android_topRightRadius, bgRadius);
                bottomLeftbgRadius = attrs.getDimensionPixelSize(R.styleable.RoundImageView_android_bottomLeftRadius, bgRadius);
                bottomRightbgRadius = attrs.getDimensionPixelSize(R.styleable.RoundImageView_android_bottomRightRadius, bgRadius);
            }
        });
    }

    private float[] getRadii() {
        return new float[]{
                (float) this.topLeftbgRadius,
                (float) this.topLeftbgRadius,
                (float) this.topRightbgRadius,
                (float) this.topRightbgRadius,
                (float) this.bottomRightbgRadius,
                (float) this.bottomRightbgRadius,
                (float) this.bottomLeftbgRadius,
                (float) this.bottomLeftbgRadius};
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        path.addRoundRect(rect, getRadii(), Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

}
