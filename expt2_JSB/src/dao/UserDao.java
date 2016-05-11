package dao;

/**
 * Created by dd on 2016/5/11.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.User;

public class UserDao {
    public String DRIVER="com.mysql.jdbc.Driver";
    public String DBURL="jdbc:mysql://localhost:3306/j2ee";
    public String DBUSER = "root";
    public String DBPWD="9508";

    private Connection conn = null;
    private PreparedStatement pStat = null;
    private ResultSet rs = null;

    public Connection getConnection(){
        try{
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(DBURL, DBUSER, DBPWD);
        }catch(Exception e){
            return null;
        }
    }

    public void close(){
        try{
            if(rs!=null) rs.close();
            if(pStat!=null) pStat.close();
            if(conn!=null) conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean findUserByUsername(String username){
        conn = getConnection();
        try{
            String sql = "SELECT * FROM users WHERE username = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            rs = pStat.executeQuery();
            return rs.next();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean findUser(String username, String password){
        conn = getConnection();
        try{
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            pStat.setString(2, password);
            rs = pStat.executeQuery();
            return rs.next();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean add(String username, String password){
        conn = getConnection();
        try{
            String sql = "INSERT INTO users VALUES(null, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, username);
            pStat.setString(2, password);
            int cnt = pStat.executeUpdate();
            return cnt>0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean add(User user){
        conn = getConnection();
        try{
            String sql = "INSERT INTO users VALUES(null, ?, ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getPassword());
            int cnt = pStat.executeUpdate();
            return cnt>0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean edit(User user){
        conn = getConnection();
        try{
            String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?)";
            pStat = conn.prepareStatement(sql);
            pStat.setString(1, user.getUsername());
            pStat.setString(2, user.getPassword());
            pStat.setInt(3,user.getId());
            int cnt = pStat.executeUpdate();
            return cnt>0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }

    public boolean del(int id){
        conn = getConnection();
        try{
            String sql = "DELETE FROM users WHERE id = ?";
            pStat = conn.prepareStatement(sql);
            pStat.setInt(1, id);
            int cnt = pStat.executeUpdate();
            return cnt>0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            close();
        }
    }
}