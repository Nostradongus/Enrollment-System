<%-- 
    Document   : updatecourse
    Created on : 02 4, 21, 7:46:33 PM
    Author     : Lopez, Angel S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFY COURSE RECORD</title>
    </head>
    <body>
        <h1>Please input course id</h1>
        <form name="coursedata" action="viewupdatecourse.jsp" method="POST">
            Course ID - <input type="text" name="courseid" id="courseid"><br><br>
            <input type="submit" value="Submit" name="Submit"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
