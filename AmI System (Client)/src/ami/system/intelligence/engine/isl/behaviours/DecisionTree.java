


package ami.system.intelligence.engine.isl.behaviours;

/**
 * 
 * @author Jonathan Perry
 */
public class DecisionTree {
    
    private DataBase dataBase;
    private RuleBase ruleBase;
    
    public DecisionTree() {
        ruleBase = new RuleBase();
    }
    
    /**
     * Generates our fuzzy rules
     * @param i
     * @param type fixed length String that represents linguistic terms returned
     * by the fuzzy rule.
     */
    public DataBase generate(int i, String type) {
        // look up the linguistic type from defined fuzzy sets for each contextual type
        String result = ruleBase.lookup(i, type);
        dataBase = new DataBase(i, type, result);
        
        return dataBase;
    }
    
}