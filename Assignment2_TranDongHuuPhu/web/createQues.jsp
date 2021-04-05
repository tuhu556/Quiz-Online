<%-- 
    Document   : createQues
    Created on : Jan 26, 2021, 9:09:21 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <c:set var="error" value="${requestScope.ERROR}"></c:set>
        <c:set var="list" value="${sessionScope.SUBJECT}"></c:set>
            <form action="CreateQuestionController" class="w3-container w3-card-4 w3-light-grey w3-text-blue w3-margin" method="POST">
                <h2 class="w3-center">Create new Question</h2>
                <div><h3>${requestScope.ERROR}</h3></div>
                <div class="w3-row w3-section">
                    <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-hotel"></i></div>
                    <div class="w3-rest">
                        <input class="w3-input w3-border" type="text" name="txtQuestionContent" placeholder="Question Content" maxlength="300" required/>
                </div>
            </div>

            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-tags"></i></div>
                <select class="w3-select" name="txtSubjectID" required>
                    <option value="" disabled selected>Subject ID</option>
                    <c:forEach var="sub" items="${list}">
                        <option value="${sub.subjectID}">${sub.subjectID}</option>   
                    </c:forEach>
                </select>
            </div>

            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-audio-description"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border" type="text" name="txtA"  placeholder="Answer A" maxlength="300" required/>
                </div>
            </div>
            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-audio-description"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border" type="text" name="txtB"  placeholder="Answer B" maxlength="300" required/>
                </div>
            </div>
            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-audio-description"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border" type="text" name="txtC"  placeholder="Answer C" maxlength="300" required/>
                </div>
            </div>
            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-audio-description"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border" type="text" name="txtD"  placeholder="Answer D" maxlength="300" required/>
                </div>
            </div>

            <div class="w3-row w3-section">
                <select class="w3-select" name="txtCorrectAnswer" required>
                    <option value="" disabled selected>Correct Answer</option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select>
            </div>

            <p class="w3-center">
                <input type="submit" value="Create" class="w3-button w3-section w3-blue w3-ripple"/>
                <a href="admin.jsp" class="w3-button w3-section w3-blue w3-ripple">Back</a>
            </p>
        </form>

    </body>
</html>
