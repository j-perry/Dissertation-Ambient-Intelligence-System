package ami.system.intelligence.engine.ils;

// local libaries
import ami.system.intelligence.engine.SystemProcessUtil;
import ami.system.intelligence.engine.isl.behaviours.DataBase;
import ami.system.intelligence.engine.isl.behaviours.FuzzyLogicController;

// Java APIs
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// third party libraries
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Constructs an initial contextual model of data collected on the first day of
 * operation. This will ensure our data presents a balanced perspective.
 *
 * @author Jonathan Perry
 */
public class InitialContextPhase {

    // JSON
    private GregorianCalendar cal;
    private Date d;
    private final String fileName = "projects/AmI_System/config.json";

    private String startDate;
    private String currentDate;

    private FuzzyLogicController flc;
    private ContextualPrompt contextualPrompt;

    private DataBase entry;

    private int hour;
    private int minute;

    public InitialContextPhase() {
        
    }

    /**
     * Checks to see if an initial monitoring phase has been run
     *
     * @return
     */
    public boolean hasRun() {
        boolean hasRun = false;

        cal = new GregorianCalendar();
        currentDate = String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
                + "-"
                + String.valueOf(cal.get(Calendar.MONTH))
                + "-"
                + String.valueOf(cal.get(Calendar.YEAR));
        
        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));
            JSONObject jsonObject = (JSONObject) obj;
            
            startDate = (String) jsonObject.get("startDate");
            
            System.out.println("hasRun(): " + startDate);
            
            // if a start date has been set
            if (startDate.contains("")) {
                // run it
                hasRun = true;
            } else {
                // otherwise don't run it
                hasRun = false;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return hasRun;
    }

    /**
     * Performs an initial monitoring phase.
     *
     * This is conditional based on whether the present date exceeds the first
     * date it started - i.e., start date: 08-08-2014, current date: 09-08-2014.
     *
     * In this instance it wouldn't perform one, but instead return a model as a
     * basis for learning activities and behaviours later-on.
     *
     * @param sessionId
     * @param hostname
     * @param tempValue
     * @param hour
     * @param minute
     */
    public void run(int sessionId, String hostname, int tempValue, int hour, int minute) {
        flc = new FuzzyLogicController();
        String date = null;
        String context;
        d = new Date();
        cal = new GregorianCalendar();
        double time;
        
        SystemProcessUtil.SystemTime util = new SystemProcessUtil.SystemTime();
        time = (double) cal.get(Calendar.HOUR_OF_DAY);
        time += (double) (Double.valueOf(new SimpleDateFormat("mm").format(new Date())) / 100);
        System.out.println("The time is: " + time);

        DecimalFormat df = new DecimalFormat("#.00");
        time = Double.valueOf(df.format(time));

        // if it is not 17.30pm
        if (time != SystemProcessUtil.terminate_time) {

            // identify the context
            contextualPrompt = new ContextualPrompt();
            context = contextualPrompt.identify(tempValue);

            // apply a fuzzy logic controller
            // NB: we will need to return a set of data to write to the database/JSON/XML file
            // agent controller??
            // generate and update a fuzzy logic model based on defined and pre-defined rules                         
            entry = new DataBase();
            entry = flc.create(tempValue, context);

            // day, month, year
            String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
            int year = cal.get(Calendar.YEAR);

            // persistInitialContextSession the generated fuzzy model to a MySQL database table
            flc.persistInitialContextSession(sessionId, hostname, hour, minute, day, month, year);
        }
    }
}