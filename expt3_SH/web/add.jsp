<%--
  Created by IntelliJ IDEA.
  User: dd
  Date: 2016/5/12
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<s:actionerror style="color:red"/>
<div style="font-size: 26px">添加用户</div>

<s:form action="add" method="post">
    <s:textfield name="user.username" label="用户名"></s:textfield>
    <s:password name="user.password" label="密码"></s:password>
    <s:submit value="添加"></s:submit>
</s:form>
</body>
</html>
