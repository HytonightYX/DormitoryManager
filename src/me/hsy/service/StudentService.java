package me.hsy.service;

import me.hsy.mapper.RoomMapper;
import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * student service层
 *
 * @author HytonightYX
 * @date 2018/12/13 20:56
 */
public class StudentService {

    /**
     * 获取全体学生列表
     * @return students
     */
    public List<Student> findStudentAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findAll();

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    /**
     * 通过学号查找学生（唯一）
     * @param id
     * @return student
     */
    public Student findStudentById(long id) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Student student = studentMapper.findById(id);

        sqlSession.commit();
        sqlSession.close();
        return student;
    }

    /**
     * 通过学院查找学生列表
     * @param stuCollege
     * @return students
     */
    public List<Student> findStudentByCollege(String stuCollege) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findByCollege(stuCollege);

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    /**
     * 通过班级查找学生列表
     * @param stuClass
     * @return students
     */
    public List<Student> findStudentByClass(String stuClass) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<Student> students = studentMapper.findByClass(stuClass);

        sqlSession.commit();
        sqlSession.close();
        return students;
    }

    /**
     * 通过姓名查找学生列表
     * @param stuName
     * @return students
     */
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
    public void studentCheckInById(long id , long roomId, String bedNumber) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        /* 修改学生表 */
        Student student = studentMapper.findById(id);
        student.setChecked(true);

        /* 获取系统当前时间戳 */
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts.setNanos(0);
        student.setCheckInTime(ts);
        student.setCheckOutTime(null);

        studentMapper.updateStudent(student);

        /* 修改寝室表 */
        RoomMapper  roomMapper = sqlSession.getMapper(RoomMapper.class);
        Room room = roomMapper.findById(roomId);
        if ("一号床".equals(bedNumber)) {
            room.setBed1(id);
        } else if ("二号床".equals(bedNumber)) {
            room.setBed2(id);
        } else if ("三号床".equals(bedNumber)) {
            room.setBed3(id);
        } else if ("四号床".equals(bedNumber)) {
            room.setBed4(id);
        }

        roomMapper.updateRoom(room);

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 学生办理退房
     * @param id
     * @param roomId
     * @param bedNumber
     */
    public void studentCheckOutById(long id , long roomId, String bedNumber) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        /* 修改学生表 */
        Student student = studentMapper.findById(id);
        student.setChecked(false);

        /* 获取系统当前时间戳 */
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts.setNanos(0);
        student.setCheckOutTime(ts);

        studentMapper.updateStudent(student);

        /* 修改寝室表 */
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        Room room = roomMapper.findById(roomId);
        if ("一号床".equals(bedNumber)) {
            room.setBed1(0);
        } else if ("二号床".equals(bedNumber)) {
            room.setBed2(0);
        } else if ("三号床".equals(bedNumber)) {
            room.setBed3(0);
        } else if ("四号床".equals(bedNumber)) {
            room.setBed4(0);
        }

        roomMapper.updateRoom(room);

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
     * 多条件查询
     * 应用于第二页面
     * 18年的最后一行代码
     * @param student
     * @return studentList
     */
    public List<Student> queryStudent(Student student) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();

        List<Student> studentList = sqlSession.selectList("queryStudent", student);

        sqlSession.commit();
        sqlSession.close();

        return studentList;
    }

    /**
     * 获取学院列表
     * @return collegeList
     */
    public List<String> getCollegeList() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<String> collegeList = studentMapper.groupByCollege();

        sqlSession.commit();
        sqlSession.close();
        return collegeList;
    }

    /**
     * 获取学院列表
     * @return departmentList
     */
    public List<String> getDepartmentList() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<String> departmentList = studentMapper.groupByDepartment();

        sqlSession.commit();
        sqlSession.close();
        return departmentList;
    }

    /**
     * 获取班级列表
     * @return classList
     */
    public List<String> getClassList() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        List<String> classList = studentMapper.groupByClass();

        sqlSession.commit();
        sqlSession.close();
        return classList;
    }
}