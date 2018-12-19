package me.hsy.test;

import me.hsy.pojo.Room;
import me.hsy.service.RoomService;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/19 21:43
 */
public class testFuzzySearch {
    /**
     * 测试模糊查找
     * @param args
     */
    public static void main(String[] args) {
        List<Room> rooms = new RoomService().findRoomByFuzzyKey(3);
        for (Room r: rooms) {
            System.out.println(r);
        }
    }
}
