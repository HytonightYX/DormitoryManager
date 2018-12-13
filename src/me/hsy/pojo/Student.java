package me.hsy.pojo;

/**
 * 学生实体类
 *
 * @author HytonightYX
 * @date 2018/12/13 8:59
 */
public class Student {

    /** 学生学号Id 主键*/
    private long stuId;

    /** 学生姓名 */
    private String stuName;

    /** 所在学院 */
    private String stuCollege;

    /** 所在专业 */
    private String stuDepartment;

    /** 所在班级 */
    private String stuClass;

    /** 入住状态 */
    private boolean isChecked;

    public long getStuId() {
        return stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuDepartment() {
        return stuDepartment;
    }

    public void setStuDepartment(String stuDepartment) {
        this.stuDepartment = stuDepartment;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuCollege='" + stuCollege + '\'' +
                ", stuDepartment='" + stuDepartment + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
