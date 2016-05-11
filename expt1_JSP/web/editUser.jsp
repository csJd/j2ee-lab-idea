<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    int id = Integer.valueOf(request.getParameter("id"));
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();//加载驱动程序
        String url = "jdbc:mysql://localhost:3306/j2ee";//数据库连接串
        Connection conn = DriverManager.getConnection(url, "root", "9508");//建立连接

        String sql = "SELECT * FROM users WHERE username= ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, username);
        ResultSet rs = pStmt.executeQuery();
        if(rs.next()) {
            session.setAttribute("hint", "用户名已经被使用,请重新输入");
            session.setAttribute("color", "red");
            response.sendRedirect("edit.jsp?id="+id);
        }
        else{
            sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, username);
            pStmt.setString(2,password);
            pStmt.setInt(3,id);
            pStmt.executeUpdate();
            session.setAttribute("hint", "修改成功");
            session.setAttribute("color","green");
            response.sendRedirect("index.jsp");
        }
    }catch (Exception e){
        session.setAttribute("hint", "修改失败");
        session.setAttribute("color","red");
        response.sendRedirect("index.jsp");
    }
%>