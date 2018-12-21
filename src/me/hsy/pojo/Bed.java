package me.hsy.pojo;

import me.hsy.service.StudentService;

public class Bed {
    private String bedNumber;
    private String sid = "";
    private String sname = "";
    private String scollege = "";
    private String sclass = "";
    private String scheckInTime = "";
    private Student student = null;

    /**
     * 有参构造方法一，该床位存在学生
     * @param bedNumber
     * @param id
     */
    public Bed(String bedNumber, Long id) {
        student = new StudentService().findStudentById(id);
        this.bedNumber = bedNumber;
        this.sid = String.valueOf(student.getStuId());
        this.sname = student.getStuName();
        this.scollege = student.getStuCollege();
        this.sclass = student.getStuClass();
        this.scheckInTime = String.valueOf(student.getCheckInTime());
    }

    /**
     * 有参构造方法二，该床位暂时无人住
     * @param bedNumber
     */
    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getScollege() {
        return scollege;
    }

    public void setScollege(String scollege) {
        this.scollege = scollege;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getScheckInTime() {
        return scheckInTime;
    }

    public void setScheckInTime(String scheckInTime) {
        this.scheckInTime = scheckInTime;
    }


}