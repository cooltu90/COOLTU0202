package com.codingtu.cooltu.lib4a.view.flowbt;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tool.HandlerTool;
import com.codingtu.cooltu.lib4a.tool.Margins;
import com.codingtu.cooltu.lib4a.tool.MobileTool;
import com.codingtu.cooltu.lib4a.tool.ViewTool;
import com.codingtu.cooltu.lib4a.view.attrs.Attrs;
import com.codingtu.cooltu.lib4a.view.attrs.AttrsTools;
import com.codingtu.cooltu.lib4a.view.attrs.GetAttrs;
import com.codingtu.cooltu.lib4a.view.base.CoreRelativeLayout;

public class FloatingButton extends CoreRelativeLayout {

    private int iconSize;
    private boolean isShow;
    private int iconPadding;
    private View shadowView;
    private ImageView imageViewOpen;
    private ImageView imageViewClose;
    private RotateAnimation openRotation;
    private RotateAnimation closeRotation;
    private AlphaAnimation showShadowAnim;
    private AlphaAnimation hiddenShadowAnim;
    private int subIconSize;
    private int iconMargin;
    private int textPadding;

    public FloatingButton(Context context) {
        super(context);
    }

    public FloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FloatingButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        super.init(context, attrs, defStyleAttr);
        AttrsTools.getAttrs(context, attrs, R.styleable.FloatingButton, new GetAttrs() {
            @Override
            public void getAttrs(Attrs attrs) {
                iconSize = attrs.getDimensionPixelSize(R.styleable.FloatingButton_icon_size, 100);
                subIconSize = attrs.getDimensionPixelSize(R.styleable.FloatingButton_sub_icon_size, 100);
                iconPadding = attrs.getDimensionPixelSize(R.styleable.FloatingButton_icon_padding, MobileTool.dpToPx(context, 10));
                iconMargin = attrs.getDimensionPixelSize(R.styleable.FloatingButton_icon_margin, MobileTool.dpToPx(context, 10));
                textPadding = attrs.getDimensionPixelSize(R.styleable.FloatingButton_text_Padding, MobileTool.dpToPx(context, 10));
            }
        });

        float iconRotation = 225f;
        int defaultDuration = 300;

        // 添加背景阴影
        shadowView = new View(context);
        shadowView.setBackgroundColor(context.getResources().getColor(R.color.colorShadow));
        addView(shadowView, ViewTool.MATCH_PARENT, ViewTool.MATCH_PARENT);
        ViewTool.gone(shadowView);

        Drawable drawable = context.getResources().getDrawable(R.mipmap.icon_flow_bt);
        // 添加开始图标
        imageViewOpen = new ImageView(context);
        imageViewOpen.setImageDrawable(drawable);
        addView(imageViewOpen, iconSize, iconSize);
        ViewTool.inRelativeRightBottom(imageViewOpen);
        Margins.rb(imageViewOpen, iconPadding, iconPadding);
        imageViewOpen.setRotation(-iconRotation);
        ViewTool.gone(imageViewOpen);

        // 添加关闭图标
        imageViewClose = new ImageView(context);
        imageViewClose.setImageDrawable(drawable);
        addView(imageViewClose, iconSize, iconSize);
        ViewTool.inRelativeRightBottom(imageViewClose);
        Margins.rb(imageViewClose, iconPadding, iconPadding);


        openRotation = new RotateAnimation(0, -iconRotation, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        openRotation.setDuration(defaultDuration);
        openRotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewTool.visible(imageViewOpen);
                ViewTool.gone(imageViewClose);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        closeRotation = new RotateAnimation(0f, iconRotation, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        closeRotation.setDuration(defaultDuration);
        closeRotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewTool.visible(imageViewClose);
                ViewTool.gone(imageViewOpen);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        showShadowAnim = new AlphaAnimation(0f, 1f);
        showShadowAnim.setDuration(defaultDuration);
        showShadowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hiddenShadowAnim = new AlphaAnimation(1f, 0f);
        hiddenShadowAnim.setDuration(defaultDuration);
        hiddenShadowAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewTool.gone(shadowView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        shadowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden();
            }
        });

        imageViewClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        imageViewOpen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden();
            }
        });
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        for (int i = 3; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof FloatingSubButton) {
                childAt.setOnClickListener(l);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 3; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof FloatingSubButton) {
                ((FloatingSubButton) childAt).setIcon(i, getChildCount(), iconSize, iconPadding, iconMargin, subIconSize, textPadding);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void show() {
        //show
        ViewTool.visible(shadowView);
        imageViewClose.startAnimation(openRotation);
        shadowView.startAnimation(showShadowAnim);
        int time = 0;
        Handler mainHandler = HandlerTool.getMainHandler();
        for (int i = getChildCount() - 1; i >= 3; i--) {
            View childAt = getChildAt(i);
            if (childAt instanceof FloatingSubButton) {
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((FloatingSubButton) childAt).show();
                    }
                }, time);
                time += 50;
            }
        }
    }

    public void hidden() {
        imageViewOpen.startAnimation(closeRotation);
        shadowView.startAnimation(hiddenShadowAnim);
        int time = 0;
        Handler mainHandler = HandlerTool.getMainHandler();
        for (int i = 3; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof FloatingSubButton) {
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ((FloatingSubButton) childAt).hidden();
                    }
                }, time);
                time += 50;
            }
        }
    }
}
