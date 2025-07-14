package com.codingtu.cooltu.lib4a.view.combine;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.codingtu.cooltu.lib4a.R;
import com.codingtu.cooltu.lib4a.tool.ViewTool;
import com.codingtu.cooltu.lib4j.destory.OnDestroy;

public class AddMinusEt implements View.OnClickListener, OnDestroy {

    private ImageView addBt;
    private ImageView minusBt;
    private EditText et;

    public AddMinusEt(ImageView addBt, ImageView minusBt, EditText et) {
        this.addBt = addBt;
        this.minusBt = minusBt;
        this.et = et;
        addBt.setTag(R.id.tag_add_bt, true);
        addBt.setOnClickListener(this);
        minusBt.setTag(R.id.tag_add_bt, false);
        minusBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean isAdd = (boolean) v.getTag(R.id.tag_add_bt);
        if (isAdd) {
            add();
        } else {
            minus();
        }
    }

    private void minus() {
        String numStr = et.getText().toString();
        int num = 0;
        try {
            num = Integer.parseInt(numStr);
        } catch (Exception e) {

        }
        if (num <= 0) {
            num = 0;
        } else {
            num--;
        }
        setNumber(num);
    }

    private void add() {
        String numStr = et.getText().toString();
        int num = 1;
        try {
            num = Integer.parseInt(numStr) + 1;
        } catch (Exception e) {

        }
        setNumber(num);
    }

    public void setNumber(int number) {
        ViewTool.setEditTextAndSelection(et, number);
    }

    @Override
    public void onDestroy() {
        if (addBt != null)
            addBt.setOnClickListener(null);
        addBt = null;
        if (minusBt != null)
            minusBt.setOnClickListener(null);
        minusBt = null;
        et = null;
    }
}
