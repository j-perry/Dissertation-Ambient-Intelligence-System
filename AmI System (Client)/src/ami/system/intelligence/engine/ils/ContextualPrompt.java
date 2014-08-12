


package ami.system.intelligence.engine.ils;

import java.util.HashMap;

/**
 * 
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
        if(value > minTemp && value < maxTemp) {
            context = "temperature";
        }
        
        // others
        
        return context;
    }
    
}