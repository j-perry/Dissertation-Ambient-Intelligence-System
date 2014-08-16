


package ami.system.operations.resources.database;

import static ami.system.operations.resources.database.IDatabase.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jonathan Perry
 */
public class ClientInfo implements IDatabase {
    
    /**
     * Properties
     */
    private int hours;
    private int minutes;
    private String macAddr;
    private int noSensors;
    
    /**
     * Database properties
     */
    private String query;    
    private Statement qryStatement;
    private PreparedStatement prepQuery;
    private ResultSet resultSet;
    private Connection conn;
    
    public ClientInfo() {
        // register the driver
        try {
            Class.forName(driver);
        }
        catch(ClassNotFoundException ex) {
            // if not found
            ex.printStackTrace();
        }
    }
    
    /**
     * Opens a new JDBC connection
     */
    @Override
    public void open() {
        try {
            // https://mysql.student.sussex.ac.uk/phpmyadmin/
//            conn = DriverManager.getConnection(dbUrl, username, password);
            conn = DriverManager.getConnection(dbUrl);
            
            // create the table if necessary
            createTable();
            System.out.println("> Connection to table 'SystemInfo' has been found.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Closes the JDBC connection
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
     * Returns the number of sessions from the SystemInfo table
     * @return 
     */
    public int getSessionId() {
       int sessionId = 0;
       query = "SELECT COUNT(*) FROM SystemInfo";
       int status = 0;
       
       try {
           qryStatement = conn.createStatement();
           ResultSet rs = qryStatement.executeQuery(query);
           
           rs.next();
           sessionId = rs.getInt(1);
           rs.close();
           conn.close();
       } catch(SQLException ex) {
           ex.printStackTrace();
       }
       
       System.out.println("getSessionId(): " + sessionId);
       
       // if none exist, start at 1
       if(sessionId == 0) {
           sessionId = 1;
       }
       
       return sessionId;
    }
    
    /**
     * Creates a SystemInfo table if it doesn't yet exist
     */
    private void createTable() {
        String tableName = "SystemInfo";
        
        query = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "( " +
                "  Id             INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "  Hours          INTEGER, " + 
                "  Minutes        INTEGER, " + 
                "  HostName       VARCHAR(30), " + 
                "  NoSensors      INTEGER  " +
                ")";
        
        int status = 0;
        
        try {
            qryStatement = conn.createStatement();
            status = qryStatement.executeUpdate(query);
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Write info about our client device for this session to the SystemInfo table
     * 
     * @param hours
     * @param minutes
     * @param macAddr
     * @param noSensors 
     */
    public void persist(int hours, int minutes, String hostname, int noSensors) {
        query = "INSERT INTO SystemInfo (Hours, Minutes, HostName, NoSensors) " +
                "VALUES " +
                "(" 
                   + hours + ", "
                   + minutes + ", "
                   + "'" + hostname + "', "
                   + noSensors + 
                ")";
        
        int status = 0;
        
        try {
            qryStatement = conn.createStatement();
            status = qryStatement.executeUpdate(query);
            
            System.out.println("> Data has been written");
            System.out.println();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
