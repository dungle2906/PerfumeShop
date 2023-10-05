<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <link href="Download/bootstrap-5.0.2_dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="CSS/styleForMain.css" rel="stylesheet" type="text/css"/>
        <script src="slider.js"></script>
        <script src="allProduct.js"></script>
        <title>Perfume Store</title>
    </head>
    <body>
        <jsp:include page="JSP/WebsitePage/Menu.jsp"></jsp:include>
            <div class="slider">
                <div class="slides">
                    <img src="<%= request.getContextPath()%>/ImageForPage/thiet-ke-shop-nuoc-hoa-7.jpg" width="100" height="50" alt="image1">
                <img src="<%= request.getContextPath()%>/ImageForPage/thiet-ke-shop-nuoc-hoa-9.jpg" width="100" height="50" alt="image2">
                <img src="<%= request.getContextPath()%>/ImageForPage/thiet-ke-shop-nuoc-hoa-3.jpg"  width="100" height="50" alt="image3">
                <img src="<%= request.getContextPath()%>/ImageForPage/thiet-ke-shop-nuoc-hoa-1.jpg" width="100" height="50" alt="image4">
                <img src="<%= request.getContextPath()%>/ImageForPage/thiet-ke-shop-nuoc-hoa-4.jpg" width="100" height="50" alt="image5">
            </div>
        </div>
        <!---------------------------------------------------------------------------------------------->
        <div class="product-container" style="height: 610px;" >
            <!--sản phẩm 1-->
            <div class="body_content">
                <div class="Image_Center">
                    <img src="ImageForPage/nuoc-hoa-nam-Dior-Sauvage-Parfum-11.png"  alt="Dior Sauvage" class="pic"/>
                </div>
                <h2 style="color: black;">DETAILS OF DIOR SAUVAGE EAU DE PARFUM</h2> 
                <p style="font-size: large;">Ở phiên bản này, mùi hương sẽ không khác nhiều so với phiên bản Eau De Toilette, vẫn là sự kết hợp quen thuộc và cổ điển từ Cam Bergamot, Ambroxan và Oải hương. Một mùi hương đã đưa thương hiệu Dior lên một tầm cao mới và giúp Dior Sauvage trở thành một trong những loại nước hoa nam bán chạy nhất và phổ biến nhất mọi thời đại. Phiên bản Eau De Parfum sẽ mang vị cay của Tiêu đậm hơn, sâu hơn và dịu hơn nhờ hương thơm của Vanilla. Vani thoáng qua nhưng không quá nồng khiến mùi hương vẫn tươi mát và nồng nàn.</p>
                <p style="font-size: large;">Với tiếng tăm lẫy lừng có đàn anh đi trước, Dior Sauvage phiên bản Eau De Parfum cũng không hề kém cạnh về mùi hương lẫn độ ưa chuộng. Và sau đây là những lý do bạn nên có một chai nước hoa Dior Sauvage EDP trong bộ sưu tập của mình,</p>
                <p style="font-size: large;">Dior Sauvage EDP giống với bản EDT từ mùi hương cho đến cả độ bám tỏa cực tốt, và cả độ đa dụng. Sauvage EDP là một mùi hương dành cho các chàng trai không muốn quá bận tâm phải xịt chai nước hoa nào mỗi ngày. Đây đích thực là một chai nước hoa có thể áp dụng cho tất cả các dịp, các thời tiết và đa dạng gu thời trang.</p>
                <p style="color: black; font-size: large;"><b>Product's Information:</b></p>

                <ul style="list-style-type: circle;">
                    <li><span>Thương hiệu:</span> 
                        Christian Dior</li>
                    <li><span>Xuất xứ:</span>
                        Pháp</li>
                    <li><span>Năm phát hành:</span>  
                        2018</li>
                    <li><span>Nhóm hương:</span>			Hương ambroxan, Cam bergamot, Hạt tiêu tứ xuyên</li>
                    <li><span>Phong cách:</span>
                        Phóng khoáng, Nam tính, Cuốn hút</li>
                    <li><span>Hương đầu:</span> Bergamot</li>
                    <li><span>Hương giữa:</span> Tiêu, Hoa Oải Hương, Đại Hồi, Nhục Đậu Khấu</li>
                    <li><span>Hương cuối:</span> Ambroxan, Vani</li>
                </ul>
                <!--sản phẩm 2-->
            </div>



            <div class="body_content">    
                <div class="Image_Center">
                    <img src="https://lh3.googleusercontent.com/slxeLBC_AiLpIg3nnih99W0Fka9G_Cimatf4xTjblg8B2s9ssWOWzxreHqRkWpoV3pDGEnVM30uwNg2BDqAlaV-rB3IjTYY62w=w500-rw"  alt="Versace Eros" class="Product_Picture"/> 
                </div>
                <h2 style="color: black;">DETAILS OF VERSACE EROS</h2> 
                <p style="font-size: large;">Không quá lời khi nói rằng, Versace Eros là mùi hương lấy lại vị thế cho nhà mốt lừng danh đến từ đất nước nước hình chiếc ủng trong thế kỷ 21. Tươi trẻ, sôi động và gợi cảm, đó là những tính từ có thể dùng để mô tả sát nghĩa nhất mùi hương nằm trong chai hương màu xanh đậm dấu ấn Địa Trung Hải này.</p>
                <p style="font-size: large;">Eros khởi đầu bằng mùi hương của Quả táo, một mùi hương Trái cây giòn tan, tươi tắn, được nhuộm vàng màu nắng bởi một Quả chanh vàng mọng nước. Bên cạnh Trái cây, Bạc hà the the, trẻ trung, phóng khoáng là nhân vật còn lại của điểm khởi đầu, đem theo một chút hậu vị ngọt ngọt nơi khoang mũi, để dẫn mùi hương của Eros vào với chủ điểm của nó, là sự ngọt ngào. Đậu Tonka, Vanilla quyện lấy nhau, một sự kết hợp ngọt ngào không xa lạ, khiến mùi hương trở nên mềm mại và gợi cảm hơn.</p>
                <p style="font-size: large;">Versace Eros Eau de Toilette là lựa chọn an toàn và hiệu quả cho mọi chàng trai trẻ trung, năng động, thích tiệc tùng và ưa ồn ào náo nhiệt.
                    Xem thêm</p>
                <p style="color: black; font-size: large;"><b>Product's Information:</b></p>

                <ul style="list-style-type: circle;">
                    <li><span>Thương hiệu:</span> 
                        Versace</li>
                    <li><span>Xuất xứ:</span>
                        Ý</li>
                    <li><span>Năm phát hành:</span>  
                        2012</li>
                    <li><span>Nhóm hương:</span>	Vani, Bạc hà, Đậu tonka, Táo</li>
                    <li><span>Phong cách:</span>
                        Nam tính, Gợi cảm, Thu hút</li>
                    <li><span>Hương đầu:</span> Bạc hà, Táo xanh, Quả chanh vàng.</li>
                    <li><span>Hương giữa:</span> Đậu Tonka, Ambroxan, Geranium.</li>
                    <li><span>Hương cuối:</span> Vanilla, Gỗ tuyết tùng, Cỏ hương bài, Rêu sồi.</li>
                </ul>
            </div>
            <!--sản phẩm 3-->


            <div class="body_content">
                <div class="Image_Center">
                    <img src="https://cdn.dutycast.com/dutycast/attachments/adspnhutnpujwc2ecnzh714p0cr3?max-w=1000"  alt="Channel" class="Product_Picture"/> 
                </div> 
                <h2 style="color: black;">DETAILS OF CHANEL NO5 EAU DE PARFUM RED EDITION</h2> 
                <p style="font-size: large;">Đây là dòng nước hoa Chanel thuộc nhóm Floral Aldehyde (Hương hoa cỏ An-Đê-Hít). No 5 Parfum Red Edition được cho ra mắt vào năm 2018. Bên cạnh đó, Hương An-đê-hít và Hoa nhài là hai hương bạn có thể dễ dàng cảm nhận được nhất khi sử dụng nước hoa này.</p>
                <p style="font-size: large;">Ra đời năm 1921, từ ý tưởng tạo ra một loại nước hoa dành cho nữ giới của Coco Chanel, đến tận bây giờ nước hoa Chanel N°5 là một trong những loại nước hoa có sức hút lớn nhất trong gần 100 năm qua, là một trong những loại nước hoa bán chạy nhất trên thế giới hiện nay.</p>
                <p style="font-size: large;">Chai nước hoa Chanel N°5 Eau De Parfum ra mắt năm 1986 được sáng tạo dựa trên hương thơm kinh điển huyền thoại của Chanel N°5 năm 1921 dưới sự sáng tạo của nhà chế tác Jacques Polges với mong muốn giữ trọn vẻ đẹp và phong cách huyền thoại của loại nước hoa này với cách thể hiện mới mẻ và hiện đại hơn.</p>
                <p style="color: black; font-size: large;"><b>Product's Information:</b></p>

                <ul style="list-style-type: circle;">
                    <li><span>Thương hiệu:</span> 
                        Chanel</li>
                    <li><span>Xuất xứ:</span>
                        Pháp</li>
                    <li><span>Năm phát hành:</span> 
                        2018</li>
                    <li><span>Nhóm hương:</span> Hương hoa cỏ An-Đê-Hít</li>
                    <li><span>Phong cách:</span> Sang trọng, Quý phái</li>
                    <li><span>Hương đầu:</span>  Hoa ngọc lan tây (Ylang-Ylang) , Hương An-đê-hít (Aldehydes) , Hoa cam Neroli , Cam Bergamot , Quả đào (Peach). </li>
                    <li><span>Hương giữa:</span>  Hoa diên vĩ (Orris) , Hoa nhài (Jasmine) , Hoa hồng (Rose) , Hoa Lily thung lũng (Lily-of-the-Valley).</li>
                    <li><span>Hương cuối:</span>  Gỗ đàn hương (Sandalwood) , Hương Va ni (Vanilla) , Rêu sồi (Oakmoss) , Cỏ hương bài (Vetiver) , Cây hoắc hương (Patchouli).</li>
                </ul>
            </div>
        </div>
        <div class="buttonStyling">
            <button style="width: 100px; background: white" id="prev-btn" onclick="showPreviousProduct()"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAMAAACahl6sAAAAV1BMVEX///8AAADb29vj4+P5+fkVFRXR0dHU1NRHR0cWFhZMTExLS0tUVFQ0NDS/v7+ioqLq6uqNjY15eXmoqKjMzMwuLi4eHh4jIyPGxsYLCwsbGxvx8fGVlZVRNMDsAAABHklEQVR4nO3b207CQABF0RaQispFvIv//51KFOTNmiGZ07LWF5ydTNuHZpoGAAAAAAAAAAAAAACAc9ncb1e1N5zDfNG27bb2inLzq3ZvUntHqZ+O9qH2kEKHjvax9pIyx4672kvKHDtuZ7WnFNGRRUeW0XXc6EigI4uOLDqy6MiiI4uOLDqy6MiiI8voOpbdJNH0vx2xnro+HbPn2jv/9tLnyK9qr+zj9ZJCBnG0enQ0TRf/sC97/oj9ff1Opok2/TJOS66H/UFUkkhJnvGUdEriKMmjJI+SPEryKMmjJI+SPCMqWShJoyTPsWTg1y5OSgZ/NelQMvx7b98lb7vaO8p1718h69orzmG3/hj8EwIAAAAAAAAAAAAAAHAhPgHpoBC7e7CcPQAAAABJRU5ErkJggg==" alt="pre" style="width: 50px; height: 50px"/></button>
            <button style="width: 100px; background: white" id="next-btn" onclick="showNextProduct()"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSb0IMr-eyDIbiYxgYM2lj6bgASIDHyOQjpbg&usqp=CAU" alt="next" style="width: 50px; height: 50px"/></button>
        </div>
        <div class="total">

            <h1 style="color: black;">BEST SELLER</h1>

            <div class="product-container">
                <%
                    DAOs.ProductDAO dao = new ProductDAO();
                    ResultSet rs = dao.getProduct2();
                    while (rs.next()) {
                %>

                <table class="product-item">

                    <tr>
                        <th>
                            <img  src="<%=rs.getString("URL")%>" alt="perfume" class="Product_Item_Picture"/>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <h3 style="color: rgb(58, 91, 125);"><%=rs.getString("name")%></h3>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p style="color: rgb(15, 15, 73); font-size: large;">Price : <%=rs.getInt("price")%>$</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="button-three">
                                <a href="/Website/Buy/<%=rs.getInt("Product_ID")%>" >BUY</a>
                            </div>
                        </td>
                    </tr>

                </table>   
                <%
                    }
                %>
            </div>              
            <div style="clear: both;"></div>
        </div><!--Kết thúc listflower-->

        <jsp:include page="JSP/WebsitePage/Footer.jsp"></jsp:include>

    </body>
</html>
