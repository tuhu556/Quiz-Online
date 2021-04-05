/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Admin
 */
public class ResultDTO {

    private String quizID;
    private String userID;
    private String subjectID;
    private int totalQuestion;
    private int totalCorrect;
    private float mark;
    private String createDate;

    public ResultDTO() {
    }

    public ResultDTO(String quizID, String userID, String subjectID, int totalQuestion, int totalCorrect, float mark, String createDate) {
        this.quizID = quizID;
        this.userID = userID;
        this.subjectID = subjectID;
        this.totalQuestion = totalQuestion;
        this.totalCorrect = totalCorrect;
        this.mark = mark;
        this.createDate = createDate;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
