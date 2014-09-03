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
        SystemProcess systemProcess = new SystemProcess();
        systemProcess.run();
    }
}