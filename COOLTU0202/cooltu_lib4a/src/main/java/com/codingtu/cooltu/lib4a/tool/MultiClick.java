package com.codingtu.cooltu.lib4a.tool;

import android.view.View;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.es.impl.IntegerEs;

import java.util.Comparator;

public class MultiClick {

    public static final long DIVIDER_TIME = 300;

    public static ClickItem getClickItem(int times, View.OnClickListener onClickListener) {
        return new ClickItem(times, onClickListener);
    }

    private abstract static class MyClickListener implements View.OnClickListener {
        public IntegerEs needTimesEs;
        public BaseEs<View.OnClickListener> onClickListenerEs;

        public MyClickListener(BaseEs<ClickItem> clickItemEs) {
            needTimesEs = Es.ints();
            onClickListenerEs = Es.es();
            clickItemEs.ls(new Es.EachEs<ClickItem>() {
                @Override
                public boolean each(int position, ClickItem clickItem) {
                    needTimesEs.add(clickItem.times);
                    onClickListenerEs.add(clickItem.onClickListener);
                    return false;
                }
            });

        }
    }

    private abstract static class MultiClickRunnable implements Runnable {
        protected View view;
        protected View.OnClickListener onClickListener;
        protected int times;

        public MultiClickRunnable(View view, View.OnClickListener onClickListener, int times) {
            this.view = view;
            this.onClickListener = onClickListener;
            this.times = times;
        }

        public void clear() {
            view = null;
            onClickListener = null;
        }
    }

    public static class ClickItem {
        private int times;
        private View.OnClickListener onClickListener;

        public ClickItem(int times, View.OnClickListener onClickListener) {
            this.times = times;
            this.onClickListener = onClickListener;
        }
    }

    private static long getLastClickTime(View v) {
        Object tag = v.getTag(R.id.tagLastClickTime);
        if (tag == null || !(tag instanceof Long)) {
            return 0;
        }
        return (long) tag;
    }

    private static int getClickTimes(View v) {
        Object tag = v.getTag(R.id.tagClickTimes);
        if (tag == null || !(tag instanceof Integer)) {
            return 0;
        }
        return (int) tag;
    }

    public static int checkTimes(View.OnClickListener rootOnclickListener, View v, int... timesArray) {
        return checkTimes(rootOnclickListener, v, Es.ints(timesArray));
    }

    private static int checkTimes(View.OnClickListener rootOnclickListener, View v, IntegerEs timesEs) {
        if (timesEs.count() == 0) {
            return 0;
        }

        Object tagCheckClickTimes = v.getTag(R.id.tagCheckClickTimes);
        if (tagCheckClickTimes != null && (tagCheckClickTimes instanceof Integer)) {
            v.setTag(R.id.tagCheckClickTimes, null);
            return (int) tagCheckClickTimes;
        }


        long nowTime = System.currentTimeMillis();
        int clickTimes = ((nowTime - getLastClickTime(v)) > DIVIDER_TIME) ? 1 : (getClickTimes(v) + 1);

        timesEs.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        }).ls(new Es.EachEs<Integer>() {
            @Override
            public boolean each(int position, Integer needTimes) {
                //前面的
                if (needTimes == clickTimes) {
                    HandlerTool.getMainHandler().postDelayed(new MultiClickRunnable(v, rootOnclickListener, needTimes) {
                        @Override
                        public void run() {
                            if (times == getClickTimes(view)) {
                                //说明可以了
                                view.setTag(R.id.tagCheckClickTimes, needTimes);
                                view.setTag(R.id.tagLastClickTime, 0);
                                view.setTag(R.id.tagClickTimes, 0);
                                onClickListener.onClick(view);
                            }
                            clear();
                        }
                    }, DIVIDER_TIME);
                }
                return false;
            }
        });
        v.setTag(R.id.tagLastClickTime, nowTime);
        v.setTag(R.id.tagClickTimes, clickTimes);
        return 0;
    }

    public static void set(View view, ClickItem... clickItems) {
        view.setOnClickListener(new MyClickListener(Es.es(clickItems)) {
            @Override
            public void onClick(View v) {
                int i = checkTimes(this, v, needTimesEs);
                int index = needTimesEs.index(i);
                View.OnClickListener byIndex = onClickListenerEs.getByIndex(index);
                if (byIndex != null) {
                    byIndex.onClick(v);
                }
            }
        });
    }

}
