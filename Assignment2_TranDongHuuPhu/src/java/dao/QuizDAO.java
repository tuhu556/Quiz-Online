/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuestionDTO;
import dto.ResultDTO;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class QuizDAO {

    private static final Logger LOG = Logger.getLogger(QuizDAO.class);

    public List<QuestionDTO> getQuestionRamdom(int numberQues, String subjectID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<QuestionDTO> list = new ArrayList<>();
        String query = "select TOP(?) questionID, questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, process \n"
                + "from tblQuestions where status = 1 and subjectID like ? order by NEWID()  ";
        try {
            conn = util.getCon();
            stm = conn.prepareStatement(query);
            stm.setInt(1, numberQues);
            stm.setString(2, "%" + subjectID + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString("questionID");
                String questionContent = rs.getString("questionContent");
                subjectID = rs.getString("subjectID");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                String correctAnswer = rs.getString("correctAnswer");
                boolean process = rs.getBoolean("process");
                list.add(new QuestionDTO(questionID, questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, true, "", "", process));
            }
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int getNumberPageQuiz(String subjectID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "select numberOfQuestion from tblSubjects where subjectID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + subjectID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int total = rs.getInt("numberOfQuestion");
                    int countPage = 0;
                    countPage = total / 1;
                    if (total % 1 != 0) {
                        countPage++;
                    }
                    return countPage;
                }
            }

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public int createQuiz(ResultDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quizID = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblQuizs (userID, subjectID, totalQuestion, totalCorrect, mark, createDate) VALUES(?,?,?,?,?,?)";
            Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, dto.getUserID());
            stm.setString(2, dto.getSubjectID());
            stm.setInt(3, dto.getTotalQuestion());
            stm.setInt(4, dto.getTotalCorrect());
            stm.setFloat(5, dto.getMark());
            stm.setTimestamp(6, createDate);
            stm.execute();

            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                quizID = rs.getInt(1);
            }
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quizID;
    }

    public void createDetail(int questionID, String Answer, int quizID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int detailID = 0;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblDetail (quizID, questionID, userAnswer) VALUES(?,?,?)";
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, quizID);
            stm.setInt(2, questionID);
            stm.setString(3, Answer);
            stm.execute();

            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                detailID = rs.getInt(1);
            }
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getNumberOfQuestion(String subjectID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "select numberOfQuestion from tblSubjects where subjectID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + subjectID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int num = rs.getInt("numberOfQuestion");
                    return num;
                }
            }

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

    public int getTime(String subjectID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "select time from tblSubjects where subjectID like ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + subjectID + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int time = rs.getInt("time");
                    return time;
                }
            }

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }
}
