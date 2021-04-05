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
public class UserErrorDTO {
    private String emailError;
    private String passwordError;
    private String nameError;
    private String roleError;
    private String statusError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String emailError, String passwordError, String nameError, String roleError, String statusError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.nameError = nameError;
        this.roleError = roleError;
        this.statusError = statusError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getRoleError() {
        return roleError;
    }

    public void setRoleError(String roleError) {
        this.roleError = roleError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }
    
}
