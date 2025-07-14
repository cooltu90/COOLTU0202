package com.codingtu.cooltu.lib4a.tool;

import android.widget.EditText;
import android.widget.TextView;

import com.codingtu.cooltu.lib4a.exception.CheckException;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup0;
import com.codingtu.cooltu.lib4a.view.radiogroup.RadioGroup1;
import com.codingtu.cooltu.lib4j.tool.StringTool;

public class CheckTool {

    public static String editText(EditText et, String prompt) throws CheckException {
        String value = et.getText().toString();
        if (StringTool.isBlank(value)) {
            throw new CheckException(prompt);
        }
        return value;
    }

    public static String textView(TextView tv, String prompt) throws CheckException {
        String value = tv.getText().toString();
        if (StringTool.isBlank(value)) {
            throw new CheckException(prompt);
        }
        return value;
    }

    public static int radioGroup(RadioGroup0 radioGroup, String prompt) throws CheckException {
        int selected = radioGroup.getSelected();
        if (selected == -1) {
            throw new CheckException(prompt);
        }
        return selected;
    }

    public static int radioGroup(RadioGroup1 radioGroup, String prompt) throws CheckException {
        int selected = radioGroup.getSelected();
        if (selected == -1) {
            throw new CheckException(prompt);
        }
        return selected;
    }


}
