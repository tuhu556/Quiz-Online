/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class QuizDTO {
    private Date startTime;
    private Date endTime;
    private String subject;
    private List<QuestionDTO> list;

    public QuizDTO() {
    }

    public QuizDTO(Date startTime, Date endTime, String subject, List<QuestionDTO> list) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.list = list;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<QuestionDTO> getList() {
        return list;
    }

    public void setList(List<QuestionDTO> list) {
        this.list = list;
    }

    
    
}
