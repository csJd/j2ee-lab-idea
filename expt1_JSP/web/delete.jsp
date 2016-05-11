<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除用户</title>
</head>
<body>
<%
    int id = Integer.valueOf(request.getParameter("id"));
    String username = null, password = null;
    try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();//加载驱动程序
        String url = "jdbc:mysql://localhost:3306/j2ee";//数据库连接串
        Connection conn = DriverManager.getConnection(url, "root", "9508");//建立连接

        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();
        rs.next();
        username = rs.getString("username");
        password = rs.getString("password");
    }catch (Exception e){
        e.printStackTrace();
    }


%>
<div style="font-size: 26px">确定删除此用户吗？</div>
<form action="deleteUser.jsp">
    <table>
        <tr>
            <td>编号：</td>
            <td><input type="text" name="id" value="<%=id %>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="<%=username %> " readonly="readonly"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password" value="<%=password %>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="确定"/>
            </td>
            <td>
                <input type="button" value="取消" onclick="location='index.jsp'">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
