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
    private SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
    private RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);


    /**
     * 获取所有寝室
     * @return rooms
     */
    public List<Room> findRoomAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);

        List<Room> rooms = roomMapper.findAll();

        sqlSession.commit();
        sqlSession.close();
        return rooms;
    }

    /**
     * 根据寝室ID查找寝室
     * @param roomId
     * @return room
     */
    public Room findRoomById(long roomId) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);

        Room room = roomMapper.findById(roomId);

        sqlSession.commit();
        sqlSession.close();
        return room;
    }

    /**
     * 房间号模糊查找寝室
     * @param key
     * @return
     */
    public List<Room> findRoomByFuzzyKey(long key) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);

        List<Room> rooms = roomMapper.findByFuzzyKey(key);

        sqlSession.commit();
        sqlSession.close();

        return rooms;
    }
}
