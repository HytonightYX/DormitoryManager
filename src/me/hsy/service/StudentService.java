package me.hsy.service;

import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * student 业务层
 *
 * @author HytonightYX
 * @date 2018/12/13 20:56
 */
public class StudentService {

//    private static void get(StudentMapper mapper) {
//        Category c= mapper.get(8);
//        System.out.println(c.getName());
//    }
//    public void deleteById(StudentMapper mapper, long stuId) {
//        mapper.deleteById(1);
//    }

    public List<Student> findStudentAll(StudentMapper mapper) {
        return mapper.findAll();
    }

    public Student findStudentById(StudentMapper mapper, long id) {
        return mapper.findById(id);
    }

    public List<Student> findStudentByCollege(StudentMapper mapper, String stuCollege) {
        return mapper.findByCollege(stuCollege);
    }

    public List<Student> findStudentByClass(StudentMapper mapper, String stuClass) {
        return mapper.findByClass(stuClass);
    }

    public List<Student> findStudentByName(StudentMapper mapper, String stuName) {
        return mapper.findByName(stuName);
    }

    //    private static void add(StudentMapper mapper) {
////        Category c = new Category();
////        c.setName("新增加的Category");
////        mapper.add(c);
////        listAll(mapper);
////    }
//
}
