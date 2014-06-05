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
        
    }
    
    public void init() {
        System.out.println("AmISystem\n");
        menu();
    }
    
    public void menu() {
        AmISystemMenu menu = new AmISystemMenu();
        menu.display();
        menu.input();
    }
    
}
