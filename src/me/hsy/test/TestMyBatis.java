package me.hsy.test;

import me.hsy.mapper.StudentMapper;
import me.hsy.pojo.Student;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HytonightYX
 * @date 2018/12/28 13:30
 */
public class TestMyBatis {

    /**
     * 测试多条件查询
     * @param args
     */
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();

        Map<String, Object> params = new HashMap<>();
        Student student = new Student();
        student.setStuDepartment(null);
        List<Student> ss = sqlSession.selectList("queryStudent", student);
        for (Student s : ss) {
            System.out.println(s.toString());
        }

        sqlSession.commit();
        sqlSession.close();
    }
}