<%--
  Created by IntelliJ IDEA.
  beans.User: dd
  Date: 2016/5/11
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>用户管理</title>
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
    UserDao dao = new UserDao();
    List<User> list = dao.findAllUser();
%>


<div style="font-size: 30px">用户管理</div>
<table align="center" border="true" width="100%">
    <tr>
        <th>编号</th>
        <th>用户名</th>
        <th>密码</th>
        <th colspan="2">操作</th>
    </tr>
    <%for (User u : list) {%>
    <tr>
        <td align="center"><%=u.getId() %>
        </td>
        <td align="center"><%=u.getUsername() %>
        </td>
        <td align="center"><%=u.getPassword() %>
        </td>
        <td align="center"><a href="edit.jsp?id=<%=u.getId()%>">编辑</a></td>
        <td align="center"><a href="delete.jsp?id=<%=u.getId()%>">删除</a></td>
    </tr>
    <%} %>
</table>

<br>
<a href="add.jsp" style="font-size: 25px">添加用户</a>

</body>
</html>
