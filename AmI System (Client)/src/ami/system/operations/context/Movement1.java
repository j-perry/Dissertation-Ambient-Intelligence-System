/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.operations.context;

//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioFactory;
//import com.pi4j.io.gpio.GpioPinDigitalInput;
//import com.pi4j.io.gpio.GpioPinDigitalOutput;
//import com.pi4j.io.gpio.PinMode;
//import com.pi4j.io.gpio.PinState;
//import com.pi4j.io.gpio.RaspiPin;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to create an instance of an ultrasonic transceiver sensor
 * 
 * @author Jonathan Perry
 */
public class Movement1 implements ISession {
    
    
    /*                 DEVICE INTERFACE
     **************************************************/
//    private final GpioController ultraSonicTransceiverCtrl;    
//    private final GpioPinDigitalOutput trigPin;
//    private final GpioPinDigitalInput echoPin;
    
    private final Gpio ultraSonicTransceiver;
    
    
    /*                 PROPERTIES
     *********************************************/
    private final int TRIG = 23; // input pin
    private final int ECHO = 24; // output pin
    
        
    public Movement1() {
//        ultraSonicTransceiverCtrl = GpioFactory.getInstance();
////        ultraSonicTransceiverCtrl.setMode(PinMode.PWM_OUTPUT, pin);
//        ultraSonicTransceiverCtrl.setMode(GpioPin., pin);
//        trigPin = null;
//        echoPin = null;
        
        ultraSonicTransceiver = null;
    }
    
    /**
     * Setup the ultrasonic transceiver
     */
    @Override
    public void setup() {
        if(Gpio.wiringPiSetup() == -1) {
            System.out.println("> GPIO SETUP FAILED");
        }
               
        
        // set GPIO # as the OUTPUT trigger
//        GpioUtil.export(/* GPIO PIN */, GpioUtil.DIRECTION_OUT);
        GpioUtil.getEdgeDetection(ECHO);
        Gpio.pinMode(ECHO, Gpio.OUTPUT);
        Gpio.pullUpDnControl(ECHO, Gpio.LOW);
        
        // set GPIO # as the INPUT trigger 
//        GpioUtil.export(/* GPIO PIN */, GpioUtil.DIRECTION_IN);
        GpioUtil.getEdgeDetection(TRIG);
        Gpio.pinMode(TRIG, Gpio.INPUT);
        Gpio.pullUpDnControl(TRIG, Gpio.LOW);
              
        
        
        // sleep for 2 seconds
        try {
            Thread.sleep((2 * 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Movement1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        for(;;) {
            Gpio.digitalRead(16);
//        }
            
        
            
    //        // output (send signal to object)
    //        GpioPinDigitalOutput trigPin = 
    //                ultraSonicTransceiverCtrl.provisionDigitalOutputPin(RaspiPin.GPIO_16);
    //        
    //        // input (receieves echo from ultra sonic wave to indicate the proximity between 
    //        // the object and the ultra-sonic transceiver itself
    //                ultraSonicTransceiverCtrl.provisionDigitalInputPin(RaspiPin.GPIO_16);
    //                ultraSonicTransceiverCtrl.provisionDigitalInputPin(RaspiPin.GPIO_16);  
    }
    
    /**
     * First-point of call when creating a movement class
     */
    @Override
    public boolean initialise() {
        boolean connected = false;
        
        // check it is connected
//        if() {
//            System.out.println("Ultrasonic Transceiver is online.");
//            connected = true;
//        } else {
//            System.out.println("Could not connect to the Ultrasonic Transceiver.");
//            connected = false;
//        }
        
        return connected;
    }
        
    /**
     * Get the distance between the sensor and the user
     */
    @Override
    public int readValue() {
        
        
        return 1;
    }
        
    /**
     * WE WON'T REQUIRE THIS INHERITED ABSTRACT METHOD FOR THIS SENSOR
     */
    @Override
    public void active() {        
    }
    
    /**
     * WE WON'T REQUIRE THIS INHERITED ABSTRACT METHOD FOR THIS SENSOR
     */
    @Override
    public void standby() {
    }
    
    /**
     * WE WON'T REQUIRE THIS INHERITED ABSTRACT METHOD FOR THIS SENSOR
     * @return 
     */
    @Override
    public byte readRegister(int addressToRead) {
        return 0;
    }
    
    /**
     * WE WON'T REQUIRE THIS INHERITED ABSTRACT METHOD FOR THIS SENSOR
     */
    @Override
    public void writeRegister() {        
    }
    
}