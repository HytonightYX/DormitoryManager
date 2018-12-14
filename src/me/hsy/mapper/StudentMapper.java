package me.hsy.mapper;

import me.hsy.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Student 数据库连接
 *
 * 查询功能。至少需要实现以下5项查询功能：
 * OK	按系别查询，即可以查看到某个系的所有学生的入住寝室情况。还未办理入住的学生也需要在结果中显示
 * OK	按班级查询，即可以查看到某个班的所有学生的入住寝室情况。还未办理入住的学生也需要在结果中显示
 * OK	按学号查询，即可以查看到某位学生的入住寝室情况
 * OK	按姓名查询，即可以查看到同名学生的入住寝室情况
 * OK	按寝室查询，即可以查看到入住某个寝室的所有学生信息。查询结果中必须有学号、姓名和床号
 *
 * @author HytonightYX
 * @date 2018/12/13 12:03
 */
public interface StudentMapper {

    /**
     * 根据学生id删除学生
     * @param stuId
     */
//    @Delete(" delete from studentinfo where stu_id= #{stuId} ")
//    public void deleteById(long stuId);


//    @Insert("insert into studentinfo values(@{stuId}, #{stuName}, #{})")
//    public void addOne()

    /**
     * 获取所有学生列表
     * @return
     */
    @Select("select * from studentinfo")
    List<Student> findAll();

    /**
     * 按系别查询
     * @param stuCollege
     * @return
     */
    @Select("select * from studentinfo where stu_college=#{stuCollege}")
    List<Student> findByCollege(String stuCollege);

    /**
     * 按班级查询
     * @param stuClass
     * @return
     */
    @Select("select * from studentinfo where stu_class=#{stuClass}")
    List<Student> findByClass(String stuClass);

    /**
     * 根据学生学号查找学生信息
     * @param stuId
     * @return
     */
    @Select("select * from studentinfo where stu_id=#{stuId}")
    Student findById(long stuId);

    /**
     * 按姓名查询
     * @param stuName
     * @return
     */
    @Select("select * from studentinfo where stu_name=#{stuName}")
    List<Student> findByName(String stuName);
}
