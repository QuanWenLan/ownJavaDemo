<%--
  Created by IntelliJ IDEA.
  User: 15797
  Date: 2020/3/15
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    index--${pageContext.request.contextPath}
    <form action="uploadServlet" method="post" enctype="multipart/form-data">
        文件： <input type="file" name="photo"> <br/>
        <input type="submit" value="上传">
    </form>
</body>
</html>
