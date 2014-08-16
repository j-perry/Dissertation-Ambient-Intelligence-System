


package ami.system.intelligence.engine.ils;

import java.util.HashMap;

/**
 * 
 * @author Jonathan Perry
 */
public class IncrementalSynchronousLearning {
    
    // instance variables
    private Coordinator coord;
    private ExperienceBank exBank;
    private ContextualPrompt pContext;
    private InitialMonitoringPhase initialMonitoringPhase;
    private MonitoringPhase sessionPhase;
    
    private HashMap<String, Integer> context;
    
    // sensor values (for processing)
    private int tempValue;
    
    public IncrementalSynchronousLearning() {        
        exBank = new ExperienceBank();
        pContext = new ContextualPrompt();
        initialMonitoringPhase = new InitialMonitoringPhase();
    }
    
    /**
     * Runs an initial monitoring phase
     */
    public boolean runInitialMonitoringPhase(int sessionId, String hostname, int tempValue, int hour, int minute) {
        boolean run;
        
        // if it hasn't run yet
        if(initialMonitoringPhase.hasRun() == false) {
            initialMonitoringPhase.run(sessionId, hostname, tempValue, hour, minute);
            run = true;
        } else {
            run = false;
        }
        return run;
    }
    
    /**
     * Runs our learning system (ISL?), following the initial monitoring phase
     * which keeps our overall model nice and consistent (well, that's the idea anyway!)
     */
    public void run(int sessionId, String hostname, int tempValue, int hour, int minute) {
        if(runInitialMonitoringPhase(0, null, 0, 0, 0) == true) {
            sessionPhase.run(sessionId, hostname, tempValue, hour, minute);
        }
    }
    
}