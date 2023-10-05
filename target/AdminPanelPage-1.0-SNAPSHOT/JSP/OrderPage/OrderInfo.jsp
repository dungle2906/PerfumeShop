<%-- 
    Document   : Analytics
    Created on : Jun 2, 2023, 8:34:48 AM
    Author     : User
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Info</title>
        <script src="https://code.jquery.com/jquery-3.5.1.js">
        </script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/StyleAdminPanel.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/button5.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/listStyle.css">
    </head>
    <body>
        <!-- SIDEBAR -->
        <%@include file="/JSP/SectionList.jsp" %>  
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- MAIN -->

            <main>

                <div class="table-data">
                    <div class="order">
                        <form action="Order" method="post">
                            <div class="head">
                                <h3>Catagories Management</h3>
                            </div>
                            <%
                                ResultSet rs = (ResultSet) request.getAttribute("rs");
                                while (rs.next()) {
                            %>
                            <div id="example">
                                <ul class="rectangle-list">
                                    <li><p><span>Fullname: </span><%=rs.getString("fn")%></p></li>
                                    <li><p><span>Username: </span>Legerdemain</p></li>
                                    <li><p><span>Email: </span><%=rs.getString("email")%></p></li>
                                    <li> <p><span>Shopping Cart</span></p>
                                        <ul>
                                            <%
                                                ResultSet rsOD = (ResultSet) request.getAttribute("rsOD");
                                                while (rsOD.next()) {
                                            %>
                                            <li><p><span>Name: </span><%=rsOD.getString("P_Name")%><br> <span>Amount: </span> <%=rsOD.getInt("P_Amount")%> <br><span>Price: </span><%=rsOD.getInt("P_Price")%>$</li>

                                            <%
                                                }
                                            %>
                                        </ul>
                                    </li>
                                    <li><p><span>Total Price: </span><%=rs.getInt("Total_Price")%>$</p></li>
                                    <li><p><span>Phone Number: </span><%=rs.getString("Phone_number")%></p></li>
                                    <li><p><span>Delivery date: </span><%=rs.getDate("Delivery_Time")%></p></li>                              
                                    <li><p><span>Delivery note: </span><%=rs.getString("Delivery_Instruction")%></p></li>
                                    <li><p><span>Delivery Address: </span><%=rs.getString("Delivery_Address")%></p></li>
                                    <li><p><span>Payment Method: </span><%=rs.getString("Pay_Met")%></p></li>
                                    <li><p><span>Status: </span><%=rs.getString("Status")%></li>
                                </ul>
                            </div>
                            

                            <div class="text-center">                           
                                <input type="submit" value="Complete Order" name="SubmitCart"class="button-1">
                                <input type="hidden" name="hiddenID" value="<%=rs.getInt("Order_ID")%>">
                            </div>
                            <%
                                }
                            %>
                        </form>
                    </div>				
                </div> 
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="../JavaScript/script.js"></script>
    </body>
</html>
