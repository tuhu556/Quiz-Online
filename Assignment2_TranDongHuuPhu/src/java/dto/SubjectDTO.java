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
public class SubjectDTO {

    private String subjectID;
    private String subjectName;
    private int numberOfQuestion;
    private int time;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int numberOfQuestion, int time) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numberOfQuestion = numberOfQuestion;
        this.time = time;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(int numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
