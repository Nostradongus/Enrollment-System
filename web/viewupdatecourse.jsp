<%-- 
    Document   : viewupdatecourse
    Created on : 02 4, 21, 7:47:59 PM
    Author     : Lopez, Angel S11
                 CCINFOM 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFY COURSE RECORD</title>
    </head>
    <body>
        <h1>Course's current record</h1>
        <jsp:useBean id="coursesBean" class="enrollpackage.courses" scope="session"/>
        <%
            // clear previous course record data accessed
            coursesBean = new courses();
            
            // get existing course record
            coursesBean.courseid = request.getParameter("courseid");
            coursesBean.viewRecord();
            
            // check if course record exists
            if (coursesBean.coursename == null) { %> 
                Data does not exist in the database! Please try again!<br>
        <%  } else { %>
                Course ID - <%=coursesBean.courseid%><br>
                Course Name - <%=coursesBean.coursename%><br>
                Department - <%=coursesBean.department%><br>
                
                <h3>Please input new course name and department to modify current record</h3>
                <form name="coursedata" action="confirmupdatecourse.jsp" method="POST">
                    New Course Name - <input type="text" name="coursename" id="coursename"><br>
                    New Department - <input type="text" name="department" id="department"><br><br> 
                    <input type="submit" value="Modify Record" name="Modify Record"/>
                </form>
        <%  } %> 
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
