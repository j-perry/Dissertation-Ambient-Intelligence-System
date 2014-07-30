


package ami.system.operations.menu;

import ami.system.operations.engine.SystemProcess;

import java.io.*;

/**
 * Creates a new session
 * @author Jonathan Perry
 */
public class Session {

    private boolean CaptureTemperature,
                    CaptureAtmosphere,
                    CaptureMotion,
                    CaptureLight;

    public Session() {
        
    }

    /**
     * Displays options for contextual gathering - i.e., temperature, motion, etc.
     */
    public void setup() {
        SystemProcess sysProcess = new SystemProcess();
//        systemProcess.setupSensors();
        sysProcess.run();
    }
    
}