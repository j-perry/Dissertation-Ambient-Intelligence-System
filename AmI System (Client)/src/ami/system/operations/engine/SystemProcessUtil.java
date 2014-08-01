package ami.system.operations.engine;

import java.net.*;
import java.text.*;
import java.util.*;

/**
 * Utility class for use whilst processing system activity to hold and manipulate
 * system meta-data
 * 
 * @author Jonathan Perry
 */
public class SystemProcessUtil {

    // instance variables
    private Calendar cal;
    
    // properties
    private int hours;
    private int minutes;
    private int seconds;
    private int noSensors;

    public SystemProcessUtil() {
    }

    /**
     * Checks whether the system is in bounds (9.30+ PM) in operation terms
     *
     * @return true if it is in bounds, false if not
     */
    public boolean beforeHours() {
        boolean result;
        final double beforeHours = 9.30;
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
        DecimalFormat df = new DecimalFormat("#.##");
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
     * Checks whether the system is out of bounds (17.30 PM >) in operation
     * terms
     *
     * @return true if it is out of bounds, false if not
     */
    public boolean afterHours() {
        boolean result;
        final double afterHours = 17.30;
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
        DecimalFormat df = new DecimalFormat("#.##");
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

        double time;
        double timeTerminate = 17.30;

        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        double d_hour = (double) hour;
        double d_minute = (double) minute / 100;

        time = d_hour;
        time += d_minute;

        // if the current time is 17.30 PM
        if (time == timeTerminate) {
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
        return hours;
    }

    /**
     * Set's the number of hours accumulated
     *
     * @param hours
     */
    public void setAccumulatedHours(int hours) {
        this.hours = hours;
    }

    /**
     * Returns the number of minutes accumulated
     *
     * @return
     */
    public int getAccumulatedMinutes() {
        return minutes;
    }

    /**
     * Set's the number of minutes accumulated
     *
     * @param minutes
     */
    public void setAccumulatedMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Returns the number of seconds accumulated
     *
     * @return
     */
    public int getAccumulatedSeconds() {
        return seconds;
    }

    /**
     * Set's the number of seconds accumulated
     *
     * @param seconds
     */
    public void setAccumulatedSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Returns the number of hours and minutes accumulated during the operation
     * of the system.
     *
     * @return
     */
    public String getAccumulatedDuration() {
        StringBuilder duration = new StringBuilder();
        
        return "";
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
     * Returns the client device MAC address. This will be used to persist data
     * to the database for presentation on the web application.
     *
     * @return the agent's MAC address
     */
    public String getDeviceAddress() {
        InetAddress ipAddr;
        StringBuilder mac = new StringBuilder();

        try {
            ipAddr = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ipAddr);
            byte[] macAddr = network.getHardwareAddress();

            for (int i = 0; i < macAddr.length; i++) {
                mac.append(String.format("%02X%s", macAddr[i], (i < macAddr.length - 1) ? "-" : ""));
            }

        } catch (UnknownHostException | SocketException ex) {
            ex.printStackTrace();
        }
        
        return mac.toString();
    }
}