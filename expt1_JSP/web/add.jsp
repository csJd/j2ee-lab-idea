<%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/11
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<%
    String hint = (String) session.getAttribute("hint");
    String color = (String) session.getAttribute("color");
    if (hint != null) {
%>
<div style="color: <%=color%>"><%=hint%></div>
<%
        session.removeAttribute("hint");
    }
%>
<div style="font-size: 26px">请输入用户信息：</div>
<form action="addUser.jsp">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="password"/></td>
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
