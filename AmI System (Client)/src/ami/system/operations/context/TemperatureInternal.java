


package ami.system.operations.context;

//import ami.system.resources.database.Temperature;
import com.pi4j.io.i2c.*;
import java.io.*;

/**
 * Class used to create an instance of a temperature sensor
 * @author Jonathan Perry
 */
public class TemperatureInternal implements ISession {
    
    private I2CBus bus;
    private I2CDevice tempSensor;
    
    private final static int address = 0x48;
    
    public TemperatureInternal() {
        setup();
        initialise();
    }
    
    /**
     * Sets up our temperature sensor
     */
    @Override
    public void setup() {
       try {
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
//            tempSensor = bus.getDevice(address);          DOES IT WORK WITHOUT THIS LINE OF CODE?
        } catch(IOException ex) {
            ex.printStackTrace();
        } 
    }

    /**
     * Initialises a new instance of our temperature sensor
     */
    @Override
    public boolean initialise() {
        boolean connected;
        
        try {            
            tempSensor = bus.getDevice(address);
            connected = true;
        } catch(Exception ex) {
            connected = false;
            ex.printStackTrace();
        }
        
        return connected;
    }
    
    /**
     * Get the temperature value
     * @return the temperature value
     * @throws Exception 
     */
    @Override
    public int readValue() {
        byte [] buffer = new byte[1];
        int value = 0;
        
        try {
            tempSensor.read(address, buffer, 0, buffer.length);
            value = buffer[0];
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return value;        
    }

    /**
     * 
     * @throws Exception 
    
    @Override
    public void run() throws Exception {
        int tempAddr = 0x48;
        byte [] buffer = new byte [1];
        int count = 30000;
        int i = 0;
        int temp = 0;
        Temperature temperature = new Temperature();
        
        try {
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            tempSensor = bus.getDevice(tempAddr);
            System.out.println("> Connected ok");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println();
        
//        temperature.open();
        
        // capture the temperature every 10 minutes
        try {
            while(true) {
                // date
                DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
                Date d = new Date();
                String date = df.format(d).toString();
                
                // time
                df = new SimpleDateFormat("HH:mm");
                String time = df.format(d).toString();
                
                // if the time is 10pm, terminate
                if(time.equals("22:00")) {
                    break;
                }
                else {
                    int noBytes = tempSensor.read(tempAddr, buffer, 0, 1);
                    temp = buffer[0];
                    
                    try {
                        // this will probably need to change
//                        temperature.insert(temp, date, time);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                    int duration = 600 * 1000; // 10 minutes
                    Thread.sleep(duration);
                }              
            }
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
                
//        temperature.close();
    }
    */
    
    @Override
    public void active() {
        
    }

    @Override
    public void standby() {
        
    }
    
    /**
     * 
     * @param addressToRead
     * @return 
     */
    @Override
    public byte readRegister(int addressToRead) {
        return 0;
    }
    
    @Override
    public void writeRegister() {
        
    }
    
}