


package ami.system.operations.resources.database;

import static ami.system.operations.resources.database.IDatabase.dbUrl;
import static ami.system.operations.resources.database.IDatabase.driver;
import static ami.system.operations.resources.database.IDatabase.password;
import static ami.system.operations.resources.database.IDatabase.username;

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
    
    
    public ClientInfo(int hours, int minutes, String macAddr, int noSensors) {
        // register the driver
        try {
            Class.forName(driver);
        }
        catch(ClassNotFoundException ex) {
            // if not found
            ex.printStackTrace();
        }
        
        this.hours = hours;
        this.minutes = minutes;
        this.macAddr = macAddr;
        this.noSensors = noSensors;
    }
    
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
            conn = DriverManager.getConnection(dbUrl, username, password);
            
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
     * Creates a SystemInfo table if it doesn't yet exist
     */
    private void createTable() {
        String tableName = "SystemInfo";
        
        query = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "( " +
                "  Id             INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "  Hours          INTEGER, " + 
                "  Minutes        INTEGER, " + 
                "  MacAddr        VARCHAR(30), " + 
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
    public void persist(int hours, int minutes, String macAddr, int noSensors) {
        query = "INSERT INTO SystemInfo (Hours, Minutes, MacAddr, NoSensors) " +
                "VALUES " +
                "(" + hours + ", " +
                + minutes + ", "
                + "'" + macAddr + "', " +
                + noSensors + ")";
        
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
