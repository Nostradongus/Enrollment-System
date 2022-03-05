<%-- 
    Document   : selectdrop
    Created on : 02 4, 21, 7:41:49 PM
    Author     : Jadie, Joshue S11
                 CCINFOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, enrollpackage.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COURSE DROP</title>
    </head>
    <body>
        <h1>Dropping of courses</h1>
        <jsp:useBean id="dropBean" class="enrollpackage.drop" scope="session" />
        <%
            int studentid;
            int term;
            int schoolyear; 
            
            // first instance of course dropping
            if (request.getParameter("studentid") != null) {
                // since first instance, clear drop list 
                dropBean.clearDrop();
                studentid = Integer.parseInt(request.getParameter("studentid"));
                term = Integer.parseInt(request.getParameter("term"));
                schoolyear = Integer.parseInt(request.getParameter("schoolyear"));
            }
            // succeeding instances of course dropping
            else {
                studentid = Integer.parseInt(session.getAttribute("studentid").toString());
                term = Integer.parseInt(session.getAttribute("term").toString());
                schoolyear = Integer.parseInt(session.getAttribute("schoolyear").toString());
            }
            
            // Check if student record exists in the database
            if (dropBean.getStudentRecord(studentid) != 1) { %>
                Data does not exist in the database! Make sure to register first!<br>
        <% } else { %>
                ID Number: <%=studentid%><br>
                Name: <%=dropBean.Student.completename%><br>
                Degree: <%=dropBean.Student.degreeid%><br>
                Term: <%=term%><br>
                School Year: <%=schoolyear%><br><br>
                <%
                // Load enrollment list (courses enrolled) of current student user 
                dropBean.loadEnrollment(term, schoolyear);
                %>
                <% if (dropBean.DropList.size() > 0) { %>
                    Enrolled courses added to your drop list so far:<br>
                    <!-- display list of enrolled courses added to the drop list -->
                    <%
                    for (int i = 0; i < dropBean.DropList.size(); i++) {
                        enrollment enr = dropBean.DropList.get(i); %>
                        <%=enr.courseid%><br>
                <%  }
                } else { %>
                
                <% } %>
                <br>
                <!-- if there are enrolled courses to drop with -->
                <% if (dropBean.EnrollmentList.size() > 0) { %>
                    <form name="dropcourses" action="addtodrop.jsp" method= "POST">
                        Select course to drop -
                        <select name="enrolled" id="enrolled">
                            <% for (int i = 0; i < dropBean.EnrollmentList.size(); i++) {
                                enrollment enr = dropBean.EnrollmentList.get(i); %>
                                <option value="<%=enr.courseid%>"><%=enr.courseid%></option>
                            <% } %>
                        </select>
                        <%
                            session.setAttribute("studentid", studentid);
                            session.setAttribute("term", term);
                            session.setAttribute("schoolyear", schoolyear);
                        %>
                        <br><br>
                        <input type="submit" value="Add to Drop List" name="Add to Drop List"/>
                    </form>
                <!-- else, inform current student user that he/she has no courses to drop -->
                <% } else { %>
                        You have no enrolled courses to drop with<br><br>
                <% } %>
                <form name="cleardrop" action="cleardrop.jsp" method = "POST">
                    <input type="submit" value="Clear Drop List" name="Clear Drop List">
                    <%
                        session.setAttribute("studentid", studentid);
                        session.setAttribute("term", term);
                        session.setAttribute("schoolyear", schoolyear);
                    %>
                </form>
                <form name="submitdrop" action="submitdrop.jsp" method = "POST">
                    <input type="submit" value="Confirm Drop" name="Confirm Drop"/>
                    <%
                        session.setAttribute("studentid", studentid);
                        session.setAttribute("term", term);
                        session.setAttribute("schoolyear", schoolyear);
                    %>
                </form>
        <% } %>
        <br>
        <a href="drop.jsp">Go back to student input page</a><br><br>
        <a href="index.jsp">Go back to main menu</a>
    </body>
</html>
