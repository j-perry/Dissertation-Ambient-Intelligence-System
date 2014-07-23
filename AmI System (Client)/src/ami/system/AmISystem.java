


package ami.system;

import ami.system.operations.menu.AmISystemMenu;

/**
 * Initialises our Ambient Intelligence Learning System
 * @author Jonathan Perry
 */
public class AmISystem {
    
    public AmISystem() {
        System.out.println();
        System.out.println("Ambient Intelligence Learning System");
        System.out.println();
    }
    
    public void init() {
        menu();
    }
    
    public void menu() {
        AmISystemMenu menu = new AmISystemMenu();
        menu.display();
        menu.input();
        
        // garbage collection...
        menu = null;
    }
    
}