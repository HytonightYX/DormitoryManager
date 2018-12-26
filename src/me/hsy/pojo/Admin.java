package me.hsy.pojo;

/**
 * 管理员 pojo
 *
 * @author HytonightYX
 * @date 2018/12/14 10:32
 */
public class Admin {
    /** 管理员账号 */
    private String adminName;

    /** 管理员密码 */
    private String adminPassword;

    /** 是否已修改密码 */
    private boolean isChanged;

    public Admin() {
        adminName = "";
        adminPassword = "";
        isChanged = false;
    }

    public Admin(String adminName, String adminPassword, boolean isChanged) {
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.isChanged = isChanged;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", isChanged=" + isChanged +
                '}';
    }
}