<%-- 
    Document   : submitenroll
    Created on : 02 4, 21, 7:45:48 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ENROLLMENT SUBMISSION</title>
    </head>
    <body>
        <h1>Submitting your enrollment</h1>
        <jsp:useBean id="enrollBean" class="enrollpackage.enroll" scope="session" />
        <!-- check if adding of enrollment list data to the database was successful -->
        <% 
            int studentid = Integer.parseInt(session.getAttribute("studentid").toString());
            int term = Integer.parseInt(session.getAttribute("term").toString());
            int schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            
            // check if submission of enrollment and updating of database is successful
            if (enrollBean.confirmEnrollment() == 1) { %>
                Enrollment submission successful! 
        <% } else { %>
                Error occurred in enrollment submission
        <% } %>
        <%
            session.setAttribute("studentid", studentid);
            session.setAttribute("term", term);
            session.setAttribute("schoolyear", schoolyear);
        %>
        <br><br>
        <a href="selectenroll.jsp">Go back to course enrollment</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
