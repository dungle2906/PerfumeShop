<%-- error.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <%
        String error = request.getParameter("error");
        if (error != null) {
            if (error.equals("register_failed")) {
                out.println("<p>Registration failed. Please try again.</p>");
            } else {
                out.println("<p>Unknown error occurred.</p>");
            }
        } else {
            out.println("<p>Unknown error occurred.</p>");
        }
    %>
</body>
</html>
