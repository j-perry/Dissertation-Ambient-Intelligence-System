/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jonathan Perry
 */
public class AmISystemMenu {
    
    public AmISystemMenu() {
        
    }
    
    public void display() {
        System.out.println("--------------");
        System.out.println("Menu");
        System.out.println("--------------\n");
        
        final String option_one = "1. New Session";
        final String option_two = "2. Session History";
        final String option_three = "3. Settings";
        final String option_four = "4. Exit";
        
        System.out.println(option_one);
        System.out.println(option_two);
        System.out.println(option_three);
        System.out.println(option_four);
        
        System.out.println();
    }
    
    public void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.print("Input choice: ");
            
            int choice = Integer.valueOf(bf.readLine() );
            parseChoice(choice);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    private void parseChoice(int choice) {
        switch(choice) {
            case 1:
                // AmISystemSession session = new AmISystemSession();
                break;
            case 2:
                // AmISystemSessionHistory session = new AmISystemSessionHistory();
                break;
            case 3:
                // AmISystemSettings session = new AmISystemSettings();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                System.out.println();
                display();
                input();
                break;
        }
    }
    
}
