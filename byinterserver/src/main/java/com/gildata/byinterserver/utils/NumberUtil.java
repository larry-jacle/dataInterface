package com.gildata.byinterserver.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by chenchen on 2018/11/19.
 */
public class NumberUtil {

    public static String getRatio(int numerator, int denominator) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);

        return numberFormat.format((float)numerator / (float)denominator * 100);
    }

    public static String getNumberFormat(int number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }

    public static int clear(String number) {
        return Integer.parseInt(number.replaceAll(",", ""));
    }
}
