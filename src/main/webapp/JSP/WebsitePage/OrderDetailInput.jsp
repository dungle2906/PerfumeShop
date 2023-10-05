<%-- 
    Document   : OrderDetailInput
    Created on : Jul 10, 2023, 3:02:20 PM
    Author     : User
--%>

<%@page import="DAOs.OrderDAO"%>
<%@page import="java.sql.ResultSet"%>
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
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="<%= request.getContextPath()%>/fonts/icomoon/style.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/bootstrap.min.css">
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js">
        </script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <!-- Style -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/StyleInputForm.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/button5.css">
        <link rel="stylesheet" href="<%= request.getContextPath()%>/CSS/StyleAdminPanel.css">
        <link href="<%= request.getContextPath()%>/CSS/style.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/DetailCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/styleForMain.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/js/ValidateOrderInput.js"></script>
        <!--  -->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
        <style>

            .error {
                color: red;
                border-style: none;
            }
            label{

            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <%
            String Fullname, MobileNumber, Address;
            int TotalPrice = Integer.parseInt(request.getAttribute("TotalPrice").toString());
            Fullname = request.getAttribute("Fullname").toString();
            MobileNumber = request.getAttribute("MobilePhone").toString();
            Address = request.getAttribute("Address").toString();
        %>

        <div class="content">    
            <div class="container">
                <div class="table-data">
                    <div class="col-md-12">
                        <div class="box">
                            <form class="mb-5" action="" method="post"  onsubmit="return ValidOrder()" > 
                                <div class="col-md-12 form-group">
                                    <a href="/Website/Cart" class="button-2">Return</a>
                                </div>
                                <div class="row">  
                                    <div class="col-md-6 form-group">
                                        <label for="email" class="col-form-label">Fullname</label>
                                        <input type="text" class="form-control" name="txtOrderFullname" id="Fullname" readonly="" value="<%=Fullname%>">
                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="name" class="col-form-label">Phone Number</label>
                                        <input type="text" class="form-control" name="txtOrderPhoneNumber" id="PhoneNumber" value="<%=MobileNumber%>">
                                        <div class="error" id="ErrorOrderPhoneNumber"></div>
                                    </div>
                                </div>

                                <div class="row">                                   
                                    <div class="col-md-6 form-group">
                                        <label for="name" class="col-form-label">Total Price</label>
                                        <input type="text" class="form-control" name="txtTotalPrice" id="TotalPrice" value="<%=TotalPrice%>$" readonly="">

                                    </div>
                                    <div class="col-md-6 form-group">
                                        <label for="name" class="col-form-label">Payment Method</label>                                           
                                        <select name="slctPaymentMethod" id="brand" class="form-control"> 
                                            <%
                                                OrderDAO dao = new OrderDAO();
                                                ResultSet rs2 = dao.GetPaymentMethod();
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%=rs2.getInt("Payment_Method_ID")%>"><%=rs2.getString("Payment_Method")%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>
                                </div>


                                <div class="row"><div class="col-md-12 form-group">
                                        <label for="email" class="col-form-label">Order Detail</label>
                                        <table id="example">
                                            <thead>
                                                <tr> 
                                                    <th>Product Name</th>
                                                    <th>Amount</th>
                                                    <th>Price</th>
                                                </tr>
                                            </thead>
                                            <tbody> 
                                                <%
                                                    ResultSet rs = (ResultSet) request.getAttribute("rs1");
                                                    while (rs.next()) {
                                                %>
                                                <tr>

                                                    <td><%=rs.getString("P_Name")%></td>
                                                    <td><%=rs.getInt("P_Amount")%></td>
                                                    <td><%=rs.getInt("P_Price")%>$</td>
                                                </tr>
                                                <%
                                                    }
                                                %>    
                                            </tbody> 
                                        </table>
                                    </div>                                       
                                </div>

                                <div class="row"><div class="col-md-12 form-group">
                                        <label for="email" class="col-form-label">Delivery Date</label>
                                        <input type="date" class="form-control" name="dateDelivery" id="DeliveryDate" value="">
                                        <div class="error" id="ErrorDate"></div>
                                    </div>                                       
                                </div>
                                <div class="row"><div class="col-md-12 form-group">
                                        <label for="email" class="col-form-label">Delivery Instruction</label>
                                        <textarea class="form-control" name="txtDeliveryInstrution" rows="10" id="DeliveryInstruction">No instruction</textarea>
                                        <div class="error" id="ErrorDate"></div>
                                    </div>                                       
                                </div>
                                <div class="row">
                                    <div class="col-md-12 form-group">
                                        <label for="email" class="col-form-label">Delivery Address</label>
                                        <input type="text" class="form-control" name="txtAddress" id="DeliveryAddress" value="<%=Address%>">
                                        <div class="error" id="ErrorOrderDeliveryAddress"></div>
                                    </div>
                                </div>                               

                                <div class="row">

                                    <div class="col-md-12 form-group">
                                        <input type="submit" value="Submit" name="btnSubmitOrder" class="button-5 btn-block btn-primary rounded-0 py-2 px-4">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>

    </body>

</html>
