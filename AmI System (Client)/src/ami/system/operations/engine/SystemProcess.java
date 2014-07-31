


package ami.system.operations.engine;

import ami.system.operations.context.Movement;
import ami.system.operations.context.Temperature;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

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
        
        /*      Values
          *******************/
        int tempValue = 0;
        
        
        /*      Titles
         ********************/
        final String temperatureTitle = "Temperature Value: ";
        
        
        /*      Run The Temperature
         **********************************/
        Temperature temp = new Temperature();        
        temp.setup();
        temp.initialise();
        tempValue = temp.readValue();
        
        System.out.println(temperatureTitle + tempValue);
        
        /*      Parse temperature data to our incremental learning system
         ***********************************************************************/       
        
        
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