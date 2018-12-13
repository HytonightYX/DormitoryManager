package me.hsy;
   
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.hsy.mapper.RoomMapper;
import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.service.RoomService;
import me.hsy.service.StudentService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author hsy
 */
public class TestMybatis {

   
    public static void main(String[] args) throws IOException {

        String resource = "mybatis-config.xml";
        StudentService studentService = new StudentService();
        RoomService roomService = new RoomService();

        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        RoomMapper roomMapper = session.getMapper(RoomMapper.class);

        List<Student> students = studentService.findStudentByName(studentMapper, "小猫");
        System.out.println(students.size());
        for (Student c : students) {
            System.out.println(c.getStuName() + " " + c.getStuCollege() + " " + c.getStuClass());
        }

        List<Room> rooms = roomService.findRoomAll(roomMapper);
        for (Room r : rooms) {
            System.out.println(r.toString());
        }

        Student student = studentService.findStudentById(studentMapper, 2018003);
        System.out.println(student);

        Room room = roomService.findRoomById(roomMapper, 102);
        System.out.println(room);


        session.commit();
        session.close();
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