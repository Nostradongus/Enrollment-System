/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollpackage;

/**
 *
 * @author Ponce, Andre S11
 *         CCINFOM
 */
import java.sql.*;

public class courses {
    /**
     * courseid (PK) field of courses table
     */
    public String courseid;
    /**
     * coursename field of courses table 
     */
    public String coursename;
    /**
     * department field of courses table
     */
    public String department;
    
    /**
     * Sole constructor of courses DAO
     */
    public courses () {}
    
    /**
     * Modifies an existing record in the courses table of enrolldb
     * 
     * @return 1 if existing record modification is successful, 0 otherwise
     */
    public int modRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection successful!");
            // Prepare UPDATE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("UPDATE courses          " +
                                                            "SET    coursename = ?,  " +
                                                            "       department = ?   " +
                                                            "WHERE  courseid   = ?   ");
            // Fill the missing values with this DAO's stored field values
            pstmt.setString(1, coursename);
            pstmt.setString(2, department);
            pstmt.setString(3, courseid);
            
            // Execute UPDATE statement with set field values
            pstmt.executeUpdate();
            
            // Close system statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of existing record modification
            return 1;
        } 
        // If exception occurred, there is an error in record modification
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of record modification
            return 0;
        }
    }
    
    /**
     * Deletes an existing record in the courses table of enrolldb
     * 
     * @return 1 if existing record deletion is successful, 0 otherwise
     */
    public int delRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection successful!"); 
            // Prepare DELETE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM courses WHERE courseid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            
            // Execute DELETE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of deleting an existing record
            return 1;
        }
        // If exception occurred, there is an error in existing record deletion
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of existing record deletion
            return 0;
        }
    }
    
    /**
     * Adds a new record to the courses table of enrolldb
     * 
     * @return 1 if adding of new record is successful, 0 otherwise
     */
    public int addRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection successful!"); 
            // Prepare INSERT statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO courses VALUES (?,?,?)");
            
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            pstmt.setString(2, coursename);
            pstmt.setString(3, department);
            
            // Execute INSERT statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of adding a new record
            return 1;
        }
        // If exception occurred, there is an error in adding a new record 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of adding a new record
            return 0;
        }
    }
    
    /**
     * Retrieves an existing record from the courses table of enrolldb
     * 
     * @return 1 if existing record retrieval is successful, 0 otherwise
     */
    public int viewRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection successful!"); 
            // Prepare query statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM courses WHERE courseid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            
            // Execute query statement 
            ResultSet rs = pstmt.executeQuery();
            
            // Get the results from query 
            while (rs.next()) {
                courseid = rs.getString("courseid");
                coursename = rs.getString("coursename");
                department = rs.getString("department");
            }
            
            // Close system statements after execution
            rs.close();
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of accessing a record
            return 1;
        }
        // If exception occurred, there is an error in accessing a record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of accessing a record
            return 0;
        }
    }
    
    // For testing and debugging purposes 
    public static void main(String args[]) {
        
    }
}
