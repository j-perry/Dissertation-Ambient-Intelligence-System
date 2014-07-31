


package ami.system.operations.engine;

import ami.system.operations.context.*;
import com.pi4j.io.i2c.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 
 * @author Jonathan Perry
 */
public class SystemProcess {
            
    private I2CBus bus;    
    private I2CDevice accelSensor; // event-driven (need a callback function)
    
    // devices
    private Movement movement;
        
    
    public SystemProcess() {
        processHeading();
    }
    
    private void processHeading() {
        final String heading = "\n"                                  +
                               "-----------------------------------" +
                               "\n\n"                                +
                               "\t"                                  +
                               "Started new system"                  +
                               "\n\n"                                +
                               "-----------------------------------" +
                               "\n";
        
        System.out.println(heading);
    }
    
    /**
     * TODO - Very important method!!!
     * This is our 2nd root point
     * Runs a new System Process
     */
    public void run() {
        boolean run_application = true;
        
        /*      Values
          *******************/
        int tempValue = 0;
        
        
        /*      Titles
         ********************/
        final String temperatureTitle = "Temperature Value: ";
        
        
        /*      Parse temperature data to our incremental learning system
         * 
         *      This will be our main application loop.          * 
         *      The application will not terminate until 17:30 PM.         * 
         *      The application however will also not start unless the time is 9:30am or thereafter.
         * 
         ********************************************************************************************/
        Calendar cal = new GregorianCalendar();
        
        final double before = 9.30;
        
        Double actualTime = 0.0;
        double d_hour   = 0.0;
        double d_minute = 0.0;
        
        // get the current hour and minute in the hour
        int hour   = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        
        // convert to doubles
        d_hour   = (double) hour;
        d_minute += (double) minute / 100;
                
        // compute products
        actualTime = d_hour;
        actualTime += d_minute;
        
        // convert to .2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        actualTime = Double.valueOf(df.format(actualTime) );
        
        // print the time
        System.out.println("The time is: " + actualTime);
        
        
//        if(actualTime >= before) {
//            while(run_application) {
//                
//                /*      Run The Temperature
//                 **********************************/
//                Temperature temp = new Temperature();
//                temp.setup();
//                temp.initialise();
//                tempValue = temp.readValue();
//
//                System.out.println(temperatureTitle + tempValue);
//                
//                
//            }
//        }
        
        
        
        
        
    }    
}




//        
//        
//        // accelerometer
//        if(accelSensor != null) {
//            
//            // NB: just capture data once! also, remember we'll need an event-driven approach
//            try {
//                // X, Y, Z
//                accelX = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//                accelY = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//                accelZ = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//
//                // we'll do some correction, based on sensitivity (inaccuracy) of the device
//                // source from the data sheet
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }