<%-- 
    Document   : questionManager
    Created on : Jan 27, 2021, 9:41:25 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    </head>

    <body>
        <header>
            <nav class="navbar navbar-dark bg-dark" aria-label="First navbar example" >
                <div class="container-fluid">
                    <a class="navbar-brand" href="admin.jsp">Admin: ${sessionScope.LOGIN_USER.name}</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarsExample01">
                        <ul class="navbar-nav me-auto mb-2">
                            <li class="nav-item active">
                                <a class="nav-link" aria-current="page" href="SearchController?txtSearch=&btnAction=Search&Subject=">Manager</a>
                                <a class="nav-link" aria-current="page" href="createQues.jsp">Create Question</a>
                                <a class="nav-link" aria-current="page" href="LogoutController">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <br>
        <main>
            <div>
                <c:set var="sub" value="${sessionScope.SUBJECT}"/> 
                <form action="SearchController">
                    Search: <input type="text" name="txtSearch" value="${param.search}" class="search"/>
                    <input type="submit" name="btnAction" value="Search"/>
                    <select id="categoryCmb" name="Subject">
                        <option value="">All</option>
                        <c:forEach var="entry" items="${sub}">
                            <option value="${entry.subjectID}" <c:if test="${entry.subjectID eq requestScope.sub}"> selected</c:if>>${entry.subjectID}-${entry.subjectName}</option>   
                        </c:forEach>
                    </select>
                    <select id="statusCmb" name="status">
                        <option value="True" <c:if test="${'True' == requestScope.status}"> selected</c:if> >True</option>   
                        <option value="False" <c:if test="${'False' == requestScope.status}"> selected</c:if>>False</option>   
                        </select>
                    </form>
                    <br>
                <c:set var="list" value="${requestScope.LIST}"></c:set>
                <div>${requestScope.NOT_DELETE}</div>
                <c:if test="${list!=null}">
                    <c:if test="${not empty list}">
                        <table border="1" class="table table-hover">
                            <thead class="thead-dark">
                                <tr>
                                    <th>No.</th>
                                    <th>Subject ID</th>
                                    <th>Question Content</th>
                                    <th>Answer A</th>
                                    <th>Answer B</th>
                                    <th>Answer C</th>
                                    <th>Answer D</th>
                                    <th>Correct Answer</th>
                                    <th>Create Date</th>
                                    <th>Update Date</th>
                                    <th>Status</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="ques" items="${list}" varStatus="counter">
                                <form action="AdminController" method="POST">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <select id="categoryCmb" name="txtSubjectID">
                                                <c:forEach var="entry" items="${sub}">
                                                    <option value="${entry.subjectID}"<c:if test="${entry.subjectID eq ques.subjectID}"> selected</c:if>>${entry.subjectID}</option>   
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input type="text" name="txtQuestionContent" value="${ques.questionContent}" maxlength="300"></td>
                                        <td><input type="text" name="txtA" value="${ques.answerA}" maxlength="300"></td>
                                        <td><input type="text" name="txtB" value="${ques.answerB}" maxlength="300"></td>
                                        <td><input type="text" name="txtC" value="${ques.answerC}" maxlength="300"></td>
                                        <td><input type="text" name="txtD" value="${ques.answerD}" maxlength="300"></td>
                                        <td>
                                            <select name="txtCorrectAnswer" required>
                                                <option value="A" <c:if test="${'A' eq ques.correctAnswer}"> selected</c:if>>A</option>
                                                <option value="B" <c:if test="${'B' eq ques.correctAnswer}"> selected</c:if>>B</option>
                                                <option value="C" <c:if test="${'C' eq ques.correctAnswer}"> selected</c:if>>C</option>
                                                <option value="D" <c:if test="${'D' eq ques.correctAnswer}"> selected</c:if>>D</option>
                                                </select>
                                            </td>
                                            <td>${ques.createDate}</td>
                                        <td>${ques.updateDate}</td>

                                        <td>
                                            <input type="checkbox" name="txtStatus" <c:if test="${ques.status}">checked</c:if>/>
                                            </td>
                                            <td>
                                                <input type="hidden" name="txtQuestionID" value="${ques.questionID}"/>
                                            <input type="hidden" name="txtSearch" value="${param.search}"/>
                                            <input type="submit" name="btnAction" value="Update">
                                        </td>
                                        <td>
                                            <input type="submit" name="btnAction" value="Delete">
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <!---------paging-------->
                        <jsp:useBean id="a" class="dao.QuestionDAO" scope="request"></jsp:useBean>
                        <c:if test="${a.numberPage != 0}">
                            <div class="paging">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:forEach begin="1" end="${a.numberPage}" var="i">
                                            <c:url var="page" value="SearchController">
                                                <c:param name="btnAction" value="Search"></c:param>
                                                <c:param name="txtSearch" value="${requestScope.search}"></c:param>
                                                <c:param name="Subject" value="${requestScope.sub}"></c:param>
                                                <c:param name="index" value="${i}"></c:param>
                                                <c:param name="status" value="${requestScope.status}"></c:param>
                                            </c:url>
                                            <li class="page-item"><a class="page-link" href="${page}">${i}</a></li>

                                        </c:forEach>

                                    </ul>
                                </nav>
                            </div>
                        </c:if>
                    </c:if>
                </c:if>
            </div>
        </main>
        <br>
        <footer class="footer">
            <div class="container">
                <span class="text-muted">Quiz Company</span>
            </div>
        </footer>
    </body>
</html>
