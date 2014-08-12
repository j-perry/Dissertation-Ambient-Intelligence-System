


package ami.system.intelligence.engine.isl.behaviours;

/**
 * Stores a fuzzy set based on the type of context identified by the system
 * @author Jonathan Perry
 */
public class DataBase {
    
    private int value;
    private String type;
    private String linguisticType;
    
    public DataBase() {
        
    }
    
    public DataBase(int value, String type, String linguisticType) {
        this.value = value;
        this.type = type;
        this.linguisticType = linguisticType;
    }
            
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setType(String type) {
        this.type = type;                
    }
    
    public String getType() {
        return type;
    }
    
    public void setLinguisticType(String linguisticType) {
        this.linguisticType = linguisticType;
    }
    
    public String getLinguisticType() {
        return linguisticType;
    }
    
}