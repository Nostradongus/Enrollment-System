<%-- 
    Document   : deletestudent
    Created on : 02 4, 21, 7:40:22 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE STUDENT RECORD</title>
    </head>
    <body>
        <h1>Please input student's ID number</h1>
        <form name="studentdata" action="confirmdeletestudent.jsp" method="POST">
            ID Number - <input type="text" name="studentid" id="studentid"><br><br>
            <input type="submit" value="Delete Record" name="Delete Record"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
