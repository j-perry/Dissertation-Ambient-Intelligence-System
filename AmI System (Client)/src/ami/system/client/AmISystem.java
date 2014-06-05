/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ami.system.client;

/**
 *
 * @author Jonathan Perry
 */
public class AmISystem {
    
    public AmISystem() {
        System.out.println("AmI System");
    }
    
    public void init() {
        menu();
    }
    
    public void menu() {
        AmISystemMenu menu = new AmISystemMenu();
        menu.display();
        menu.input();
        
        menu = null; // garbage collection...
    }
    
}
