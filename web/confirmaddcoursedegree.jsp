<%-- 
    Document   : confirmaddcoursedegree
    Created on : 02 4, 21, 7:32:32 PM
    Author     : Saril, Dean S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD COURSE DEGREE RECORD</title>
    </head>
    <body>
        <h1>Assigning degree to course</h1>
        <jsp:useBean id="coursesBean" class="enrollpackage.courses" scope="session"/>
        <jsp:useBean id="degreeBean" class="enrollpackage.degree" scope="session"/>
        <jsp:useBean id="cdBean" class="enrollpackage.coursedegree" scope="session"/>
        <%
            // clear previously accessed course and degree records
            coursesBean = new courses();
            degreeBean = new degree();
            
            // get course and degree records 
            coursesBean.courseid = request.getParameter("courseid");
            degreeBean.degreeid = request.getParameter("degree");
            coursesBean.viewRecord();
            degreeBean.viewRecord();
            
            // check if both course and degree records exist 
            if (coursesBean.coursename == null || degreeBean.degreename == null) { %>
                Course or degree data does not exist in the database! Please try again!<br>
        <%  } else { 
                cdBean.courseid = coursesBean.courseid; 
                cdBean.degree = degreeBean.degreeid; 
                
                // check if adding of new coursedegree record was successful 
                if (cdBean.addRecord() == 1) { %> 
                    Degree assigned to course! Successful!<br>
        <%      } else { %> 
                    Error occurred in assigning degree to course<br>
        <%      }
            } %>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>    
    </body>
</html>
