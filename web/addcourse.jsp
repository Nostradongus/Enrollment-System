<%-- 
    Document   : addcourse
    Created on : 02 4, 21, 7:25:32 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD COURSE RECORD</title>
    </head>
    <body>
        <h1>Please input course id, name, and department</h1>
        <form name="coursedata" action="confirmaddcourse.jsp" method="POST">
            Course ID - <input type="text" name="courseid" id="courseid"><br>
            Course Name - <input type="text" name="coursename" id="coursename"><br>
            Department - <input type="text" name="department" id="department"><br><br>
            <input type="submit" value="Create Record" name="Create Record"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
