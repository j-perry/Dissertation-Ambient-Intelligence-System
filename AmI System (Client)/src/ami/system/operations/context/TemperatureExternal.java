

package ami.system.operations.context;

import java.io.*;

/**
 * Reads a temperature value from a Python script over an I2C data bus
 * @author Jonathan Perry
 */
public class TemperatureExternal {
    
    public TemperatureExternal() {
        
    }
    
    public double readValue() {
        String output = null;
        double tempValue = 0.0;
 
        try {
            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("sudo python /home/pi/projects/AmI_System/temperature.py");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the temperature output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((output = stdInput.readLine()) != null) {
                tempValue = Double.valueOf(output);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((output = stdError.readLine()) != null) {
                System.out.println(output);
            }

        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        return tempValue;
    }
}
