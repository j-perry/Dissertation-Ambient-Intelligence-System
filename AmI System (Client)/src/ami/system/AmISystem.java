


package ami.system;

//import ami.system.operations.menu.AmISystemMenu;
import ami.system.operations.engine.SystemProcess;

/**
 * Initialises our Ambient Intelligence Learning System
 * @author Jonathan Perry
 */
public class AmISystem {
    
    public AmISystem() {
        System.out.println("---------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println("\t");
        System.out.println("Ambient Intelligence Learning System");
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------");        
        System.out.println();
    }
    
    public void init() {
        // attempt to auto start and run a new system process
        // this is reliant on conditions set between 9:00 - 17:30
        new SystemProcess().run();
    }
    
    /*
    public void init() {
        menu();
    }
    
    public void menu() {
        AmISystemMenu menu = new AmISystemMenu();
        menu.input();
    }
    */
}