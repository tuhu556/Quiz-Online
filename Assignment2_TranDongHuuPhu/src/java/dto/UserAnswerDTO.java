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
public class UserAnswerDTO {
    private String questionID;
    private String anwser;

    public UserAnswerDTO() {
    }

    public UserAnswerDTO(String questionID, String anwser) {
        this.questionID = questionID;
        this.anwser = anwser;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }
    
}
