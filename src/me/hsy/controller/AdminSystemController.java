package me.hsy.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;
import me.hsy.MainApp;
import me.hsy.pojo.Admin;
import me.hsy.pojo.Room;
import me.hsy.service.AdminService;
import me.hsy.service.RoomService;
import me.hsy.util.CurrentAdminUtil;

/**
 * @author HytonightYX
 */
public class AdminSystemController {
    private AdminService adminService = new AdminService();
    private RoomService roomService = new RoomService();
    private List<Room> roomList;
    private ObservableList<Room> roomObservableList;

    private int year;
    private int month;
    private int day;
    private int second;
    private int minute;
    private int hour;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signOutBtn;

    @FXML
    private Label timeInfoLabel;

    @FXML
    private Label adminInfoLabel;

    @FXML
    private TableColumn<Room, Long> roomIdCol;

    @FXML
    private TableColumn<Room, Long> bed1Col;

    @FXML
    private TableColumn<Room, Long> bed2Col;

    @FXML
    private TableColumn<Room, Long> bed3Col;

    @FXML
    private TableColumn<Room, Long> bed4Col;

    @FXML
    private Button changePwdBtn;

    @FXML
    private Button searchRoomBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField roomIdTf;

    @FXML
    private Label studentInfoLabel;

    @FXML
    private TableView<Room> roomTable;

    @FXML
    void searchRoom(ActionEvent event) {
        long key = Long.parseLong(roomIdTf.getText());
        List<Room> searchedRoomList = roomService.findRoomByFuzzyKey(key);
        roomObservableList = FXCollections.observableList(searchedRoomList);
        roomTable.setItems(roomObservableList);
    }

    @FXML
    void reset(ActionEvent event) {
        flashRoomTable();
    }

    /**
     * 退出登录按钮事件
     * @param event
     * @throws IOException
     */
    @FXML
    void signOut(ActionEvent event) throws IOException {
        CurrentAdminUtil.setCurrentAdmin(null);
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
        Scene scene = new Scene(parent);
        MainApp.primaryStage.setScene(scene);
    }

    /**
     * 修改密码按钮事件
     * @param event
     * @throws IOException
     */
    @FXML
    void changePwd(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ResetPassword.fxml"));
        Scene scene = new Scene(parent);
        MainApp.primaryStage.setScene(scene);
    }

    /**
     * 窗口顶部显示当前时间
     */
    private void showTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            year = LocalDateTime.now().getYear();
            month = LocalDateTime.now().getMonth().getValue();
            day = LocalDateTime.now().getDayOfMonth();
            second = LocalDateTime.now().getSecond();
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            timeInfoLabel.setText(String.format("现在是 %d年%d月%d日, %02d:%02d:%02d", year, month, day, hour, minute, second));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * 刷新列表
     */
    private void flashRoomTable() {
        roomList = roomService.findRoomAll();
        roomObservableList = FXCollections.observableList(roomList);
        roomTable.setItems(roomObservableList);
    }

    @FXML
    void initialize() {
        /* 显示当前时间信息 */
        showTime();

        /* 显示欢迎信息 */
        adminInfoLabel.setText("欢迎登陆，" + CurrentAdminUtil.getCurrentAdmin().getAdminName());

        /* 初始化寝室情况列表 */
        roomIdCol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        bed1Col.setCellValueFactory(new PropertyValueFactory<>("bed1"));
        bed2Col.setCellValueFactory(new PropertyValueFactory<>("bed2"));
        bed3Col.setCellValueFactory(new PropertyValueFactory<>("bed3"));
        bed4Col.setCellValueFactory(new PropertyValueFactory<>("bed4"));

        /* 刷新列表内容 */
        flashRoomTable();

        /* 设定选中行事件 */
        roomTable.setRowFactory(e -> {
            TableRow<Room> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Room clickedRow = row.getItem();
                    System.out.println(clickedRow);
                }
            });
            return row;
        });

        assert signOutBtn != null : "fx:id=\"signOutBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert timeInfoLabel != null : "fx:id=\"timeInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert adminInfoLabel != null : "fx:id=\"adminInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert changePwdBtn != null : "fx:id=\"changePwdBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert studentInfoLabel != null : "fx:id=\"studentInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
    }
}
