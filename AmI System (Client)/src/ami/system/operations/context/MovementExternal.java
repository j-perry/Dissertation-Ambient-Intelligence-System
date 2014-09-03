


package ami.system.operations.context;

import java.io.*;

/**
 * Reads an ultrasonic transceiver value from a Python script over an I2C data bus
 * @author Jonathan Perry
 */
public class MovementExternal {

    public MovementExternal() {

    }

    public String readValue() {
        String output = null;
        String ultrasonicValue = null;

        try {
            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("sudo python /home/pi/projects/AmI_System/movement.py");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the temperature output from the command
            while ((output = stdInput.readLine()) != null) {
                ultrasonicValue = output;
            }

            // read any errors from the attempted command
            while ((output = stdError.readLine()) != null) {
                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return ultrasonicValue;
    }
}