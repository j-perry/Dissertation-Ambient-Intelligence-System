/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.operations.menu;

/**
 *
 * @author Jonathan Perry
 */
public class AboutApp {
    
    public AboutApp() {
        
    }
    
    /**
     * 
     */
    public void display() {
       String message = "----------------------------------------------------" +
                        "\n\n" +
                        "About" +
                        "\n\n" +
                        "An Ambient Intelligence Learning System developed as part of an MSc Advanced Computer Science, " +
                        "University of Sussex (2014)." +
                        "\n\n" +
                        "The project seeks to explore the efficiency of workplace activity, captured through qualities employees display each day in their positions as to enhance performance as a collective team."; 
        
       System.out.println(message);
       return;
    }
    
}
