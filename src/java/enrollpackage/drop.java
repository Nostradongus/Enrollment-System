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

public class drop {
    /**
     * students DAO referring to the current student user dropping courses
     */
    public students                 Student         = new students();
    /**
     * List of courses enrolled by the current student user
     */
    public ArrayList<enrollment>    EnrollmentList  = new ArrayList<> ();
    /**
     * List of courses to be dropped by the current student user
     */
    public ArrayList<enrollment>    DropList        = new ArrayList<> ();
    
    /**
     * Sole constructor of the drop TO
     */
    public drop() {
        // clear up data from the lists
        clearDrop();
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
     * Clears dropping data of the current student user
     * 
     * @return 1 to indicate success of data clearance
     */
    public int clearDrop() {
        DropList.clear();
        return 1;
    }
    
    /**
     * Loads data of courses enrolled by the current student user 
     * 
     * @return 1 if loading of data was successful, 0 otherwise
     */
    public int loadEnrollment(int term, int schoolyear) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            System.out.println("Connection Successful");
            
            // Retrieves enrollment records based on studentid of current student user
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM enrollment WHERE studentid = ? AND term = ? AND schoolyear = ?");
            
            // Fill missing value with student DAO's studentid
            pstmt.setLong(1, Student.studentid);
            pstmt.setInt(2, term);
            pstmt.setInt(3, schoolyear);
            
            // Execute query statement and store results to result set 
            ResultSet rs = pstmt.executeQuery();
            
            // Clear EnrollmentList first 
            EnrollmentList.clear();
            
            // Store all records from query onto EnrollmentList
            while (rs.next()) {
                enrollment E = new enrollment();
                E.studentid = rs.getLong("studentid");
                E.courseid = rs.getString("courseid");
                E.term = rs.getInt("term");
                E.schoolyear = rs.getInt("schoolyear");
                
                // check if enrollment record has not yet been added to drop list 
                boolean unique = true; 
                for (int i = 0; i < DropList.size() && unique; i++)
                    if (DropList.get(i).courseid.equalsIgnoreCase(E.courseid))
                        unique = false;
                
                // if not yet added to drop list 
                if (unique)
                    EnrollmentList.add(E);
            }
            
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
     * Drops (deletes) the courses inputted by current student user in the
     * enrollment data
     * 
     * @return 1 if enrollment data deletion was successful, 0 otherwise
     */
    public int confirmDrop() {
        try {
            enrollment dropCourse;
            // Get iterator to traverse through the courses to be dropped 
            Iterator<enrollment> itr = DropList.iterator();
            
            // Drop each course one-by-one by iterator
            while (itr.hasNext()) {
                // Get current enrollment record to be dropped
                dropCourse = itr.next();
                // Drop (delete) current enrollment record 
                dropCourse.delRecord();
            }
            
            return 1;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;  
        }  
    }   
    
    // For testing and debugging purposes
    public static void main(String[] args) {
        /*
            drop DRO = new drop();
            DRO.Student.studentid = 10100001;
            DRO.Student.completename = "Dean Saril";
            DRO.Student.degreeid = "BSCS" ;

            enrollment dropCourse = new enrollment();

            dropCourse.courseid = "CCICOMP";
            dropCourse.studentid = 10100001;
            dropCourse.term = 1;
            dropCourse.schoolyear  = 20192020;

            DRO.DropList.add(dropCourse);

            if(DRO.loadEnrollment() == 1)
            {
                Iterator<enrollment> courseItr = DRO.EnrollmentList.iterator(); 

                while(courseItr.hasNext())
                {
                    enrollment enrolled = new enrollment();
                    enrolled = courseItr.next();
                    System.out.println(enrolled.studentid + " " +
                                       enrolled.courseid + " " + 
                                       enrolled.term + " " + 
                                       enrolled.schoolyear);
                }
            }


            DRO.confirmDrop();
            DRO.clearDrop();
        */
    }
}
