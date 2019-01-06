package me.hsy.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.hsy.MainApp;
import me.hsy.pojo.Bed;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.AdminService;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;
import me.hsy.util.*;

import static me.hsy.MainApp.primaryStage;
import static me.hsy.MainApp.secondStage;


/**
 * 主界面控制层
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

    /** 存储学生查询结果的列表 */
    private ObservableList<Student> studentObservableList;

    /** 存储时间 */
    private int year;
    private int month;
    private int day;
    private int second;
    private int minute;
    private int hour;

    /** 当前选中的房间号，床位号，学生学号 */
    private long selectedRoomId;
    private String selectedBedNumber;
    private long selectedStuId;

    @FXML
    private Button infoTabStuResetBtn;

    @FXML
    private Button infoTabStuQueryBtn;

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
    private Button checkInTabSearchBtn;

    @FXML
    private TextField roomIdTf;

    @FXML
    private TextField checkInTabStuDepartmentTf;

    @FXML
    private TextField checkInTabStuIdTf;

    @FXML
    private TextField checkOutTabStuDepartmentTf;

    @FXML
    private TextField checkInTabStuClassTf;

    @FXML
    private TextField checkOutTabcheckOutTimeTf;

    @FXML
    private TextField checkOutTabStuClassTf;

    @FXML
    private TextField checkInTabCheckInTimeTf;

    @FXML
    private TextField checkOutTabStuIdTf;

    @FXML
    private TextField checkOutTabStuCollegeTf;

    @FXML
    private TextField checkInTabStuCollegeTf;

    @FXML
    private TextField checkOutTabStuNameTf;

    @FXML
    private TextField checkInTabStuNameTf;

    @FXML
    private TextField checkOutTabcheckInTimeTf;

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
    private TableView<Student> infoTabTable;

    @FXML
    private TableColumn<Student, Long> idCol;

    @FXML
    private TableColumn<Student, String> nameCol;

    @FXML
    private TableColumn<Student, String> collegeCol;

    @FXML
    private TableColumn<Student, String> departmentCol;

    @FXML
    private TableColumn<Student, String> classCol;

    @FXML
    private TableColumn<Student, Boolean> checkedCol;

    @FXML
    private TableColumn<Student, Timestamp> inTimeCol;

    @FXML
    private TableColumn<Student, Timestamp> outTimeCol;

    @FXML
    private TextField infoTabStuIdTf;

    @FXML
    private TextField infoTabStuNameTf;

    @FXML
    private ChoiceBox infoTabStuCollegeCb;

    @FXML
    private ChoiceBox infoTabStuDCb;

    @FXML
    private ChoiceBox infoTabStuClassCb;

    @FXML
    private ChoiceBox infoTabCheckedCb;

    @FXML
    private TextField infoTabRoomIdTf;

    @FXML
    private ImageView backupDBIco;

    @FXML
    private ImageView exportExcelIco;

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
        primaryStage.setScene(scene);
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
        secondStage.setScene(scene);
        MainApp.secondStage.setTitle("修改密码");
        MainApp.secondStage.show();
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
     * 设定退房Tab信息
     */
    public void setCheckOutTab(Student student) {
        if (student != null) {
            String time = LocalDateTime.now().toString().replace('T', ' ');
            String checkInTime = student.getCheckInTime().toString();
            checkOutTabStuIdTf.setText(String.valueOf(student.getStuId()));
            checkOutTabStuNameTf.setText(student.getStuName());
            checkOutTabStuClassTf.setText(student.getStuClass());
            checkOutTabStuCollegeTf.setText(student.getStuCollege());
            checkOutTabStuDepartmentTf.setText(student.getStuDepartment());
            checkOutTabcheckInTimeTf.setText(checkInTime.substring(0, checkInTime.length() - 2));
            checkOutTabcheckOutTimeTf.setText(time.substring(0, time.length() - 4));
        }
    }

    /**
     * 右侧入住Tab，通过id查找并填充学生信息
     * @param event
     */
    @FXML
    void checkInTabSearchById(ActionEvent event) {
        try {
            Long id = Long.parseLong(checkInTabStuIdTf.getText());
            Student student = studentService.findStudentById(id);
            if (student != null) {
                if (!student.getChecked()) {
                    String time = LocalDateTime.now().toString().replace('T', ' ');
                    checkInTabStuNameTf.setText(student.getStuName());
                    checkInTabStuClassTf.setText(student.getStuClass());
                    checkInTabStuCollegeTf.setText(student.getStuCollege());
                    checkInTabStuDepartmentTf.setText(student.getStuDepartment());
                    checkInTabCheckInTimeTf.setText(time.substring(0, time.length() - 4));
                    selectedStuId = student.getStuId();
                } else {
                    new AlertInfoUtil("提示","该学生已入住！").showAndWait();
                    System.out.println("用户输入了已入住的学号，已提醒");
                }
            } else {
                new AlertInfoUtil("提示","该学号不存在").showAndWait();
                System.out.println("用户输入了不存在的学号，已提醒");
            }
        } catch (NumberFormatException e) {
            new AlertInfoUtil("警告","请输入正确的学号").showAndWait();
            System.out.println("用户输入了不合法的值，已警告");
        }
    }

    /**
     * 确认入住按钮事件
     * @param event
     */
    @FXML
    void checkInTabConfirm(ActionEvent event) {
        studentService.studentCheckInById(selectedStuId, selectedRoomId, selectedBedNumber);
        checkInTab.setDisable(true);
        checkOutTab.setDisable(true);
        flash4BedTable(roomService.findRoomById(selectedRoomId));
        flashRoomTable();
    }

    /**
     * 确认退房按钮事件
     * @param event
     */
    @FXML
    void checkOutTabConfirm(ActionEvent event) {
        studentService.studentCheckOutById(selectedStuId, selectedRoomId, selectedBedNumber);
        checkInTab.setDisable(true);
        checkOutTab.setDisable(true);
        flash4BedTable(roomService.findRoomById(selectedRoomId));
        flashRoomTable();
    }

    /**
     * 学生查询条件重置
     * @param event
     */
    @FXML
    void infoTabStuReset(ActionEvent event) {
        infoTabStuResetImpl();
    }

    /**
     * 学生查询条件重置实现
     * 该方法多次用到，分离出来单独实现
     */
    private void infoTabStuResetImpl() {
        infoTabStuIdTf.setText("");
        infoTabStuNameTf.setText("");

        // 设置下拉框列表
        infoTabStuDCb.setItems(FXCollections.observableArrayList(studentService.getDepartmentList()));
        infoTabStuClassCb.setItems(FXCollections.observableArrayList(studentService.getClassList()));
        infoTabStuCollegeCb.setItems(FXCollections.observableArrayList(studentService.getCollegeList()));
        infoTabCheckedCb.setItems(FXCollections.observableArrayList(
                new ArrayList<String>(){{add("是"); add("否");}}
        ));

        // 清空下拉框
        infoTabStuCollegeCb.setValue("");
        infoTabStuClassCb.setValue("");
        infoTabStuDCb.setValue("");
        infoTabCheckedCb.setValue("");
    }

    /**
     * 根据学生条件查询学生列表并显示
     * @param event
     */
    @FXML
    void infoTabStuQuery(ActionEvent event) {
        Student student = new Student();

        try {
            student.setStuId(null);
            student.setStuId("".equals(infoTabStuIdTf.getText()) ? null : Long.parseLong(infoTabStuIdTf.getText()));
        } catch (NumberFormatException e) {
            new AlertInfoUtil("警告","学号不合法，请修改后重试！").showAndWait();
            infoTabStuIdTf.setText("");
            System.out.println("用户在查询界面输入学号不合法");
        }

        student.setStuName(infoTabStuNameTf.getText());
        student.setStuDepartment(infoTabStuDCb.getValue().toString());
        student.setStuCollege(infoTabStuCollegeCb.getValue().toString());
        student.setStuClass(infoTabStuClassCb.getValue().toString());

        if ("是".equals(infoTabCheckedCb.getValue().toString())) {
            student.setChecked(true);
        } else if ("否".equals(infoTabCheckedCb.getValue().toString())) {
            student.setChecked(false);
        }

        List<Student> studentList = studentService.queryStudent(student);

        System.out.println(studentList);

        studentObservableList = FXCollections.observableList(studentList);
        infoTabTable.setItems(studentObservableList);
    }


    /**
     * 寝室查询条件重置
     * @param event
     */
    @FXML
    void infoTabRoomReset(ActionEvent event) {
        infoTabRoomResetImpl();
    }

    /**
     * 寝室查询条件重置实现
     */
    private void infoTabRoomResetImpl() {
        infoTabRoomIdTf.setText("");
    }

    /**
     * 根据寝室条件查询学生列表并显示
     * @param event
     */
    @FXML
    void infoTabRoomQuery(ActionEvent event) {
        String num = infoTabRoomIdTf.getText();
        try {
            Long key = Long.parseLong(num);
            Room room = roomService.findRoomById(key);
            if (room == null) {
                throw new NumberFormatException();
            }
            List<Student> studentList = new ArrayList<>(4);
            if(room.getBed1() != 0) {
                studentList.add(studentService.findStudentById(room.getBed1()));
            }
            if(room.getBed2() != 0) {
                studentList.add(studentService.findStudentById(room.getBed2()));
            }
            if(room.getBed3() != 0) {
                studentList.add(studentService.findStudentById(room.getBed3()));
            }
            if(room.getBed4() != 0) {
                studentList.add(studentService.findStudentById(room.getBed4()));
            }
            studentObservableList = FXCollections.observableList(studentList);

            infoTabTable.setItems(studentObservableList);
            if (studentList.size() == 0) {
                new AlertInfoUtil("提示","该寝室暂时无人居住").showAndWait();
            }
        } catch (NumberFormatException e) {
            new AlertInfoUtil("警告","请输入正确的房间号").showAndWait();
            System.out.println("用户输入了不合法的值，已警告");
        }
    }

    /**
     * 设置更多功能栏图片
     */
    public void showImage() {
        try {
            Image image1 = new Image("images/Database.png");
            Image image2 = new Image("images/Excel.png");
            backupDBIco.setImage(image1);
            exportExcelIco.setImage(image2);
            backupDBIco.setCache(true);
            exportExcelIco.setCache(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        /* 初始化学生信息查询Tab列表 */
        idCol.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        collegeCol.setCellValueFactory(new PropertyValueFactory<>("stuCollege"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("stuDepartment"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("stuClass"));
        inTimeCol.setCellValueFactory(new PropertyValueFactory<>("checkInTime"));
        outTimeCol.setCellValueFactory(new PropertyValueFactory<>("checkOutTime"));
        /* 重要！列表添加单选的方法 */
        checkedCol.setCellFactory(column -> new CheckBoxTableCell<>());
        checkedCol.setCellValueFactory(cellData -> {
            Student cellValue = cellData.getValue();
            BooleanProperty property = new SimpleBooleanProperty(cellValue.getChecked());
            return property;
        });

        /* 刷新列表内容 */
        flashRoomTable();

        /* 设定左侧列表选中行事件 */
        roomTable.setRowFactory(e -> {
            TableRow<Room> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                // 选中左侧先屏蔽入住退房Tab
                checkInTab.setDisable(true);
                checkOutTab.setDisable(true);

                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Room clickedRow = row.getItem();
                    selectedRoomId = clickedRow.getRoomId();
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
                    selectedBedNumber = clickedRow.getBedNumber();
                    System.out.println("用户选中了" + clickedRow.toString());

                    // 入住和退房Tab只能且自动二选一
                    if ("".equals(clickedRow.getSid())) {
                        // 如果该床位没人，那么只能办理入住
                        checkInTab.setDisable(false);
                        checkOutTab.setDisable(true);
                        System.out.println("该床位没人，那么只能办理入住");
                    } else {
                        try {
                            // 如果该床位有有效学号， 那么只能办理退房
                            String tmp = clickedRow.getSid();
                            tmp = "".equals(tmp)?"0" : tmp;
                            Long id = Long.parseLong(tmp);
                            Student student = studentService.findStudentById(id);
                            if (student != null) {
                                // 设定只允许退房且自动填充信息
                                checkInTab.setDisable(true);
                                checkOutTab.setDisable(false);
                                setCheckOutTab(student);
                                selectedStuId = student.getStuId();
                                System.out.println("该床位有有效学号， 只能办理退房");
                            } else {
                                // 学号存在但是不合法
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException nfe) {
                            // 如果该床位学号不合法，则两个Tab均屏蔽
                            checkInTab.setDisable(true);
                            checkOutTab.setDisable(true);
                            new AlertInfoUtil("警告","选中床位信息不合法，请检查数据库！").showAndWait();
                            System.out.println("学生信息不合法， 请检查数据库！");
                        }
                    }
                }
            });
            return row;
        });

        // 创建4张床位
        init4Bed();

        // 屏蔽入住退房Tab防止用户误操作
        checkInTab.setDisable(true);
        checkOutTab.setDisable(true);

        // 重置学生查询条件
        infoTabStuResetImpl();

        // 重置寝室查询条件
        infoTabRoomResetImpl();


        // 初始化Tab3图标
        showImage();

        // 设置图标单击事件
        backupDBIco.setOnMouseClicked(e -> {
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../view/BackupDB.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene scene = new Scene(parent);
            secondStage.setScene(scene);
            secondStage.setTitle("备份数据库");
            secondStage.show();
        });

        exportExcelIco.setOnMouseClicked(e -> {
            String fileName = "学生入住详情-" + year + '年' + month + '月' + day + "日_" + hour + '点' + minute + "分";
            // 弹出路径选择框
            Stage stage = new Stage();
            DirectoryChooser directoryChooser=new DirectoryChooser();
            directoryChooser.setTitle("选择生成文件路径");
            File file = directoryChooser.showDialog(stage);
            try {
                String savePath = file.getPath();
                ExportExcelUtil.createExcel(studentService.findStudentAll(), savePath, fileName);
            } catch (NullPointerException npe) {
                System.out.println("用户取消了导出报表操作");
            }
        });

        assert signOutBtn != null : "fx:id=\"signOutBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert timeInfoLabel != null : "fx:id=\"timeInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert adminInfoLabel != null : "fx:id=\"adminInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert changePwdBtn != null : "fx:id=\"changePwdBtn\" was not injected: check your FXML file 'AdminSystem.fxml'.";
        assert studentInfoLabel != null : "fx:id=\"studentInfoLabel\" was not injected: check your FXML file 'AdminSystem.fxml'.";
    }
}
