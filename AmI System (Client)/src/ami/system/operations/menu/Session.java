


package ami.system.operations.menu;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Jonathan Perry
 */
public class Session {
    
    private boolean CaptureTemperature,
                    CaptureAtmosphere,
                    CaptureMotion,
                    CaptureLight;
    
    public Session() {
        
    }
    
    /**
     * Displays options for contextual gathering - i.e., temperature, motion, etc.
     */
    public void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        final String options = "Y/N";
        
        final String msg_one   = "> Would you like to capture the room temperature? ";
        final String msg_two   = "> Would you like to capture the room volume? ";
        final String msg_three = "> Would you like to capture the motion of the attached chair? ";
        final String msg_four  = "> Would you like to capture the light in the room? ";
        
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("> Data Acquisition");
        System.out.println();
                
        // context option 1
        System.out.println(msg_one + options);
        CaptureTemperature = getOptionInput();
        System.out.println();
                
        // context option 2
        System.out.println(msg_two + options);
        CaptureAtmosphere = getOptionInput();
        System.out.println();
                
        // context option 3
        System.out.println(msg_three + options);
        CaptureMotion = getOptionInput();
        System.out.println();  
        
        // context option 4
        System.out.println(msg_four + options);
        CaptureLight = getOptionInput();
        System.out.println();
        
        processChosenContexts(CaptureTemperature, CaptureAtmosphere, CaptureMotion, CaptureLight);
    }
    
    /**
     * Gets the user's choice of context
     */
    public boolean getOptionInput() {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String choice = "";
       boolean result = false;
       
       try {
           System.out.print("> Choice: ");
           choice = br.readLine();
           
           if(choice == "Y") {
               result = true;
           }
           else {
               result = false;
           }
       }
       catch(IOException ex) {
           ex.printStackTrace();
       }
       finally {
           return result;
       }
    }
    
    /**
     * Processes chosen contexts
     */
    public void processChosenContexts(boolean capture_temperature, 
                                      boolean capture_atmosphere, 
                                      boolean capture_motion, 
                                      boolean capture_light) {
        
        // enable the temperature sensor
        if(capture_temperature == true) {
            System.exit(0);
        }
        // enable the microphone sensor
        else if(capture_atmosphere = true) {
            System.exit(0);
        }
        // enable the accelerometer
        else if(capture_motion) {
            System.exit(0);
        }
        // enable the RGB light sensor
        // TODO
//        else if(capture_light) {
//            System.exit(0);            
//        }
        
        // return a new instanceof
    }
    
}
