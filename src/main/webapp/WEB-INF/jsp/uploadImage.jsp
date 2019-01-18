<%--
  Created by IntelliJ IDEA.
  User: jianghaotian
  Date: 2019/1/18
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>UploadImage</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadImage" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="file">
    <br/>
    <input type="submit" value="上传">
</form>
<img src="image/${src}">
</body>
</html>
