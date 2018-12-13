package me.hsy;
   
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import me.hsy.service.StudentService;
import me.hsy.service.StudentService.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author hsy
 */
public class TestMybatis {

   
    public static void main(String[] args) throws IOException {
        StudentService studentService = new StudentService();
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
//        CategoryMapper mapper = session.getMapper(CategoryMapper.class);
//        add(mapper);
//        delete(mapper);
//        get(mapper);
//        update(mapper);
        List<Student> students = studentService.findStudentByName(mapper, "小猫");
        System.out.println(students.size());
        for (Student c : students) {
            System.out.println(c.getStuName() + " " + c.getStuCollege() + " " + c.getStuClass());
        }

        Student student = studentService.findStudentById(mapper, 2018003);
        System.out.println(student);

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