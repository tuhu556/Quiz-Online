/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.SHA_256.getSHA;
import static controller.SHA_256.toHexString;
import dao.UserDAO;
import dto.UserDTO;
import dto.UserErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class SignUpController extends HttpServlet {

    private static final String SUCCESS = "createSuccess.html";
    private static final String ERROR = "signup.jsp";
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SignUpController.class);
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
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        boolean check = true;
        try {
            String email = request.getParameter("txtEmail");
            String name = request.getParameter("txtName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            UserDAO dao = new UserDAO();
            UserErrorDTO error = new UserErrorDTO("", "", "", "", "");

            if (!name.trim().equals("")) {
                if (name.length() > 30) {
                    check = false;
                    error.setNameError("Max length is 30 words!");
                }
            }
            if (!dao.checkID(email)) {
                check = false;
                error.setEmailError("This Email is already exist--Please try another Email");
            }
            if (!email.trim().equals("")) {
                if (!email.matches("(\\w*\\d*)+@(\\w+\\.\\w+){1}(\\.\\w+)?")) {
                    check = false;
                    error.setEmailError("Wrong format Email!");
                }
            }

            if (!password.trim().equals("")) {
                if (password.length() < 3 || password.length() > 30) {
                    check = false;
                    error.setPasswordError("Password length from 3 to 30");
                }
                if (!confirm.trim().equals("")) {
                    if (!confirm.equals(password)) {
                        error.setPasswordError("confirm does not macth with password! Please try again!");
                    }
                }

                if (check) {
                    String SHA_Password = toHexString(getSHA(password));
                    UserDTO dto = new UserDTO(email, SHA_Password, name, "US", true);
                    dao.create(dto);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", error);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
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
