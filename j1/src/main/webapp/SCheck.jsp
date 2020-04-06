
<%@ page import="java.util.List" %>
<%@ page import="org.example.spring.mvc.model.Homework" %>
<%@ page import="org.example.spring.mvc.jdbc.HomeworkJdbc" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2020/3/9
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<style type="text/css">  <!--  .red {   color: #880000;   font-size: 24px;  }  -->  </style>
<div   style="position: relative; width: 100%; display: table; * position: absolute; * top: 10%; * left: 0;">
    <p    style="position: absolute; top: 10%; left: 0; text-align: center; width: 100%; * top: 0;">
        <strong class="red">提交作业</strong>   </p>  </div>

<table style="margin:50px 0 20px 0;"align="center" width="960" border="1"
       bgcolor="black" cellpadding="1" cellspacing="1">
    <tr align="center" bgcolor="#FFC8B4" height="50">
        <td>作业号</td>
        <td>作业标题</td>
        <td>作业要求</td>
        <td>创建时间</td>
        <td>提交</td>
    </tr>
    <%
        List<Homework> list2 = HomeworkJdbc.selectAll();
        if(null == list2 || list2.size() <= 0){
            out.print("None data.");
        }else {
            for (Homework h : list2){
    %>
    <tr align="center" bgcolor="white" height="30">
        <td><%=h.getId()%></td>

        <td><%=h.getHomeworkTitle()%></td>
        <td><%=h.getHomeworkContent()%></td>
        <td><%=h.getCreateTime()%></td>

        <td><a href="SSubmit.jsp?id=<%=h.getId()%>"><input type="button" value="提交"></a></td>
    </tr>
    <%
            }
        }
    %>

</table>

<form action="main.jsp" method=post >
    <table class="table"align="center" border="1" width="50%" cellpadding="6">
        <tr>
            <th colspan="2" align="center" ><input type="submit" value="返回" /></th>
        </tr>
    </table>
</form>
</body>
</html>
