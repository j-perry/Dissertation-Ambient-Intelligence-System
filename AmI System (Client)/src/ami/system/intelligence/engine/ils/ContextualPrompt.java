/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.intelligence.engine.ils;

/**
 * Identifies the context being parsed
 * @author Jonathan Perry
 */
public class ContextualPrompt {
    
    public ContextualPrompt() {
        
    }
    
    /**
     * Identifies the context being parsed before being processed through
     * the fuzzy logic controller.
     * @param value
     * @return 
     */
    public String identify(int value) {
        // what context
        String context = null;
        
        // temperature
        int minTemp = 0;
        int maxTemp = 40;
        
        // temperature
        if((double) value > minTemp && (double) value < maxTemp) {
            context = "temperature";
        }
        
        // others
        
        return context;
    }
    
}