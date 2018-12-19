package me.hsy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import me.hsy.MainApp;
import me.hsy.pojo.Admin;
import me.hsy.service.AdminService;
import me.hsy.util.CurrentAdminUtil;

/**
 * 修改密码控制器
 *
 * @author HytonightYX
 */
public class ResetPasswordController {
    private AdminService adminService = new AdminService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label checkLabel;

    @FXML
    private TextField adminNameTf;

    @FXML
    private PasswordField adminPasswordTf;

    @FXML
    private Button cancelBtn;

    @FXML
    private PasswordField newPwd;

    @FXML
    private PasswordField confirmPwd;

    @FXML
    private Button confirmBtn;

    /** 获取输入框文本 */
    private String newPassword;
    private String confirmPassword;
    private String adminName;
    private String oldPassword;
    private Admin admin;

    private void check2Pwd() {
        newPassword = newPwd.getText();
        confirmPassword = confirmPwd.getText();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("确认密码不同！");
            checkLabel.setText("确认密码不同！");
            checkLabel.setVisible(true);
        } else {
            checkLabel.setVisible(true);
        }
    }

    private void checkAdminName() {
        if (admin == null) {
            System.out.println("用户名不存在！");
            checkLabel.setText("用户名不存在！");
            checkLabel.setVisible(true);
        } else {
            checkLabel.setVisible(true);
        }
    }

    private void checkOldPwd() {
        adminName = adminNameTf.getText();
        oldPassword = adminPasswordTf.getText();
        Admin admin = adminService.findAdminByName(adminName);
        if (!oldPassword.equals(admin.getAdminPassword())) {
            System.out.println("原始密码错误！");
            checkLabel.setText("原始密码错误！");
            checkLabel.setVisible(true);
        } else {
            checkLabel.setVisible(false);
        }
    }

    @FXML
    void cancelChangePwd(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Scene scene = new Scene(parent);
        MainApp.primaryStage.setScene(scene);
        System.out.println("用户返回了登录页面");
    }

    @FXML
    void confirmChangePwd(ActionEvent event) throws IOException {
        adminName = adminNameTf.getText();
        newPassword = newPwd.getText();
        confirmPassword = confirmPwd.getText();
        oldPassword = adminPasswordTf.getText();
        admin = adminService.findAdminByName(adminName);

        if (admin == null) {
            checkAdminName();
        } else {
            if (!newPassword.equals(confirmPassword)) {
                check2Pwd();
            } else if (!oldPassword.equals(admin.getAdminPassword())) {
                checkOldPwd();
            } else if (newPassword.length() < 6) {
                checkLabel.setText("密码长度至少6位！");
                checkLabel.setVisible(true);
            } else {
                System.out.println("密码验证成功，可以修改");
                adminService.updateAdminPasswordByName(adminName, newPassword);
                System.out.println("用户密码修改成功");

                Parent parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
                Scene scene = new Scene(parent);
                MainApp.primaryStage.setScene(scene);
                System.out.println("用户返回了登录页面");
            }
        }
    }

    @FXML
    void initialize() {
        assert adminNameTf != null : "fx:id=\"adminNameTf\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert adminPasswordTf != null : "fx:id=\"adminPasswordTf\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert newPwd != null : "fx:id=\"newPwd\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert confirmPwd != null : "fx:id=\"confirmPwd\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert confirmBtn != null : "fx:id=\"confirmBtn\" was not injected: check your FXML file 'ResetPassword.fxml'.";
        assert checkLabel != null : "fx:id=\"checkLabel\" was not injected: check your FXML file 'ResetPassword.fxml'.";

        newPassword = "";
        confirmPassword = "";
        adminName = "";
        oldPassword = "";

        adminNameTf.setText(CurrentAdminUtil.getCurrentAdmin().getAdminName());
        adminPasswordTf.setText("");
        newPwd.setText("");
        confirmPwd.setText("");
        admin = new Admin();
    }
}



