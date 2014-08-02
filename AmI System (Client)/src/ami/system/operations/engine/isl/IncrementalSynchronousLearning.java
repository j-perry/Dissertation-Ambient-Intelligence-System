


package ami.system.operations.engine.isl;

/**
 *
 * @author Jonathan Perry
 */
public class IncrementalSynchronousLearning {
    
    // instance variables
    private Coordinator coord;
    private ExperienceBank exBank;
    private PromptContext pContext;
    
    // sensor values (for processing)
    private int tempValue;
    
    public IncrementalSynchronousLearning() {
        
    }
    
    /**
     * 
     * @param tempValue 
     */    
    public void parseTemperatureValue(int tempValue) {
        this.tempValue = tempValue;
    }
    
}
