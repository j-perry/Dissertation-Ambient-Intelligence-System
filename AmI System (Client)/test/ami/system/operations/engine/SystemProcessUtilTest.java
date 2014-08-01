


package ami.system.operations.engine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonathan Perry
 */
public class SystemProcessUtilTest {
    
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
}