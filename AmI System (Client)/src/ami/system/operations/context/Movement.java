


package ami.system.operations.context;

import com.pi4j.io.i2c.*;

/**
 * Class used to create an instance of an ultrasonic transceiver sensor
 * 
 * @author Jonathan Perry
 */
public class Movement implements ISession {
    
    /*                 DEVICE INTERFACE
     **************************************************/
    
    
        
    /*                 PROPERTIES
     *********************************************/
    
        
    public Movement() {
        setup();
        initialise();
    }
    
    /**
     * 
     */
    @Override
    public void setup() {
        try {
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * First-point of call when creating a movement class
     */
    @Override
    public boolean initialise() {
        boolean connected;
        
        // check it is connected
        if() {
            System.out.println("Ultrasonic Transceiver is online.");
            connected = true;
        } else {
            System.out.println("Could not connect to the Ultrasonic Transceiver.");
            connected = false;
        }
        
        return connected;
    }
        
    /**
     * 
     */
    @Override
    public void active() {        
    }
    
    /**
     * 
     */
    @Override
    public void standby() {
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public byte readRegister(int addressToRead) {
        return 0;
    }
    
    @Override
    public void writeRegister() {        
    }

    /**
     * Get the distance between the sensor and the user
     */
    @Override
    public int readValue() {
        
        
        return 1;
    }
    
}
