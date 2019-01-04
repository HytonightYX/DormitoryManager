package me.hsy.util;

import java.time.LocalDateTime;

/**
 * 获取当前时间工具类
 *
 * @author HytonightYX
 * @date 2019/1/4 15:26
 */
public class LocalDateTimeUtil {
    public static String getTime() {
        String time = LocalDateTime.now().toString().replace('T', ' ');
        return time.substring(0, time.length() - 4);
    }
}
