package me.hsy.service;

import me.hsy.mapper.AdminMapper;
import me.hsy.pojo.Admin;
import me.hsy.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/14 13:28
 */
public class AdminService {

    /**
     * 根据管理员名称查询管理员
     * @param adminName
     * @return
     */
    public Admin findAdminByName(String adminName) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        Admin admin = adminMapper.findByName(adminName);

        sqlSession.commit();
        sqlSession.close();
        return admin;
    }

    /**
     * 获取所有管理员的列表
     * @return
     */
    public List<Admin> findAdminAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        List<Admin> admins = adminMapper.findAll();

        sqlSession.commit();
        sqlSession.close();
        return admins;
    }

    /**
     * 根据管理员用户名修改管理员密码，
     * 并且将修改标记置为1
     */
    public void updateAdminPasswordByName(String adminName, String adminPassword) {
        SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);

        Admin admin = adminMapper.findByName(adminName);
        admin.setAdminPassword(adminPassword);
        admin.setChanged(true);
        adminMapper.updateAdmin(admin);

        sqlSession.commit();
        sqlSession.close();
    }


}