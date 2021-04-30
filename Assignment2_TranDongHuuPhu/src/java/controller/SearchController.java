/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import dto.QuestionDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class SearchController extends HttpServlet {

    private static final String SUCCESS = "questionManager.jsp";
    private static final String ERROR = "error.html";
    private static final Logger LOG = Logger.getLogger(SearchController.class);

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
            String search = request.getParameter("txtSearch");
            String index = request.getParameter("index");
            String subject = request.getParameter("Subject");
            String txtStatus = request.getParameter("status");
            if (search == null) {
                search = "";
            }
            if (subject == null) {
                subject = "";
            }
            if (index == null) {
                index = "1";
            }
            if (txtStatus == null) {
                txtStatus = "True";
            }
            int indexPage = Integer.parseInt(index);
            QuestionDAO dao = new QuestionDAO();
            int numberPage = dao.getNumberPage(search, subject, txtStatus);
            List<QuestionDTO> list = dao.getQuestion(indexPage, search, subject, txtStatus);
            if (list != null) {
                request.setAttribute("totalPages", numberPage);
                request.setAttribute("search", search);
                request.setAttribute("sub", subject);
                request.setAttribute("LIST", list);
                request.setAttribute("status", txtStatus);
                request.setAttribute("index", indexPage);
                url = SUCCESS;
            } else {
                url = ERROR;
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
