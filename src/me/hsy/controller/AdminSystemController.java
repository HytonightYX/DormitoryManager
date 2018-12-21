package me.hsy.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


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
import me.hsy.pojo.Bed;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.AdminService;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;
import me.hsy.util.AlertInfoUtil;
import me.hsy.util.CurrentAdminUtil;
import org.apache.ibatis.jdbc.Null;

/**
 * @author HytonightYX
 */
public class AdminSystemController {
    /** 各个service层实例，用来调用各种数据操作方法 */
    private AdminService adminService = new AdminService();
    private RoomService roomService = new RoomService();
    private StudentService studentService = new StudentService();

    /** 存储寝室概况的列表 */
    private List<Room> roomList;
    private ObservableList<Room> roomObservableList;

    /** 存储寝室详情的列表 1个寝室有4张床位*/
    private Bed[] beds = new Bed[4];
    private List<Bed> bedArrayList = new ArrayList<>(5);
    private ObservableList<Bed> bedObservableList;

    /** 存储时间 */
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
    private Label rightRoomIdLabel;

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private Tab checkInTab;

    @FXML
    private Tab checkOutTab;

    @FXML
    private TableView<Bed> studentTable;

    @FXML
    private TableColumn<Bed, String> rightBedCol;

    @FXML
    private TableColumn<Bed, Long> sidCol;

    @FXML
    private TableColumn<Bed, String> snameCol;

    @FXML
    private TableColumn<Bed, String> scollegeCol;

    @FXML
    private TableColumn<Bed, String> sclassCol;

    @FXML
    private TableColumn<Bed, String> scheckInTimeCol;

    @FXML
    void searchRoom(ActionEvent event) {
        String num = roomIdTf.getText();
        // 确保输入框中输入了内容
        try {
            long key = Long.parseLong(num);
            List<Room> searchedRoomList = roomService.findRoomByFuzzyKey(key);
            roomObservableList = FXCollections.observableList(searchedRoomList);
            roomTable.setItems(roomObservableList);
        } catch (NumberFormatException e) {
            new AlertInfoUtil("警告","请输入正确的房间号").showAndWait();
            System.out.println("用户输入了不合法的值，已警告");
        }
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
     * 刷新左侧寝室列表
     */
    private void flashRoomTable() {
        roomList = roomService.findRoomAll();
        roomObservableList = FXCollections.observableList(roomList);
        roomTable.setItems(roomObservableList);
    }

    /**
     * 初始化4张床位
     */
    private void init4Bed() {
        beds[0] = new Bed("一号床");
        beds[1] = new Bed("二号床");
        beds[2] = new Bed("三号床");
        beds[3] = new Bed("四号床");
    }

    /**
     * 刷新右侧寝室详情列表
     */
    private void flash4BedTable(Room room) {
        Student student = null;
        int index = 0;

        // 将4个学号存入数组， 0表示该床位暂时空置
        Long[] bedList = {room.getBed1(), room.getBed2(), room.getBed3(), room.getBed4()};

        // 先初始化床位，避免与上次信息产生冲突
        init4Bed();

        // 设定四张床
        for (long bedId: bedList) {
            student = studentService.findStudentById(bedId);
            if (student != null) {
                String time = student.getCheckInTime().toString();
                beds[index].setSid(String.valueOf(student.getStuId()));
                beds[index].setSname(student.getStuName());
                beds[index].setScollege(student.getStuCollege());
                beds[index].setSclass(student.getStuClass());
                beds[index].setScheckInTime(time.substring(0, time.length() - 2));
            }
            index++;
        }
        // 将床位对象加入列表
        bedArrayList = Arrays.asList(beds);
        System.out.println(bedArrayList);
        bedObservableList = FXCollections.observableList(bedArrayList);

        // 设置右侧寝室详情列表显示床位列表
        studentTable.setItems(bedObservableList);
        rightRoomIdLabel.setText(room.getRoomId() + " 寝室");
    }

    /**
     * 办理入住方法
     */
    public void checkIn() {

    }


    /**
     * 办理退房方法
     */
    public void checkOut() {

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

        /* 初始化右侧寝室详情列表 */
        rightBedCol.setCellValueFactory(new PropertyValueFactory<>("bedNumber"));
        sidCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        snameCol.setCellValueFactory(new PropertyValueFactory<>("sname"));
        scollegeCol.setCellValueFactory(new PropertyValueFactory<>("scollege"));
        sclassCol.setCellValueFactory(new PropertyValueFactory<>("sclass"));
        scheckInTimeCol.setCellValueFactory(new PropertyValueFactory<>("scheckInTime"));

        /* 刷新列表内容 */
        flashRoomTable();

        /* 设定左侧列表选中行事件 */
        roomTable.setRowFactory(e -> {
            TableRow<Room> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Room clickedRow = row.getItem();
                    System.out.println("用户选中了" + clickedRow);

                    /* 用户选中之后要刷新右侧寝室详情列表 */
                    flash4BedTable(clickedRow);
                }
            });
            return row;
        });

        // 设定右侧列表选中行事件
        studentTable.setRowFactory(e -> {
            TableRow<Bed> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Bed clickedRow = row.getItem();
                    System.out.println("用户选中了" + clickedRow);


                }
            });
            return row;
        });

        // 创建4张床位
        init4Bed();

        assert signOutBtn != null : "fx:id=\"signOutBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert timeInfoLabel != null : "fx:id=\"timeInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert adminInfoLabel != null : "fx:id=\"adminInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert changePwdBtn != null : "fx:id=\"changePwdBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert studentInfoLabel != null : "fx:id=\"studentInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
    }

}
