<%-- 
    Document   : detail
    Created on : Feb 6, 2021, 9:54:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>
    <body>
        <c:set var="list" value="${requestScope.LIST_A}"></c:set>
            <table border="1" class="table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Question Content</th>
                        <th>Answer A</th>
                        <th>Answer B</th>
                        <th>Answer C</th>
                        <th>Answer D</th>
                        <th>Your Answer</th>
                        <th>Correct Answer</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="result" items="${list}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${result.questionContent}</td>
                        <td>${result.answerA}</td>
                        <td>${result.answerB}</td>
                        <td>${result.answerC}</td>
                        <td>${result.answerD}</td>
                        <td>${result.userAnswer}</td>
                        <td>${result.correctAnswer}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="history.jsp" class="button">Back</a>
    </body>
</html>
