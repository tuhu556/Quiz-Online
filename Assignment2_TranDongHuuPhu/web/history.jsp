<%-- 
    Document   : history
    Created on : Feb 5, 2021, 8:53:12 PM
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
            <form action="HistoryController">
                Search: <input type="text" name="txtSearch" value="${param.search}" class="search"/>
                <input type="submit" value="Search"/>
                <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.email}"/>

            </form>
            <c:set var="list" value="${requestScope.HISTORY}"></c:set>
            <c:if test="${list!=null}">
                <c:if test="${not empty list}">
                    <table border="1" class="table">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Create Date</th>
                                <th>Subject ID</th>
                                <th>Total Question</th>
                                <th>Total Correct Answers</th>
                                <th>Mark</th>
                                <th>Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="result" items="${list}" varStatus="counter">
                            <form action="DetailController" method="POST">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${result.createDate}</td>
                                    <td>${result.subjectID}</td>
                                    <td>${result.totalQuestion}</td>
                                    <td>${result.totalCorrect}/${result.totalQuestion}</td>
                                    <td>${result.mark}</td>
                                    <td><input type="submit" value="Detail"></td>
                                    <input type="hidden" name="quizID" value="${result.quizID}"/>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!---------paging-------->
                    <c:if test="${a.numberPage != 0}">
                        <div class="paging">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <c:forEach begin="1" end="${requestScope.TOTAL_PAGE}" var="i">
                                        <c:url var="page" value="HistoryController">
                                            <c:param name="txtSearch" value="${requestScope.search}"></c:param>
                                            <c:param name="index" value="${i}"></c:param>
                                        </c:url>
                                        <li class="page-item"><a class="page-link" href="${page}">${i}</a></li>

                                    </c:forEach>

                                </ul>
                            </nav>
                        </div>
                    </c:if>
                </c:if>
            </c:if>
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
