package me.hsy.mapper;

import me.hsy.pojo.Room;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 模糊查找寝室
     * @return
     */
    @Select("select * from roominfo where room_id LIKE CONCAT(CONCAT('%', #{text}), '%')")
    List<Room> findByFuzzyKey(long key);

    /**
     * 更新寝室状态
     * @param room
     */
    @Update("update roominfo set " +
            "bed1 = #{bed1}, " +
            "bed2 = #{bed2}, " +
            "bed3 = #{bed3}, " +
            "bed4 = #{bed4} " +
            "where room_id = #{roomId}")
    void updateRoom(Room room);
}
