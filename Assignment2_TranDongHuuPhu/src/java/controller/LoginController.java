/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import dto.UserDTO;
import static controller.SHA_256.getSHA;
import static controller.SHA_256.toHexString;
import dao.QuestionDAO;
import dto.SubjectDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";
    private static final String AD = "AD";
    private static final String US = "US";
    private static final Logger LOG = Logger.getLogger(LoginController.class);

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
        String url = ERROR;

        try {
            String userID = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String SHA_Password = toHexString(getSHA(password));
            UserDAO dao = new UserDAO();
            QuestionDAO daoQ = new QuestionDAO();
            List<SubjectDTO> lstSubject = daoQ.getSubject();
            UserDTO user = dao.checkLogin(userID, SHA_Password);
            HttpSession session = request.getSession();
            if (user == null) {
                url = ERROR;
                request.setAttribute("LOGIN_ERROR", "Incorrect User ID or password!");

            } else {
                if (user.isStatus()) {
                    session.setAttribute("LOGIN_USER", user);
                    session.setAttribute("SUBJECT", lstSubject);
                    if (user.getRole().equals(AD)) {
                        url = ADMIN;
                    }
                    if (user.getRole().equals(US)) {
                        url = USER;
                    }
                } else {
                    url = ERROR;
                    request.setAttribute("LOGIN_ERROR", "This account has been terminated!");
                }
            }

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
