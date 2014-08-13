


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
    public boolean runInitialMonitoringPhase(int tempValue, int hour, int minute) {
        boolean run;
        
        if(initialMonitoringPhase.hasRun() == false) {
            initialMonitoringPhase.run(tempValue, hour, minute);
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
    public void run(int tempValue) {
        if(runInitialMonitoringPhase(0, 0, 0) == true) {
//            context = pContext.identify(tempValue);
        }
    }
    
}