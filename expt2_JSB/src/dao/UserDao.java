package dao;

/**
 * Created by dd on 2016/5/11.
 */

import beans.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public String DRIVER = "com.mysql.jdbc.Driver";
    public String DBURL = "jdbc:mysql://localhost:3306/j2ee";
    public String DBUSER = "root";
    public String DBPWD = "9508";

    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public Connection getConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(DBURL, DBUSER, DBPWD);
        } catch (Exception e) {
            return null;
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pStat != null) pStat.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean findUserByUsername(String username) {
        conn = getConnection();
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            rs = pStat.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public User findUserById(int id) {
        conn = getConnection();
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, id);
            rs = pStat.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                return u;
            } else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close();
        }
    }

    public List findAllUser() {
        conn = getConnection();
        List ret = new ArrayList();
        try {
            String sql = "SELECT * FROM users";
            pStat = conn.prepareStatement(sql);
            rs = pStat.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getByte("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                ret.add(user);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {
            close();
        }
    }

    public boolean add(String username, String password) {
        conn = getConnection();
        try {
            String sql = "INSERT INTO users VALUES(null, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            pStat.setString(2, password);
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean add(User user) {
        conn = getConnection();
        try {
            String sql = "INSERT INTO users VALUES(null, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getPassword());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean update(User user) {
        conn = getConnection();
        try {
            String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getPassword());
            pStat.setInt(3, user.getId());
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public boolean delete(int id) {
        conn = getConnection();
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, id);
            int cnt = pStat.executeUpdate();
            return cnt > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close();
        }
    }
}