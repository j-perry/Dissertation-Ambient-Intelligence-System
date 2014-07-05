/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.core;

import ami.system.database.Temperature;
import com.pi4j.io.i2c.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author Jonathan Perry
 */
class TemperatureSession implements ISession {

    private I2CBus bus;
    private I2CDevice tempSensor;
    
    public TemperatureSession() {
        
    }

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
            System.out.println("Connected ok");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
        temperature.open();
        
        for(;;) {
            if(i == count) {
                break;
            }
            else {
                int noBytes = tempSensor.read(tempAddr, buffer, 0, 1); // TODO
                System.out.println("Temperature: " + buffer[0]);
                temp = buffer[0];
                
                System.out.println("Count: " + i);
                
                // date
                DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
                Date d = new Date();
                String date = df.format(d).toString();
                
                // time
                df = new SimpleDateFormat("HH:mm");
                String time = df.format(d).toString();
                
                try {                    
                    temperature.insert(temp, date, time);
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
                
                i++;
            }
        }
        
        temperature.close();        
    }

    @Override
    public void analyse() {
        
    }
    
}