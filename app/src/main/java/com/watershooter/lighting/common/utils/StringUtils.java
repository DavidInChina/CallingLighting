package com.watershooter.lighting.common.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String EMAIL_REGULAR_EXPRESSION = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    private static final String PHONE_REGULAR_EXPRESSION = "^[1][3-9]{1}[0-9]{9}$";
    private static final String ALL_NUMBER = "^[-\\d]*$";

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmail(String paramString) {
        if (TextUtils.isEmpty(paramString)) {
            return false;
        }
        return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(paramString).matches();
    }

    private static boolean isMatch(String paramString1, String paramString2) {
        if ((paramString2 == null) || (paramString2.trim().equals(""))) {
            return false;
        }
        return Pattern.compile(paramString1).matcher(paramString2).matches();
    }

    public static boolean isNumber(String paramString) {
        return (isPositiveInteger(paramString)) || (isPositiveDecimal(paramString));
    }

    public static boolean isInputNumber(String paramString) {
        return (isPositiveInteger(paramString)) || (isPositiveDecimalTwo(paramString));
    }

    public static boolean isPhoneNumber(String paramString) {
        if (TextUtils.isEmpty(paramString)) {
            return false;
        }
        return Pattern.compile("^[1][3-9]{1}[0-9]{9}$").matcher(paramString).matches();
    }

    public static boolean isMobileAndHomeNumber(String num) {
        if (TextUtils.isEmpty(num)) {
            return false;
        }
        return isNumeric(num);
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 去除多余分隔符空格或者其他
     *
     * @param oldNum 筛选之前的号码
     * @return
     */
    public static String getNumber(String oldNum) {
        StringBuffer buffer = new StringBuffer();
        char arr[] = oldNum.toCharArray();
        for (char character : arr) {
            if (isNumeric(String.valueOf(character)) || '-' == character) {
                buffer.append(character);
            }
        }
        return buffer.toString();
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    public static boolean isPositiveDecimal(String paramString) {
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", paramString);
    }

    public static boolean isPositiveDecimalTwo(String paramString) {
        return isMatch("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$", paramString);
    }

    public static boolean isPositiveInteger(String paramString) {
        return isMatch("^\\+{0,1}[1-9]\\d*", paramString);
    }

    public static String getTwoNum(String num) {
        DecimalFormat df = new DecimalFormat("#.##");
        double d = Double.valueOf(num);
        return df.format(d) + "";
    }
}