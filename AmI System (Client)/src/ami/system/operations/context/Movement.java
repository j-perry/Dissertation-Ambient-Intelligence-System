


package ami.system.operations.context;

import com.pi4j.io.i2c.*;
import java.io.IOException;

/**
 * 
 * @author Jonathan Perry
 */
public class Movement implements ISession {
    
    private I2CDevice accelerometer;
    private I2CBus bus;
    
    /*                 REGISTERS
     *********************************************/
    private final static int ADDRESS = 0x1D;
    
    private final static int OUT_X_MSB    = 0x01;
    private final static int XYZ_DATA_CFG = 0x0E;
    private final static int WHO_AM_I     = 0x0D;
    private final static int CTRL_REG_1   = 0x2A;
    
    public Movement() {
        
    }
    
    /**
     * Sets the MMA8452Q accelerometer on "active" mode (HIGH state)
     */
    @Override
    public void active() {
        byte b = readRegister(CTRL_REG_1);
        byte active = (byte) (b | (0x01));
        
        try {
            accelerometer.write(CTRL_REG_1, active);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Sets the MMA8452Q accelerometer on "standby" mode (LOW state)
     */
    @Override
    public void standby() {
        byte b = readRegister(CTRL_REG_1);
        byte standby = (byte) (b & (~(0x01)));
        
        try {
            accelerometer.write(CTRL_REG_1, standby);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Reads the required internal register on the device
     * @param addressToRead
     * @return 
     */
    private byte readRegister(int addressToRead) {
        byte b [] = new byte[1];
        byte result = 0;
        
        try {
            accelerometer = bus.getDevice(ADDRESS);
            accelerometer.write(addressToRead, (byte) 0b000000);
            
            accelerometer.read(ADDRESS, b, 0, 1);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        
        result = b[0];
        
        return result;
    }

    @Override
    public void run() {
        
    }

    @Override
    public void analyse() {
        
    }
    
}
