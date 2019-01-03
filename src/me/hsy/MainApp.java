package me.hsy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author HytonightYX
 * @date 2018/12/17 22:09
 */
public class MainApp extends Application {
    /** 这个primaryStage当做全局变量用 */
    public static Stage primaryStage;
    /** 第二舞台， 用于放置其他功能 */
    public static Stage secondStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 第二舞台设置为模态窗口
        secondStage.initModality(Modality.APPLICATION_MODAL);
        MainApp.primaryStage = primaryStage;
        Parent parent = FXMLLoader.load(getClass().getResource("./view/Login.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Hsy寝室管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
