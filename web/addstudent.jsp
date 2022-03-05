<%-- 
    Document   : addstudent
    Created on : 02 4, 21, 7:27:16 PM
    Author     : Lopez, Angel S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD STUDENT RECORD</title>
    </head>
    <body>
        <h1>Please input student's full name and degree</h1>
        <form name="studentdata" action="confirmaddstudent.jsp" method="POST">
            Full Name [First and Last] - <input type="text" name="completename" id="completename"><br>
            Degree - <input type="text" name="degreeid" id="degreeid"><br><br>
            <input type="submit" value="Create Record" name="Create Record"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
