<%-- 
    Document   : confirmaddcourse
    Created on : 02 4, 21, 7:31:41 PM
    Author     : Lopez, Angel S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD COURSE RECORD</title>
    </head>
    <body>
        <h1>Adding new course record</h1>
        <jsp:useBean id="coursesBean" class="enrollpackage.courses" scope="session"/>
        <%
            // get parameters passed
            coursesBean.courseid = request.getParameter("courseid"); 
            coursesBean.coursename = request.getParameter("coursename");
            coursesBean.department = request.getParameter("department");
                
            // check if adding of new course record was successful
            if (coursesBean.addRecord() == 1) { %>
                New course record added! Successful!<br>
        <%  } else { %>
                Error occurred in adding new course record<br>
        <%  } %>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
