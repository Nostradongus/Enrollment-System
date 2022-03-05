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

public class enroll {
    /**
     * students DAO referring to the current student user enrolling
     */
    public students                 Student         = new students();
    /**
     * List of courses to be enrolled by the current student user
     */
    public ArrayList<enrollment>    EnrollmentList  = new ArrayList<> ();
    /**
     * List of courses available for the current student user to enroll
     */
    public ArrayList<coursedegree>  CourseList      = new ArrayList<> ();
	
    /**
     * Sole constructor of the enroll TO
     */
    public enroll() {
        // clear up data from lists
        clearEnrollment();
    } 
    
    /**
     * Gets student record from students table of enrolldb based on ID number
     * inputted
     * 
     * @return 1 if student record retrieval was successful, 0 otherwise
     */
    public int getStudentRecord(long id) {
        try {
            // clear previous student user data instance
            Student = new students();
            
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            System.out.println("Connection successful!");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM students WHERE studentid = ?");
            pstmt.setLong(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Student.studentid = rs.getLong("studentid");
                Student.completename = rs.getString("completename");
                Student.degreeid = rs.getString("degreeid");
            }
            
            // If given data does not exist in the database
            if (Student.completename == null)
                return 0;
            
            rs.close();
            pstmt.close();
            conn.close();
            
            return 1;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    /**
     * Clears enrollment data of the current student user
     * 
     * @return 1 to indicate success of enrollment data clearance
     */
    public int clearEnrollment() {
	EnrollmentList.clear();
	return 1;   
    }   
	
    /**
     * Loads course data into the course list based on current student user's
     * degree
     * 
     * @return 1 if course data loading was successful, 0 otherwise 
     */
    public int loadCourses(int term, int schoolyear) {
        try {
            // connects the database
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            System.out.println("Connection Successful");

            // retrieves courses that have the same degree as the Student
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT * FROM coursedegree c " + 
                "WHERE c.courseid NOT IN (SELECT courseid FROM enrollment WHERE studentid = ? AND term = ? AND schoolyear = ?) AND c.degree = ?"
            );
            // Fill missing values with student DAO's data
            pstmt.setLong(1, Student.studentid);
            pstmt.setInt(2, term);
            pstmt.setInt(3, schoolyear);
            pstmt.setString(4, Student.degreeid);
            // Execute query to get courses data list
            ResultSet rs = pstmt.executeQuery();

            //clears the course list array first
            CourseList.clear();
            
            // stores all the valid courses
            while(rs.next()) {
                coursedegree cd = new coursedegree();
                cd.courseid = rs.getString("courseid");
                cd.degree = rs.getString("degree");
                
                // TEST PRINT
                System.out.println(cd.courseid + " " + cd.degree);
                
                // check if course hasn't been added yet to the enrollment list 
                // of current student user 
                boolean unique = true; 
                for (int i = 0; i < EnrollmentList.size() && unique; i++)
                    if (EnrollmentList.get(i).courseid.equalsIgnoreCase(cd.courseid))
                        unique = false;
                
                if (unique)
                    CourseList.add(cd);
            }
            
            rs.close();
            pstmt.close();
            conn.close();

            return 1;
        } catch (SQLException e) { 
            System.out.println(e.getMessage());
            return 0; 
        }
    } 
    
    /**
     * Saves enrollment data inputted by the current student user into the
     * enrolldb database
     * 
     * @return 1 if saving of data was successful, 0 otherwise
     */
    public int confirmEnrollment() {
        try {
            enrollment E = new enrollment();
            Iterator<enrollment> itr = EnrollmentList.iterator(); 

            //iterates through the enrollment records entered by the student
            while(itr.hasNext()) {
                //gets the next record
                E = itr.next();
                //adds the record to the enrollment database
                E.addRecord();
            }

            return 1;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;   
        }
    }
    
    // For testing and debugging purposes
    public static void main(String args[]) {
//        enroll ENR = new enroll();
//        ENR.Student.studentid = 10100001;
//        ENR.Student.completename = "Dean Saril";
//        ENR.Student.degreeid = "BSCS" ;
//
//        enrollment enrollCourse = new enrollment();
//
//        enrollCourse.courseid = "CCICOMP";
//        enrollCourse.studentid = 10100001;
//        enrollCourse.term = 1;
//        enrollCourse.schoolyear = 20192020;
//        ENR.EnrollmentList.add(enrollCourse);
//
//        if(ENR.loadCourses() == 1)
//        {
//            Iterator<coursedegree> courseItr = ENR.CourseList.iterator(); 
//
//            while(courseItr.hasNext())
//            {
//                coursedegree cdegree = new coursedegree();
//                cdegree = courseItr.next();
//                System.out.println(cdegree.courseid + " " + cdegree.degree);
//            }
//        }
//
//        ENR.confirmEnrollment();
//        ENR.clearEnrollment();
    }
}
