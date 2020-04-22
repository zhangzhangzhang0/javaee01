
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.spring.mvc.model.Homework" %>
<%@ page import="java.util.List" %>

<%@ page import="org.example.spring.mvc.jdbc.JdbcService" %>

<html>
<head>
    <title>Student Homepage</title>
</head>
<body>

<style type="text/css">  <!--  .red {   color: #880000;   font-size: 24px;  }  -->  </style>
<div   style="position: relative; width: 100%; display: table; * position: absolute; * top: 10%; * left: 0;">
    <p    style="position: absolute; top: 10%; left: 0; text-align: center; width: 100%; * top: 0;">
        <strong class="red">学生提交作业</strong>   </p>  </div>

<table style="margin:50px 0 20px 0;"align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#FFC8B4" height="50">
        <td>学生学号</td>
        <td>作业ID</td>
        <td>作业标题</td>
        <td>作业内容</td>
        <td>创建时间</td>
        <td>提交作业</td>
    </tr>
    <%

        List<Homework> list = JdbcService.selectAll3();
        if(null == list || list.size() <= 0){
            out.print("None data.");
        }else {
            for (Homework h : list){
    %>
    <tr align="center" bgcolor="white" height="30">

        <td><input name="sId" readonly  value="${pageContext.request.getParameter("sId")}"></td>
        <td><%=h.getId()%></td>
        <td><%=h.getHomeworkTitle()%></td>
        <td><%=h.getHomeworkContent()%></td>
        <td><%=h.getCreateTime()%></td>
        <td><a href="${pageContext.request.contextPath}/app/submit2?hwId=<%=h.getId()%>&&sId=${pageContext.request.getParameter("sId")}&&hwTitle=<%=h.getHomeworkTitle()%>&&hwContent=<%=h.getHomeworkContent()%>"><input type="button" value="提交"></a></td>
    </tr>
    <%
            }
        }
    %>



    <form action="/j1_war_exploded/SChoose.jsp" method=post >
        <table class="table"align="center" border="1" width="50%" cellpadding="6">
            <tr>
                <th colspan="2" align="center" ><a href="${pageContext.request.contextPath}/app/login?sId=${pageContext.request.getParameter("sId")}"><input type="button" value="返回"></a></th>
            </tr>
        </table>
    </form>
</table>

</body>
</html>

