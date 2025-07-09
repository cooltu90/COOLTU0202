package com.codingtu.cooltu.lib4j.tool;

public class IntTool {

    /**************************************************
     *
     * 格式化数字。比如12，格式化成4位的0012
     *
     * @param number 需要格式化的数字，整数型
     * @param digits 位数
     * @return 格式化的数字
     *
     **************************************************/
    public static String format(int number, int digits) {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        int rest = digits - sb.length();
        if (rest > 0) {
            for (int i = 0; i < rest; i++) {
                sb.insert(0, "0");
            }
        }
        return sb.toString();
    }

    public static String format(Integer number, int digits) {
        if (number == null)
            throw new NullPointerException("传入的数据为空");
        return format(number, digits);
    }

}
