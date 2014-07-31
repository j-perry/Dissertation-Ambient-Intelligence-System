

package ami.system.operations.engine;

import java.text.*;
import java.util.*;

/**
 *
 * @author Jonathan Perry
 */
public class SystemProcessUtil {

    private Calendar cal;

    public SystemProcessUtil() {
        
    }
    
    /**
     * Checks whether the system is in bounds (9.30 PM >) in operation terms
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
        if(time == timeTerminate) {
            // inform the system to terminate
            terminate = true;
        } else {
            // inform the system to continue running
            terminate = false;
        }

        return terminate;
    }
}