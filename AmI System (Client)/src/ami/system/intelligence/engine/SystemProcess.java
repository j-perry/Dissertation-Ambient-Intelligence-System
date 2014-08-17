/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.intelligence.engine;

// libraries
import ami.system.operations.menu.AmISystemMenu;
import ami.system.operations.context.*;
import ami.system.intelligence.engine.ils.IncrementalSynchronousLearning;
import ami.system.operations.resources.database.ClientInfo;

// Java APIs
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Stack;

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

        // used to check it hasn't been inserted into the stack for insertion
        // if the value has, don't write it.
        Stack<Double> hourlyValues = new Stack<>();


        /*      
         *      This will be our main application loop.
         * 
         *      The application will not terminate until 17:30 PM.
         *      The application however will also not start unless the time is 9:00am or thereafter.
         * 
         *************************************************************************************************/
        
        
        // if it is between Monday and Friday, continue...
        if (new SystemProcessUtil().withinWeekdays() == true) {
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
                util = new SystemProcessUtil(
                        utilTime.getCurrentHour(), // hour
                        utilTime.getCurrentMinute(), // minute
                        utilTime.getCurrentSeconds(), // second
                        sessionId // session ID
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
                        clientInfo.persist(
                                util.getAccumulatedHours(),
                                util.getAccumulatedMinutes(),
                                util.getDeviceName(),
                                noSensors);
                        clientInfo.close();
                    } // else, continue running the system
                    else {

                        // ... temperature value
                        // if the current minute is greater than the past minute
                        // (NB: what happens if the current minute is 0 and the past minute is 59?)
                        if (utilTime.getCurrentMinute() > prevMinute) {
                            prevMinute = utilTime.getCurrentMinute();

                            GregorianCalendar cal = new GregorianCalendar();
                            double time = 0.0;
                            double hour = cal.get(Calendar.HOUR_OF_DAY);
                            double minute = (Double.valueOf(new SimpleDateFormat("mm").format(new Date()).toString()) / 100);

                            time = (double) hour;
                            time += minute;

                            DecimalFormat df = new DecimalFormat("0.00");
                            time = Double.valueOf(df.format(time));

                            if (!hourlyValues.contains(time)) {
                                hourlyValues.add(time);

                                /**
                                 * This is our main loop
                                 *
                                 * First, see if we need to run an initial
                                 * monitoring phase. If not, the first method
                                 * call inside this method will get ignored, and
                                 * a full system process will execute
                                 */
                                isl.run(
                                        util.getSessionId(), // session ID
                                        util.getDeviceName(), // hostname
                                        getTemperature(time), // temperature
                                        Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY)), // hour
                                        Integer.valueOf(cal.get(Calendar.MINUTE)));     // minute

                            }
                        } else if (utilTime.getCurrentMinute() == 0) {
                            prevMinute = utilTime.getCurrentMinute(); // reset it

                            GregorianCalendar cal = new GregorianCalendar();
                            double time = 0.0;
                            double hour = cal.get(Calendar.HOUR_OF_DAY);
                            double minute = (Double.valueOf(new SimpleDateFormat("mm").format(new Date()).toString()) / 100);

                            time = (double) hour;
                            time += minute;

                            DecimalFormat df = new DecimalFormat("0.00");
                            time = Double.valueOf(df.format(time));

                            // to make sure we don't keep writing duplicate values more than once!!!
                            if (!hourlyValues.contains(time)) {
                                hourlyValues.add(time);

                                /**
                                 * This is our main loop
                                 *
                                 * First, see if we need to run an initial
                                 * monitoring phase. If not, the first method
                                 * call inside this method will get ignored, and
                                 * a full system process will execute
                                 */
                                isl.run(
                                        util.getSessionId(), // session ID
                                        util.getDeviceName(), // hostname
                                        getTemperature(time), // temperature
                                        Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY)), // hour
                                        Integer.valueOf(cal.get(Calendar.MINUTE)));     // minute
                            }

                        }

                    }

                    // check whether to terminate the system process
                    if (run_application == false) {
                        System.out.print("System has finished for the day. ");
                        System.out.println();

                        // display the application menu
                        AmISystemMenu menu = new AmISystemMenu();
                        menu.input();
                    }

                }
            } else if (new SystemProcessUtil().beforeHours() == false) {
                System.out.println("It is before 9:00. Try again later.");

                // display the application menu
                AmISystemMenu menu = new AmISystemMenu();
                menu.input();
            }
        } // if it is Saturday or Sunday, don't start the system
        else {
            String msg = "> The system is only operational weekdays (Monday to Friday).\n> Please try again on those days.";
            System.out.println(msg);
            
            // display the application menu
            AmISystemMenu menu = new AmISystemMenu();
            menu.input();
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

        String strTime = new DecimalFormat("0.00").format(time).toString();

        // write a temperature value
        System.out.println(temperatureTitle + tempValue + "\t\t" + "Time: " + strTime);

        return tempValue;
    }

    /**
     * Returns the ultra-sonic transceiver distance (in Fahrenheit)
     *
     * @return
     */
    private double getUltrasonicDistance() {
        double usValue = 0.0;
        final String ultrasonicTitle = "Ultrasonic Transceiver Value: ";

        /*      Run The Ultrasonic Transceiver
         *******************************************/
        Movement usSensor = new Movement();
        usSensor.setup();
        usSensor.initialise();

        // get the distance
        usValue = usSensor.readValue();

        return usValue;
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
}