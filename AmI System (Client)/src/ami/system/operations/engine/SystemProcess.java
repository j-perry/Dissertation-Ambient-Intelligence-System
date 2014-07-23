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
    ******************************/
    final int tempAddr = 0x48;    
    final int accelAddr = 0x1d;
    final int lightAddr = 0x44;
    //final int micAddr = 0x;
    
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
    
    
    public SystemProcess() {
        
    }
    
    /**
     * Defines the contextual mode(s) to run on this ISL system
     * @param mode 
     */
    public void setContextMode(String mode) throws IOException {       
        
        switch (mode) {
            case mode_01:
                bus = I2CFactory.getInstance(I2CBus.BUS_1);
                tempSensor = bus.getDevice(tempAddr);
                break;
            case mode_02:
                bus = I2CFactory.getInstance(I2CBus.BUS_1);
                accelSensor = bus.getDevice(accelAddr);
                System.out.println("Accel connected");
                break;
            case mode_03:
                bus = I2CFactory.getInstance(I2CBus.BUS_1);
                lightSensor = bus.getDevice(lightAddr);
                break;
//            case mode_04:
//                bus = I2CFactory.getInstance(I2CBus.BUS_1);
//                micSensor = bus.getDevice(micAddr);
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
        
        
        // temperature
        if(tempSensor != null) {
            
            try {
                int noBytes = tempSensor.read(tempAddr, buffer, 0, 1);
                temp = buffer[0];
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        // accelerometer
        if(accelSensor != null) {
            
            // NB: just capture data once! also, remember we'll need an event-driven approach
            try {
                // X, Y, Z
                accelX = (short) accelSensor.read(accelAddr, buffer, 0, 1);
                accelY = (short) accelSensor.read(accelAddr, buffer, 0, 1);
                accelZ = (short) accelSensor.read(accelAddr, buffer, 0, 1);

                // we'll do some correction, based on sensitivity (inaccuracy) of the device
                // source from the data sheet
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        // output temperature    
        System.out.print("Temperature: " + temp);
        System.out.print("\t\t");
        
        // output accelerometer
        System.out.println("Accelerometer: " + accelX + ", " + accelY + ", " + accelZ);
        System.out.print("\t\t");
        
        System.out.print("Light: ");
        
        System.out.println();
        System.out.println();
        
        
        // output light
//        System.out.println("Light: " + temp);
    }
    
}