


package ami.system.operations.context;

import com.pi4j.io.i2c.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/**
 * 
 * @author Jonathan Perry
 */
public class Movement implements ISession {
    
    /*                 DEVICE INTERFACE
     **************************************************/
    private I2CDevice accelerometer;
    private I2CBus bus;
    
    /*                 REGISTERS
     *********************************************/
    private final static int ADDRESS = 0x1d;
    
    private final static int OUT_X_MSB    = 0x01;
    private final static int XYZ_DATA_CFG = 0x0E;
    private final static int WHO_AM_I     = 0x0D;
    private final static int CTRL_REG_1   = 0x2A;
    
    /*                 PROPERTIES
     *********************************************/
    private short accelX;
    private short accelY;
    private short accelZ;
        
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
    public void initialise() {
        byte b = readRegister(WHO_AM_I);
        
        // check it is connected
        if(b == 0x2A) {
            System.out.println("Accelerometer is online");
        } else {
            System.out.println("Could not connect to the Accelerometer: " + b);
        }
        
        // set the device on standby
//        standby();
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
        } catch(Exception ex) {
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
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Reads the required internal register on the device
     * @param addressToRead
     * @return 
     */
    @Override
    public byte readRegister(int addressToRead) {
        byte b [] = new byte[1];
        byte result = 0;
        
        try {
            accelerometer = bus.getDevice(ADDRESS);
            //accelerometer.write(addressToRead, (byte) 0b00000000);
//            int bytes = accelerometer.read(ADDRESS, b, 0, 4); // parse 'addressToRead' NOT 'ADDRESS'
            int bytes = accelerometer.read(addressToRead, b, 0, b.length); // parse 'addressToRead' NOT 'ADDRESS'
            DataInputStream abc = new DataInputStream(new ByteArrayInputStream(b));
            System.out.println("bytes: " + bytes);
            
            int p = 0;
            
            for(int i = 0; i < b.length; i++) {
                System.out.print(abc.readByte() + ", ");
//                p += abc.readByte();
            }
            
//            p = (p << 5);
            
//            System.out.println("abc: " + p );
            
            result = b[0];
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    @Override
    public void writeRegister() {
        
    }

    /**
     * 
     */
    @Override
    public int readValue() {
        return 1;
    }
    
}
