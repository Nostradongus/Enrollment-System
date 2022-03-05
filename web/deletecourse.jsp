<%-- 
    Document   : deletecourse
    Created on : 02 4, 21, 7:39:07 PM
    Author     : Saril, Dean S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE COURSE RECORD</title>
    </head>
    <body>
        <h1>Please input course ID</h1>
        <form name="coursedata" action="confirmdeletecourse.jsp" method="POST">
            Course ID - <input type="text" name="courseid" id="courseid"><br><br>
            <input type="submit" value="Delete Record" name="Delete Record"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
