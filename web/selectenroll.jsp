<%-- 
    Document   : selectenroll
    Created on : 02 4, 21, 7:42:49 PM
    Author     : Ponce, Andre S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COURSE ENROLLMENT</title>
    </head>
    <body>
        <h1>Enrolling of courses</h1>
        <jsp:useBean id="enrollBean" class="enrollpackage.enroll" scope="session" />
        <%  
            int studentid;
            int term;
            int schoolyear;
            
            // first instance of course enrolling
            if (request.getParameter("studentid") != null) {
                // since first instance, clear enrollment list
                enrollBean.clearEnrollment();
                studentid = Integer.parseInt(request.getParameter("studentid"));
                term = Integer.parseInt(request.getParameter("term"));
                schoolyear = Integer.parseInt(request.getParameter("schoolyear"));
            }
            // succeeding instances of course enrolling
            else {
                studentid = Integer.parseInt(session.getAttribute("studentid").toString());
                term = Integer.parseInt(session.getAttribute("term").toString());
                schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            } 
            
            // Check if student record exists in the database
            if (enrollBean.getStudentRecord(studentid) != 1) { %>  
                Data does not exist in the database! Make sure to register first!<br>
         <% } else { %>
                ID Number: <%=studentid%><br>
                Student Name: <%=enrollBean.Student.completename%><br>
                Term: <%=term%><br>
                School Year: <%=schoolyear%><br><br>
                <%
                // Load available courses that can be enrolled by current student user
                enrollBean.loadCourses(term, schoolyear);
                %>
                <% if (enrollBean.EnrollmentList.size() > 0) { %>
                    Courses added to your enrollment list so far:<br>
                    <!-- display list of courses added to the enrollment list -->
                    <%
                    for (int i = 0; i < enrollBean.EnrollmentList.size(); i++) {
                        enrollment enr = enrollBean.EnrollmentList.get(i); %>
                        <%=enr.courseid%><br>
                <%  }
                } else { %>
                    Courses added to your enrollment list so far:<br>
                <% } %>
                <br>
                <!-- if there are available courses left to enroll with -->
                <% if (enrollBean.CourseList.size() > 0) { %>
                    <form name="selectcourses" action="addtoenroll.jsp" method = "POST">
                        Select course to enroll -
                        <select name="courses" id="courses">
                            <% for (int i = 0; i < enrollBean.CourseList.size(); i++) {
                                coursedegree CD = enrollBean.CourseList.get(i); %>
                                <option value="<%=CD.courseid%>"><%=CD.courseid%></option>
                            <% } %>
                        </select>
                        <% 
                            session.setAttribute("studentid", studentid); 
                            session.setAttribute("term", term);
                            session.setAttribute("schoolyear", schoolyear);
                        %>
                        <br><br>
                        <input type="submit" value="Add to Enrollment List" name="Add to Enrollment List"/>
                    </form>
                <!-- else, inform current student user that he/she has added all courses to his/her list -->
                <% } else { %>
                        You have added all available courses to your enrollment list<br><br>
                <% } %>
                <form name="clearenroll" action="clearenroll.jsp" method = "POST">
                    <input type="submit" value="Clear Enrollment List" name="Clear Enrollment List">
                    <% 
                        session.setAttribute("studentid", studentid); 
                        session.setAttribute("term", term);
                        session.setAttribute("schoolyear", schoolyear);
                    %>
                </form>
                <form name="submitenroll" action="submitenroll.jsp" method = "POST">
                    <input type="submit" value="Confirm Enrollment" name="Confirm Enrollment"/>
                    <% 
                        session.setAttribute("studentid", studentid); 
                        session.setAttribute("term", term);
                        session.setAttribute("schoolyear", schoolyear);
                    %>
                </form>
        <% } %>
        <br>
        <a href="enroll.jsp">Go back to student input page</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
