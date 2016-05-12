<%@ page import="model.User" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/12
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除用户</title>
</head>
<body>
<%
    int id = Integer.valueOf(request.getParameter("user.id"));
    UserService userService = new UserService();
    User u = userService.findUser(id);
%>

<div style="font-size: 26px">确定要删除此用户吗?</div>
<form action="del.action">
    <table>
        <tr>
            <td>编号：</td>
            <td><input type="text" name="user.id" value="<%=id%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="user.username" value="<%=u.getUsername()%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="user.password" value="<%=u.getPassword()%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="确定"/>
                <input type="reset" value="取消" onclick="location='index.jsp'"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
