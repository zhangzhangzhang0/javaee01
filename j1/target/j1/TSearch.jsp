
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.spring.mvc.model.StudentHomework" %>
<%@ page import="org.example.spring.mvc.model.Student" %>
<%@ page import="org.example.spring.mvc.model.Homework" %>
<%@ page import="org.example.spring.mvc.jdbc.JdbcService" %>

<html>
  <head class="ie9">
      <title>Teacher Service</title>
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
                      <img class="profile-pic animated" src="img/11.png" alt="">
                  </a>

                  <h4 class="m-0">Teacher</h4>
              </div>

              <!-- Calendar -->
              <div class="s-widget m-b-25">
                  <div id="sidebar-calendar"></div>
              </div>

          </div>

          <!--------------------------------- 最左侧老师功能栏 ---------------------------------------->
          <ul class="list-unstyled side-menu">
              <li>
                  <a class="sa-side-home" href="TChoose.jsp">
                      <span class="menu-item">Back to Teacher's Home Page</span>
                  </a>
              </li>
              <li>
                  <a class="sa-side-form" href="TAddStudent.jsp">
                      <span class="menu-item">Add Student</span>
                  </a>
              </li>
              <li>
                  <a class="sa-side-typography" href="TDeleteStudent.jsp">
                      <span class="menu-item">Delete Student</span>
                  </a>
              </li>
              <li>
                  <a class="sa-side-widget" href="TSearch.jsp">
                      <span class="menu-item">Check List</span>
                  </a>
              </li>
              <li class="active">
                  <a class="sa-side-table" href="TAddHomework.jsp">
                      <span class="menu-item">Add Homework</span>
                  </a>
              </li>
              <li class="active">
                  <a class="sa-side-form" href="TCheck.jsp">
                      <span class="menu-item">Check Homework</span>
                  </a>
              </li>
              <li class="active">
                  <a class="sa-side-ui" href="main.jsp">
                      <span class="menu-item">Back to Home Page</span>
                  </a>
              </li>

          </ul>

      </aside>


      <!--------------------------------- 表栏 ---------------------------------------->
      <section id="content" class="container">


          <!-- Table Striped -->
          <div class="block-area" id="Student">
              <h3 class="block-title">Student</h3>
              <div class="table-responsive overflow">
                  <table class="tile table table-bordered table-striped">
                      <thead>
                      <tr>
                          <th>学生学号</th>
                          <th>学生姓名</th>
                          <th>创建时间</th>
                      </tr>
                      </thead>
                      <%
                          List<Student> list2 =JdbcService.selectAll2();
                          if(null == list2 || list2.size() <= 0){
                              out.print("None data.");
                          }else {
                              for (Student s : list2){
                      %>

                      <tr>
                          <td><%=s.getStudentId()%></td>
                          <td><%=s.getName()%></td>
                          <td><%=s.getCreateTime()%></td>
                      </tr>


                      <%
                              }
                          }
                      %>
                  </table>
              </div>
          </div>

          <hr class="whiter m-t-20" />
          <div class="block-area" id="Homework">
              <h3 class="block-title">Homework</h3>
              <div class="table-responsive overflow">
                  <table class="tile table table-bordered table-striped">
                      <thead>
                      <tr>
                          <th>作业号</th>
                          <th>作业题目</th>
                          <th>作业要求</th>
                          <th>创建时间</th>
                          <th>已提交个数</th>
                      </tr>
                      </thead>
                      <%
                          List<Homework> list3 = JdbcService.selectAll3();
                          if(null == list3 || list3.size() <= 0){
                              out.print("None data.");
                          }else {
                              for (Homework h : list3){
                      %>

                      <tr>
                          <td><%=h.getId()%></td>
                          <td><%=h.getHomeworkTitle()%></td>
                          <td><%=h.getHomeworkContent()%></td>
                          <td><%=h.getCreateTime()%></td>
                          <td><%=h.getNum()%></td>
                      </tr>


                      <%
                              }
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
