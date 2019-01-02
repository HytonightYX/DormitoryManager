package me.hsy.pojo;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * 学生 pojo
 *
 * @author HytonightYX
 * @date 2018/12/13 8:59
 */
public class Student {

    /** 学生学号Id 主键*/
    private Long stuId;

    /** 学生姓名 */
    private String stuName;

    /** 所在学院 */
    private String stuCollege;

    /** 所在专业 */
    private String stuDepartment;

    /** 所在班级 */
    private String stuClass;

    /** 入住状态 */
    private Boolean isChecked;

    /** 入住时间 */
    private Timestamp checkInTime;

    /** 退房时间 */
    private Timestamp checkOutTime;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
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

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
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
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                '}';
    }
}
