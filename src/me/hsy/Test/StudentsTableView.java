package me.hsy.Test;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;

/**
 * @author HytonightYX
 * @date 2018/12/17 9:23
 */
public class StudentsTableView extends Application {
    private StudentService studentService = new StudentService();

    private TableView<Student> studentTable = new TableView<Student>();
    private final ObservableList<Student> studentObservableList = FXCollections.observableArrayList((new StudentService()).findStudentAll());

    private void initData() {
        Student student = new Student();
    }

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Students Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);

        final Label label = new Label("Students Infomation");
        label.setFont(new Font("Arial", 20));

        TableColumn stuIdCol = new TableColumn("ID");
        stuIdCol.setMinWidth(100);
        stuIdCol.setCellValueFactory(new PropertyValueFactory<Student, String>("stuId"));

        TableColumn stuNameCol = new TableColumn("Name");
        stuNameCol.setMinWidth(100);
        stuNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("stuName"));

        TableColumn stuCollegeCol = new TableColumn("College");
        stuCollegeCol.setMinWidth(200);
        stuCollegeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("stuCollege"));

        studentTable.setItems(studentObservableList);
        studentTable.getColumns().addAll(stuIdCol, stuNameCol, stuCollegeCol);
        studentTable.setPrefHeight(300);

        final Button setEmailButton = new Button("Set first email in table to Alibaba");
        setEmailButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if (studentObservableList.size() > 0) {
                    long id = studentObservableList.get(0).getStuId();
                    Student thisStudent = studentService.findStudentById(id);
                    thisStudent.setStuDepartment("阿里巴巴商学院");
                    studentService.updateStudent(thisStudent);
                }
            }
        });

        final Button removeRowButton = new Button("Remove first row from the table");
        removeRowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                if (studentObservableList.size() > 0) {
                    studentObservableList.remove(0);
                }
            }
        });

        studentObservableList.addListener((ListChangeListener) change -> System.out.println("Detected a change! "));

        final Button resetButton = new Button("Reset table data");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                initData();
            }
        });

        final VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, studentTable, setEmailButton, removeRowButton, resetButton);

        stage.setScene(new Scene(new Group(vbox)));
        stage.show();
    }
}