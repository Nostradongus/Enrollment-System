<%-- 
    Document   : confirmaddstudent
    Created on : 02 4, 21, 7:33:25 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD STUDENT RECORD</title>
    </head>
    <body>
        <h1>Adding new student record</h1>
        <jsp:useBean id="studentBean" class="enrollpackage.students" scope="session"/>
        <%
            // get parameters passed
            String completename = request.getParameter("completename");
            String degreeid = request.getParameter("degreeid");
            
            // check if degree inputted exists in the database
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"     
            );
            PreparedStatement pstmt = conn.prepareStatement("SELECT degreeid FROM degree WHERE degreeid = ?");
            pstmt.setString(1, degreeid);
            
            ResultSet rs = pstmt.executeQuery();
            
            // if degree inputted does not exist in the database 
            if (!rs.next()) { %>
                Degree does not exist in the database! Please try again!<br>
        <%  } else { 
                studentBean.completename = completename; 
                studentBean.degreeid = degreeid; 

                pstmt = conn.prepareStatement("SELECT MAX(studentid)+1 as NEWSTUDENTNUM FROM students");
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    studentBean.studentid = rs.getInt("NEWSTUDENTNUM");
                }

                rs.close();
                pstmt.close();
                conn.close();
                
                // check if adding of new student record was successful 
                if (studentBean.addRecord() == 1) { %>
                    New student record added! Successful!<br>
        <%      } else { %>
                    Error occurred in adding new student record<br>
        <%      }
            } %>
        <br>
        <a href ="update.jsp">Go back to records management</a><br><br>
        <a href ="index.jsp">Go back to main menu</a>
    </body>
</html>
