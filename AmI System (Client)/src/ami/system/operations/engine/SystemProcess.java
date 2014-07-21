/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.operations.engine;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import java.io.IOException;

/**
 * 
 * @author Jonathan Perry
 */
public class SystemProcess {
    
    private final String mode_01 = "temperature";
    private final String mode_02 = "microphone";
    private final String mode_03 = "accelerometer";
    private final String mode_04 = "light";
    
    /*      Sensor Addresses       
     *****************************/
    final int tempAddr = 0x48;
    
    // NOT YET DEFINED!!!
    final int micAddr = 345;
    final int accelAddr = 34;
    final int lightAddr = 3434;
    
    private I2CBus bus;
    
    private I2CDevice tempSensor;
    private I2CDevice micSensor;
    private I2CDevice accelSensor;
    private I2CDevice lightSensor;
        
    public SystemProcess() {
        
    }
    
    /**
     * Defines the contextual mode(s) to run on this ISL system
     * @param mode 
     */
    public void setContextMode(String mode) throws IOException {       
                
        switch (mode) {
            case "temperature":
                bus = I2CFactory.getInstance(I2CBus.BUS_1);
                tempSensor = bus.getDevice(tempAddr);
                break;
//            case "microphone":
//                bus = I2CFactory.getInstance(I2CBus.BUS_1);
//                micSensor = bus.getDevice(micAddr);
//                break;
//            case "accelerometer":
//                bus = I2CFactory.getInstance(I2CBus.BUS_1);
//                accelSensor = bus.getDevice(accelAddr);
//                break;
//            case "light":
//                bus = I2CFactory.getInstance(I2CBus.BUS_1);
//                lightSensor = bus.getDevice(lightAddr);
//                break;
            default:
                break;
        }
    }
    
    /**
     * TODO - Very important method!!!!!!!!!!!!!!! This is our 2nd root point
     * Runs a new System Process
     */
    public void run() {
        byte [] buffer = new byte [1];
        int temp = 0;
        
        try {
            int noBytes = tempSensor.read(tempAddr, buffer, 0, 1);
            temp = buffer[0];
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Temperature: " + temp);
    }
    
}