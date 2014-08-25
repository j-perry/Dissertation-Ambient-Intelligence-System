/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.operations.resources.database;

// libraries
import static ami.system.operations.resources.database.IDatabase.*;

// Java APIs
import java.sql.*;

/**
 *
 * @author Jonathan Perry
 */
public class MonitoringContextTable implements IDatabase {
    
    private String query;
    
    private Statement qryStatement;
    private PreparedStatement prepQuery;
    private ResultSet resultSet;
    private Connection conn;
        
    private final String tableName = "MonitoringContext";
    
    public MonitoringContextTable() {
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
//            conn = DriverManager.getConnection(dbUrl, username, password);
            conn = DriverManager.getConnection(dbUrl);
            
            // create the table if necessary
            createTable();
            System.out.println("> Connection to table '" + tableName + "' has been found.");
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
        query = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "( " +
                "  Id             INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "  SessionId      INTEGER, " + 
                "  Hostname       VARCHAR(30), " +
                "  Hour           INTEGER, " + 
                "  Minute         INTEGER, " + 
                "  Day            VARCHAR(20), " +
                "  Month          VARCHAR(20), " +
                "  Year           INTEGER, " +
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
     * Writes data to our table
     * @param sessionId
     * @param hostname
     * @param hour
     * @param minute
     * @param day
     * @param year
     * @param linguisticType
     * @param month
     * @param value
     * @param context
     */
    public void persist(int sessionId, String hostname, int hour, int minute, String day, String month, int year, int value, String context, String linguisticType) {
        System.out.println("SESSION ID: " + sessionId);
        
        System.out.println(sessionId + ", " + 
                           hour + ", " +
                           minute + ", " +
                           day + ", " +
                           month + ", " + 
                           year + ", " +
                           value + ", " + 
                           context + ", " +
                           linguisticType
                          );
        
        query = "INSERT INTO " + tableName + " (SessionId, Hostname, Hour, Minute, Day, Month, Year, Value, Context, LinguisticType)" +
                "VALUES ('" + sessionId + "', " +
                        "'" + hostname + "', " +
                        "'" + hour + "', " +
                        "'" + minute + "', " +
                        "'" + day + "', " +
                        "'" + month + "', " +
                        "'" + year + "', " +
                        "'" + value + "', " +
                        "'" + context + "', " +
                        "'" + linguisticType + "')";
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
