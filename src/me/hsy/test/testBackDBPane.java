package me.hsy.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author HytonightYX
 * @date 2019/1/3 18:23
 */
public class testBackDBPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/BackupDB.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("备份数据库");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
