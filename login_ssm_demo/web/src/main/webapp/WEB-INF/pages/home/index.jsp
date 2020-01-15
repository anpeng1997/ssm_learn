<%--
  Created by IntelliJ IDEA.
  User: an
  Date: 2020/1/14
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>index.jsp</h1>
    当前登录用户：${sessionScope.get("currentLoginUser").name}
    <br/>
    <a href="/home/studentlist">student list</a>
</body>
</html>
