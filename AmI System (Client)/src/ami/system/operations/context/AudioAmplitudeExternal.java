


package ami.system.operations.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jonathan Perry
 */
public class AudioAmplitudeExternal {

    public AudioAmplitudeExternal() {
        
    }
    
    public double readValue() {
        String output = null;
        double amplitudeValue = 0.0;

        try {
            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec("sudo python amplitude.py");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the temperature output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((output = stdInput.readLine()) != null) {
                amplitudeValue = Double.valueOf(output);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((output = stdError.readLine()) != null) {
                System.out.println(output);
            }

            System.exit(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }

        return amplitudeValue;
    }
    
}
