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
import dto.ResultDTO;
import dto.UserAnswerDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
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
public class FinishQuizController extends HttpServlet {

    private static final String SUCCESS = "result.jsp";
    private static final String ERROR = "user.jsp";
    private static final Logger LOG = Logger.getLogger(FinishQuizController.class);
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
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            List<UserAnswerDTO> list = new ArrayList<>();
            int totalPage = (int) session.getAttribute("TOTAL_PAGE");

            for (int i = 1; i <= totalPage; i++) {
                String questionID = request.getParameter("txtQuestionID" + i);
                String answer = request.getParameter("txtAnswer" + i);
                if (answer == null) {
                    answer = " ";
                }
                list.add(new UserAnswerDTO(questionID, answer));
            }
            QuestionDAO quesDao = new QuestionDAO();
            for (UserAnswerDTO ques : list) {
                    quesDao.setProcess(ques.getQuestionID(),false);
                }
            QuizDTO dto = (QuizDTO) session.getAttribute("QUIZ");
            List<QuestionDTO> listQ = dto.getList();
            int totalCorrect = 0;
            float totalQuestion = totalPage;
            float totalPoint = 10;
            float point = 10 / totalQuestion;
            for (UserAnswerDTO l : list) {
                for (QuestionDTO a : listQ) {
                    if (l.getQuestionID().equalsIgnoreCase(a.getQuestionID())) {
                        if (l.getAnwser().equalsIgnoreCase(a.getCorrectAnswer())) {
                            System.out.println(1);
                            totalCorrect++;
                        } else {
                            System.out.println(0);
                            totalPoint = totalPoint - point;
                        }
                    }
                }
            }
            QuizDAO dao = new QuizDAO();
            ResultDTO result = new ResultDTO("", user.getEmail(), dto.getSubject(), totalPage, totalCorrect, totalPoint, "");
            int quizID = dao.createQuiz(result);
            System.out.println(totalPoint);
            for (UserAnswerDTO l : list) {
                if (l.getAnwser()== null){
                    l.setAnwser("No Answer");
                }
                dao.createDetail(Integer.parseInt(l.getQuestionID()), l.getAnwser(), quizID);
            }

            request.setAttribute("RESULT", result);
            session.removeAttribute("QUIZ");
            session.removeAttribute("LIST_QUIZ");
            url = SUCCESS;

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
