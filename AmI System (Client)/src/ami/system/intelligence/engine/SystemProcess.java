/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.intelligence.engine;

// libraries
// internal classes
import ami.system.operations.menu.AmISystemMenu;
import ami.system.operations.context.*;
import ami.system.intelligence.engine.ils.IncrementalSynchronousLearning;
import ami.system.operations.resources.database.ClientInfo;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class used to create a new system process
 *
 * @author Jonathan Perry
 */
public class SystemProcess {

    // NB: for each sensor we will need an event-driven, callback function to detect
    // changes in user interaction
    // properties
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
    private void processHeading(int sessionId, int hour, int minute, int second) {
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
                + "\t"
                + "Session ID: " + sessionId 
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
        ClientInfo clientInfo = null;


        /*      
         *      This will be our main application loop.
         * 
         *      The application will not terminate until 17:30 PM.
         *      The application however will also not start unless the time is 9:00am or thereafter.
         * 
         *************************************************************************************************/


        // check it isn't 17:30 PM or thereafter
        if (new SystemProcessUtil().afterHours() == true) {
            String msg = "It is beyond 17:30pm. Try again tomorrow at 9.00 AM or thereafter.";

            System.out.println(msg);

            // display the application menu
            AmISystemMenu menu = new AmISystemMenu();
            menu.input();
        } // check it is 9:00 AM or thereafter
        else if (new SystemProcessUtil().beforeHours() == true) {
            String msg = "It is 9:00 AM or thereafter";

            System.out.println();
            System.out.println(msg);
            System.out.println();
            
            // create a new ClientInfo object
            clientInfo = new ClientInfo();
            clientInfo.open();            
            int sessionId = clientInfo.getSessionId();
            clientInfo.close();

            // Get the start time. We'll use this for calculation in SystemProcessUtil class later.
            SystemProcessUtil.SystemTime utilTime = new SystemProcessUtil.SystemTime();
            util = new SystemProcessUtil(sessionId,
                    utilTime.getCurrentHour(), // hour
                    utilTime.getCurrentMinute(), // minute
                    utilTime.getCurrentSeconds() // second
                    );

            // indicate the system has started (incl. displaying the time when it started)
            processHeading(
                    sessionId,
                    utilTime.getCurrentHour(), // hour
                    utilTime.getCurrentMinute(), // minute
                    utilTime.getCurrentSeconds() // second
                    );
            
            IncrementalSynchronousLearning isl = new IncrementalSynchronousLearning();
            int prevMinute = utilTime.getCurrentMinute();
            int prevSecond = utilTime.getCurrentSeconds();
            
            /**
             * Check the number of devices connected to the system
             *
             ******************************************************************
             */
            SystemProcessUtil.SystemDevices utilDevices = new SystemProcessUtil.SystemDevices();
            int noSensors = 0;

            // temperature sensor
            if (utilDevices.temperatureSensorConnected() == true) {
                noSensors++;
            }

            // assign number of sensors connected
            util.setNoSensors(noSensors);

            // display number of sensors connected
            System.out.println();
            System.out.println("noSensors: " + noSensors);
            
            // main application loop
            while (run_application) {
                
                // if it is 17.30, terminate the application
                if (util.checkTimeBounds() == true) {
                    run_application = false;
                    
                    // write data about the client and this session
                    clientInfo.open();
                    clientInfo.persist(util.getAccumulatedHours(), 
                            util.getAccumulatedMinutes(), 
                            util.getDeviceName(),
                            noSensors);                    
                    clientInfo.close();
                } 
                // else, continue running the system
                else {

                    // ... temperature value
                    // if the current minute is greater than the past minute
                    // (NB: what happens if the current minute is 0 and the past minute is 59?)
                    if (utilTime.getCurrentMinute() > prevMinute) {
                        prevMinute = utilTime.getCurrentMinute();
                        
                        GregorianCalendar cal = new GregorianCalendar();
                        double time = 0.0; 
                        double hour = cal.get(Calendar.HOUR_OF_DAY);
                        double minute = cal.get(Calendar.MINUTE);
                        
                        time = (double) hour;
                        time += (minute / 100);
                        
                        DecimalFormat df = new DecimalFormat("#.##");
                        time = Double.valueOf(df.format(time).toString() );
                        
                        // see if we need to run an initial monitoring phase
                        // if not, the method call inside this method will get ignored
                        isl.runInitialMonitoringPhase(
                                util.getSessionId(),                              // session ID
                                util.getDeviceName(),                             // hostname
                                getTemperature(time),                             // temperature
                                Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY)),  // hour
                                Integer.valueOf(cal.get(Calendar.MINUTE)) );     // minute

                        // this is our main loop
//                        isl.run(getTemperature());

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
    private int getTemperature(double time) {
        int tempValue;
        final String temperatureTitle = "Temperature Value: ";

        /*      Run The Temperature
         **********************************/
        Temperature temp = new Temperature();
        temp.setup();
        temp.initialise();
        tempValue = temp.readValue();

        // write a temperature value
        System.out.println(temperatureTitle + tempValue + "\t\t" + "Time: " + time);

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
        final String msg = "System will start in 10 seconds to ensure a network connection is established";

        try {
            System.out.println(msg);
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}