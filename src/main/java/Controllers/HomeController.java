/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.CartDAO;
import DAOs.OrderDAO;
import DAOs.OrderDetailDAO;
import DAOs.ProductDAO;
import Models.Account;
import Models.Cart;
import Models.GetProduct;
import Models.Item;
import Models.Order;
import Models.OrderDetail;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/")) {
            int ID = 1;
            try {
                HttpSession session = request.getSession();
                AccountDAO dao = new AccountDAO();
                String check = (String) session.getAttribute("Customer");
                //int ID = Integer.parseInt(request.getAttribute("ID").toString());

            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getRequestDispatcher("Home.jsp").forward(request, response);
        } else {
            if (path.endsWith("/Website")) {
                request.getRequestDispatcher("JSP/WebsitePage/Detail.jsp").forward(request, response);
            } else {
                if (path.startsWith("/Website/Buy/")) {
                    // /Product/View
                    try {
                        try {
                            String[] s = path.split("/");
                            int ProductID = Integer.parseInt(s[s.length - 1]);
                            ProductDAO dao = new ProductDAO();
                            GetProduct pro = dao.getProductByID(ProductID);
                            if (ProductID == 0) {
                                response.sendRedirect("/");
                            } else {
                                request.setAttribute("detail", pro);
                                request.getRequestDispatcher("/JSP/WebsitePage/Detail.jsp").forward(request, response);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (path.startsWith("/Website/Delete/")) {
                        try {
                            String[] s = path.split("/");
                            int ProductID = Integer.parseInt(s[s.length - 1]);
                            AccountDAO accDAO = new AccountDAO();
                            CartDAO cartDAO = new CartDAO();
                            int A_ID = accDAO.GetIDFromFullname((String) request.getAttribute("fullname"));
                            cartDAO.DeleteProductFromCart(ProductID, A_ID);
                            response.sendRedirect("/Website/Cart");

                        } catch (Exception ex) {
                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        if (path.endsWith("/Website/Cart")) {
                            try {
                                AccountDAO accDAO = new AccountDAO();
                                CartDAO cartDAO = new CartDAO();
                                int A_ID = accDAO.GetIDFromFullname((String) request.getAttribute("fullname"));
                                ResultSet rs = cartDAO.ShowCartByID(A_ID);
                                request.setAttribute("rs", rs);
                                request.setAttribute("TotalPrice", cartDAO.GetTotalPriceBasedOnAccountID(A_ID));
                                request.getRequestDispatcher("/JSP/WebsitePage/buy.jsp").forward(request, response);
                            } catch (Exception ex) {
                                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            if (path.endsWith("/Website/SubmitOrder")) {
                                try {
                                    String Fullname = (String) request.getAttribute("fullname");
                                    AccountDAO acc = new AccountDAO();
                                    CartDAO cartDAO = new CartDAO();
                                    int A_ID = acc.GetIDFromFullname(Fullname);
                                    ResultSet rs = acc.GetInfoAccount(A_ID);
                                    String MobilePhone = null, Address = null;
                                    if (rs.next()) {
                                        MobilePhone = rs.getString("Mobile_Number");
                                        Address = rs.getString("Address");
                                    }
                                    ResultSet rs1 = cartDAO.ShowCartByID(A_ID);
                                    request.setAttribute("rs1", rs1);
                                    request.setAttribute("TotalPrice", cartDAO.GetTotalPriceBasedOnAccountID(A_ID));
                                    request.setAttribute("Fullname", Fullname);
                                    request.setAttribute("MobilePhone", MobilePhone);
                                    request.setAttribute("Address", Address);
                                    request.getRequestDispatcher("/JSP/WebsitePage/OrderDetailInput.jsp").forward(request, response);
                                } catch (Exception ex) {
                                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                if (path.startsWith("/Website/Info/")) {
                                    try {
                                        AccountDAO accDAO = new AccountDAO();
                                        String[] s = path.split("/");
                                        String Fullname = s[s.length - 1];
                                        int ID = Integer.parseInt(Fullname);
                                        ResultSet rs = accDAO.GetAll(ID);
                                        request.setAttribute("rs", rs);
                                        request.getRequestDispatcher("/JSP/WebsitePage/Information.jsp").forward(request, response);
                                    } catch (Exception ex) {
                                        Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    if (path.startsWith("/Website/Update/")) {
                                        try {
                                            AccountDAO accDAO = new AccountDAO();
                                            String[] s = path.split("/");
                                            int ID = Integer.parseInt(s[s.length - 1]);
                                            ResultSet rs = accDAO.GetAll(ID);
                                            request.setAttribute("rs", rs);
                                            request.getRequestDispatcher("/JSP/WebsitePage/Update.jsp").forward(request, response);
                                        } catch (Exception ex) {
                                            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean IsCookieAvailable(HttpServletRequest request) throws IOException, jakarta.servlet.ServletException {
        boolean CookieChecker = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie i : cookies) {
                if (i.getName().equals("Customer")) {
                    CookieChecker = true;
                    break;
                }
            }
        }
        return CookieChecker;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnLogout") != null && request.getParameter("btnLogout").equals("Submit")) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            response.sendRedirect("/Login");
        }

        if (request.getParameter("btnCart") != null && request.getParameter("btnCart").equals("Submit")) {
            try {

                HttpSession session = request.getSession();
                String check = (String) session.getAttribute("Customer");
                if (!IsCookieAvailable(request) && check == null) {
                    response.sendRedirect("/Login");
                } else {
                    AccountDAO accDAO = new AccountDAO();
                    CartDAO cartDAO = new CartDAO();
                    int A_ID = accDAO.GetIDFromFullname((String) request.getAttribute("fullname"));
                    int P_ID = Integer.parseInt(request.getParameter("hiddenID"));
                    String P_Name = request.getParameter("ProductName");
                    int P_Amount = Integer.parseInt(request.getParameter("quantity"));
                    int P_Price = Integer.parseInt(request.getParameter("ProductPrice"));
                    int Amount = cartDAO.GetCartProductAmountByProductIDAndAccountID(P_ID, A_ID);
                    if (Amount == 0) {
                        Cart c = new Cart(P_ID, A_ID, P_Name, P_Amount, P_Price * P_Amount);
                        cartDAO.AddNewCart(c);
                    } else {
                        cartDAO.UpdateCartAmountAndPrice(P_Amount + Amount, (P_Price * Amount) + (P_Price * P_Amount), P_ID, A_ID);
                    }
                    response.sendRedirect("/Website/Cart");
                }

            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("SubmitCart") != null && request.getParameter("SubmitCart").equals("Submit Order")) {
            try {
                response.sendRedirect("/Website/SubmitOrder");
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("btnSubmitOrder") != null && request.getParameter("btnSubmitOrder").equals("Submit")) {
            try {
                AccountDAO accDAO = new AccountDAO();
                OrderDAO ordDAO = new OrderDAO();
                CartDAO cartDAO = new CartDAO();
                OrderDetailDAO odDAO = new OrderDetailDAO();
                int Order_ID = ordDAO.GetLastOrderID();
                int A_ID = accDAO.GetIDFromFullname((String) request.getAttribute("fullname"));
                String TotalPrice = request.getParameter("txtTotalPrice");
                int TotalPriceNum = Integer.parseInt(TotalPrice.substring(0, TotalPrice.length() - 1));
                String PhoneNumber = request.getParameter("txtOrderPhoneNumber");
                String DeliveryInstruction = request.getParameter("txtDeliveryInstrution");
                String DeliveryAddress = request.getParameter("txtAddress");
                Date DeliveryTime = Date.valueOf(request.getParameter("dateDelivery"));
                int GetPaymentMethod = Integer.parseInt(request.getParameter("slctPaymentMethod"));
                Order ord = new Order(Order_ID, A_ID, TotalPriceNum, PhoneNumber, DeliveryInstruction, DeliveryAddress, DeliveryTime, 3, GetPaymentMethod);
                int result = ordDAO.InsertOrder(ord);
                //int Counter = cartDAO.GetNumberOfCartBasedOnID(A_ID);
                ResultSet rs = cartDAO.ShowCartByID(A_ID);
                while (rs.next()) {
                    OrderDetail Detail = new OrderDetail(rs.getInt("Product_ID"), Order_ID, rs.getInt("Account_ID"), rs.getString("P_Name"),
                            rs.getInt("P_Amount"), rs.getInt("P_Price"));
                    odDAO.InsertIntoOrderDetail(Detail);
                }
                cartDAO.DeleteFromCartWithAccountID(A_ID);
                if (result != 0) {
                    response.sendRedirect("/");
                }
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (request.getParameter("btnUpdate") != null && request.getParameter("btnUpdate").equals("Update")) {

            try {
                String fullname = request.getParameter("Fullname");
                String username = request.getParameter("Username");
                String phone = request.getParameter("Mobile_Number");
                String email = request.getParameter("Email");
                String address = request.getParameter("Address");
                String gender = request.getParameter("slctGender");
                Date DoB = Date.valueOf(request.getParameter("DoB"));

                HttpSession session = request.getSession();
                int ID = Integer.parseInt(request.getAttribute("ID").toString());
                AccountDAO dao = new AccountDAO();
                int kq = dao.UpdateAccount(fullname, username, phone, email, address, gender, DoB, ID);
                if (kq != 0) {
                    response.sendRedirect("/");
                }
            } catch (Exception ex) {

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
