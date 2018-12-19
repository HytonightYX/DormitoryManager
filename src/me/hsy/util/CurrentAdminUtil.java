package me.hsy.util;

import me.hsy.pojo.Admin;

import java.util.Currency;

/**
 * 创建、保存当前管理员信息
 *
 * @author HytonightYX
 * @date 2018/12/19 19:09
 */
public class CurrentAdminUtil {
    private static Admin currentAdmin;

    /** 获取当前管理员 */
    public static Admin getCurrentAdmin() {
        if (currentAdmin == null) {
            currentAdmin = new Admin();
        }
        return currentAdmin;
    }

    /** 设定当前管理员 */
    public static void setCurrentAdmin(Admin admin) {
        currentAdmin = admin;
    }
}
