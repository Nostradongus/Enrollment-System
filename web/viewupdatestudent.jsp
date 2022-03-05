<%-- 
    Document   : viewupdatestudent
    Created on : 02 4, 21, 7:48:46 PM
    Author     : Jadie, Joshue S11
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
        <h1>Student's current record</h1>
        <jsp:useBean id="studentBean" class="enrollpackage.students" scope="session"/>
        <%
            // clear previous student record data accessed 
            studentBean = new students();
            
            // get existing student record
            studentBean.studentid = Long.parseLong(request.getParameter("studentid"));
            studentBean.viewRecord();
            
            // check if student record exists
            if (studentBean.completename == null) { %>
                Data does not exist in the database! Please try again!<br>
        <%  } else { %>
                ID Number - <%=studentBean.studentid%><br>
                Name - <%=studentBean.completename%><br>
                Degree - <%=studentBean.degreeid%><br><br>
                
                <h3>Please input new full name and degree to modify current record</h3>
                <form name="studentdata" action="confirmupdatestudent.jsp" method="POST">
                    New Name [First and Last] - <input type="text" name="completename" id="completename"><br>
                    New Degree - <input type="text" name="degreeid" id="degreeid"><br><br>
                    <input type="submit" value="Modify Record" name="Modify Record"/>
                </form>
        <%  } %>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
