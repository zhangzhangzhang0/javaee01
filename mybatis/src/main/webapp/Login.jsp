<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StudentLogin</title>
</head>
<body>
<style type="text/css">  <!--  .red {   color: #880000;   font-size: 24px;  }  -->  </style>

<div   style="position: relative; width: 100%; display: table; * position: absolute; * top: 10%; * left: 0;">
    <p    style="position: absolute; top: 10%; left: 0; text-align: center; width: 100%; * top: 0;">
        <strong class="red">学生课程平台登陆</strong>   </p>  </div>

<form action="${pageContext.request.contextPath}/login" method="post">
    <table style="margin:60px 0 20px 0;"class="table"align="center" border="1" width="50%" cellpadding="6">

        <tr>
            <td align="center" >学生学号</td>
            <td align="left" ><input type="text" name="sId"></td>
        </tr>

        <tr>
            <th colspan="2" align="center" ><input type="submit" name="submit" value="登录"></th>
        </tr>
    </table>
</form>

<form action="/main.jsp" method=post >
    <table class="table"align="center" border="1" width="50%" cellpadding="6">
        <tr>
            <th colspan="2" align="center" ><input type="submit" value="返回" /></th>
        </tr>
    </table>
</form>
</body>
</html>