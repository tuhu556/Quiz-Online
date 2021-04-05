<%-- 
    Document   : user
    Created on : Jan 25, 2021, 10:29:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quiz Company</title>
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
            <c:set var="sub" value="${sessionScope.SUBJECT}"/>
            <div class="album py-5 bg-light">
                <div class="container">
                    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        <c:forEach var="entry" items="${sub}">
                            <form action="AttemptQuizController" method="POST">
                                <div class="col">
                                    <div class="card shadow-sm">
                                        <h1>${entry.subjectID}</h1>
                                        <p>${entry.subjectName}</p>
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div class="btn-group">
                                                    <input type="hidden" name="txtSubjectID" value="${entry.subjectID}">
                                                    <input type="submit" value="Attempt Quiz Now">
                                                </div>
                                                <small class="text-muted">${entry.time} mins</small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                </div>
            </div>
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
