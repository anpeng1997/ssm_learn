<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="${ pageContext.request.contextPath }/hello">go success page</a>

<form action="${ pageContext.request.contextPath }/user/login" method="post">
 username: <input type="text" name="username">
 password: <input type="text" name="password">
 account：<input type="text" name="account.money">
 account1: <input type="text" name="accounts[0].money">

 account2: <input type="text" name="accounts[1].money">
 Date: <input type="text" name="date">
 <input type="submit" value="ok">
</form>

</body>
</html>
