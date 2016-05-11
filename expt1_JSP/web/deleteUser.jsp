<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");

    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();//加载驱动程序
        String url = "jdbc:mysql://localhost:3306/j2ee";//数据库连接串
        Connection conn = DriverManager.getConnection(url, "root", "9508");//建立连接


        String sql = "DELETE FROM users WHERE id = ? ";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, id);
        pStmt.executeUpdate();

        session.setAttribute("hint", "删除成功");
        session.setAttribute("color", "green");
        response.sendRedirect("index.jsp");


    } catch (Exception e) {
        e.printStackTrace();
        session.setAttribute("hint", "删除失败");
        session.setAttribute("color", "red");
        response.sendRedirect("index.jsp");
    }
%>
