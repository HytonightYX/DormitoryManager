package me.hsy.pojo;

/**
 * 一个寝室中的床位pojo
 *
 * @author HytonightYX
 */
public class Bed {
    private String bedNumber;
    private String sid;
    private String sname;
    private String scollege;
    private String sclass;
    private String scheckInTime;

    /**
     * 有参构造方法，该床位暂时无人住
     * @param bedNumber
     */
    public Bed(String bedNumber) {
        this.bedNumber = bedNumber;
        sid = "";
        sname = "";
        scollege = "";
        sclass = "";
        scheckInTime = "";
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

    @Override
    public String toString() {
        return "Bed{" +
                "bedNumber='" + bedNumber + '\'' +
                ", sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", scollege='" + scollege + '\'' +
                ", sclass='" + sclass + '\'' +
                ", scheckInTime='" + scheckInTime + '\'' +
                '}';
    }
}