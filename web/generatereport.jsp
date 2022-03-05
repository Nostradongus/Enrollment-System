<%-- 
    Document   : generatereport
    Created on : 02 4, 21, 7:41:04 PM
    Author     : Saril, Dean S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GENERATED REPORT</title>
    </head>
    <body>
        <h1>Number of students enrolled per course based on term and school year:</h1>
        <jsp:useBean id="reportBean" class="enrollpackage.report" scope="session" />
        <%
            // check first if valid data was entered
            if (request.getParameter("term") != null && request.getParameter("schoolyear") != null) {
                reportBean.term = Integer.parseInt(request.getParameter("term"));
                reportBean.schoolyear = Integer.parseInt(request.getParameter("schoolyear"));
            }
            else { %>
                Invalid data entered! Please try again!<br>
        <%  }
            
            // check if report generation is successful 
            if (reportBean.generateReport() != 1) { %>
                Data does not exist in the database! Please try again!<br>
        <%  } else { %>
                Term: <%=reportBean.term%><br>
                School Year: <%=reportBean.schoolyear%><br><br>
                COURSE ID - NUMBER OF STUDENTS ENROLLED<br><br>
        <%        
                // iterate through the list, displaying course ids and number of students per course
                for (int i = 0; i < reportBean.CourseList.size(); i++) {
                    String courseid = reportBean.CourseList.get(i);
                    int count = reportBean.StudentCount.get(i); %>
                    <%=courseid%> - <%=count%><br>
        <%      }
            } %>   
        <br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
