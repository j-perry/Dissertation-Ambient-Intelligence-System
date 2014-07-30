


package ami.system.operations.context;

/**
 *
 * @author Jonathan Perry
 */
public interface ISession {
    
    public void initialise();
    
    public void active();
    
    public void standby();
    
    public void run() throws Exception;
        
}
