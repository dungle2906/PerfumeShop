/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import Models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *
 * @author User
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    private AccountDAO accountDAO;

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        if (path.endsWith("/Login")) {
            request.getRequestDispatcher("JSP/Login/Login.jsp").forward(request, response);
        } else {
            if (path.endsWith("/Login/Signup")) {
                request.getRequestDispatcher("/JSP/Login/signup.jsp").forward(request, response);
            } else {
                if (path.endsWith("/Login/ForgetPass")) {
                    request.getRequestDispatcher("/JSP/Login/forgot_password.jsp").forward(request, response);
                } else {
                    if (path.endsWith("/Login/OTP")) {
                        request.getRequestDispatcher("/JSP/Login/OTP.jsp").forward(request, response);
                    } else {
                       if (path.endsWith("/Login/Reset")) {
                        request.getRequestDispatcher("/JSP/Login/ResetPassword.jsp").forward(request, response);
                    } 
                    }
                }
            }
        }
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
        if (request.getParameter("btnLogin") != null && request.getParameter("btnLogin").equals("Submit")) {
            try {
                String us = request.getParameter("txtUS");
                String pwd = request.getParameter("txtPWD");
                Account acc = new Account(us, pwd);
                AccountDAO dao = new AccountDAO();

                boolean ketqua = false;
                try {
                    ketqua = dao.Login(acc);
                } catch (SQLException ex) {

                }
                boolean isAdmin = dao.IsAdmin(us);
                // Check if admin
                // If it is Admin, add cookie admins
                // If not add cookie customers
                if (ketqua) {
                    HttpSession session = request.getSession();
                    if (request.getParameter("chkRem") != null) {
                        if (isAdmin) {
                            Cookie c = new Cookie("Adminstrator", us);
                            c.setMaxAge(3 * 24 * 60 * 60);
                            response.addCookie(c);
                        } else {
                            Cookie c = new Cookie("Customer", us);
                            c.setMaxAge(3 * 24 * 60 * 60);
                            response.addCookie(c);
                        }
                    } else {
                        session.setAttribute("quantri", us);
                    }

                    String fullname = dao.GetFullName(us);
                    int ID = dao.GetIDFromFullname(fullname);
                    if (isAdmin) { // Kiểm tra nếu là quản trị viên
                        session.setAttribute("Adminstrator", fullname);
                        session.setAttribute("AdminstratorID", ID);
                        response.sendRedirect("/Account"); // Chuyển hướng đến trang "admin.jsp"
                    } else {
                        session.setAttribute("Customer", fullname);
                        session.setAttribute("CustomerID", ID);
                        response.sendRedirect("/"); // Chuyển hướng đến trang "Home.jsp" cho người dùng thông thường
                    }
                } else {
                    response.sendRedirect("/Login"); // Đăng nhập thất bại, chuyển hướng trở lại trang "index.jsp"
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
