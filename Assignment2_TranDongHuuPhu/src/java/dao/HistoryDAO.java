/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AnswerDTO;
import dto.ResultDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class HistoryDAO {
    private static final Logger LOG = Logger.getLogger(HistoryDAO.class);
    public List<ResultDTO> getHistory(int index, String subjectID, String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<ResultDTO> list = new ArrayList<>();
        String query = "select quizID, userID, subjectID, totalQuestion,  totalCorrect, mark, createDate from tblQuizs WHERE  subjectID like ? AND userID like ? order by createDate offset ? rows fetch first 20 rows only";
        try {
            conn = util.getCon();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + subjectID + "%");
            ps.setString(2, userID);
            ps.setInt(3, (index - 1) * 20);
            rs = ps.executeQuery();
            while (rs.next()) {
                String quizID = rs.getString("quizID");
                userID = rs.getString("userID");
                subjectID = rs.getString("subjectID");
                int totalQuestion = rs.getInt("totalQuestion");
                int totalCorrect = rs.getInt("totalCorrect");
                float mark = rs.getFloat("mark");
                String createDate = rs.getString("createDate");
                list.add(new ResultDTO(quizID, userID, subjectID, totalQuestion, totalCorrect, mark, createDate));
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
    public List<AnswerDTO> getDetail(int quizID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBUtils util = new DBUtils();
        List<AnswerDTO> list = new ArrayList<>();
        String query = "select detailID, quizID, tblQuestions.questionID, tblQuestions.questionContent, tblQuestions.answerA, tblQuestions.answerB, tblQuestions.answerC, tblQuestions.answerD, tblQuestions.correctAnswer ,userAnswer from tblDetail full join tblQuestions on tblDetail.questionID = tblQuestions.questionID where quizID = ?";
        try {
            conn = util.getCon();
            ps = conn.prepareStatement(query);
            ps.setInt(1, 28);
            rs = ps.executeQuery();
            while (rs.next()) {
                int detailID = rs.getInt(1);
                quizID = rs.getInt(2);
                int questionID = rs.getInt(3);
                String questionContent = rs.getString(4);
                String answerA = rs.getString(5);
                String answerB = rs.getString(6);
                String answerC = rs.getString(7);
                String answerD = rs.getString(8);
                String correctAnswer = rs.getString(9);
                String userAnswer = rs.getString(10);
                list.add(new AnswerDTO(detailID, quizID, questionID, questionContent, answerA, answerB, answerC, answerD, correctAnswer, userAnswer));
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
    public int getNumberPageHistory(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getCon();
            if (conn != null) {
                String sql = "select COUNT(quizID) from tblQuizs where userID like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
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
