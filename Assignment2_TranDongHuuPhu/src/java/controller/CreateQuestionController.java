/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import dto.QuestionDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class CreateQuestionController extends HttpServlet {

    private static final String SUCCESS = "createQues.jsp";
    private static final String ERROR = "error.html";
    private static final Logger LOG = Logger.getLogger(CreateQuestionController.class);

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
        boolean check = true;
        String errorStr = "";
        try {
            String questionContent = request.getParameter("txtQuestionContent");
            String subjectID = request.getParameter("txtSubjectID");
            String answerA = request.getParameter("txtA");
            String answerB = request.getParameter("txtB");
            String answerC = request.getParameter("txtC");
            String answerD = request.getParameter("txtD");
            String correctAnswer = request.getParameter("txtCorrectAnswer");
            if (answerA.equalsIgnoreCase(answerB) || answerA.equalsIgnoreCase(answerC) || answerA.equalsIgnoreCase(answerD)
                    || answerB.equalsIgnoreCase(answerC) || answerB.equalsIgnoreCase(answerD) || answerC.equalsIgnoreCase(answerD)){
                errorStr = "Duplicate Answer!";
                check = false;
            }
            if (check){
                QuestionDAO dao = new QuestionDAO();
                QuestionDTO dto = new QuestionDTO("", questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, check,"","",false);
                if (dao.createQues(dto)){
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }
            } else {
                request.setAttribute("ERROR", errorStr);
                System.out.println(errorStr);
                url = SUCCESS;
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
