/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.database;

import java.sql.*;

/**
 *
 * @author Jonathan Perry
 */
public class Temperature implements IDatabase {

    private String query;
    
    private Statement qryStatement;
    private PreparedStatement prepQuery;
    private ResultSet resultSet;
    private Connection conn;
    
    public Temperature() {
        // register the driver
        try {
            Class.forName(driver);
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Driver cannot be found");
        }
        
        System.out.println("> Driver has been found");
    }

    /**
     * Open a JDBC connection
     */
    @Override
    public void open() {
        try {
            // https://mysql.student.sussex.ac.uk/phpmyadmin/
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("> Connection has been found");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Close the JDBC connection
     */
    @Override
    public void close() {
        try {
            conn.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Insert some data (for testing purposes)
     * @param value
     * @throws SQLException 
     */
    public void insert(int value) throws SQLException {
        query = "INSERT INTO temperature (Value)" +
                "VALUES ('" + value + "')";
        
        int status = 0;
        
        try {
            qryStatement = conn.createStatement();
            status = qryStatement.executeUpdate(query);
            
            System.out.println("> Data has been written");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Status: " + String.valueOf(value));
    }
    
}