


package ami.system.intelligence.engine.ils;

/**
 *
 * @author Jonathan Perry
 */
public class ContextualPrompt {
    
    public ContextualPrompt() {
        
    }
    
    public String parse(int value) {
        String label = null;
        
        // temperature
        if(value > 4 && value < 40) {
            label = "temperature";
        }
        
        return label;
    }
    
}
