package com.sans.utils;

import java.io.UnsupportedEncodingException;

/**
 * 排序工具
 * @author Sans
 */
public class SortUtils {

    /**
     * 汉字排序
     */
    static final int GB_SP_DIFF = 160;
    /**
     * 存放国标一级汉字不同读音的起始区位码
     */
    public static final int[] SEC_POS_VALUE_LIST = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635,
            3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };
    /**
     * 存放国标一级汉字不同读音的起始区位码对应读音
     */
    public static final char[] FIRST_LETTER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'W', 'X', 'Y', 'Z' };

    /**
     * 获取首字母拼写
     * @param str 字符串
     * @return 单个字符单词
     */
    public static String getFirstSpells(String str){
        StringBuilder buffer = new StringBuilder();
        char ch = str.charAt(0);
        // 判断是否为汉字
        if ((ch >> 7) == 0) {
            buffer.append(ch);
        }else{
            buffer.append(getFirstLetter(ch));
        }
        return buffer.toString();
    }

    /**
     * 提取汉字字符串的首字母
     * @param characters 汉字字符串
     * @return 字母拼写
     */
    public static String getSpells(String characters) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < characters.length(); i++) {

            char ch = characters.charAt(i);
            if ((ch >> 7) == 0) {
                // 判断是否为汉字，如果左移7位为0就不是汉字，否则是汉字
                buffer.append(ch);
            } else {
                buffer.append(getFirstLetter(ch));
            }
        }
        return buffer.toString();
    }

    /**
     * 获取一个汉字的首字母
     * @param ch 汉字
     * @return 单个拼写单词
     */
    public static Character getFirstLetter(char ch) {
        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        // 非汉字
        if (uniCode[0] < 128 && uniCode[0] > 0) {
            return null;
        } else {
            return convert(uniCode);
        }
    }

    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */
    private static char convert(byte[] bytes) {
        char result = '#';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= SEC_POS_VALUE_LIST[i] && secPosValue < SEC_POS_VALUE_LIST[i + 1]) {
                result = FIRST_LETTER[i];
                break;
            }
        }
        return result;
    }


}
