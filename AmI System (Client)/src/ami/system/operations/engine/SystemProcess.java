


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
        
    private SystemProcessUtil util;
        
    
    public SystemProcess() {
        
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
        processHeading();
        
        boolean run_application = true;
        util = new SystemProcessUtil();
        
        /*      Values
          *******************/
        int tempValue = 0;
        
        
        /*      Titles
         ********************/
        final String temperatureTitle = "Temperature Value: ";
        
        
        /*      
         *      This will be our main application loop.
         * 
         *      The application will not terminate until 17:30 PM.
         *      The application however will also not start unless the time is 9:30am or thereafter.
         * 
         ********************************************************************************************/
        
        
        // check it isn't 17:30 PM or thereafter
        if(util.afterHours() == true) {
            System.out.println("It is beyond 17:30pm. Try again tomorrow at 9.30 AM or thereafter.");
            System.exit(0);
        }
        else if(util.beforeHours() == true) {
            System.out.println();
            System.out.println("It is 9:30 AM or thereafter");
            System.out.println();
            
            // main application loop
            while(run_application) {
                           
                // if it is 17.30, terminate the application
                if(util.checkTimeBounds() == true) {
                    run_application = false;
                } 
                // else, continue running the system
                else {
                    
                    /*      Run The Temperature
                     **********************************/
                    Temperature temp = new Temperature();
                    temp.setup();
                    temp.initialise();
                    tempValue = temp.readValue();
                                        
                    // output the temperature value
                    System.out.println(temperatureTitle + tempValue);
                    
                    
                    /*      Parse temperature data to our incremental learning system
                     ***********************************************************************/
                    
                }                
                
            }
        }
                        
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