<%--
  Created by IntelliJ IDEA.
  User: 15797
  Date: 2020/3/17
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.print(session.getAttribute("name"));
%>
</body>
</html>
