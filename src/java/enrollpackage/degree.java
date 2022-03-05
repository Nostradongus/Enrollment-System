/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enrollpackage;

/**
 *
 * @author Lopez, Angel S11
 *         CCINFOM
 */
import java.sql.*;

public class degree {
    /**
     * degreeid (PK) field of degree table
     */
    public String degreeid;
    /**
     * degreename field of degree table
     */
    public String degreename;
    
    /**
     * Sole constructor of degree DAO
     */
    public degree () {}
    
    /**
     * Modifies an existing record in the degree table of enrolldb
     * 
     * @return 1 if existing record modification is successful, otherwise 0
     */
    public int modRecord() { 
        try {
            // Connect to the enrolldb database in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection Successful!");
            // Prepare UPDATE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("UPDATE degree         " +
                                                            "SET    degreename = ? " +
                                                            "WHERE  degreeid = ?   ");
            // Fill missing values with this DAO's stored field values
            pstmt.setString (1, degreename);
            pstmt.setString (2, degreeid);
            
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
     * Deletes an existing record from the degree table of enrolldb
     * 
     * @return 1 if existing record deletion is successful, 0 otherwise. 
     */
    public int delRecord() { 
        try {
            // Connect to the enrolldb database in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection successful!");
            // Prepare DELETE statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM degree WHERE degreeid = ?");
            // Supply missing values with this DAO's stored field values
            pstmt.setString(1, degreeid);
            // Execute DELETE statement with set field values
            pstmt.executeUpdate();
            
            // Close statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 as deletion of existing record was successful
            return 1;
        }
        // If exception occurred, there was an error in deleting an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 as error occurred in deleting an existing record
            return 0;
        }
    }
    
    /**
     * Adds a new record to the degree table of enrolldb
     * 
     * @return 1 if adding of new record was successful, 0 otherwise
     */
    public int addRecord() { 
        try {
            // Connect to the enrolldb database in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection Successful!");
            // Prepare INSERT statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO degree VALUES (?,?)");
            // Fill blank values with this DAO's stored field values
            pstmt.setString (1, degreeid);
            pstmt.setString (2, degreename);
            
            // Execute INSERT statement with set field values
            pstmt.executeUpdate();
            // Close system statements after execution
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of adding new record
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
     * Retrieves an existing record from the degree table of enrolldb
     * 
     * @return 1 if existing record retrieval is successful, 0 otherwise
     */
    public int viewRecord() { 
        try {
            // Connect to the enrolldb database in MySQL
            Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/enrolldb?useTimezone=true&serverTimezone=UTC&user=root&password=12345678"
            );
            // Connection successful indicator
            System.out.println("Connection Successful!");
            // Prepare query statement to be filled with this DAO's stored field values
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM degree WHERE degreeid = ?");
            // Fill blank values with this DAO's stored field values
            pstmt.setString (1, degreeid);
            
            // Execute query statement with set field values
            ResultSet rs = pstmt.executeQuery();
            
            // Get the results from query 
            while (rs.next()) {
                degreeid = rs.getString("degreeid");
                degreename = rs.getString("degreename");
            }
            
            // Close system statements after execution
            rs.close();
            pstmt.close();
            conn.close();
            
            // Return 1 to indicate success of accessing an existing record
            return 1;
        } 
        // If exception occurred, there is an error in accessing an existing record
        catch (SQLException e) {
            System.out.println(e.getMessage());
            // Return 0 to indicate error of accessing an existing record
            return 0;
        } 
    }
    
    // For testing and debugging purposes
    public static void main(String args[]) {
//        degree deg = new degree ();
//        
//        deg.degreeid = "TEST";
//        deg.degreename = "Test Name";
//        deg.addRecord();
//        
//        deg.modRecord();
//        
//        deg.viewRecord();
//        System.out.println(deg.degreeid + " " + deg.degreename);
//        
//        deg.delRecord();
    }
}
