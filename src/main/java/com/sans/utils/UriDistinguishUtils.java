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
    private static final String USER = "user";
    private static final String CLIENT = "client";
    private static final String GOODS = "goods";
    private static final String ORDERS = "orders";
    /**
     * 二级 类型
     */
    private static final String ADD = "add";
    private static final String DEL = "del";
    private static final String EDIT = "edit";
    private static final String SEARCH = "search";
    private static final String QRCODE = "qrcode";
    private static final String CREATE = "create";
    private static final String REGISTER = "register";
    private static final String LOGIN = "login";
    private static final String FOND = "find";

    /**
     * 一级解析uri
     * @param uri
     * @return 返回人们理解的方式
     */
    public static String oneLevelAnalysis(String uri) {
        if (uri.matches(oneRegex(USER))) return "用户";
        if (uri.matches(oneRegex(CLIENT))) return "客户";
        if (uri.matches(oneRegex(GOODS))) return "商品";
        if (uri.matches(oneRegex(ORDERS))) return "订单";
        return null;
    }

    /**
     * 二级分析
     * @param uri
     * @return 返回人们理解的方式
     */
    public static String twoLevelAnalysis(String uri){
        if (uri.matches(twoRegex(ADD))) return "添加";
        if (uri.matches(twoRegex(DEL))) return "删除";
        if (uri.matches(twoRegex(EDIT))) return "编辑";
        if (uri.matches(twoRegex(SEARCH))) return "查找";
        if (uri.matches(twoRegex(QRCODE))) return "生成码";
        if (uri.matches(twoRegex(CREATE))) return "创建";
        if (uri.matches(twoRegex(REGISTER))) return "注册";
        if (uri.matches(twoRegex(LOGIN))) return "登录";
        if (uri.matches(twoRegex(FOND))) return "查找";
        return null;
    }

    public static String oneRegex(String str){
        return '.'+str+"(.*)";
    }
    public static String twoRegex(String str){
        return "(.+)"+str+"(.*)";
    }



}
