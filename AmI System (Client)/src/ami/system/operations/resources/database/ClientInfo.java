


package ami.system.operations.resources.database;

/**
 *
 * @author Jonathan Perry
 */
public class ClientInfo implements IDatabase {
    
    private int hour;
    private int minute;
    private String macAddr;
    private int noSensors;
    
    public ClientInfo(int hour, int minute, String macAddr, int noSensors) {
        this.hour = hour;
        this.minute = minute;
        this.macAddr = macAddr;
        this.noSensors = noSensors;
    }
    
    public ClientInfo() {
        
    }
    
    /**
     * Opens a new JDBC connection
     */
    @Override
    public void open() {
        
    }

    /**
     * Closes the JDBC connection
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Write info about our client device for this session to the SystemInfo table
     * 
     * @param hour
     * @param minute
     * @param macAddr
     * @param noSensors 
     */
    public void persist(int hour, int minute, String macAddr, int noSensors) {
        
    }
    
}
