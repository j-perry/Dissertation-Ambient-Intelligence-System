package ami.system.operations.engine;

import ami.system.operations.menu.AmISystemMenu;
import ami.system.operations.context.*;
import ami.system.operations.engine.isl.IncrementalSynchronousLearning;
import com.pi4j.io.i2c.*;

/**
 *
 * @author Jonathan Perry
 */
public class SystemProcess {

    private I2CBus bus;
    private I2CDevice accelSensor; // event-driven (need a callback function)
    private SystemProcessUtil util;

    public SystemProcess() {
    }

    /**
     * Make it clear the system has started and at what time
     *
     * @param hour
     * @param minute
     * @param second
     */
    private void processHeading(int hour, int minute, int second) {
        // used for formatting
        String strMinute = "",
                strSecond = "";

        // format the minute
        if (minute < 10) {
            strMinute = "0" + minute;
        } else {
            strMinute = String.valueOf(minute);
        }

        // format the second
        if (second < 10) {
            strSecond = "0" + second;
        } else {
            strSecond = String.valueOf(second);
        }

        final String heading = "\n"
                + "---------------------------------------------"
                + "\n\n"
                + "\t"
                + "Started new system: " + hour + ":" + strMinute + ":" + strSecond
                + "\n\n"
                + "---------------------------------------------"
                + "\n";

        System.out.println(heading);
    }

    /**
     * This is our 2nd root point and runs a new system process
     */
    public void run() {
        boolean run_application = true;
        
        
        /*      
         *      This will be our main application loop.
         * 
         *      The application will not terminate until 17:30 PM.
         *      The application however will also not start unless the time is 9:30am or thereafter.
         * 
         *************************************************************************************************/


        // check it isn't 17:30 PM or thereafter
        if (new SystemProcessUtil().afterHours() == true) {
            System.out.println("It is beyond 17:30pm. Try again tomorrow at 9.30 AM or thereafter.");
            
            // display the application menu
            AmISystemMenu menu = new AmISystemMenu();
            menu.input();
        } // check it is 9:00 AM or thereafter
        else if (new SystemProcessUtil().beforeHours() == true) {
            System.out.println();
            System.out.println("It is 9:30 AM or thereafter");
            System.out.println();

            // Get the start time. We'll use this for calculation in SystemProcessUtil class later.
            SystemProcessUtil.SystemTime utilTime = new SystemProcessUtil.SystemTime();
            util = new SystemProcessUtil(utilTime.getCurrentHour(), // hour
                    utilTime.getCurrentMinute(), // minute
                    utilTime.getCurrentSeconds() // second
                    );

            // indicate the system has started (incl. displaying the time when it started)
            processHeading(utilTime.getCurrentHour(), // hour
                    utilTime.getCurrentMinute(), // minute
                    utilTime.getCurrentSeconds() // second
                    );

            IncrementalSynchronousLearning isl = new IncrementalSynchronousLearning();
            int prevMinute = utilTime.getCurrentMinute();
            int prevSecond = utilTime.getCurrentSeconds();
            
            /**
             *      Check the number of devices connected to the system
             *
             *******************************************************************/
            SystemProcessUtil.SystemDevices utilDevices = new SystemProcessUtil.SystemDevices();
            int noSensors = 0;
            
            // temperature sensor
            if (utilDevices.temperatureSensorConnected() == true) {
                noSensors++;
            }

            // assign number of sensors connected
            util.setNoSensors(noSensors);
            
            // display number of sensors connected
            System.out.println("noSensors: " + noSensors);

            // main application loop
            while (run_application) {

                // if it is 17.30, terminate the application
                if (util.checkTimeBounds() == true) {
                    run_application = false;
                } // else, continue running the system
                else {

                    // ... temperature value
                    // if the current minute is greater than the past minute
                    // (nb: what happens if the current minute is 0 and the past minute is 59?)
                    if (utilTime.getCurrentMinute() > prevMinute) {
                        prevMinute = utilTime.getCurrentMinute();
                        
                        //  Parse temperature data to our incremental learning system
                        isl.parseTemperatureValue(getTemperature());

                        //
                        //  REPEAT FOR OTHER SENSORS
                        //
                        

                    } else if (utilTime.getCurrentMinute() == 0 && prevMinute == 59) {
                        prevMinute = 0; // reset it                        
                    }

                }

                // check whether to terminate the system process
                if (run_application == false) {
                    System.out.print("System has finished for the day. ");
                    
                    // display the application menu
                    AmISystemMenu menu = new AmISystemMenu();
                    menu.input();
                }

            }
        }
    }
    
    /**
     * Returns the current temperature (in Fahrenheit)
     *
     * @return
     */
    private int getTemperature() {
        int tempValue;
        final String temperatureTitle = "Temperature Value: ";

        /*      Run The Temperature
         **********************************/
        Temperature temp = new Temperature();
        temp.setup();
        temp.initialise();
        tempValue = temp.readValue();

        // write a temperature value
        System.out.println(temperatureTitle + tempValue);
        
        return tempValue;
    }

    /**
     * Stops the operation of the ambient intelligence learning system
     */
    public void stop() {
    }

    /**
     * Pauses the operation of the ambient intelligence learning system
     */
    public void pause() {
    }

    /**
     * Delays the operation of the ambient intelligence learning system defined
     * in milliseconds
     *
     * @param milliseconds
     */
    public void delay(int seconds) {
        int milliseconds = (seconds * 1000);
        
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
//        
//        
//        // accelerometer
//        if(accelSensor != null) {
//            
//            // NB: just capture data once! also, remember we'll need an event-driven approach
//            try {
//                // X, Y, Z
//                accelX = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//                accelY = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//                accelZ = (short) accelSensor.read(accelAddr, buffer, 0, 1);
//
//                // we'll do some correction, based on sensitivity (inaccuracy) of the device
//                // source from the data sheet
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }