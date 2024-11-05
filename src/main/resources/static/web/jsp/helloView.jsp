<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World JSP Page</title>
</head>
<body>
<h1>Hello, jsp!</h1>
<%-- 这里可以放置Java代码片段，但通常不建议这样做，因为它使JSP难以维护 --%>
<%
    // 这是一个Java代码片段，但通常不推荐在JSP中直接写Java代码
    // String message = "Hello from Java code in JSP";
    // out.println(message);
%>
<!-- 这里可以放置静态HTML内容和JSP表达式标签 -->
<p>This is a JSP page.</p>
<%-- JSP表达式标签用于输出Java变量的值 --%>
<%-- 假设你有一个在后台设置的名为"message"的属性，你可以这样输出它 --%>

</body>
</html>