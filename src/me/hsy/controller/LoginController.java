package me.hsy.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import me.hsy.MainApp;
import me.hsy.pojo.Admin;
import me.hsy.service.AdminService;
import me.hsy.util.AlertInfoUtil;
import me.hsy.util.CurrentAdminUtil;

/**
 * Login控制器
 *
 * @author HytonightYX
 */
public class LoginController {
    private AdminService adminService = new AdminService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField adminNameTf;

    @FXML
    private Button loginBtn;

    @FXML
    private Label adminPasswordLabel;

    @FXML
    private Label adminNameLabel;

    @FXML
    private Button resetPasswordBtn;

    @FXML
    private PasswordField adminPasswordTf;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        Admin admin = adminService.findAdminByName(adminNameTf.getText());
        if (admin != null) {
            if (admin.getAdminPassword().equals(adminPasswordTf.getText())) {
                System.out.println("登陆成功" + admin.getAdminName());
                if (!admin.isChanged()) {
                    System.out.println("您还未修改密码， 请修改");

                    new AlertInfoUtil("提示",
                            "检测到您是首次登陆,请修改初始密码").show();

                    // 前往修改密码界面
                    Parent parent = FXMLLoader.load(getClass().getResource("../view/ResetPassword.fxml"));
                    Scene scene = new Scene(parent);
                    MainApp.primaryStage.setScene(scene);
                } else {
                    // 前往到管理员界面
                    CurrentAdminUtil.setCurrentAdmin(admin);
                    Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminSystem.fxml"));
                    Scene scene = new Scene(parent);
                    MainApp.primaryStage.setScene(scene);
                }
            } else {
                new AlertInfoUtil("提示",
                        "用户名或密码错误！").show();
                System.out.println("用户名或密码错误！");
            }
        } else {
            new AlertInfoUtil("警告",
                    "信息不完整，请重新输入！").show();
        }
    }

    @FXML
    void handleResetPassword(ActionEvent event) throws IOException {
        // 前往修改密码界面
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ResetPassword.fxml"));
        Scene scene = new Scene(parent);
        MainApp.primaryStage.setScene(scene);
    }

    @FXML
    void initialize() {
        assert adminNameTf != null : "fx:id=\"adminName\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert adminPasswordLabel != null : "fx:id=\"adminPasswordLabel\" was not injected: check your FXML file 'Login.fxml'.";
        assert adminNameLabel != null : "fx:id=\"adminNameLabel\" was not injected: check your FXML file 'Login.fxml'.";
        assert resetPasswordBtn != null : "fx:id=\"resetPasswordBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert adminPasswordTf != null : "fx:id=\"adminPassword\" was not injected: check your FXML file 'Login.fxml'.";

        adminNameTf.setText(CurrentAdminUtil.getCurrentAdmin().getAdminName());
        adminPasswordTf.setText("");
    }
}
