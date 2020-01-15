<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css"/>
</head>
<body>
<div style="width: 400px;height: 200px;margin: 100px auto 0;">
    <form action="/home/login" method="post">
        <div class="form-group">
            用户名： <input type="text" name="name" class="form-control">
        </div>
        <div class="form-group">
            密码： <input type="text" name="password" class="form-control">
        </div>
        <div class="form-group">
            验证码：<a href="javascript:refreshCode();"><img id="codeimg" src="${pageContext.request.contextPath}/verification/codeimg"> </a>
            <input type="text" name="verificationCode" class="form-control">
        </div>
        <div class="form-group" style="text-align: center;">
            <input type="submit" class="btn btn-primary" value="login">
        </div>
    </form>
    <c:if test="${not empty requestScope.login_error}">
        <div class="alert alert-danger" role="alert">
                ${requestScope.login_error}
        </div>
    </c:if>
</div>
<script>
    function refreshCode(){
        document.getElementById("codeimg")
            .setAttribute("src","${pageContext.request.contextPath}/verification/codeimg?date="+new Date().getTime())
    }
</script>
</body>
</html>
