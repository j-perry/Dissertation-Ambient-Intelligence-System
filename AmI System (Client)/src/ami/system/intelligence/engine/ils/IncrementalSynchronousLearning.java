/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
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
     * Runs our learning system (ISL?). 
     * 
     * First it checks to see if an initial monitoring phase has been passed, 
     * which keeps our overall model nice and consistent (well, that's the idea anyway!)
     * 
     * If it has been passed, run a full 
     */
    public void run(int sessionId, String hostname, int tempValue, int hour, int minute) {
                
        // if it has already been performed
        if(initialMonitoringPhase.hasRun() == true) {
            sessionPhase.run(sessionId, hostname, tempValue, hour, minute);
        } else {
            // if it hasn't run yet
            initialMonitoringPhase.run(sessionId, hostname, tempValue, hour, minute);
        }
    }
        
}