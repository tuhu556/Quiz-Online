<%-- 
    Document   : result
    Created on : Feb 4, 2021, 12:57:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-dark bg-dark" aria-label="First navbar example">
                <div class="container-fluid">
                    <a class="navbar-brand" href="user.jsp">User: ${sessionScope.LOGIN_USER.name}</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarsExample01">
                        <ul class="navbar-nav me-auto mb-2">
                            <li class="nav-item active">
                                <a class="nav-link" aria-current="page" href="history.jsp">History</a>
                                <a class="nav-link" aria-current="page" href="LogoutController">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <main>
            <c:set var="list" value="${requestScope.RESULT}"/>
            <table border="1" class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>Subject ID</th>
                        <th>Email</th>
                        <th>Total Question</th>
                        <th>Total Correct Answers</th>
                        <th>Mark</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${list.subjectID}</td>
                        <td>${list.userID}</td>
                        <td>${list.totalQuestion}</td>
                        <td>${list.totalCorrect}</td>
                        <td>${list.mark}</td>
                    </tr>
                </tbody>
            </table>
        </main>

        <footer class="footer">
            <div class="container">
                <span class="text-muted">Quiz Company</span>
            </div>
        </footer>
        <style>
            .footer {
                position: fixed;
                left: 0;
                bottom: 0;
                width: 100%;
                background-color: black;
                color: white;
                text-align: center;
            }
        </style>  
    </body>
</html>
