package me.hsy.mapper;

import me.hsy.pojo.Admin;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Amdin DAO
 *
 * @author HytonightYX
 * @date 2018/12/14 13:21
 */
public interface AdminMapper {

    /**
     * 根据管理员用户名查询管理员密码
     * @param adminName
     * @return
     */
    @Select("select * from admininfo where admin_name=#{adminName}")
    Admin findByName(String adminName);

    /**
     * 查询所有管理员
     * @return
     */
    @Select("select * from admininfo")
    List<Admin> findAll();

    /**
     * 更新管理员信息
     * @param admin
     */
    @Update("update admininfo set admin_password = #{adminPassword}, is_changed = #{isChanged} where admin_name = #{adminName}")
    void updateAdmin(Admin admin);
}
