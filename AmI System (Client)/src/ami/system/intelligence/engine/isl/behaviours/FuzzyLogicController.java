package ami.system.intelligence.engine.isl.behaviours;

import ami.system.operations.resources.database.InitialMonitoringTable;
import ami.system.operations.resources.database.MonitoringTable;

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
    private MonitoringTable dbMonitoring;

    public FuzzyLogicController() {
        dataBase = new DataBase();
        ruleBase = new RuleBase();
        decisionTree = new DecisionTree();        
        dbMonitoring = new MonitoringTable();
        
        dbMonitoring.open();
    }

    /**
     * Generates a model of data that is bound by a set of pre-defined fuzzy
     * rules
     *
     * @param i
     * @param context
     */
    public DataBase create(int i, String context) {
        dataBase = decisionTree.generate(i, context);
        
        // type
        System.out.println("Linguistic Type: " + dataBase.getLinguisticType());
        System.out.println();
        
        return dataBase;
    }
    
    /**
     * Write our sampled contextual data to a MySQL database table for computation on
     * the web server.
     * 
     * @param hour
     * @param minute
     */
    public void persistInitialMonitoringSession(int sessionId, 
            String hostname, 
            int hour, 
            int minute, 
            String day, 
            String month, 
            int year) {
        
        dbMonitoring.open();
        dbMonitoring.persist(
                sessionId, 
                hostname,
                hour, 
                minute, 
                day, 
                month, 
                year,
                dataBase.getValue(), 
                dataBase.getType(), 
                dataBase.getLinguisticType());
        dbMonitoring.close();
    }
    
    /**
     * Write contextual data to our monitoring session table
     * 
     * @param sessionId
     * @param hostname
     * @param hour
     * @param minute
     * @param day
     * @param month
     * @param year 
     */
    public void persistMonitoringSession(int sessionId, 
            String hostname, 
            int hour, 
            int minute, 
            String day, 
            String month, 
            int year) {
        
        dbMonitoring.open();
        dbMonitoring.persist(
                sessionId, 
                hostname,
                hour, 
                minute, 
                day, 
                month, 
                year,
                dataBase.getValue(), 
                dataBase.getType(), 
                dataBase.getLinguisticType());
        dbMonitoring.close();
    }
    
}