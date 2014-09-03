package ami.system.intelligence.engine.isl.behaviours;

import java.util.*;

/**
 *
 * @author Jonathan Perry
 */
public class RuleBase {

    /*
     * Predefined fuzzy rules
     */
    // ... temperature
    private final int very_cold = 0;
    private final int moderately_cold = 10;
    private final int moderately_warm = 20;
    private final int moderately_hot = 30;
    private final int very_hot = 40;

    // 
    private final int away = 0;   // 0cm
    private final int present = 100; // 20cm ??? Can the device reach 20cm
    
    // this indicates the user is away from their desk. 
    private final int away_2 = 4000;

    private LinkedHashMap<Integer, String> tempRules;
    private LinkedHashMap<Integer, String> ultrasonicRules;

    // others to follow...
    public RuleBase() {
        // create our two dimensional array that will store our pre-defined fuzzy rules
        // start by creating rules for the temperature sensor
        this.tempRules = new LinkedHashMap<>();
        this.ultrasonicRules = new LinkedHashMap<>();

        // look up table for temperature sensor
        // parameters: key, value
        this.tempRules.put(very_cold, "Very cold"); // 
        this.tempRules.put(moderately_cold, "Cold");
        this.tempRules.put(moderately_warm, "Warm");
        this.tempRules.put(moderately_hot, "Hot");
        this.tempRules.put(very_hot, "Very hot");
        
        this.ultrasonicRules.put(away, "Away");
        this.ultrasonicRules.put(present, "Present");
        this.ultrasonicRules.put(away_2, "Away");
    }

    /**
     * Looks up what position the linguistic type holds in our fuzzy sets for
     * each context.
     *
     * @param value
     * @param type
     * @return the linguistic type for the parsed type into the system
     */
    public String lookup(int value, String type) {
        String result = null;
        Iterator it;
        
        System.out.println("TYPE:" + type);

        // this is where we look up rules defined in our two dimensional matrix,
        // sorted by contextual type (i.e., temperature)
        switch (type) {
            case "temperature": {
                // temperature fuzzy set
                it = tempRules.entrySet().iterator();
                Map.Entry currTempEntry = null;
                Map.Entry prevTempEntry = null;
                // get the first entry
                currTempEntry = (Map.Entry) it.next();
                while (it.hasNext()) {
                    // store the previous entry
                    prevTempEntry = currTempEntry;

                    // get the next entry
                    currTempEntry = (Map.Entry) it.next();

                        // if... then...
                    // very cold vs cold, cold vs warm, etc.
                    // if value is between 0 AND value is between 10
                    // assign it this linguistic type
                    if (value >= (int) prevTempEntry.getKey()
                            && value <= (int) currTempEntry.getKey()) {
                        result = (String) currTempEntry.getValue();
                    }
                }
                break;
            }
            case "movement": {
                // temperature fuzzy set
                it = ultrasonicRules.entrySet().iterator();
                Map.Entry currTempEntry = null;
                Map.Entry prevTempEntry = null;
                // get the first entry
                currTempEntry = (Map.Entry) it.next();
                while (it.hasNext()) {
                    // store the previous entry
                    prevTempEntry = currTempEntry;

                    // get the next entry
                    currTempEntry = (Map.Entry) it.next();

                        // if... then...
                    // away vs present, etc.
                    // if value is between 0 AND value is between 10
                    // assign it this linguistic type
                    if (value >= (int) prevTempEntry.getKey()
                            && value <= (int) currTempEntry.getKey()) {
                        result = (String) currTempEntry.getValue();
                    }
                }
                break;
            }
        }

        return result;
    }
}
