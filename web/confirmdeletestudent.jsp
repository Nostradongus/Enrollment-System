<%-- 
    Document   : confirmdeletestudent
    Created on : 02 4, 21, 7:35:27 PM
    Author     : Jadie, Joshue S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE STUDENT RECORD</title>
    </head>
    <body>
        <h1>Deleting student record</h1>
        <jsp:useBean id="studentBean" class="enrollpackage.students" scope="session" />
        <%
            // clear previous student record accessed 
            studentBean = new students();
            
            studentBean.studentid = Long.parseLong(request.getParameter("studentid"));
            
            // check if student record exists with given ID number
            studentBean.viewRecord();
            
            // if data does not exist 
            if (studentBean.completename == null) { %>
                Data does not exist in the database! Please try again!<br>
        <%  } else { 
                // delete enrollment records associated with the student record being delete
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
                );
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM enrollment WHERE studentid = ?");
                pstmt.setLong(1, studentBean.studentid);
                
                // deletes nothing if student did not enroll yet any course
                pstmt.executeUpdate();

                pstmt.close();
                conn.close();

                // check if student record deletion was successful 
                if (studentBean.delRecord() == 1) { %>
                    Student record deleted. Successful!<br>
        <%      } else { %>
                    Error occurred in deleting student record<br>
        <%      } 
            } %>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
