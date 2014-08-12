package ami.system.intelligence.engine.isl.behaviours;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

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
    // JSON
    private Gson gson;

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
     * Serialize our data into JSON representation for analysis later on. We
     * will primarily use this method to append data to the file
     *
     * @param hour
     * @param minute
     */
    public void serialize(int hour, int minute) throws IOException {
        String jsonFile = "/home/pi/projects/AmI_System/overview.json";

        JsonWriter jsonWriter = null;
        
        String param1 = "Hour";
        String param2 = "Minute";
        String param3 = "Value";
        String param4 = "Context";
        String param5 = "LinguisticType";
        
        try {
            jsonWriter = new JsonWriter(new FileWriter(jsonFile));
            jsonWriter.beginObject();
            
            jsonWriter.name("Entry");
            jsonWriter.beginArray();
            
            // write values
            jsonWriter.value(hour);
            jsonWriter.value(minute);
            jsonWriter.value(dataBase.getValue() );                        
            jsonWriter.value(dataBase.getType() );
            jsonWriter.value(dataBase.getLinguisticType() );         
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {            
            jsonWriter.endArray();
            jsonWriter.endObject();
            jsonWriter.close();
        }

    }
}