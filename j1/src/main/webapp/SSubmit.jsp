
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.spring.mvc.model.Homework" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.spring.mvc.jdbc.JdbcService" %>
<%@ page import="org.example.spring.mvc.model.Student" %>
<%@ page import="org.example.spring.mvc.model.StudentHomework" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"
            +request.getServerName()+":"
            +request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<title>Student</title>

<!DOCTYPE html>
<html>
<head>
    <title>Student</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">
    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">
    <!-- CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/form.css" rel="stylesheet">
    <link href="css/calendar.css" rel="stylesheet">
    <link href="css/media-player.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/icons.css" rel="stylesheet">
    <link href="css/generics.css" rel="stylesheet">
</head>
<body id="skin-blur-violate">

<header id="header" class="media">
    <a href="" id="menu-toggle"></a>
    <a class="logo pull-left" >Homework Platform 1.0</a>
    <!--------------------------------- 顶部时间栏 ---------------------------------------->
    <div class="media-body">
        <div class="media" id="top-menu">

            <div id="time" class="pull-right">
                <span id="hours"></span>
                :
                <span id="min"></span>
                :
                <span id="sec"></span>
            </div>

        </div>
    </div>
</header>

<div class="clearfix"></div>

<section id="main" class="p-relative" role="main">
    <!--------------------------------- 左侧栏 ---------------------------------------->
    <aside id="sidebar">

        <!-- Sidbar Widgets -->
        <div class="side-widgets overflow">
            <!-- Profile Menu -->
            <div class="text-center s-widget m-b-25 dropdown" id="profile-menu">
                <a href="" data-toggle="dropdown">
                    <img class="profile-pic animated" src="img/22.png" alt="">
                </a>

                <h4 class="m-0">Student ID</h4>
                <input type="text" class="form-control input-sm" name="sId" readonly  value="${pageContext.request.getParameter("sId")}"></td>

            </div>

            <!-- Calendar -->
            <div class="s-widget m-b-25">
                <div id="sidebar-calendar"></div>
            </div>

        </div>

        <!--------------------------------- 最左侧学生功能栏 ---------------------------------------->
        <ul class="list-unstyled side-menu">
            <li>
                <a class="sa-side-home" href="main.jsp">
                    <span class="menu-item">Back to Home Page</span>
                </a>
            </li>


        </ul>

    </aside>


    <section id="content" class="container">

        <!-- Table Striped -->
        <div class="block-area" id="Homework">
            <h3 class="block-title">Homework</h3>
            <div class="table-responsive overflow">
                <table class="table tile table-condensed">
                    <thead>
                        <tr>
                            <th>作业ID</th>
                            <th>作业标题</th>
                            <th>作业内容</th>
                            <th>创建时间</th>
                            <th>提交作业</th>
                        </tr>
                    </thead>
                    <%

                        String s=request.getParameter("sId");
                        long l = Long.parseLong(s);
                        List<Homework> list = JdbcService.selectAll3();
                       // List<StudentHomework> list2= JdbcService.find1(l);
                        if(null == list || list.size() <= 0){
                            out.print("None data.");
                        }else {
                            for (Homework h : list){

                                List<StudentHomework> list2= JdbcService.find1(l,h.getId());



                                if(null == list2 || list2.size() <= 0){
                    %>

                        <tr>

                            <td><%=h.getId()%></td>
                            <td><%=h.getHomeworkTitle()%></td>
                            <td><%=h.getHomeworkContent()%></td>
                            <td><%=h.getCreateTime()%></td>
                            <td><a href="${pageContext.request.contextPath}/app/submit2?hwId=<%=h.getId()%>&&sId=${pageContext.request.getParameter("sId")}&&hwTitle=<%=h.getHomeworkTitle()%>&&hwContent=<%=h.getHomeworkContent()%>"><input type="button" value="提交" class="btn btn-sm m-t-10"></a></td>



                        </tr>


                    <%
                                }else{
                                    for(StudentHomework sh : list2){
                    %>

                    <tr>


                        <td><%=h.getId()%></td>
                        <td><%=h.getHomeworkTitle()%></td>
                        <td><%=h.getHomeworkContent()%></td>
                        <td><%=h.getCreateTime()%></td>
                        <td><%=sh.getScore()%></td>
                    </tr>


                    <%
                                }
                            }}
                        }
                    %>
                </table>
            </div>
        </div>


    </section>
    <br/><br/>
</section>


<!-- Javascript Libraries -->
<!-- jQuery -->
<script src="js/jquery.min.js"></script> <!-- jQuery Library -->
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- UX -->
<script src="js/scroll.min.js"></script> <!-- Custom Scrollbar -->
<!-- Other -->
<script src="js/calendar.min.js"></script> <!-- Calendar -->
<script src="js/feeds.min.js"></script> <!-- News Feeds -->
<!-- All JS functions -->
<script src="js/functions.js"></script>
</body>
</html>

