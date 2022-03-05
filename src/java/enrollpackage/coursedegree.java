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

public class coursedegree {
    /**
     * courseid (FK) field of coursedegree table
     */
    public String courseid;
    /**
     * degree (FK) field of coursedegree table
     */
    public String degree;
    
    /**
     * Sole constructor of coursedegree DAO
     */
    public coursedegree () {}
    
    /**
     * Modifies an existing record in the coursedegree table of enrolldb
     * 
     * @return 1 if existing record modification was successful, 0 otherwise
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE coursedegree " +
                                                            "SET    degree   = ? " +
                                                            "WHERE  courseid = ? ");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, degree);
            pstmt.setString(2, courseid);
            
            // Execute UPDATE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of existing record modification
            return 1;
        }
        // If exception occurred, there is an error in modifying an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of record modification
            return 0;
        }
    }
    
    /**
     * Deletes an existing record in the coursedegree table of enrolldb
     * 
     * @return 1 if deletion of existing record was successful, 0 otherwise
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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM coursedegree WHERE courseid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            
            // Execute DELETE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of existing record deletion
            return 1;
        }
        // If exception occurred, there is an error in deleting an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of record deletion
            return 0;
        }
    }
    
    /**
     * Adds a new record in the coursedegree table of enrolldb
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO coursedegree VALUES (?,?)");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            pstmt.setString(2, degree);
            
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
     * Retrieves an existing record from the coursedegree table of enrolldb
     * 
     * @return 1 if retrieval of existing record was successful, 0 otherwise
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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM coursedegree WHERE courseid = ?");
            // Fill missing values with this DAO's stored field values
            pstmt.setString(1, courseid);
            
            // Execute query statement
            ResultSet rs = pstmt.executeQuery();
            
            // Get values from query
            while (rs.next()) {
                courseid = rs.getString("courseid");
                degree = rs.getString("degree");
            }
            
            // Close statements after execution
            rs.close();
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of data retrieval
            return 1;
        }
        // If exception occurred, there is an error in retrieving data
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of data retrieval
            return 0;
        }
    }
    
    // For testing and debugging purposes
    public static void main(String args[]) {
       
    }
}
