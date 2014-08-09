package ami.system.intelligence.engine.ils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.*;

/**
 * Constructs an initial contextual model of data collected on the first day of
 * operation. This will ensure our data presents a balanced perspective.
 *
 * @author Jonathan Perry
 */
public class InitialMonitoringPhase {

    private Date date;
    private File initialMonitoringPhaseConfig;
    private DocumentBuilderFactory xmlBuilderFactory;
    private DocumentBuilder xmlBuilder;
    private ArrayList<Integer> tempValues;

    public InitialMonitoringPhase() {
    }

    public InitialMonitoringPhase(ArrayList<Integer> tempValues) {
    }

    /**
     * Performs an initial monitoring phase. This is conditional based on
     * whether the present date exceeds the first date it started - i.e., start
     * date: 08-08-2014 current date: 09-08-2014. In this instance it wouldn't
     * perform one, but instead return a model as a basis for learning
     * activities and behaviours later-on.
     */
    public void run() {
        String startDate = null,
               currentDate = null;
        final String fileName = "projects/AmI_System/config.xml";
        date = new Date();

        GregorianCalendar cal = new GregorianCalendar();
        currentDate = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) ) +
                      "/" +
                      String.valueOf(cal.get(Calendar.MONTH) ) +
                      "/" +
                      String.valueOf(cal.get(Calendar.YEAR) );

        try {
            initialMonitoringPhaseConfig = new File(fileName);
            xmlBuilderFactory = DocumentBuilderFactory.newInstance();
            xmlBuilder = xmlBuilderFactory.newDocumentBuilder();
            Document doc = xmlBuilder.parse(initialMonitoringPhaseConfig);
            
            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath path = pathFactory.newXPath();
            startDate = (String) path.evaluate("config/initial-monitoring/startDate", doc);
            
            // if empty
            if(startDate.isEmpty()) {
                Node root = doc.getFirstChild();
                Node sd = doc.getElementsByTagName("startDate").item(0);
                startDate = String.valueOf(cal.get(Calendar.DAY_OF_MONTH) ) +
                            "/" +
                            String.valueOf(cal.get(Calendar.MONTH) ) +
                            "/" +
                            String.valueOf(cal.get(Calendar.YEAR) );
                sd.setTextContent(startDate);
                
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));
		transformer.transform(source, result);
            }          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     *
     */
    public void identifyContext() {
    }

    /**
     *
     */
    private void generateSaturatedModel() {
    }

    /**
     *
     */
    private void generateUnsaturatedModel() {
    }
}
