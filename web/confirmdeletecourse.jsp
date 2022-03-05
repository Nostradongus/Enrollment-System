<%-- 
    Document   : confirmdeletecourse
    Created on : 02 4, 21, 7:34:45 PM
    Author     : Jadie, Joshue S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DELETE COURSE RECORD</title>
    </head>
    <body>
        <h1>Deleting course record</h1>
        <jsp:useBean id="coursesBean" class="enrollpackage.courses" scope="session" />
        <%
            // clear previous course record accessed
            coursesBean = new courses();
            
            coursesBean.courseid = request.getParameter("courseid");
            
            // check if course record exists with given course ID
            coursesBean.viewRecord();
            
            // if data does not exist 
            if (coursesBean.coursename == null) { %> 
                Data does not exist in the database! Please try again!<br>
        <%  } else { 
                // delete coursedegree and enrollment records associated with course record 
                // being deleted
                Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
                );
                PreparedStatement pstmt1 = conn.prepareStatement("DELETE FROM coursedegree WHERE courseid = ?");
                PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM enrollment WHERE courseid = ?");
                pstmt1.setString(1, coursesBean.courseid);
                pstmt2.setString(1, coursesBean.courseid);

                // deletes nothing if course is not yet assigned a degree nor enrolled by any student
                pstmt1.executeUpdate();
                pstmt2.executeUpdate();

                pstmt1.close();
                pstmt2.close();
                conn.close();

                // check if course record deletion was successful 
                if (coursesBean.delRecord() == 1) { %> 
                    Course record deleted! Successful!<br>
        <%      } else { %> 
                    Error occurred in deleting course record<br>
        <%      }
            } %>
        <br>
        <a href="update.jsp">Go back to records management</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
