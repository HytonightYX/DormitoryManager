package me.hsy;
   
import java.io.IOException;
import java.util.List;

import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;


/**
 * @author hsy
 */
public class TestMybatis {
   
    public static void main(String[] args) throws IOException {


        StudentService studentService = new StudentService();
        RoomService roomService = new RoomService();

        List<Student> students = studentService.findStudentByName("小猫");
        System.out.println(students.size());
        for (Student c : students) {
            System.out.println(c.getStuName() + " " + c.getStuCollege() + " " + c.getStuClass());
        }

        List<Room> rooms = roomService.findRoomAll();

        for (Room r : rooms) {
            System.out.println(r.toString());
        }

        Student student = studentService.findStudentById(2018003);
        System.out.println(student);

        Room room = roomService.findRoomById(102);
        System.out.println(room);

    }
  
//    private static void update(StudentMapper mapper) {
//        Student student = mapper.get(8);
//        c.setName("修改了的Category名稱");
//        mapper.update(c);
//        listAll(mapper);
//    }
  
//    private static void get(StudentMapper mapper) {
//        Category c= mapper.get(8);
//        System.out.println(c.getName());
//    }

//    private static void add(StudentMapper mapper) {
//        Category c = new Category();
//        c.setName("新增加的Category");
//        mapper.add(c);
//        listAll(mapper);
//    }

//    private static void listAll(StudentMapper mapper) {
//        List<Student> cs = mapper.findByCollege("生科院");
//        for (Student c : cs) {
//            System.out.println(c.getStuName() + " " + c.getStuCollege() + " " + c.getStuClass());
//        }
//    }
}