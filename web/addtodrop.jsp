<%-- 
    Document   : addtodrop
    Created on : 02 4, 21, 7:28:07 PM
    Author     : Jadie, Joshue S11
                 CCINFOM   
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COURSE DROP PROCESSING</title>
    </head>
    <body>
        <h1>Adding course to drop list</h1>
        <jsp:useBean id="dropBean" class="enrollpackage.drop" scope="session" />
        <%
            String courseid = request.getParameter("enrolled");
            int studentid = Integer.parseInt(session.getAttribute("studentid").toString());
            int term = Integer.parseInt(session.getAttribute("term").toString());
            int schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            
            enrollment E = new enrollment();
            E.studentid = studentid;
            E.courseid = courseid;
            E.term = term;
            E.schoolyear = schoolyear;
            
            if (dropBean.DropList.add(E)) { %>
                Enrolled course successfully added to your drop list! 
        <%  } else { %> 
                Error occurred in adding enrolled course to drop list
        <%  } %>
        <% 
            session.setAttribute("studentid", studentid);
            session.setAttribute("term", term);
            session.setAttribute("schoolyear", schoolyear);
        %>
        <br><br>
        <a href="selectdrop.jsp">Go back to course dropping</a>
    </body>
</html>
