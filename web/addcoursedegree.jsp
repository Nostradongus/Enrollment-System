<%-- 
    Document   : addcoursedegree
    Created on : 02 4, 21, 7:26:30 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD COURSE DEGREE RECORD</title>
    </head>
    <body>
        <h1>Please input course's ID and degree to be assigned to it</h1>
        <form name="degreedata" action="confirmaddcoursedegree.jsp" method="POST">
            Course ID - <input type="text" name="courseid" id="courseid"><br>
            Degree - <input type="text" name="degree" id="degree"><br><br>
            <input type="submit" value="Add Degree" name="Add Degree"/>
        </form>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
