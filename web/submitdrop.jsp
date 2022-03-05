<%-- 
    Document   : submitdrop
    Created on : 02 4, 21, 7:44:50 PM
    Author     : Jadie, Joshue S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DROP SUBMISSION</title>
    </head>
    <body>
        <h1>Dropping your courses</h1>
        <jsp:useBean id="dropBean" class="enrollpackage.drop" scope="session" />
        <%
            int studentid = Integer.parseInt(session.getAttribute("studentid").toString());
            int term = Integer.parseInt(session.getAttribute("term").toString());
            int schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            
            // check if drop submission and deletion of records in database is successful
            if (dropBean.confirmDrop() == 1) { %>
                Courses dropped! Successful!
        <%  } else { %>
                Error occurred in dropping the courses
        <%  } %>
        <%
            session.setAttribute("studentid", studentid);
            session.setAttribute("term", term);
            session.setAttribute("schoolyear", schoolyear);
        %>
        <br><br>
        <a href="selectdrop.jsp">Go back to course dropping</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
