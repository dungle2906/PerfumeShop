<%-- 
    Document   : Detail
    Created on : Jul 1, 2023, 2:30:40 PM
    Author     : Le Dung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="<%= request.getContextPath()%>/CSS/style.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/DetailCSS.css" rel="stylesheet" type="text/css"/>
        <link href="<%= request.getContextPath()%>/CSS/styleForMain.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>

    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container">
                <form action="" method="post">
                    <section class="product">
                        <div class="product__photo">                           <div class="photo-main">

                                <img style="margin-left: 100px; width: 400px; border-radius: 10px;" src="<%= request.getContextPath()%>/${detail.URL}" alt="green apple slice">
                        </div>


                    </div>
                    <div class="product__info">
                        <div class="title">
                            <h1 name="ProductName" value="${detail.name}">${detail.name}</h1>
                            <input type="hidden" name="ProductName" value="${detail.name}">
                            <ul class="InfoList">
                                <li><span style="margin-top: 20px">Brand: ${detail.brand}</span></li>
                                <li><span style="margin-top: 20px">Type: ${detail.cat}</span></li>
                            </ul>                       
                            <div class="price">
                                PRICE: <span>${detail.price}</span>$
                                <input type="hidden" name="ProductPrice" value="${detail.price}">
                            </div>
                            <div class="description">
                                <h3>INFORMATION ABOUT THE PRODUCT</h3>
                                <ul class="InfoList">
                                    <li>Status of product: ${detail.status}</li>
                                    <li>Origin of product: ${detail.country}</li>
                                    <li>Size of product: ${detail.size}</li>
                                </ul>
                            </div><br><br><br>
                            <div class="counter">
                                <span class="down" onClick='decreaseCount(event, this)'>-</span>
                                <input type="text" value="1" name="quantity">
                                <span class="up"  onClick='increaseCount(event, this)'>+</span>
                            </div>
                            <script type="text/javascript">
                                function increaseCount(a, b) {
                                    var input = b.previousElementSibling;
                                    var value = parseInt(input.value, 10);
                                    value = isNaN(value) ? 0 : value;
                                    value++;
                                    input.value = value;
                                }
                                function decreaseCount(a, b) {
                                    var input = b.nextElementSibling;
                                    var value = parseInt(input.value, 10);
                                    if (value > 1) {
                                        value = isNaN(value) ? 0 : value;
                                        value--;
                                        input.value = value;
                                    }
                                }
                            </script>
                            <script>
                                function updateQuantity() {
                                    var quantity = document.getElementById("quantityInput").value;
                                    var buyLink = document.getElementById("buyLink");
                                    buyLink.href += quantity;
                                }
                            </script>
                            <div style="margin-top: 20px">
                                <input type="hidden" name="hiddenID" value="${detail.ID}">
                                <input type="submit" name="btnCart" value="Submit" class="buy--btn">
                            </div>
                        </div>
                </section>
            </form>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
