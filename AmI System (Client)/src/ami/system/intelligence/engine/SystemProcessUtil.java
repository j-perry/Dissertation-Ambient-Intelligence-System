/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.intelligence.engine;

// Java APIs
import java.net.*;
import java.text.*;
import java.util.*;

// internal classes
import ami.system.operations.context.Temperature;

/**
 * Utility class for use whilst processing system activity to hold and manipulate
 * system meta-data
 * 
 * @author Jonathan Perry
 */
public class SystemProcessUtil {
    
    // this constant variable should be kept initialised at 17.30
    public static final double terminate_time = 18.21;

    // instance variables
    private Calendar cal;
    
    // initialisation properties
    private int startHour,
                startMinute,
                startSeconds;
    
    // properties
    private int hours,
                minutes,
                seconds;
    
    private int sessionId;
    private int noSensors;

    /**
     * 
     * @param startHour    the hour the system began
     * @param startMinute  the minute in the hour the system began
     * @param startSeconds the seconds in the hour the system began
     * @param sessionId    the session ID
     */
    public SystemProcessUtil(int startHour, int startMinute, int startSeconds, int sessionId) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.startSeconds = startSeconds;
        this.sessionId = sessionId;
    }
    
    public SystemProcessUtil() {
        
    }

    /**
     * Checks whether the system is in bounds (9.00+ PM) in operation terms
     *
     * @return true if it is in bounds, false if not
     */
    public boolean beforeHours() {
        boolean result;
        final double beforeHours = 9.00;
        cal = new GregorianCalendar();

        Double actualTime;
        double d_hour;
        double d_minute = 0.0;

        // get the current hour and minute in the hour
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // convert to doubles
        d_hour = (double) hour;
        d_minute += (double) minute / 100;

        // compute products
        actualTime = d_hour;
        actualTime += d_minute;

        // convert to .2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        actualTime = Double.valueOf(df.format(actualTime));

        // if the time equals 9.30 AM or thereafter
        if (actualTime >= beforeHours) {
            // inform the application to (officially) start the system
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Checks whether the system is out of bounds (17.30 PM >) in operation terms
     *
     * @return true if it is out of bounds, false if not
     */
    public boolean afterHours() {
        boolean result;
        final double afterHours = 20.30;
        cal = new GregorianCalendar();
        
        Double actualTime;
        double d_hour;
        double d_minute = 0.0;
        
        // get the current hour and minute in the hour
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // convert to doubles
        d_hour = (double) hour;
        d_minute += (double) minute / 100;

        // compute products
        actualTime = d_hour;
        actualTime += d_minute;
        
        // convert to .2 decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        actualTime = Double.valueOf(df.format(actualTime));

        // if the time is greater than or equal to 17.30 PM
        if (actualTime >= afterHours) {
            // inform the application to terminate
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    /**
     * Checks the current time. If it is 17.30 PM, stop system operation
     *
     * @return
     */
    public boolean checkTimeBounds() {
        boolean terminate;
        cal = new GregorianCalendar();
        
        double test = terminate_time;

        double time;
//        double timeTerminate = 22.30; // 17.30
        double timeTerminate = test;

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        double d_hour = (double) hour;
        double d_minute = (double) minute / 100;

        time = d_hour;
        time += d_minute;
        
        // if the current time is 17.30 PM
        if (time == timeTerminate || time > timeTerminate) {
            // inform the system to terminate
            terminate = true;
        } else {
            // inform the system to continue running
            terminate = false;
        }

        return terminate;
    }

    /**
     * Returns the number of hours accumulated
     *
     * @return
     */
    public int getAccumulatedHours() {
        cal = new GregorianCalendar();
        int hours;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        hours = hour - startHour;
        
        return hours;
    }
    
    /**
     * Validation method for hours accumulated
     * @return 
     */
    private void adjustAccumulatedHours() {
        cal = new GregorianCalendar();
        
        // reduce the hour by one
        startHour--;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        
        this.hours = (hour - startHour);
    }
    
    /**
     * Returns the number of minutes accumulated
     *
     * @return
     */
    public int getAccumulatedMinutes() {
        cal = new GregorianCalendar();
        
        // what if...
        // the current minute in the hour is '12'
        // and the minute on the hour it started was '45'?
        // we would need to reduce no. of accumulated hours by 1
        int minute = cal.get(Calendar.MINUTE);
        minutes = minute - startMinute;
        
        if(minutes < 0) {
            int left = minutes; // suppose 12 - 45 = -33 seconds
            adjustAccumulatedHours();
            minutes = (60 - left);
        }
        
        return minutes;
    }
    
    /**
     * Validation method for minutes accumulated
     * @return 
     */
    private void adjustAccumulatedMinutes() {
        cal = new GregorianCalendar();
        
        // reduce minutes by one
        startMinute--;
        int minute = cal.get(Calendar.MINUTE);
        
        this.minutes = (minute - startMinute);
    }
    
    /**
     * Returns the number of seconds accumulated
     *
     * @return
     */
    public int getAccumulatedSeconds() {
        cal = new GregorianCalendar();
        
        // what if...
        // the current second in the minute is '12'
        // and the second on the hour it started was '31'?
        // we would need to reduce no. of accumulated minutes by 1
        int second = cal.get(Calendar.SECOND);
        seconds = second - startSeconds;
        
        if(seconds < 0) {
            int left = seconds; // suppose 12 - 45 = -33 seconds
            adjustAccumulatedMinutes();
            seconds = (60 - left);
        }
        
        return seconds;
    }
    
    /**
     * 
     * @param sessionId 
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    
    /**
     * 
     * @return 
     */
    public int getSessionId() {
        return sessionId;
    }
    
    /**
     * Returns the number of hours and minutes accumulated during the operation of the system.
     *
     * @return HH:mm:ss
     */
    public String getAccumulatedDuration() {
        StringBuilder duration = new StringBuilder();
        
        // hours
        duration.append(String.valueOf(getAccumulatedHours() ));
        duration.append(":");
        
        // minutes
        duration.append(String.valueOf(getAccumulatedSeconds() ));
        duration.append(":");
        
        // seconds
        duration.append(String.valueOf(getAccumulatedSeconds() ));
        
        return duration.toString();
    }

    /**
     * Returns the total number of sensors connected to the ambient intelligence
     * learning system on the client device.
     *
     * @return
     */
    public int getNoSensors() {
        return noSensors;
    }

    /**
     * Set's the number of sensors connected to the ambient intelligence
     * learning system on the client device.
     *
     * @param number
     */
    public void setNoSensors(int noSensors) {
        this.noSensors = noSensors;
    }

    /**
     * Returns the client (host) name. This will be used to persist data
     * to the database for presentation on the web application.
     *
     * @return the agent's host name
     */
    public String getDeviceName() {
        InetAddress ipAddr;
        StringBuilder hostName = new StringBuilder();

        try {
            ipAddr = InetAddress.getLocalHost();
            hostName.append(ipAddr.getHostName() );
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        
        return hostName.toString();
    }
    
    /**
     * Internal class handles the starting time. Note: do not implement any date
     * related methods. We will process this on the resources level when persisting MySQL
     * data, which will use the Timestamp function.
     */
    public static class SystemTime {
        
        private GregorianCalendar cal;
        
        public SystemTime() {
            
        }
        
        /**
         * Gets the current hour in the day
         * @return 
         */
        public int getCurrentHour() {
            cal = new GregorianCalendar();
            return (int) cal.get(Calendar.HOUR_OF_DAY); // 0 - 23           
        }
        
        /**
         * Gets the current minute in the hour
         * @return 
         */
        public int getCurrentMinute() {
            cal = new GregorianCalendar();
            return (int) cal.get(Calendar.MINUTE);
        }
        
        /**
         * Gets the current second in the minute
         * @return 
         */
        public int getCurrentSeconds() {
            cal = new GregorianCalendar();
            return (int) cal.get(Calendar.SECOND);
        }
                
    }
    
    /**
     * Internal class used for device-related utility functionality
     */
    public static class SystemDevices {
        
        private boolean connected;
        
        public SystemDevices() {
            
        }
        
        /**
         * Checks whether the temperature sensor is connected to the agent
         * @return 
         */
        public boolean temperatureSensorConnected() {
            Temperature temp = new Temperature();
            
            if(temp.initialise() == true) {
                connected = true;                
            } else {
                connected = false;
            }
            
            return connected;
        }
        
    }
}