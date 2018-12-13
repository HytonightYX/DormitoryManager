package me.hsy.mapper;

import me.hsy.pojo.Room;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/14 0:32
 */
public interface RoomMapper {

    /**
     * 获取所有寝室列表
     * @return
     */
    @Select("select * from roominfo")
    List<Room> findAll();

    /**
     * 根据寝室号查询寝室信息
     * @param roomId
     * @return
     */
    @Select("select * from roominfo where room_id=#{roomId}")
    Room findById(long roomId);
}
