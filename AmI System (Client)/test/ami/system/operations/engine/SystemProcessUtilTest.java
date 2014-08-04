


package ami.system.operations.engine;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan Perry
 */
public class SystemProcessUtilTest {
    
    private GregorianCalendar cal;
    
    public SystemProcessUtilTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of beforeHours method, of class SystemProcessUtil.
     */
    @Test
    public void testBeforeHours() {
        System.out.println("beforeHours");
        SystemProcessUtil instance = new SystemProcessUtil();
        boolean expResult = false;
        boolean result = instance.beforeHours();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of afterHours method, of class SystemProcessUtil.
     */
    @Test
    public void testAfterHours() {
        System.out.println("afterHours");
        SystemProcessUtil instance = new SystemProcessUtil();
        boolean expResult = false;
        boolean result = instance.afterHours();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTimeBounds method, of class SystemProcessUtil.
     */
    @Test
    public void testCheckTimeBounds() {
        System.out.println("checkTimeBounds");
        SystemProcessUtil instance = new SystemProcessUtil();
        boolean expResult = false;
        boolean result = instance.checkTimeBounds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test gets the current hour in the day
     */
    @Test
    public void testGetCurrentHour() {
        int expecting = 0; // hour
        cal = new GregorianCalendar();
        int actual = cal.get(Calendar.HOUR_OF_DAY); // 0 - 23
        
        assertEquals(expecting, actual);
    }
    
    /**
     * Test gets the current minute in the hour
     */
    @Test
    public void testGetCurrentMinute() {
        int expecting = 58; // minute
        cal = new GregorianCalendar();
        int actual = cal.get(Calendar.MINUTE);
        
        assertEquals(expecting, actual);
    }
    
    /**
     * Test calculates the number of hours accumulated since starting
     */
    @Test
    public void testGetAccumulatedHours() {
       cal = new GregorianCalendar();
       int expecting = 3;
       
       int startHour = 14;
       int hour = cal.get(Calendar.HOUR_OF_DAY);
       int actual = hour - startHour;
       
       assertEquals(expecting, actual);
    }
    
    /**
     * Test calculates the number of minutes accumulated since starting
     */
    @Test
    public void testGetAccumulatedMinutes() {
        cal = new GregorianCalendar();
        int expecting = 4;
        
        int startMinute = 41;
        int minute = cal.get(Calendar.MINUTE);
        int actual = minute - startMinute;
        
        assertEquals(expecting, actual);
    }
    
}