package me.hsy.service;

import me.hsy.mapper.RoomMapper;
import me.hsy.pojo.Room;
import me.hsy.util.DBHandler;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/14 0:34
 */
public class RoomService {

    RoomMapper roomMapper = DBHandler.getSession().getMapper(RoomMapper.class);


    public List<Room> findRoomAll() {
        return roomMapper.findAll();
    }

    public Room findRoomById(long roomId) {
        return roomMapper.findById(roomId);
    }
}
