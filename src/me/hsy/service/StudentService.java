package me.hsy.service;

import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * student 业务层
 *
 * @author HytonightYX
 * @date 2018/12/13 20:56
 */
public class StudentService {

    public List<Student> findStudentAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findAll();

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    public Student findStudentById(long id) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.findById(id);

        sqlSession.commit();
        sqlSession.close();
        return student;
    }

    public List<Student> findStudentByCollege(String stuCollege) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findByCollege(stuCollege);

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    public List<Student> findStudentByClass(String stuClass) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findByClass(stuClass);

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    public List<Student> findStudentByName(String stuName) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findByName(stuName);

        sqlSession.commit();
        sqlSession.close();
        return students;
    }
}
