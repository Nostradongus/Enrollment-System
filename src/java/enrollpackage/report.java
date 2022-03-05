/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollpackage;

/**
 *
 * @author Saril, Dean S11
 *         CCINFOM
 */
import java.sql.*;
import java.util.*;

public class report {
    /**
     * Courses from the enrollment table of enrolldb
     */
    public ArrayList<String> CourseList = new ArrayList<> ();
    /**
     * Number of students per course from the enrollment table of enrolldb
     */
    public ArrayList<Integer> StudentCount = new ArrayList<> ();
    /**
     * Term specified by the user for report generation
     */
    public int term = 0;
    /**
     * School year specified by the user for report generation
     */
    public int schoolyear = 0;
    
    /**
     * Sole constructor of the report TO
     */
    public report () {
        // Clear lists as well as initialize TO variables
        resetReport();
    }
    
    /**
     * Clears all data lists for report generation
     * 
     * @return 1 to indicate success of data clearance
     */
    public int resetReport() {
        // Clear lists
        CourseList.clear();
        StudentCount.clear();
        // Initialize variables
        term = schoolyear = 0;
        
        return 1;
    }
    
    /**
     * Generates report from list of enrollments based on the enrollment table
     * of enrolldb
     * 
     * @return 1 if accessing of data and report generation was successful, 0 otherwise
     */
    public int generateReport() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            System.out.println("Connection Successful");

            // retrieves number of students enrolled per course based on term and schoolyear entered
            PreparedStatement pstmt = conn.prepareStatement("SELECT courseid, COUNT(studentid) " + 
                                                            "FROM enrollment " +
                                                            "WHERE term = ? " +
                                                            "AND schoolyear = ? " +
                                                            "GROUP BY courseid");
        
            // sets studentid in SQL statement as report's term and schoolyear entered
            pstmt.setInt(1, term); 
            pstmt.setInt(2, schoolyear);
        
            // stores the result of the query
            ResultSet rs = pstmt.executeQuery();

            // clears CourseList and StudentCount array first
            CourseList.clear();
            StudentCount.clear();
        
            // stores all records of enrollment onto EnrollmentList
            while(rs.next()) {
                String course = rs.getString("courseid");
                int count = rs.getInt("COUNT(studentid)");

                // store current courseid and count to the lists
                CourseList.add(course);
                StudentCount.add(count);
            }
            
            // if there is no data stored in CourseList or StudentCount, invalid data was entered
            if (CourseList.size() == 0 || StudentCount.size() == 0)
                return 0;
        
            // closes connections
            rs.close();
            pstmt.close();
            conn.close();

            return 1;
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // For testing and debugging purposes
    public static void main(String[] args) {
//        report RE = new report();
//
//        RE.term = 1;
//        RE.schoolyear = 20192020;
//
//        if(RE.generateReport() == 1)
//        {
//                Iterator<String> courseItr = RE.CourseList.iterator(); 
//                Iterator<Integer> countItr = RE.StudentCount.iterator();
//
//                while(courseItr.hasNext() && countItr.hasNext())
//                {
//                        String curCourse = courseItr.next();
//                        Integer curCount = countItr.next();
//                        System.out.println(curCourse + " " + curCount);
//                }
//        }
//        else
//            System.out.println("Error occurred!");
    }
}
