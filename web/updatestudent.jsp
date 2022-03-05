<%-- 
    Document   : updatestudent
    Created on : 02 4, 21, 7:47:12 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFY STUDENT RECORD</title>
    </head>
    <body>
        <h1>Please input student's ID number</h1>
        <form name="studentdata" action="viewupdatestudent.jsp" method="POST">
            ID Number - <input type="text" name="studentid" id="studentid"><br>
            <input type="submit" value="Submit" name="Submit"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
