package com.sans.utils;

/**
 * URI 识别工具
 * @author Sans
 */
public class UriDistinguishUtils {

    // API
    /**
     * 一级 范围
     */
    public static final String USER = "user";
    public static final String CLIENT = "client";
    public static final String GOODS = "goods";
    public static final String ORDERS = "orders";
    /**
     * 二级 类型
     */
    public static final String ADD = "add";
    public static final String DEL = "del";
    public static final String EDIT = "edit";
    public static final String SEARCH = "search";
    public static final String QRCODE = "qrcode";
    public static final String CREATE = "create";
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    public static final String FIND = "find";
    public static final String LIST = "list";
    public static final String CURRENT = "current";

    /**
     * 一级解析uri
     * @param uri
     * @return 返回人们理解的方式
     */
    public static String oneLevelAnalysis(String uri) {
        if (uri.matches(oneRegex(USER))) return "user";
        if (uri.matches(oneRegex(CLIENT))) return "client";
        if (uri.matches(oneRegex(GOODS))) return "goods";
        if (uri.matches(oneRegex(ORDERS))) return "order";
        return null;
    }

    /**
     * 二级分析
     * @param uri
     * @return 返回人们理解的方式
     */
    public static String twoLevelAnalysis(String uri){
        if (uri.matches(twoRegex(ADD))) return "add";
        if (uri.matches(twoRegex(DEL))) return "del";
        if (uri.matches(twoRegex(EDIT))) return "update";
        if (uri.matches(twoRegex(SEARCH))) return "search";
        if (uri.matches(twoRegex(QRCODE))) return "generateCode";
        if (uri.matches(twoRegex(CREATE))) return "create";
        if (uri.matches(twoRegex(REGISTER))) return "registered";
        if (uri.matches(twoRegex(LOGIN))) return "login";
        if (uri.matches(twoRegex(FIND))) return "find";
        return null;
    }

    public static String oneRegex(String str){
        return '.'+str+"(.*)";
    }
    public static String twoRegex(String str){
        return "(.+)"+str+"(.*)";
    }
}
