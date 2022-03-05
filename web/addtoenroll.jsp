<%-- 
    Document   : addtoenroll
    Created on : 02 4, 21, 7:28:58 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD COURSE PROCESSING</title>
    </head>
    <body>
        <h1>Adding course to your enrollment list</h1>
        <jsp:useBean id="enrollBean" class="enrollpackage.enroll" scope="session" />
        <%
            String courseid = request.getParameter("courses");
            int studentid = Integer.parseInt(session.getAttribute("studentid").toString());
            int term = Integer.parseInt(session.getAttribute("term").toString());
            int schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            
            enrollment E = new enrollment();
            E.studentid = studentid;
            E.courseid = courseid;
            E.term = term;
            E.schoolyear = schoolyear;
            
            if (enrollBean.EnrollmentList.add(E)) { %>
                Course successfully added to your enrollment list!
        <%  } else { %>
                Error occurred in adding course to your list
        <%  } %>
        <%
            session.setAttribute("studentid", studentid);
            session.setAttribute("term", term);
            session.setAttribute("schoolyear", schoolyear);
        %>
        <br><br>
        <a href="selectenroll.jsp">Go back to course enrollment</a>
    </body>
</html>
