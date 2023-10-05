/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AccountController extends HttpServlet {

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
            out.println("<title>Servlet AccountController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountController at " + request.getContextPath() + "</h1>");
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
        // /Account
        // /Account/Info
        if (path.endsWith("/Account")) {
            /**
             * Redirect to Main Page if the URL is /Account
             *
             * @param
             */
            request.getRequestDispatcher("JSP/AccountPage/MainPage.jsp").forward(request, response);
        } else {
            if (path.startsWith("/Account/View/")) {
                /**
                 * View all of the Information of a specific Account if the
                 * accounte exist, go to Account Info If not go back to Main
                 * Page
                 *
                 * @param
                 */
                try {
                    try {
                        String[] s = path.split("/");
                        int AccountID = Integer.parseInt(s[s.length - 1]);
                        if (AccountID == 0) {
                            response.sendRedirect("/Account");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("AccountID", AccountID);
                            request.getRequestDispatcher("/JSP/AccountPage/AccountInfo.jsp").forward(request, response);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (path.startsWith("/Account/MakeAdmin/")) {
                    /**
                     * Make an Account an admin by changing it IsAdmin from 0 to
                     * 1
                     *
                     * @param
                     */
                    try {
                        try {
                            String[] s = path.split("/");
                            int AccountID = Integer.parseInt(s[s.length - 1]);
                            AccountDAO dao = new AccountDAO();
                            int result = dao.AddAdmin(AccountID);
                            if (result != 0) {
                                response.sendRedirect("/Admin");
                            } else {
                                response.sendRedirect("/Account");
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (path.endsWith("/Account/Logout")) {
                        Cookie[] cookies = request.getCookies();
                        for (Cookie cookie : cookies) {
                            cookie.setMaxAge(0);
                            cookie.setPath("/");
                            response.addCookie(cookie);
                        }
                        response.sendRedirect("/");
                    } else {
                        if (path.startsWith("/Account/Delete/")) {
                            try {
                                String[] s = path.split("/");
                                int AccountID = Integer.parseInt(s[s.length - 1]);
                                AccountDAO dao = new AccountDAO();
                                dao.DeleteAccount(AccountID);
                                response.sendRedirect("/Account");
                            } catch (Exception ex) {
                                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
