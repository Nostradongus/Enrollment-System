/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollpackage;

/**
 *
 * @author Jadie, Joshue S11
 *         CCINFOM
 */
import java.sql.*;

public class enrollment {
    /**
     * studentid field in enrollment table of enrolldb
     */
    public long studentid;
    /**
     * courseid field in enrollment table of enrolldb 
     */
    public String courseid;
    /**
     * term field in enrollment table of enrolldb 
     */
    public int term;
    /**
     * schoolyear field in enrollment table of enrolldb 
     */
    public int schoolyear;
    
    /**
     * Sole constructor of enrollment DAO
     */
    public enrollment () {}
    
    /**
     * Modifies an existing record in the enrollment table of enrolldb
     * 
     * @return 1 if existing record modification was successful, 0 otherwise
     */
    public int modRecord() {
        try {
            // Connect to enrolldb in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Indicator of successful connection
            System.out.println("Connection successful!");
            // Prepare UPDATE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("UPDATE  enrollment       " +
                                                             "SET    term       = ? , " +
                                                             "       schoolyear = ?   " +
                                                             "WHERE  studentid  = ? , " +
                                                             "AND    courseid   = ?   ");
            // Fill missing values with this DAO's stored field values
            pstmt.setInt(1, term);
            pstmt.setInt(2, schoolyear);
            pstmt.setLong(3, studentid);
            pstmt.setString(4, courseid);
            
            // Execute UPDATE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of modifying an existing record
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
     * Deletes an existing record in the enrollment table of enrolldb 
     * 
     * @return 1 if deletion of record was successful, 0 otherwise
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
            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM enrollment WHERE studentid = ? AND courseid = ? AND term = ? AND schoolyear = ?"
            );
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            pstmt.setString(2, courseid);
            pstmt.setInt(3, term);
            pstmt.setInt(4, schoolyear);
            
            // Execute DELETE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of existing record deletion
            return 1;
        }
        // If exception occurred, there is an error in record deletion
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of existing record deletion
            return 0;
        }
    }
    
    /**
     * Adds a new record to the enrollment table of enrolldb
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO enrollment VALUES (?,?,?,?)");
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            pstmt.setString(2, courseid);
            pstmt.setInt(3, term);
            pstmt.setInt(4, schoolyear);
            
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
     * Retrieves an existing record from the enrollment table of enrolldb
     * 
     * @return 1 if data retrieval was successful, 0 otherwise
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM enrollment WHERE studentid = ? AND courseid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setLong(1, studentid);
            pstmt.setString(2, courseid);
            
            // Execute query statement with set field values
            ResultSet rs = pstmt.executeQuery();
            
            // Get values from query 
            while (rs.next()) {
                studentid = rs.getLong("studentid");
                courseid = rs.getString("courseid");
                term = rs.getInt("term");
                schoolyear = rs.getInt("schoolyear");
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
    public static void main(String args[]) {
        
    }
}
