package me.hsy.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.hsy.MainApp;

/**
 * @author HytonightYX
 * @date 2018/12/19 14:10
 */
public class TestPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AdminSystem.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Hsy寝室管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
