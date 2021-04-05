<%-- 
    Document   : paging
    Created on : Feb 3, 2021, 8:33:59 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quiz</title>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="css/quiz.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.4/css/bootstrap.min.css" />
        <script src="https://code.jquery.com/jquery-3.2.1.js" ></script>
        <!-- JS tạo nút bấm di chuyển trang start -->
        <script src="http://1892.yn.lt/blogger/JQuery/Pagging/js/jquery.twbsPagination.js" type="text/javascript"></script>
        <!-- JS tạo nút bấm di chuyển trang end -->
        <script type="text/javascript">
            $(function () {
                var pageSize = 1; // Hiển thị 6 sản phẩm trên 1 trang
                showPage = function (page) {
                    $(".contentPage").hide();
                    $(".contentPage").each(function (n) {
                        if (n >= pageSize * (page - 1) && n < pageSize * page)
                            $(this).show();
                    });
                }
                showPage(1);
                ///** Cần truyền giá trị vào đây **///
                var totalRows = ${sessionScope.TOTAL_PAGE}; // Tổng số sản phẩm hiển thị
                var btnPage = 10; // Số nút bấm hiển thị di chuyển trang
                var iTotalPages = Math.ceil(totalRows / pageSize);

                var obj = $('#pagination').twbsPagination({
                    totalPages: iTotalPages,
                    visiblePages: btnPage,
                    onPageClick: function (event, page) {
                        console.info(page);
                        showPage(page);
                    }
                });
                console.info(obj.data());
            });
        </script>
        <style>
            #pagination {
                display: flex;
                display: -webkit-flex; /* Safari 8 */
                flex-wrap: wrap;
                -webkit-flex-wrap: wrap; /* Safari 8 */
                justify-content: center;
                -webkit-justify-content: center;
            }
        </style>
    </head>

    <body style="width: 800px; margin:auto;">
        <script>
            function cal(timer) {
                var minutes = parseInt(timer / 60, 10);
                var seconds = parseInt(timer % 60, 10);

                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                document.querySelector('#timer').textContent = minutes + ":" + seconds;

            }

            function startTimer(duration) {
                var timer = duration;
                cal(timer);
                var intervalCount = setInterval(function () {
                    cal(timer);

                    if (--timer < 0) {
                        document.getElementById('submit').submit();
                        clearInterval(intervalCount);
                    }

                }, 1000);
            }

            window.onload = function () {
                var endTime = ${sessionScope.QUIZ.endTime.time};
                var curTime = new Date().getTime();
                var diff = Math.round((endTime - curTime) / 1000);
                var fiveMinutes = diff;
                startTimer(fiveMinutes);
            }

        </script>
        <header>
            <div class="text-center">
                <h1>Subject: ${sessionScope.SUB}</h1>
                <h2>Student's Name: ${sessionScope.LOGIN_USER.name}</h2>
            </div>
            <div>
                Time left: <span id="timer"></span>
            </div>
        </header>
        <!-- Hiên thị nút bấm -->
        <ul id="pagination"></ul>

        <!-- Hiển thị sản phẩm -->
        <c:set var="ques" value="${sessionScope.QUIZ.list}"/>
        <form action="FinishQuizController" method="POST" id="submit">
            <div class="btnFinish">
                    <input type="submit" class="btn btn-primary btn-lg" value="Finish">
                </div>
            <c:forEach var="q" items="${ques}" varStatus="counter">
                <div class="contentPage">
                    <div class="list-group">
                        <div class="container mt-sm-5 my-1">
                            <div class="question ml-sm-5 pl-sm-5 pt-2">
                                <div class="py-2 h5"><b>${counter.count}. ${q.questionContent}</b></div>
                                <input type="hidden" value="${q.questionID}" name="txtQuestionID${counter.count}">

                                <div class="ml-md-3 ml-sm-3 pl-md-5 pt-sm-0 pt-3" id="options"> 

                                    <label class="options">A. ${q.answerA} <input type="radio" name="txtAnswer${counter.count}" value="A" > <span class="checkmark"></span> </label> 

                                    <label class="options">B. ${q.answerB} <input type="radio" name="txtAnswer${counter.count}" value="B" > <span class="checkmark"></span> </label> 

                                    <label class="options">C. ${q.answerC} <input type="radio" name="txtAnswer${counter.count}" value="C" > <span class="checkmark"></span> </label> 
                                    <label class="options">D. ${q.answerD} <input type="radio" name="txtAnswer${counter.count}" value="D" > <span class="checkmark"></span> </label> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </form>
    </body>
</html>
