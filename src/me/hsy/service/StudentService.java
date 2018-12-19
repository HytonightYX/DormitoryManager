package me.hsy.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Date;
import java.sql.Timestamp;
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

    /**
     * 修改学生入住状态
     * @param student
     * @param bool
     */
    public void updateStudentCheckState(Student student, boolean bool) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        student.setChecked(bool);
        studentMapper.updateStudent(student);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 学生办理入住
     * @param id
     */
    public void studentCheckInById(long id) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.findById(id);
        student.setChecked(true);

        /** 获取系统当前时间戳 */
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts.setNanos(0);
        student.setCheckInTime(ts);
        student.setCheckOutTime(null);

        studentMapper.updateStudent(student);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新学生信息
     * @param student
     */
    public void updateStudent(Student student) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        studentMapper.updateStudent(student);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试用
     * @param args
     */
    public static void main(String[] args) {
        Student student = new Student();
        student.setStuId(2018001);
        student.setStuName("大猫222");
        student.setStuCollege("生科院222");
        student.setStuDepartment("生物化学2222");
        student.setStuClass("生化1812");

        System.out.println(student);

        new StudentService().studentCheckInById(2018001);
        System.out.println(new StudentService().findStudentById(2018001));
    }
}