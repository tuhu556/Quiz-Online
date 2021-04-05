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
public class UpdateQuestionController extends HttpServlet {

    private static final String SUCCESS = "/SearchController";
     private static final String ERROR = "questionManager.jsp";
    private static final Logger LOG = Logger.getLogger(UpdateQuestionController.class);

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
        try {

            String questionID = request.getParameter("txtQuestionID");
            String questionContent = request.getParameter("txtQuestionContent");
            String subjectID = request.getParameter("txtSubjectID");
            String answerA = request.getParameter("txtA");
            String answerB = request.getParameter("txtB");
            String answerC = request.getParameter("txtC");
            String answerD = request.getParameter("txtD");
            String correctAnswer = request.getParameter("txtCorrectAnswer");
            String status = request.getParameter("txtStatus");
            QuestionDAO dao = new QuestionDAO();
            List<QuestionDTO> list = dao.getQuestion1(questionID);
            for (QuestionDTO questionDTO : list) {
                if (questionDTO.isProcess() == true) {
                    check = false;
                }
            }
            if (check) {
                boolean st = true;
                if (status == null) {
                    st = false;
                }
                QuestionDTO dto = new QuestionDTO(questionID, questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, st, "", "", false);
                dao.updateQues(dto);
                url = SUCCESS;
            }else {
                request.setAttribute("NOT_DELETE", "Cannot Delete this question: " + questionID + " cause this question is being used in the exam!");
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
