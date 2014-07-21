/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.operations.menu;

import java.util.Scanner;

/**
 *
 * @author Jonathan Perry
 */
public class About {
    
    public About() {
        
    }
    
    /**
     * Display info about the application
     */
    public void info() {
       String message = "----------------------------------------------------" +
                        "\n\n" +
                        "About" +
                        "\n\n" +
                        "An Ambient Intelligence Learning System developed as part of an MSc Advanced Computer Science, " +
                        "University of Sussex (2014)." +
                        "\n\n" +
                        "The project seeks to explore the efficiency of workplace activity, captured through qualities employees display each day in their positions as to enhance performance as a collective team."; 
       
       System.out.println(message);
       
       // until the user has pressed ENTER
       pauseApplication();
       
       AmISystemMenu ami = new AmISystemMenu();
       ami.display();
       ami.input();
    }
    
    /**
     * Pauses our application
     */
    private void pauseApplication() {
        System.out.println("\n" +
                           "> Press Enter to Continue");
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }
    
}
