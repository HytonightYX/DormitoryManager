package me.hsy.Test;
   
import java.io.IOException;
import java.util.List;

import me.hsy.pojo.Admin;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.AdminService;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;


/**
 * 测试类
 * @author hsy
 */
public class Test {

    public static void main(String[] args) throws IOException {
        /**
         * 测试数据库连接
         */
        new Test().testMybatis();

    }

    private void testMybatis() {
        StudentService studentService = new StudentService();
        AdminService adminService = new AdminService();
        RoomService roomService = new RoomService();

//        List<Student> students = studentService.findStudentByName("小猫");
//        System.out.println(students.size());
//        for (Student c : students) {
//            System.out.println(c.getStuName() + " " + c.getStuCollege() + " " + c.getStuClass());
//        }
//
//        List<Room> rooms = roomService.findRoomAll();
//
//        for (Room r : rooms) {
//            System.out.println(r.toString());
//        }
//
//        List<Admin> admins = adminService.findAdminAll();
//
//        for (Admin a : admins) {
//            System.out.println(a.toString());
//        }
//
//        Admin admin = adminService.findAdminByName("admin");
//        System.out.println(admin);
//
//        Student student = studentService.findStudentById(2018003);
//        System.out.println(student);
//
//        rooms = roomService.findRoomAll();
//
//        for (Room r : rooms) {
//            System.out.println(r);
//        }
//
//        Room room = roomService.findRoomById(102);
//        System.out.println(room);
//
//        /**
//         * 测试修改管理员密码
//         */
//        admin = adminService.findAdminByName("admin1");
//        System.out.println(admin);
//
//        adminService.updateAdminPasswordByName("admin1", "修改了6次的密码");
//        System.out.println(adminService.findAdminAll());
//        System.out.println(adminService.findAdminByName("admin1"));

        Admin admin = adminService.findAdminByName("admin342");
        System.out.println(admin);
    }
}