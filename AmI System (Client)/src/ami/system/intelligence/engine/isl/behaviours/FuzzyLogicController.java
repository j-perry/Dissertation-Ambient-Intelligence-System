package ami.system.intelligence.engine.isl.behaviours;

/**
 * Based on implementation in
 * http://www.researchgate.net/profile/Luis_Magdalena/publication/223851736_A_Fuzzy_logic_controller_with_learning_through_the_evolution_of_its_knowledge_base/links/0912f50a37e31dc51d000000
 *
 * @author Jonathan Perry
 */
public class FuzzyLogicController {

    private DataBase dataBase;
    private RuleBase ruleBase;
    private DecisionTree decisionTree;
    

    public FuzzyLogicController() {
        dataBase = new DataBase();
        ruleBase = new RuleBase();
        decisionTree = new DecisionTree();
    }

    /**
     * Generates a model of data that is bound by a set of pre-defined fuzzy
     * rules
     *
     * @param i
     * @param context
     */
    public void create(int i, String context) {
        dataBase = decisionTree.generate(i, context);
    }

    /**
     * Write our sampled contextual data to a MySQL database table for computation on
     * the web server.
     * 
     * @param hour
     * @param minute
     */
    public void persist(int hour, int minute) {
        
        
    }
}