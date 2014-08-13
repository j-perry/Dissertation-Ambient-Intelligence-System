


package ami.system.operations.resources.database;

import static ami.system.operations.resources.database.IDatabase.*;
import java.sql.*;

/**
 *
 * @author Jonathan Perry
 */
public class InitialMonitoringTable implements IDatabase {
    
    private String query;
    
    private Statement qryStatement;
    private PreparedStatement prepQuery;
    private ResultSet resultSet;
    private Connection conn;
    
    public InitialMonitoringTable() {
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
     * Open a JDBC connection
     */
    @Override
    public void open() {
        try {
            // https://mysql.student.sussex.ac.uk/phpmyadmin/
            conn = DriverManager.getConnection(dbUrl, username, password);
            
            // create the table if necessary
            createTable();
            System.out.println("> Connection to table Initial has been found.");
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
     * Creates a table if it doesn't exist
     */
    private void createTable() {
        String tableName = "initial";
        query = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "( " +
                "  Id             INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "  Hour           INTEGER, " + 
                "  Minute         INTEGER, " + 
                "  Value          INTEGER, " + 
                "  Context        VARCHAR(20), " + 
                "  LinguisticType VARCHAR(30) " + 
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
     * 
     */
    public void persist(int hour, int minute, int value, String context, String linguisticType) {
        query = "INSERT INTO initial (Hour, Minute, Value, Context, LinguisticType)" +
                "VALUES ('" + hour + "' ," +
                        "'" + minute + "' ," +
                        "'" + value + "' ," +
                        "'" + context + "' ," +
                        "'" + linguisticType + "')";
        int status = 0;
                
        // create the database table, if it doen't exist
        
        
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
