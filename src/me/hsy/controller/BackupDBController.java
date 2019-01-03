package me.hsy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import me.hsy.MainApp;
import me.hsy.util.AlertInfoUtil;
import me.hsy.util.BackupDataBaseUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 备份数据库界面控制器
 *
 * @author HytonightYX
 * @date 2019/1/3 18:16
 */
public class BackupDBController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField DBNameTf;

    @FXML
    private Button cancelBtn;

    @FXML
    private PasswordField userPasswordTf;

    @FXML
    private TextField userNameTf;

    @FXML
    private TextField backupPathTf;

    @FXML
    private TextField fileNameTf;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button choiceBackupPathBtn;

    @FXML
    void choiceBackupPath(ActionEvent event) {
        Stage stage = new Stage();
        DirectoryChooser directoryChooser=new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        //选择的文件夹路径
        String path = file.getPath();
        backupPathTf.setText(path);
    }

    @FXML
    void cancel(ActionEvent event) {
        MainApp.secondStage.close();
    }

    @FXML
    void confirm(ActionEvent event) {
        String userName = userNameTf.getText();
        String userPassword = userPasswordTf.getText();
        String DBName = DBNameTf.getText();
        String backupPath = backupPathTf.getText();
        String fileName = fileNameTf.getText();

        if ("".equals(userName) || "".equals(DBName) || "".equals(backupPath) || "".equals(fileName)) {
            new AlertInfoUtil("提示", "数据库信息填写不完整！").showAndWait();
        } else {
            BackupDataBaseUtil.dbBackUp(userName, userPassword, DBName, backupPath, fileName);
        }
    }

    @FXML
    void initialize() {

        /* 初始化TextField */
        userNameTf.setText("root");
        userPasswordTf.setText("admin");
        DBNameTf.setText("dormitory");
        backupPathTf.setText("");
        fileNameTf.setText("dormitory");

        assert DBNameTf != null : "fx:id=\"DBNameTf\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert userPasswordTf != null : "fx:id=\"userPasswordTf\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert userNameTf != null : "fx:id=\"userNameTf\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert backupPathTf != null : "fx:id=\"backupPathTf\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert confirmBtn != null : "fx:id=\"confirmBtn\" was not injected: check your FXML file 'BackupDB.fxml'.";
        assert choiceBackupPathBtn != null : "fx:id=\"choiceBackupPathBtn\" was not injected: check your FXML file 'BackupDB.fxml'.";
    }
}
