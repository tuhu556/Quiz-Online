<%-- 
    Document   : login.jsp
    Created on : Jan 25, 2021, 10:29:34 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    </head>
    <body>
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/signin-image.jpg" alt="sing up image"></figure>
                        <a href="signup.jsp" class="signup-image-link">Create an account</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Sign in</h2>
                        <form action="LoginController" method="POST" class="register-form" id="login-form">
                            <p class="text-danger" >
                                ${requestScope.LOGIN_ERROR}
                            </p>
                            <br>
                            <div class="form-group">
                                <label for="your_name"><i class="fas fa-user"></i></label>
                                <input type="text" name="txtEmail" id="your_name" value="${param.txtEmail}" placeholder="Your Email" required/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="fas fa-key"></i></label>
                                <input type="password" name="txtPassword" id="your_pass" value="${param.txtPassword}" placeholder="Password" required/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="btnAction" value="Login" id="signin" class="form-submit"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
