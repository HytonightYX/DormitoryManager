package me.hsy.test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import me.hsy.pojo.Admin;
import me.hsy.pojo.Student;
import me.hsy.service.AdminService;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/17 9:23
 */
public class AdminTableView extends Application {
    private TableView<Admin> adminTable = new TableView<Admin>();
    private List<Admin> admins = (new AdminService()).findAdminAll();
    private final ObservableList<Admin> adminObservableList = FXCollections.observableArrayList(admins);

    private void initData() {
//        adminObservableList.setAll(
//                new Admin("admin", "admin", 1),
//                new Admin("admin2", "admin2", 0),
//                new Admin("admin3", "admin3", 1)
//        );
    }

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {
        for (Admin a : admins) {
            System.out.println(a);
        }

        stage.setTitle("Admin Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);

        final Label label = new Label("Admin Infomation");
        label.setFont(new Font("Arial", 20));

        TableColumn adminNameCol = new TableColumn("AdminName");
        adminNameCol.setMinWidth(100);
        adminNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("adminName"));

        TableColumn adminPassWordCol = new TableColumn("password");
        adminPassWordCol.setMinWidth(100);
        adminPassWordCol.setCellValueFactory(new PropertyValueFactory<Student, String>("adminPassword"));

        TableColumn changedCol = new TableColumn("changed");
        changedCol.setMinWidth(100);
        changedCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("isChanged"));


//        TableColumn stuCollegeCol = new TableColumn("College");
//        stuCollegeCol.setMinWidth(200);
//        stuCollegeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("stuCollege"));

        adminTable.setItems(adminObservableList);
        adminTable.getColumns().addAll(adminNameCol, adminPassWordCol);
        adminTable.setPrefHeight(300);

        final Button setEmailButton = new Button("setAdminPassword Alibaba");
        setEmailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if (adminObservableList.size() > 0) {
                    adminObservableList.get(0).setAdminPassword("Alibaba");
                }
            }
        });

        final Button removeRowButton = new Button("Remove first row from the table");
        removeRowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if (adminObservableList.size() > 0) {
                    adminObservableList.remove(0);
                }
            }
        });

        final Button resetButton = new Button("Reset table data");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                initData();
            }
        });

        final VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, adminTable, setEmailButton, removeRowButton, resetButton);

        stage.setScene(new Scene(new Group(vbox)));
        stage.show();
    }
}