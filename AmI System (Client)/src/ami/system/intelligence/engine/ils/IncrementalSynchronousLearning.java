


package ami.system.intelligence.engine.ils;


/**
 * 
 * @author Jonathan Perry
 */
public class IncrementalSynchronousLearning {
    
    /*
     * instance variables
     */
//    private Coordinator coord;
//    private ExperienceBank exBank;
    private ContextualPrompt pContext;
    private InitialMonitoringPhase initialMonitoringPhase;
    private MonitoringPhase sessionPhase;
    
    // sensor values (for processing)
    private int tempValue;
    
    public IncrementalSynchronousLearning() {
        pContext = new ContextualPrompt();
        initialMonitoringPhase = new InitialMonitoringPhase();
        sessionPhase = new MonitoringPhase();
    }
    
    /**
     * Runs an initial monitoring phase
     */
    public boolean runInitialMonitoringPhase(int sessionId, String hostname, int tempValue, int hour, int minute) {
        boolean run;
        
        // if it has already been performed
        if(initialMonitoringPhase.hasRun() == true) {
            run = true;
        } else {
            // if it hasn't run yet
            initialMonitoringPhase.run(sessionId, hostname, tempValue, hour, minute);
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