


package ami.system.operations.menu;

import ami.system.operations.engine.SystemProcess;

/**
 * Creates a new session
 * @author Jonathan Perry
 */
public class Session {
    
    public Session() {
        
    }

    /**
     * Sets up a new session
     */
    public void setup() {
        SystemProcess sysProcess = new SystemProcess();
        sysProcess.run();
    }
    
}