/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.operations.context;

import java.io.*;

/**
 *
 * @author Jonathan Perry
 */
public class Session {
    
    public Session() {
        
    }
    
    /**
     * Displays options for contextual gathering - i.e., wireless devices, temperature, movement, etc.
     */
    public void displayOptions() {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("> What data would you like to gather?");
        
        final String option_one    = "> 1. Room temperature";
        final String option_two    = "> 2. Wireless device activity"; // TODO privacy?
        final String option_three  = "> 3. Chair movement";
        final String option_four   = "> 4. Room volume";
        final String option_five   = "> 5. Keyboard activity"; // TODO Is this possible?!
        
        System.out.println(option_one);
        System.out.println(option_two);
        System.out.println(option_three);
        System.out.println(option_four);
        System.out.println(option_five);
    }
    
    /**
     * Gets the user's choice of contextual gathering from the list displayed in displayOptions()
     */
    public void getOptionInput() {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int choice = 0;
       
       try {
           System.out.println();
           System.out.print("> Choice: ");
           choice = Integer.valueOf(br.readLine());
           parseOption(choice);
       }
       catch(IOException ex) {
           ex.printStackTrace();
       }       
    }
    
    private void parseOption(int choice) {
        VolumeSession volSession = null;
        
        switch(choice) {
            case 1:
                TemperatureSession tempSession = new TemperatureSession();
                
                try {
                    tempSession.run();
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                }
                
                //tempSession.analyse();
                
                break;
                
                /*
            case 2:
                WirelessSession wireSession = new WirelessSession();
                wireSession.run();
                break;
            case 3:
                MovementSession moveSession = new MovementSession();
                moveSession.run();
                moveSession.analyse();
                break;
            case 4:
                volSession = new VolumeSession();
                volSession.run();
                volSession.analyse();
                break;
            case 5:
                // We're capturing lo-frequency data? Do we need a different class?
                volSession = new VolumeSession();
                volSession.run();
                volSession.analyse();
                break;
                */
            default:
                System.out.println("> Invalid option. Try again.");
                System.out.println();
                displayOptions();
                getOptionInput();
                break;                
        }
    }
    
}
