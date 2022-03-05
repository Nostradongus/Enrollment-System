<%-- 
    Document   : confirmupdatestudent
    Created on : 02 4, 21, 7:37:37 PM
    Author     : Lopez, Angel S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFY STUDENT RECORD</title>
    </head>
    <body>
        <h1>Modifying student record</h1>
        <jsp:useBean id="studentBean" class="enrollpackage.students" scope="session" />
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
                rs.close();
                pstmt.close();
                conn.close();

                studentBean.completename = completename; 
                studentBean.degreeid = degreeid; 
                
                // check if existing student record modification was successful
                if (studentBean.modRecord() == 1) { %>
                    Student record modified! Successful!<br>
        <%      } else { %>
                    Error occurred in modifying student record<br>
        <%      } 
            } %>
        <br>
        <a href ="update.jsp">Go back to records management</a><br><br>
        <a href ="index.jsp">Go back to main menu</a>      
    </body>
</html>
