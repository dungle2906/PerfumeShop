<%-- 
    Document   : buy
    Created on : Jul 2, 2023, 3:06:59 PM
    Author     : Le Dung
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Account"%>
<%@page import="DAOs.ProductDAO"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="<%= request.getContextPath()%>/CSS/style.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/DetailCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/styleForMain.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/button5.css">
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js">
        </script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    </head>
    <body>
        <%
            int TotalPrice = Integer.parseInt(request.getAttribute("TotalPrice").toString());
        %>
        <jsp:include page="Menu.jsp"></jsp:include>
            <form action="" method="post">
                <h1>Total Price: <%=request.getAttribute("TotalPrice")%>$</h1>
            <input type="hidden" name="hiddenTotalPrice" value="<%=request.getAttribute("TotalPrice")%>">
            <table id="example">
                <thead>
                    <tr>

                        <th>Product Name</th>
                        <th>Amount</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody> 
                    <%
                        ResultSet rs = (ResultSet) request.getAttribute("rs");
                        while (rs.next()) {
                    %>

                    <tr>

                        <td><a href="/Website/Buy/<%=rs.getInt("Product_ID")%>"><%=rs.getString("P_Name")%></a></td>
                        <td><%=rs.getInt("P_Amount")%></td>
                        <td><%=rs.getInt("P_Price")%>$</td>
                        <td>
                            <div class="text-center">                           
                                <a onclick="return confirm('Confirm for deletion?');" href="/Website/Delete/<%=rs.getInt("Product_ID")%>"class="button-1" role="button"><i class="fa-solid fa-trash"></i></i></a>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>    
                </tbody> 
            </table>
            <div class="text-center">                           
                <input type="submit" value="Submit Order" name="SubmitCart"class="button-1">
            </div>
        </form>
        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://kit.fontawesome.com/53d8d93477.js" crossorigin="anonymous"></script>
    </body>
</html>
