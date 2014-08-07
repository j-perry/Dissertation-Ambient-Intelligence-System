


package ami.system.intelligence.engine.ils;

import java.util.ArrayList;

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
    private ArrayList<Integer> tempValues;
    
    public IncrementalSynchronousLearning() {
        
    }
    
    /**
     * 
     * @param tempValue 
     */    
    public void parseTemperatureValue(int tempValue) {
        this.tempValues.add(tempValue);
    }
    
}