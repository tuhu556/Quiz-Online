/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.QuestionDTO;
import dto.SubjectDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class QuestionDAO {

    private static final Logger LOG = Logger.getLogger(QuestionDAO.class);

    public boolean createQues(QuestionDTO dto) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getCon();
            String sql = "INSERT INTO tblQuestions (subjectID, questionContent, answerA, answerB, answerC, answerD, correctAnswer, status, createDate, process) VALUES(?,?,?,?,?,?,?,?,?,?)";
            Date createDate = Date.valueOf(LocalDate.now());
            boolean status = true;
            stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, dto.getSubjectID());
            stm.setString(2, dto.getQuestionContent());
            stm.setString(3, dto.getAnswerA());
            stm.setString(4, dto.getAnswerB());
            stm.setString(5, dto.getAnswerC());
            stm.setString(6, dto.getAnswerD());
            stm.setString(7, dto.getCorrectAnswer());
            stm.setBoolean(8, status);
            stm.setDate(9, createDate);
            stm.setBoolean(10, false);
            stm.execute();

            rs = stm.getGeneratedKeys();
            int questionID = 0;
            if (rs.next()) {
                questionID = rs.getInt("questionID");
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
        return true;
    }

    public void updateQues(QuestionDTO dto) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblQuestions SET subjectID=?, questionContent = ?, answerA=?, answerB = ?, answerC = ?, answerD=?, correctAnswer=?, status=?, updateDate=? WHERE questionID=?";
            Date updateDate = Date.valueOf(LocalDate.now());
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getSubjectID());
            stm.setString(2, dto.getQuestionContent());
            stm.setString(3, dto.getAnswerA());
            stm.setString(4, dto.getAnswerB());
            stm.setString(5, dto.getAnswerC());
            stm.setString(6, dto.getAnswerD());
            stm.setString(7, dto.getCorrectAnswer());
            stm.setBoolean(8, dto.isStatus());
            stm.setDate(9, updateDate);
            stm.setString(10, dto.getQuestionID());
            stm.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void deleteQues(String ID) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblQuestions SET status=?, updateDate=? WHERE questionID=?";
            Date updateDate = Date.valueOf(LocalDate.now());
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setDate(2, updateDate);
            stm.setString(3, ID);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    public void setProcess(String ID, boolean process) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getCon();
            String sql = "UPDATE tblQuestions SET process=? WHERE questionID=?";
            stm = conn.prepareStatement(sql);
            stm.setBoolean(1, process);
            stm.setString(2, ID);
            stm.executeUpdate();

        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<SubjectDTO> getSubject() throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<SubjectDTO> list = new ArrayList<>();
        String query = "select subjectID, subjectName, numberOfQuestion, time from tblSubjects";
        try {
            conn = util.getCon();
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String subjectID = rs.getString("subjectID");
                String subjectName = rs.getString("subjectName");
                int numberOfQuestion = rs.getInt("numberOfQuestion");
                int time = rs.getInt("time");
                list.add(new SubjectDTO(subjectID, subjectName, numberOfQuestion, time));
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

    public List<QuestionDTO> getQuestion(int index, String search, String subject, String txtStatus) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<QuestionDTO> list = new ArrayList<>();
        String query = "select questionID, subjectID, questionContent, answerA, answerB, answerC, answerD, correctAnswer, status, createDate, updateDate, process from tblQuestions WHERE  questionContent like ? AND subjectID like ? AND status = ? order by subjectID offset ? rows fetch first 20 rows only";
        try {
            conn = util.getCon();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + subject + "%");
            ps.setString(3, txtStatus);
            ps.setInt(4, (index - 1) * 20);
            rs = ps.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString("questionID");
                String subjectID = rs.getString("subjectID");
                String questionContent = rs.getString("questionContent");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                String correctAnswer = rs.getString("correctAnswer");
                boolean status = rs.getBoolean("status");
                String createDate = rs.getString("createDate");
                String updateDate = rs.getString("updateDate");
                boolean process = rs.getBoolean("process");
                list.add(new QuestionDTO(questionID, questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, status, createDate, updateDate, process));
            }
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    public List<QuestionDTO> getQuestion1(String ID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<QuestionDTO> list = new ArrayList<>();
        String query = "select questionID, subjectID, questionContent, answerA, answerB, answerC, answerD, correctAnswer, status, createDate, updateDate, process from tblQuestions WHERE  questionID=?";
        try {
            conn = util.getCon();
            ps = conn.prepareStatement(query);
            ps.setString(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String questionID = rs.getString("questionID");
                String subjectID = rs.getString("subjectID");
                String questionContent = rs.getString("questionContent");
                String answerA = rs.getString("answerA");
                String answerB = rs.getString("answerB");
                String answerC = rs.getString("answerC");
                String answerD = rs.getString("answerD");
                String correctAnswer = rs.getString("correctAnswer");
                boolean status = rs.getBoolean("status");
                String createDate = rs.getString("createDate");
                String updateDate = rs.getString("updateDate");
                boolean process = rs.getBoolean("process");
                list.add(new QuestionDTO(questionID, questionContent, subjectID, answerA, answerB, answerC, answerD, correctAnswer, status, createDate, updateDate, process));
            }
        } catch (Exception e) {
            LOG.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int getNumberPage() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "select COUNT(questionID) from tblQuestions";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int total = rs.getInt(1);
                    int countPage = 0;
                    countPage = total / 20;
                    if (total % 20 != 0) {
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
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return 0;
    }

}
