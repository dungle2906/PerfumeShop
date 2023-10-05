 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ForgotPassword;

import DAOs.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiet
 */
public class UpdatePass extends HttpServlet {
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
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

        HttpSession mySession = request.getSession();
        String email = (String) mySession.getAttribute("email");
        String newPassword = request.getParameter("password").trim();
        String confPassword = request.getParameter("confPassword").trim();

        AccountDAO dao = null;
        try {
            dao = new AccountDAO();
        } catch (Exception ex) {
            Logger.getLogger(UpdatePass.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            newPassword = encrypt(newPassword);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);        }

        try {
            confPassword = encrypt(confPassword);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }
       
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            dao.forgetpass(newPassword, email);
            request.setAttribute("mess", "ForgetPassWord Successfully");

             response.sendRedirect("/Login");
        } else {
            request.setAttribute("mess", "Password not same");
            response.sendRedirect("/Login/Reset");
        }

    }

    public String encrypt(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter.printHexBinary(digest);
        return myHash;
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