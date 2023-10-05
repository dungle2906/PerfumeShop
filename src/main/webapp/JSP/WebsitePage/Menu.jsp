<%-- 
    Document   : Menu
    Created on : Jun 29, 2023, 12:43:17 AM
    Author     : Le Dung
--%>

<%@page import="DAOs.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="filter">
    <input type="text" id="keyword" placeholder="Product's name">
    <input type="number" id="maxPrice" placeholder="Price">
    <button onclick="filterProducts()"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Search_Icon.svg/768px-Search_Icon.svg.png" alt="search" style="width: 20px; height: 20px; align-content: center"/></button>
</div>
<div class="header">

    <div class="hText1">
        <p class="Banner_Design">We are pleased</p>
    </div>

    <div >
        <img style="padding: 10px;" src="<%= request.getContextPath()%>/ImageForPage/8849af8414cf241c9e0539ab7e0ce2bd.jpg" alt="logo" />
    </div>

    <div class="hText2">
        <p class="Banner_Design">To serve you</p>
    </div>

</div>

<!---------------------------------------------------------------------------------------------->

<div class="menu">
    <!--you will code the horizontal menu here-->
    <%
        String fullname = (String) request.getAttribute("fullname");

    %>
    <div class="choosen"> 
        <a href="/">Home</a>  
        <a href="">About</a> 
        <a href="" onclick="showProductSuggestions(event)">All Products &#9660</a>
        <%     if (fullname == null) {
        %>
        <a href="/Login">Account</a>  

        <%
        } else {         
        %>
        <a href="/Website/Info/${requestScope.ID}"> <%=fullname%></a> 
        <a href="/Website/Cart"> Cart</a> 
        <form action="" method="post">
            <button type="submit" name="btnLogout" value="Submit">Logout</button>
        </form>
        <%
            }
        %>
    </div>   
</div>      

<div id="productSuggestions">
    <!-- Danh sách gợi ý sản phẩm sẽ được hiển thị ở đây -->

    <a href="url" class="suggestion">Product 1</a> <br>
    <a href="url" class="suggestion">Product 2</a><br>
    <a href="url" class="suggestion">Product 3</a><br>
    <a href="url" class="suggestion">Product 4</a>
    <a href="url" class="suggestion">Product 5</a>
    <a href="url" class="suggestion">Product 6</a>
    <!-- Thêm các mục gợi ý sản phẩm khác -->
</div>

<!---------------------------------------------------------------------------------------------->

