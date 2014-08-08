package ami.system.intelligence.engine.ils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
        final String fileName = "/xml/ami-config.xml";        
        date = new Date();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = df.format(date).toString();

        // XML File
        initialMonitoringPhaseConfig = new File(fileName);

        try {
            xmlBuilderFactory = DocumentBuilderFactory.newInstance();
            xmlBuilderFactory.newDocumentBuilder();
            Document doc = xmlBuilder.parse(initialMonitoringPhaseConfig);
            NodeList parentNode = doc.getElementsByTagName("initial-monitoring");

            // we only have one level in our XML file
            Node childrenNodes = parentNode.item(0);

            if (childrenNodes.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) parentNode;
                startDate = element.getElementsByTagName("startDate").item(0).getTextContent();
            }

            // if the 'start-date' element is empty...
            if (startDate.equals("")) {
                // set the element...
                Element element = (Element) parentNode;
                element.appendChild(doc.createTextNode(startDate) );
                
                // and write it...
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileName));
                transformer.transform(source, result);
                
                // then run the initial monitoring phase                
                
                
            } else if (startDate.equals(currentDate)) {
                return;
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
    private void saturatedModel() {
    }

    /**
     *
     */
    private void unsaturatedModel() {
    }
}
