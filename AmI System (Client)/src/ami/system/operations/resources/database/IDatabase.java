


package ami.system.operations.resources.database;

/**
 *
 * @author Jonathan Perry
 */
public interface IDatabase {
    
    public final static String driver = "com.mysql.jdbc.Driver";
    
    
    public final static String dbUrl = "jdbc:mysql://mysql.student.sussex.ac.uk/jp373?user=jp373&password=ripe-faraway-tomato&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=25";
    
    // DO NOT CHANGE CONSTANT VALUE!!!
//    public final static String dbUrl = "jdbc:mysql://mysql.student.sussex.ac.uk/jp373";
    public final static String username = "jp373";
    public final static String password = "ripe-faraway-tomato";
    
    /**
     * Open a new database connection
     */
    public void open();
    
    /*
     * Close an existing database connection
     */
    public void close();
    
}