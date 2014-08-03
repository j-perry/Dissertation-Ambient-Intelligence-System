


package ami.system;

//import ami.system.operations.menu.AmISystemMenu;
import ami.system.operations.engine.SystemProcess;

/**
 * Initialises our Ambient Intelligence Learning System
 * @author Jonathan Perry
 */
public class AmISystem {
    
    public AmISystem() {
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("\t");
        System.out.println("Ambient Intelligence Learning System");
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------");        
        System.out.println();
    }
    
    /**
     * Starts our application
     */
    public void init() {
        // attempt to auto start and run a new system process.
        // this is reliant on conditions set between 9:00 - 17:30.
        // however, before doing so, delay the start by 15 seconds to ensure
        // a network connection is established (just as a precaution).
        SystemProcess systemProcess = new SystemProcess();
        systemProcess.delay(15);
        systemProcess.run();
    }
}