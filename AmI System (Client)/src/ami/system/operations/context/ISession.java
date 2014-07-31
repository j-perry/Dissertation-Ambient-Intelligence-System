


package ami.system.operations.context;

/**
 *
 * @author Jonathan Perry
 */
public interface ISession {
    
    public void setup();
    
    public void initialise();
    
    public void active();
    
    public void standby();
    
    public byte readRegister(int addressToRead);
    
    public void writeRegister();
    
    public void run() throws Exception;
        
}
