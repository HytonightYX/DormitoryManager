package me.hsy.util;

import javafx.scene.control.Alert;

/**
 * 弹出框工具类
 *
 * @author HytonightYX
 * @date 2018/12/19 11:32
 */
public class AlertInfoUtil {
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    public AlertInfoUtil(String alertTitle, String alertContentText) {
        alert.setTitle(alertTitle);
        alert.setContentText(alertContentText);
        alert.setHeaderText(null);
    }

    public void showAndWait() {
        alert.showAndWait();
    }

    public void show() {
        alert.show();
    }
}
