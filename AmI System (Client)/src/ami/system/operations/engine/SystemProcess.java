


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
    
    private I2CDevice tempSensor;
    private I2CDevice micSensor;
    private I2CDevice accelSensor; // event-driven (need a callback function)
    private I2CDevice lightSensor;
    
    /*      Device properties
    ******************************/
    
    // temperature
    private int temperature;
    
    // acceleromter
    private short accelX;
    private short accelY;
    private short accelZ;
    
    // devices
    private Movement movement;
    
    
    public SystemProcess() {
        processHeading();
    }
    
    private void processHeading() {
        final String heading = "\n"                                  +
                               "-----------------------------------" +
                               "\n\n"                                +
                               "\tStarted new system"                +
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
        int tempValue = 0;
        
        // run the temperature
        Temperature temp = new Temperature();        
        temp.setup();
        temp.initialise();
        tempValue = temp.readValue();
        
        System.out.println("Temperature Value: " + tempValue);
        
        
//        byte [] buffer = new byte [1];
//        int temp = 0;
//                
//        // temperature
//        if(tempSensor != null) {
//            
//            try {
//                int noBytes = tempSensor.read(tempAddr, buffer, 0, 1);
//                temp = buffer[0];
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
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
                
        // output temperature    
//        System.out.print("Temperature: " + temp);
//        System.out.print("\t\t");
        
        // output accelerometer
//        System.out.println("Accelerometer: " + accelX + ", " + accelY + ", " + accelZ);
//        System.out.print("\t\t");
        
//        System.out.print("Light: ");        
//        System.out.println();
//        System.out.println();
        
        // output light
//        System.out.println("Light: " + temp);
    }
    
}