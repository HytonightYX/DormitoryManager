package me.hsy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * @author HytonightYX
 * @date 2018/12/17 22:09
 */
public class MainApp extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.primaryStage = primaryStage;
        Parent parent = FXMLLoader.load(getClass().getResource("./view/Login.fxml"));
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Hsy寝室管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
