/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.QuestionDAO;
import dao.QuizDAO;
import dto.QuestionDTO;
import dto.QuizDTO;
import java.io.IOException;
import java.util.Calendar;
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
public class AttemptQuizController extends HttpServlet {

    private static final String SUCCESS = "quiz.jsp";
    private static final String ERROR = "error.html";
    private static final Logger LOG = Logger.getLogger(AttemptQuizController.class);

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
            String subjectID = request.getParameter("txtSubjectID");

            QuizDAO dao = new QuizDAO();
            int numberOfQues = dao.getNumberOfQuestion(subjectID);
            int timing = dao.getTime(subjectID);
            List<QuestionDTO> list = dao.getQuestionRamdom(numberOfQues, subjectID);
            if (list.size() == 0 || numberOfQues == 0) {
                url = "errorQuiz.html";
            } else {
                HttpSession session = request.getSession();
                QuizDTO quiz = new QuizDTO();
                QuestionDAO quesDao = new QuestionDAO();
                Calendar time = Calendar.getInstance();
                quiz.setStartTime(time.getTime());
                time.add(Calendar.MINUTE, timing);
                quiz.setEndTime(time.getTime());
                quiz.setList(list);
                quiz.setSubject(subjectID);
                for (QuestionDTO ques : list) {
                    quesDao.setProcess(ques.getQuestionID(),true);
                }
                session.setAttribute("LIST_QUIZ", list);
                session.setAttribute("QUIZ", quiz);
                session.setAttribute("TOTAL_PAGE", numberOfQues);
                session.setAttribute("SUB", subjectID);
                url = SUCCESS;
            }

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            response.sendRedirect(url);
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
