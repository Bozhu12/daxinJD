package com.sans.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志信息缓存对象
 * @author Sans
 */
@Slf4j
public class LogMsgUtils {

    public static Map<String, Object> logMessage = new HashMap<>();

    public static void logOutput(String msg) {
        // 日志期望格式 : 日期-时间-操作人-2级Name-1级Name
        log.info(logMessage.get("userName")+"  "+logMessage.get("typeName")+" "+logMessage.get("operationName")+" "+msg);
        logMessage.clear();
    }

    public static StringBuffer readLogFile(){
        StringBuffer src = new StringBuffer();
        try {
            FileReader reader = new FileReader("my.log");
            char[] ch = new char[1024];
            int len = reader.read(ch);
            while (len != -1) {
                src.append(new String(ch, 0, len));
                len = reader.read(ch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return src;
    }



}
