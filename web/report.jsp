<%-- 
    Document   : report
    Created on : 02 4, 21, 7:24:12 PM
    Author     : Jadie, Joshue S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REPORT GENERATION</title>
    </head>
    <body>
        <h1>Enter term and school year</h1>
        <form name="reportdata" action="generatereport.jsp" method="POST">
            Term - <input type="text" name="term" id="term"><br>
            School Year - <input type="text" name="schoolyear" id="schoolyear"><br><br>
            <input type="submit" value="Submit Data" name="Submit Data"/>
        </form>
        <br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
