<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>
</head>
<body>
<%
    String hint = (String) session.getAttribute("hint");
    String color = (String) session.getAttribute("color");
    if (hint != null) {
%>
<div style="color: <%=color%>"><%=hint%>
</div>
<%
        session.removeAttribute("hint");
    }
%>

<%
    Class.forName("com.mysql.jdbc.Driver").newInstance();//加载驱动程序
    String url = "jdbc:mysql://localhost:3306/j2ee";//数据库连接串
    Connection conn = DriverManager.getConnection(url, "root", "9508");//建立连接

    int id = Integer.valueOf(request.getParameter("id"));
    String sql = "SELECT * FROM users WHERE id = ?";
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setInt(1, id);
    ResultSet rs = pStmt.executeQuery();
    rs.next();
%>
<div style="font-size: 26px">请输入新的用户信息：</div>
<form action="editUser.jsp">
    <table>
        <tr>
            <td>编号：</td>
            <td><input type="text" name="id" value="<%=id%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="<%=rs.getString("username")%>"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password" value="<%=rs.getString("password")%>"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
