package com.codingtu.cooltu.lib4a.tool;

import com.codingtu.cooltu.lib4a.CorePreferences;

public class PfTool {

    private static CorePreferences pf;

    public static CorePreferences pf() {
        if (pf == null)
            pf = new CorePreferences();
        return pf;
    }


}
