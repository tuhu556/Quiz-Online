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
                <center>
                    <form action="SearchController">
                        Question Content: <input name="txtSearch" value="${param.txtSearch}" cols="20">
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
                            <br>
                            <br>
                            <input type="submit" class="btn btn-outline-info" name="btnAction" value="Search"/>
                        </form>
                    </center>
                    <br>
                <c:set var="list" value="${requestScope.LIST}"></c:set>
                <div>${requestScope.NOT_DELETE}</div>
                <c:if test="${list!=null}">
                    <c:if test="${not empty list}">
                        <div class="album py-5 bg-light">
                            <div class="container">
                                <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 g-2">
                                    <c:forEach var="ques" items="${list}">
                                        <form action="AdminController" method="POST">
                                            <div class="col">
                                                <div class="card text-black bg-info me-auto" style="width: 40rem;">
                                                    <div class="card-header">
                                                        Subject ID: <select id="categoryCmb" name="txtSubjectID">
                                                            <c:forEach var="entry" items="${sub}">
                                                                <option value="${entry.subjectID}"<c:if test="${entry.subjectID eq ques.subjectID}"> selected</c:if>>${entry.subjectID}</option>   
                                                            </c:forEach>
                                                        </select>
                                                        Correct Answer: <select name="txtCorrectAnswer" required>
                                                            <option value="A" <c:if test="${'A' eq ques.correctAnswer}"> selected</c:if>>A</option>
                                                            <option value="B" <c:if test="${'B' eq ques.correctAnswer}"> selected</c:if>>B</option>
                                                            <option value="C" <c:if test="${'C' eq ques.correctAnswer}"> selected</c:if>>C</option>
                                                            <option value="D" <c:if test="${'D' eq ques.correctAnswer}"> selected</c:if>>D</option>
                                                            </select>
                                                            <div class="form-check form-switch">
                                                                <input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" name="txtStatus" <c:if test="${ques.status}">checked</c:if>>
                                                                <label class="form-check-label" for="flexSwitchCheckDefault">Status</label>
                                                            </div>
                                                            <center>
                                                                <h6>Create Date: ${ques.createDate}</h6>
                                                            <h6>Update Date: ${ques.updateDate}</h6>
                                                        </center>
                                                    </div>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item"><label>Question:<textarea name="txtQuestionContent" maxlength="300" rows="2" cols="80">${ques.questionContent}</textarea></label></li>
                                                        <li class="list-group-item"><label>A: <textarea name="txtA" maxlength="300" rows="2" cols="70">${ques.answerA}</textarea></label></li>
                                                        <li class="list-group-item"><label>B: <textarea name="txtB" maxlength="300" rows="2" cols="70">${ques.answerB}</textarea></label></li>
                                                        <li class="list-group-item"><label>C: <textarea name="txtC" maxlength="300" rows="2" cols="70">${ques.answerC}</textarea></label></li>
                                                        <li class="list-group-item"><label>D: <textarea name="txtD" maxlength="300" rows="2" cols="70">${ques.answerD}</textarea></label></li>
                                                    </ul>
                                                    <div class="card-body">
                                                        <input type="hidden" name="txtQuestionID" value="${ques.questionID}"/>
                                                        <input type="hidden" name="txtSearch" value="${param.search}"/>
                                                        <input class="btn btn-warning" type="submit" name="btnAction" value="Update">
                                                        <input class="btn btn-danger" type="submit" name="btnAction" value="Delete">
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <br>
                        <!---------paging-------->
                        <c:if test="${a.numberPage != 0}">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <c:if test="${requestScope.index > 1}">
                                        <li class="page-item">
                                            <c:url var="previous" value="SearchController">
                                                <c:param name="btnAction" value="Search"></c:param>
                                                <c:param name="txtSearch" value="${requestScope.search}"></c:param>
                                                <c:param name="Subject" value="${requestScope.sub}"></c:param>
                                                <c:param name="index" value="${requestScope.index-1}"></c:param>
                                                <c:param name="status" value="${requestScope.status}"></c:param>
                                            </c:url>
                                            <a class="page-link" href="${previous}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                                <span class="sr-only"></span>
                                            </a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${requestScope.totalPages}" var="i">
                                        <c:url var="page" value="SearchController">
                                            <c:param name="btnAction" value="Search"></c:param>
                                            <c:param name="txtSearch" value="${requestScope.search}"></c:param>
                                            <c:param name="Subject" value="${requestScope.sub}"></c:param>
                                            <c:param name="index" value="${i}"></c:param>
                                            <c:param name="status" value="${requestScope.status}"></c:param>
                                        </c:url>
                                        <li class="page-item"><a class="page-link" href="${page}">${i}</a></li>

                                    </c:forEach>

                                    <c:if test="${requestScope.index < requestScope.totalPages}">
                                        <li class="page-item">
                                            <c:url var="next" value="SearchController">
                                                <c:param name="btnAction" value="Search"></c:param>
                                                <c:param name="txtSearch" value="${requestScope.search}"></c:param>
                                                <c:param name="Subject" value="${requestScope.sub}"></c:param>
                                                <c:param name="index" value="${requestScope.index+1}"></c:param>
                                                <c:param name="status" value="${requestScope.status}"></c:param>
                                            </c:url>
                                            <a class="page-link" href="${next}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                                <span class="sr-only"></span>
                                            </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
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
