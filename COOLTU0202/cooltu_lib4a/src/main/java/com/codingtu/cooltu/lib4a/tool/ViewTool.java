package com.codingtu.cooltu.lib4a.tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.ResultReceiver;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.config.CoreApp;
import com.codingtu.cooltu.lib4a.data.WH;
import com.codingtu.cooltu.lib4a.image.BitmapTool;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.StringEs;
import com.codingtu.cooltu.lib4j.tool.StringTool;
import com.codingtu.cooltu.lib4j.tool.TagTool;

public class ViewTool {
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    /****************************************************************
     *
     * 可见与不可见
     *
     ****************************************************************/

    //设置view状态
    public static void visible(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void visibleOnUiThread(View view) {
        if (view != null) {
            HandlerTool.getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public static void gone(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    public static void gone(View view, long delayMillis) {
        if (view != null) {
            HandlerTool.getMainHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                }
            }, delayMillis);
        }
    }

    public static void goneInUiThread(View view) {
        if (view != null) {
            HandlerTool.getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                }
            });
        }
    }

    public static void invisible(View view) {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    //判断view当前状态，visible，gone或者invisible
    public static boolean isVisible(View view) {
        return checkStatus(view, View.VISIBLE);
    }

    public static boolean isGone(View view) {
        return checkStatus(view, View.GONE);
    }

    public static boolean isInvisible(View view) {
        return checkStatus(view, View.INVISIBLE);
    }

    public static boolean checkStatus(View view, int checkStatus) {
        if (view != null) {
            return view.getVisibility() == checkStatus;
        }
        return false;
    }

    //切换view状态
    public static void visibleOrGone(View view, boolean isVisible) {
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    public static void visibleOrInvisible(View view, boolean isVisible) {
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /****************************************************************
     *
     * 输入法的收起
     *
     ****************************************************************/
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    public static void inputShow(EditText et) {
        inputShow(et, null);
    }

    public static void inputShow(EditText et, ResultReceiver resultReceiver) {
        if (et != null) {
            HandlerTool.getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = SystemTool.getInputMethodManager();
                    imm.showSoftInput(et, InputMethodManager.SHOW_FORCED, resultReceiver);
                }
            });
        }
    }

    public static void inputHidden(EditText et) {
        if (et != null) {
            InputMethodManager imm = SystemTool.getInputMethodManager();
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }
    }

    public static void inputHidden(Activity context) {
        if (context != null) {
            InputMethodManager imm = SystemTool.getInputMethodManager();
            imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
        }
    }


    public static final int PASSWORD_TYPE_NO_SEE = InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT;
    public static final int PASSWORD_TYPE_SEE = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;

    public static boolean isPasswordNoSee(EditText et) {
        return et.getInputType() == PASSWORD_TYPE_NO_SEE;
    }

    public static void setPasswordNoSee(EditText et) {
        et.setInputType(PASSWORD_TYPE_NO_SEE);
    }

    public static void setPasswordSee(EditText et) {
        et.setInputType(PASSWORD_TYPE_SEE);
    }

    public static boolean passwordShowTypeChange(EditText et) {
        et.setTypeface(Typeface.SANS_SERIF);
        if (isPasswordNoSee(et)) {
            //普通模式
            setPasswordSee(et);
            setEditTextSelection(et);

            return true;
        } else {
            //密码模式
            setPasswordNoSee(et);
            setEditTextSelection(et);
            return false;
        }
    }

    public static void passwordShowTypeChange(EditText et, View bt, int seeId, int noSeeId) {
        if (isPasswordNoSee(et)) {
            //普通模式
            setPasswordSee(et);
            bt.setBackgroundResource(seeId);
        } else {
            //密码模式
            setPasswordNoSee(et);
            bt.setBackgroundResource(noSeeId);
        }
        et.setTypeface(Typeface.SANS_SERIF);
        setEditTextSelection(et);
    }

    private static void setEditTextSelection(EditText et) {
        int length = et.getText().length();
        et.setSelection(length);
    }

    /****************************************************************
     *
     * 设置Textview
     *
     ****************************************************************/

    public static void setTextUnderLine(TextView tv) {
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv.getPaint().setAntiAlias(true);//抗锯齿
    }

    public static void setText(View tv, Object text) {
        if (tv != null && tv instanceof TextView) {
            ((TextView) tv).setText(StringTool.toString(text));
        }
    }

    public static void setText(View tv, String line, Object... tags) {
        setText(tv, TagTool.dealLine(line, tags));
    }


    public static void setTextColor(View tv, int id) {
        if (tv != null && tv instanceof TextView) {
            ((TextView) tv).setTextColor(CoreApp.APP.getResources().getColor(id));
        }
    }

    public static void setEditTextAndSelection(View view, Object obj) {
        if (view != null && view instanceof EditText) {
            String text = StringTool.toString(obj);
            setText(view, text);
            EditText et = (EditText) view;
            int length = et.getText().length();
            if (length != et.getSelectionEnd()) {
                et.setSelection(length);
            }
        }
    }

    public static void setEditTextAndSelection(View view, String line, Object... tags) {
        setEditTextAndSelection(view, TagTool.dealLine(line, tags));
    }


    /****************************************************************
     *
     * 获得rootView
     *
     ****************************************************************/

    public static View getRootView(Activity act) {
        return ((ViewGroup) act.getWindow().getDecorView().findViewById(android.R.id.content))
                .getChildAt(0);
    }

    public static ViewGroup getRootViewGroup(Activity act) {
        return (ViewGroup) getRootView(act);
    }

    /****************************************************************
     *
     * view宽高
     *
     ****************************************************************/
    public static void setW(View view, int width) {
        if (view == null)
            return;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = width;
        view.setLayoutParams(lp);
    }

    public static void setH(View view, int height) {
        if (view == null)
            return;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
    }

    /****************************************************************
     *
     * 截取控件的画面
     *
     ****************************************************************/
    public static Bitmap capture(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(); //启用DrawingCache并创建位图
        Bitmap screen = Bitmap.createBitmap(view.getDrawingCache()); //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        view.setDrawingCacheEnabled(false); //禁用DrawingCahce否则会影响性能
        return screen;
    }

    public static void setWH(View view, int width, int height) {
        if (view == null)
            return;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = width;
        lp.height = height;
        view.setLayoutParams(lp);
    }

    public static void setLayout(ImageView view, Rect rect) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        mlp.width = rect.width();
        mlp.height = rect.height();
        mlp.leftMargin = rect.left;
        mlp.topMargin = rect.top;
        view.setLayoutParams(mlp);
    }

    public static void setBlur(View bgView, ImageView blurIv, Rect rect) {
        ViewTool.setLayout(blurIv, rect);
        //捕获背景图
        Bitmap bitmap = ViewTool.capture(bgView);
        //对背景图进行模糊
        bitmap = BitmapTool.blur(bitmap);
        //对模糊的图片进行裁切
        bitmap = BitmapTool.cutBitmap(bitmap, rect);
        //将模糊的图片设置给imageview
        blurIv.setImageBitmap(bitmap);
    }

    public static void inRelativeCenter(View view) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, view.getId());
        view.setLayoutParams(lp);
    }

    public static void inRelativeCenterVertical(View view) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.addRule(RelativeLayout.CENTER_VERTICAL, view.getId());
        view.setLayoutParams(lp);
    }

    public static void inRelativeRightBottom(View view) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, view.getId());
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, view.getId());
        view.setLayoutParams(lp);
    }

    public static View[] getChildren(int skip, ViewGroup vp) {
        int count = vp.getChildCount();
        View[] views = new View[count];
        for (int i = skip; i < count; i++) {
            views[i] = vp.getChildAt(i);
        }
        return views;
    }

    public static View[] getChildren(ViewGroup... vps) {
        int count = 0;
        for (int i = 0; i < vps.length; i++) {
            ViewGroup vp = vps[i];
            count += vp.getChildCount();
        }

        View[] views = new View[count];

        count = 0;
        int childCount = 0;
        for (int i = 0; i < vps.length; i++) {
            ViewGroup vp = vps[i];
            childCount = vp.getChildCount();
            for (int j = 0; j < childCount; j++) {
                views[count + j] = vp.getChildAt(j);
            }
            count += childCount;
        }
        return views;
    }

    public static void addToAct(Activity act, View view) {
        if (act == null || view == null)
            return;
        getRootViewGroup(act).addView(view, ViewTool.MATCH_PARENT, ViewTool.MATCH_PARENT);
    }

    public static void removeFromAct(Activity act, View view) {
        if (act == null || view == null)
            return;
        getRootViewGroup(act).removeView(view);
    }

    /***************************************
     *
     *
     *
     ***************************************/
    public static interface ViewComplete {
        public void viewComplete();
    }

    public static void completeView(View view, ViewComplete viewComplete) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                viewComplete.viewComplete();
            }
        });
    }

    /***************************************
     *
     *
     *
     ***************************************/
    public static WH getTextWH(TextView tv, String text) {

        if (tv == null || text == null) {
            return new WH();
        }

        Rect bounds = new Rect();
        tv.getPaint().getTextBounds(text, 0, text.length(), bounds);
        return new WH(bounds.width(), bounds.height());
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static void arrangeView(RelativeLayout rl, int perRow, int left, int top, int divider) {
        completeView(rl, new ViewComplete() {
            @Override
            public void viewComplete() {
                int currentCount = 0;
                for (int i = 0; i < rl.getChildCount(); i++) {
                    View view = rl.getChildAt(i);
                    int l = left + (view.getWidth() + divider) * currentCount;
                    int t = top + (view.getHeight() + divider) * (i / perRow);
                    Margins.lt(view, l, t);
                    currentCount++;
                    if (currentCount >= perRow) {
                        currentCount = 0;
                    }
                }
            }
        });
    }

    /**************************************************
     *
     *
     *
     **************************************************/

    public static View[] getRadioGroupViews(int viewId, ViewGroup viewGroup) {
        BaseEs<View> viewEs = Es.es();
        getRadioGroupViews(viewId, viewEs, viewGroup);
        return viewEs.toArray();
    }


    private static void getRadioGroupViews(int viewId, BaseEs<View> viewEs, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView.getId() == viewId) {
                viewEs.add(childView);
            } else if (childView instanceof ViewGroup) {
                getRadioGroupViews(viewId, viewEs, (ViewGroup) childView);
            }
        }
    }

    public static String[] getRadioGroupItems(ViewGroup viewGroup) {
        StringEs itemEs = Es.strs();
        getRadioGroupItems(itemEs, viewGroup);
        return itemEs.toArray();
    }

    private static void getRadioGroupItems(StringEs itemEs, ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof TextView) {
                itemEs.add(((TextView) childView).getText().toString());
            } else if (childView instanceof ViewGroup) {
                getRadioGroupItems(itemEs, (ViewGroup) childView);
            }
        }
    }

    public static String[] getRadioGroupItems(int viewId, ViewGroup viewGroup) {
        return Es.es(getRadioGroupViews(viewId, viewGroup)).convert(new Es.Convert<View, String>() {
            @Override
            public String convert(int index, View view) {
                if (view instanceof TextView) {
                    return ((TextView) view).getText().toString();
                }
                return null;
            }
        }).toArray();
    }

    /**************************************************
     *
     **************************************************/
    public static View getChild(ViewGroup vp, int... indexs) {
        int count = indexs.length;
        View childAt = vp;
        for (int i = 0; i < count; i++) {
            childAt = ((ViewGroup) childAt).getChildAt(indexs[i]);
        }
        return childAt;
    }

    public static ViewGroup getChildViewGroup(ViewGroup vp, int... indexs) {
        return (ViewGroup) getChild(vp, indexs);
    }
}
