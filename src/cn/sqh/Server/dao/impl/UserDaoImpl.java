package cn.sqh.Server.dao.impl;


import cn.sqh.Server.dao.UserDao;
import cn.sqh.Server.domain.User;
import cn.sqh.Server.util.MD5;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserDaoImpl extends Dao implements UserDao {

//    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            String abstractPassword = MD5.MD5Encode(password, "utf-8");
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, abstractPassword);
            System.out.println("执行到找到User了");
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("没有找到User，返回null");
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into user values(null,?,?,?,?)";
        System.out.println("执行到添加User了");
        String abstractPassword = MD5.MD5Encode(user.getPassword(), "utf-8");
        template.update(sql, user.getUsername(), abstractPassword, user.getContainer(), user.getCurrentContain());
    }

    @Override
    public User findUserByUserName(String username) {
        try {
            String sql = "select * from user where username = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
            System.out.println("执行到找到User了");
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("没有找到User，返回null");
            return null;
        }
    }

    @Override
    public void updateCurrentContain(User userByUserName) {
        try {
            String sql = "update user set current_contain = ? where id = ? ";
            template.update(sql, userByUserName.getCurrentContain(), userByUserName.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserByCode(String code) {
        User user = null;
        try {
            String sql = "select * from user where code = ? and status = 'N' ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update user set status = 'Y' where id = ?";
        template.update(sql, user.getId());
    }

    @Override
    public User findUserById(int userId) {
        User user = null;
        try {
            String sql = "select * from user where id = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateCode(String code, int userId) {
        String sql = "update user set code = ? where id = ?";
        template.update(sql, code, userId);
    }

    @Override
    public void updatePassword(String newPassword, int id) {
        String sql = "update user set password = ? where id = ?";
        template.update(sql, id);
    }
}