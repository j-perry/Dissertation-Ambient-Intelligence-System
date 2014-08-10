


package ami.system.intelligence.engine.ils;

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
    public boolean runInitialMonitoringPhase(int tempValue) {
        boolean run;
        
        if(initialMonitoringPhase.hasRun() == false) {
            initialMonitoringPhase.run(tempValue);
            run = true;
        } else {
            run = false;
        }
        return run;
    }
    
    /**
     * 
     */
    public void run(int tempValue) {
        if(runInitialMonitoringPhase(0) == false) {
            // carry out operations
        }
    }
    
}