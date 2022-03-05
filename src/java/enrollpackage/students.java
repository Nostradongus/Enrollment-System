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

public class students {
    /**
     * studentid (PK) field of students table
     */
    public long studentid;
    /**
     * completename field of students table
     */
    public String completename;
    /**
     * degreeid (FK) field of students table
     */
    public String degreeid;
    
    /**
     * Sole constructor for students DAO
     */
    public students () {}
    
    /**
     * Modifies an existing record in the students table of enrolldb
     * 
     * @return 1 if the existing record modification was successful, 0 otherwise
     */
    public int modRecord()  {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Indicator of successful connection
            System.out.println("Connection successful!");
            // Prepare UPDATE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("UPDATE students          " +
                                                            "SET    completename = ?, " +
                                                            "       degreeid     = ?  " +
                                                            "WHERE  studentid    = ?  ");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, completename);
            pstmt.setString(2, degreeid);
            pstmt.setLong(3, studentid);
            
            // Execute UPDATE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of modifying an existing record
            return 1;
        }
        // If exception occurred, there is an error in modifying an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error in existing record modification
            return 0;
        }
    }
    
    /**
     * Deletes an existing record in the students table of enrolldb
     * 
     * @return 1 if existing record deletion was successful, 0 otherwise 
     */
    public int delRecord()  {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Indicator of successful connection
            System.out.println("Connection successful!");
            // Prepare DELETE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM students WHERE studentid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            
            // Execute DELETE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of deleting an existing record
            return 1;
        }
        // If exception occurred, there is an error in deleting an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of deleting an existing record
            return 0;
        }
    }
    
    /**
     * Adds a new record to the students table of enrolldb
     * 
     * @return 1 if adding of new record was successful, 0 otherwise
     */
    public int addRecord()  {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Indicator of successful connection
            System.out.println("Connection successful!");
            // Prepare INSERT statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students VALUES (?,?,?)");
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            pstmt.setString(2, completename);
            pstmt.setString(3, degreeid);
            
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
     * Retrieves an existing record from the students table of enrolldb
     * 
     * @return 1 if existing record retrieval was successful, 0 otherwise
     */
    public int viewRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Indicator of successful connection
            System.out.println("Connection successful!");
            // Prepare query statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM students WHERE studentid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            
            // Execute query statement with set field values
            ResultSet rs = pstmt.executeQuery();
            
            // Get values from query
            while(rs.next()) {
                studentid = rs.getLong("studentid");
                completename = rs.getString("completename");
                degreeid = rs.getString("degreeid");
            }
            
            // Close statements after execution
            rs.close();
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of data retrieval
            return 1;
        }
        // If exception occurred, there is an error in data retrieval
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error in data retrieval
            return 0;
        }
    }
    
    // For testing and debugging purposes
    public static void main(String args[]) {}
}
