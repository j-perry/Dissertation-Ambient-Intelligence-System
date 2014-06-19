/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.client;

import ami.system.core.*;
import ami.system.database.Temperature;

import java.io.*;
import java.sql.SQLException;

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
        
        final String option_one   = "> 1. New Session";
        final String option_two   = "> 2. Settings";
        final String option_three = "> 3. Exit";
        
        System.out.println(option_one);
        System.out.println(option_two);
        System.out.println(option_three);
        
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
                Session session = new Session();
                session.displayOptions();
                session.getOptionInput();
            case 2:
                Settings settings = new Settings();
                break;
            case 3:
                Temperature temp = new Temperature();
                temp.open();
                
                try {
                    temp.insert(56);
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
                finally {
                    temp.close();
                }
                
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
