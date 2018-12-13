package me.hsy.pojo;

/**
 * 寝室 pojo
 *
 * @author HytonightYX
 * @date 2018/12/14 0:29
 */
public class Room {
    /** 寝室号 */
    private long roomId;
    /** 床位号 */
    private long bed1;
    private long bed2;
    private long bed3;
    private long bed4;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getBed1() {
        return bed1;
    }

    public void setBed1(long bed1) {
        this.bed1 = bed1;
    }

    public long getBed2() {
        return bed2;
    }

    public void setBed2(long bed2) {
        this.bed2 = bed2;
    }

    public long getBed3() {
        return bed3;
    }

    public void setBed3(long bed3) {
        this.bed3 = bed3;
    }

    public long getBed4() {
        return bed4;
    }

    public void setBed4(long bed4) {
        this.bed4 = bed4;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", bed1=" + bed1 +
                ", bed2=" + bed2 +
                ", bed3=" + bed3 +
                ", bed4=" + bed4 +
                '}';
    }
}
