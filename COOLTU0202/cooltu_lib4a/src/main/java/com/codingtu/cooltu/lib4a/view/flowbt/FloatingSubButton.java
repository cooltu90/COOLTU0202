package com.codingtu.cooltu.lib4a.view.flowbt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.image.BitmapTool;
import com.codingtu.cooltu.lib4a.tool.ViewTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.base.CoreRelativeLayout;

public class FloatingSubButton extends CoreRelativeLayout {
    private Drawable icon;
    private ImageView iv;
    private int iconSize;
    private int parentIconSize;
    private int parentIconPadding;
    private int indexInParent;
    private int totalSize;
    private int iconMargin;
    private TextView tv;
    private String text;
    private int textPadding;
    private boolean isShow;
    private Animation ivShowAnimation;
    private Animation tvShowAnimation;
    private Animation tvHiddenAnimation;
    private Animation ivHiddenAnimation;


    public FloatingSubButton(Context context) {
        super(context);
    }

    public FloatingSubButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingSubButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingSubButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        AttrsTools.getAttrs(context, attrs, R.styleable.FloatingSubButton, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                icon = attrs.getDrawable(R.styleable.FloatingSubButton_icon);
                text = attrs.getString(R.styleable.FloatingSubButton_text);
            }
        });

        iconSize = 50;

        iv = new ImageView(context);
        iv.setBackground(icon);
        addView(iv, iconSize, iconSize);
        iv.setId(R.id.floatingIv);

        tv = new TextView(context);
        addView(tv, ViewTool.WRAP_CONTENT, ViewTool.WRAP_CONTENT);

        tv.setBackgroundColor(Color.BLACK);
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        ViewTool.setText(tv, text);

        ivShowAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fb_scale_show);
        tvShowAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fb_slide_in_from_right);
        ivHiddenAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fb_scale_hidden);
        ivHiddenAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewTool.gone(iv);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvHiddenAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fb_slide_out_to_right);
        tvHiddenAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewTool.gone(tv);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int textH = (int) (iconSize * 0.7f);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textH / 2);


        MarginLayoutParams mlp = (MarginLayoutParams) tv.getLayoutParams();
        mlp.height = textH;
        tv.setLayoutParams(mlp);

        tv.setPadding(textPadding, 0, textPadding, 0);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int right = r - this.parentIconPadding - getDistenceParentLeft();
        int left = right - iconSize;
        int bottom = b - getMarginBottom() - (totalSize - indexInParent - 1) * (iconSize + iconMargin);
        int top = bottom - iconSize;
        iv.layout(left, top, right, bottom);

        int textH = tv.getHeight();
        int tvRight = left - 20;
        int tvLeft = tvRight - tv.getWidth();
        int d = (iconSize - textH) / 2;
        int tvTop = top + d;
        int tvBottom = bottom - d;

        Bitmap bitmap = BitmapTool.createBitmap(tv.getWidth(), tv.getHeight(), Color.BLACK, 20);
        tv.setBackground(new BitmapDrawable(bitmap));

        tv.layout(tvLeft, tvTop, tvRight, tvBottom);

        if (!isShow) {
            ViewTool.gone(tv);
            ViewTool.gone(iv);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setIcon(int index, int totalSize, int iconSize, int iconPadding, int iconMargin, int subIconSize, int textPadding) {
        this.iconMargin = iconMargin;
        this.indexInParent = index - 3;
        this.totalSize = totalSize - 3;
        this.iconSize = subIconSize;
        this.parentIconSize = iconSize;
        this.parentIconPadding = iconPadding;
        this.textPadding = textPadding;
        iv.setTag(R.id.tag_0, indexInParent);

    }

    private int getDistenceParentLeft() {
        return (parentIconSize - iconSize) / 2;
    }

    private int getMarginBottom() {
        return parentIconPadding + parentIconSize + iconMargin;
    }

    public void show() {
        ViewTool.visible(iv);
        ViewTool.visible(tv);
        iv.startAnimation(ivShowAnimation);
        tv.startAnimation(tvShowAnimation);
        isShow = true;
    }

    public void hidden() {
        iv.startAnimation(ivHiddenAnimation);
        tv.startAnimation(tvHiddenAnimation);
        isShow = false;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        iv.setOnClickListener(l);
    }
}
