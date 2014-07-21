


package ami.system;

import ami.system.operations.menu.AmISystemMenu;

/**
 * Initialises our Ambient Intelligence Learning System
 * @author Jonathan Perry
 */
public class AmISystem {
    
    public AmISystem() {
        System.out.println("Ambient Intelligence Learning System");
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
