package me.hsy.service;

import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import me.hsy.util.DBHandler;

import java.util.List;

/**
 * student 业务层
 *
 * @author HytonightYX
 * @date 2018/12/13 20:56
 */
public class StudentService {

    StudentMapper studentMapper = DBHandler.getSession().getMapper(StudentMapper.class);

//    private static void get(StudentMapper mapper) {
//        Category c= mapper.get(8);
//        System.out.println(c.getName());
//    }
//    public void deleteById(StudentMapper mapper, long stuId) {
//        mapper.deleteById(1);
//    }

    public List<Student> findStudentAll() {
        return studentMapper.findAll();
    }

    public Student findStudentById(long id) {
        return studentMapper.findById(id);
    }

    public List<Student> findStudentByCollege(String stuCollege) {
        return studentMapper.findByCollege(stuCollege);
    }

    public List<Student> findStudentByClass(String stuClass) {
        return studentMapper.findByClass(stuClass);
    }

    public List<Student> findStudentByName(String stuName) {
        return studentMapper.findByName(stuName);
    }

    //    private static void add(StudentMapper mapper) {
////        Category c = new Category();
////        c.setName("新增加的Category");
////        mapper.add(c);
////        listAll(mapper);
////    }
//
}
