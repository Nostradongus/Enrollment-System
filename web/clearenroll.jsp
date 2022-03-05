<%-- 
    Document   : clearenroll
    Created on : 02 4, 21, 7:30:46 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CLEAR ENROLLMENT LIST</title>
    </head>
    <body>
        <h1>Clearing your enrollment list</h1>
        <jsp:useBean id="enrollBean" class="enrollpackage.enroll" scope="session" />
        <% 
            long studentid = Long.parseLong(session.getAttribute("studentid").toString());
            int term = Integer.parseInt(session.getAttribute("term").toString());
            int schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            
            // clear enrollment list of current student user
            enrollBean.clearEnrollment(); 
        %>
        Your enrollment list has been cleared!
        <%
            session.setAttribute("studentid", studentid);
            session.setAttribute("term", term);
            session.setAttribute("schoolyear", schoolyear);
        %>
        <br><br>
        <a href="selectenroll.jsp">Go back to course enrollment</a>
    </body>
</html>