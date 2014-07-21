/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.main;

/**
 *
 * @author Jonathan Perry
 */
public class AmISystemClient {
    
    /**
     * Root of our application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create a new instance of our Ambiente Intelligence Learning System
        new AmISystem().init();
    }
}
