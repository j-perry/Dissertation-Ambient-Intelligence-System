/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.client;

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
        final String option_four = "3. Exit";
        
        System.out.println(option_one);
        System.out.println(option_two);
        System.out.println(option_three);
        System.out.println(option_four);
    }
    
    public void input() {
        
    }
    
}
