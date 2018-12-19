package me.hsy.util;

/**
 * 检测密码工具类
 *
 * @author HytonightYX
 * @date 2018/12/17 21:22
 */
public class checkPasswordUtil {
    public boolean checkPassword(String input, String password) {
        return input.equals(password);
    }
}
