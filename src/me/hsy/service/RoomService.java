package me.hsy.service;

import me.hsy.mapper.RoomMapper;
import me.hsy.pojo.Room;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/14 0:34
 */
public class RoomService {
    SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
    RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);


    public List<Room> findRoomAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);

        List<Room> rooms = roomMapper.findAll();

        sqlSession.commit();
        sqlSession.close();
        return rooms;
    }

    public Room findRoomById(long roomId) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);

        Room room = roomMapper.findById(roomId);

        sqlSession.commit();
        sqlSession.close();
        return room;
    }
}
