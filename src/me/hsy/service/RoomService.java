package me.hsy.service;

import me.hsy.mapper.RoomMapper;
import me.hsy.pojo.Room;
import me.hsy.pojo.Student;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/14 0:34
 */
public class RoomService {

    public List<Room> findRoomAll(RoomMapper mapper) {
        return mapper.findAll();
    }

    public Room findRoomById(RoomMapper mapper, long roomId) {
        return mapper.findById(roomId);
    }
}
