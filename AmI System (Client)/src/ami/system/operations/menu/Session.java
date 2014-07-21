


package ami.system.operations.menu;

import ami.system.operations.engine.SystemProcess;

import java.io.*;

/**
 * Creates a new session
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
    public void setup() {
        int noContextsChosen = 0;
        boolean minContextsChosen = false;
        final String options = "Y/N";

        final String msg_one = "> Would you like to capture the room temperature? ";
        final String msg_two = "> Would you like to capture the room volume? ";
        final String msg_three = "> Would you like to capture the motion of the attached chair? ";
        final String msg_four = "> Would you like to capture the light in the room? ";

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("> Data Acquisition");
        System.out.println();

        // validation to check a context or contexts have been chosen to run the system
        while (minContextsChosen != true) {

            // context option 1: temperature
            System.out.println(msg_one + options);
            CaptureTemperature = getOptionInput();

            if (CaptureTemperature == true)
                noContextsChosen++;
            System.out.println();
            
            
            // context option 2: volume
            System.out.println(msg_two + options);
            CaptureAtmosphere = getOptionInput();
            
            if (CaptureAtmosphere == true)
                noContextsChosen++;
            System.out.println();
            
            
            // context option 3: motion (acceleromter)
            System.out.println(msg_three + options);
            CaptureMotion = getOptionInput();
            
            if (CaptureMotion == true)
                noContextsChosen++;
            System.out.println();
            
            
            // context option 4: light
            System.out.println(msg_four + options);
            CaptureLight = getOptionInput();
            
            if (CaptureLight == true)
                noContextsChosen++;            
            System.out.println();
            
            
            // check number of contexts chosen is not zero, otherwise
            // if this is the case, repeat the process again.
            if (noContextsChosen == 0) {
                System.out.println("No sensor has been selected for operation. Please choose at least one.");
                continue;
            }
            else if (noContextsChosen > 0) {
                minContextsChosen = true;
            }
        }

        // TODO
        processChosenContexts(CaptureTemperature, CaptureAtmosphere, CaptureMotion, CaptureLight);
    }

    /**
     * Gets the user's choice of context
     */
    public boolean getOptionInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        boolean result = false;

        try {
            System.out.print("> Choice: ");
            choice = br.readLine();

            if (choice.equals("Y")) {
                result = true;
            } else {
                result = false;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
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

        // not sure if this will be for definate
        SystemProcess systemProcess = new SystemProcess();

        // enable the temperature sensor
        if (capture_temperature == true) {
            try {
                systemProcess.setContextMode("temperature");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        systemProcess.run();
        
        // enable the microphone sensor
        // TODO
//        else if(capture_atmosphere = true) {
//            try {
//                systemProcess.setContextMode("microphone");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
        // enable the accelerometer
        // TODO
//        else if(capture_motion) {
//            try {
//                systemProcess.setContextMode("accelerometer");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
        // enable the RGB light sensor
        // TODO
//        else if(capture_light) {
//            try {
//                systemProcess.setContextMode("light");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }

        // return a new instanceof
    }
}