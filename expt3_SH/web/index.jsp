<%@ page import="dao.UserDao" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/12
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>用户管理</title>
  </head>

  <%
      UserService userService = new UserService();
      List<User> list = userService.findAllUser();
  %>

  <body>
  <s:actionmessage style="color:green"/>
  <s:actionerror style="color:red"/>
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
          <td align="center"><a href="edit.jsp?user.id=<%=u.getId()%>">编辑</a></td>
          <td align="center"><a href="delete.jsp?user.id=<%=u.getId()%>">删除</a></td>
      </tr>
      <%} %>
  </table>
  <a href="add.jsp" style="font-size: 25px">添加用户</a>

  </body>
</html>
