/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system;

import ami.system.intelligence.engine.SystemProcess;

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
        // Attempt to auto start and run a new system process.
        // This is reliant on conditions set between 9:00 - 17:30.
        // However, before doing so, delay the start by 15 seconds to ensure
        // a network connection is established (just as a precaution).
        final int seconds = 15;
        
        SystemProcess systemProcess = new SystemProcess();
        systemProcess.delay(seconds);
        systemProcess.run();
    }
}