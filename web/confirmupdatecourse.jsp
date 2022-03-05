<%-- 
    Document   : confirmupdatecourse
    Created on : 02 4, 21, 7:36:12 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFY STUDENT RECORD</title>
    </head>
    <body>
        <h1>Modifying course record</h1>
        <jsp:useBean id="coursesBean" class="enrollpackage.courses" scope="session" />
        <%
            // get parameters passed 
            coursesBean.coursename = request.getParameter("coursename");
            coursesBean.department = request.getParameter("department");
            
            // check if existing course record modification was successful 
            if (coursesBean.modRecord() == 1) { %> 
                Course record modified! Successful!<br> 
        <%  } else { %>
                Error occurred in modifying course record<br>
        <%  } %>
        <br>
        <a href ="update.jsp">Go back to records management</a><br><br>
        <a href ="index.jsp">Go back to main menu</a> 
    </body>
</html>
