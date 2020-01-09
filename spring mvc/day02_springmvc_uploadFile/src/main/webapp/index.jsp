<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h1>传统方式上传文件</h1>
<form action="/home/uploadfile1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload" />
    <br/>
    <input type="submit" value="上传" />
</form>

<h1>使用spring mvc中的解析器</h1>
<form action="/home/uploadfile2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload" />
    <br/>
    <input type="submit" value="上传" />
</form>

<h1>将文件转存至文件服务器</h1>
<form action="/home/uploadfile3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload" />
    <br/>
    <input type="submit" value="上传" />
</form>
</body>
</html>
