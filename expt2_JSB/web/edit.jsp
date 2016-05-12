<%@ page import="beans.User" %>
<%@ page import="dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 23:43
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
    int id = Integer.valueOf(request.getParameter("id"));
    UserDao dao = new UserDao();
    User u = dao.findUserById(id);
%>

<div style="font-size: 26px">请输入新的用户信息：</div>
<form action="EditServlet">
    <table>
        <tr>
            <td>编号：</td>
            <td><input type="text" name="id" value="<%=id%>" readonly="readonly"/></td>
        </tr>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" value="<%=u.getUsername()%>"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password" value="<%=u.getPassword()%>"/></td>
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