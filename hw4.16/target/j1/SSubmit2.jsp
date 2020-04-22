<%@ page import="org.example.spring.mvc.model.Homework" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submit</title>
</head>
<body>

<style type="text/css">  <!--  .red {   color: #880000;   font-size: 24px;  }  -->  </style>
<div   style="position: relative; width: 100%; display: table; * position: absolute; * top: 10%; * left: 0;">
    <p    style="position: absolute; top: 10%; left: 0; text-align: center; width: 100%; * top: 0;">
        <strong class="red">学生提交作业</strong>   </p>  </div>

<form action="${pageContext.request.contextPath}/app/SubmitHomeworkServlet"  >
    <table style="margin:60px 0 20px 0;"class="table"align="center" border="1" width="50%" cellpadding="6">
    <c:forEach items="${hwList}" var="hw">

          <tr>
              <td align="center" width="50%">学生学号</td>
              <<td align="left" ><input name="sId" readonly value="${pageContext.request.getParameter("sId")}"></td>
          </tr>
          <tr>
              <td align="center" width="50%">第几次作业</td>
              <td align="left" ><input name="hwId" readonly value="${pageContext.request.getParameter("hwId")}"></td>
          </tr>
          <tr>
              <td align="center"width="50%" >作业标题</td>
              <td align="left" ><input name="hwTitle" readonly  value="${pageContext.request.getParameter("hwTitle")}"></td>
          </tr>
          <tr>
              <td align="center" >作业要求</td>
              <td align="left" ><input name="hwContent" readonly value="${pageContext.request.getParameter("hwContent")}"></td>
          </tr>
          <tr>
              <td align="center" width="50%">作业内容</td>
              <td align="left" ><textarea rows="20" cols="80" name="finish"></textarea></td>
          </tr>
          <tr>
              <th colspan="2" align="center" ><input type="submit" name="submit" value="提交"></th>
          </tr>
  </c:forEach>
      </table>
  </form>

  </table>

  <form action="/j1_war_exploded/SSubmit.jsp" method=post >
      <table class="table"align="center" border="1" width="50%" cellpadding="6">
          <tr>
              <th colspan="2" align="center" ><a href="${pageContext.request.contextPath}/app/submit1?sId=${pageContext.request.getParameter("sId")}"><input type="button" value="返回"></a></th>
          </tr>
      </table>
  </form>

  </body>
  </html>

  </body>
  </html>
