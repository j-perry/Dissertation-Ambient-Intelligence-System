/*
 * MSc Advanced Computer Science, University of Sussex
 * Jonathan Perry
 * Candidate No. 102235
 */
package ami.system.operations.context;

import com.pi4j.wiringpi.Gpio;

/**
 * Class used to create an instance of an ultrasonic transceiver sensor
 * Article: https://www.modmypi.com/blog/hc-sr04-ultrasonic-range-sensor-on-the-raspberry-pi
 * 
 * @author Jonathan Perry
 */
public class Movement implements ISession {
    
    /*                 GPIO PINS
     *********************************************/
    private final int TRIG = 23; // input
    private final int ECHO = 24; // output
    
    
    public Movement() {        
        
    }
    
    /**
     * Setup the ultrasonic transceiver
     */
    @Override
    public void setup() {
        // note: all comments in this method are what was provided in the 
        // setup tutorial for the ultra sonic transceiver written in Python using
        // the Raspberry Pi GPIO library.
        // 
        // Tutorial: https://www.modmypi.com/blog/hc-sr04-ultrasonic-range-sensor-on-the-raspberry-pi
        // Reference: http://wiringpi.com/reference/setup/
        int pulse_start = 0;
        int pulse_end = 0;
        int pulse_duration = 0;
        int distance = 0;
        
        // same as GPIO.setmode(GPIO.BCM in Python)
        if(Gpio.wiringPiSetupGpio() == -1) {
            System.out.println("> GPIO SETUP FAILED");
        } else {
            System.out.println("> GPIO SETUP SUCCESSFUL");
        }
           
        // GPIO.setup(TRIG,GPIO.OUT)
        // GPIO.setup(ECHO,GPIO.IN)
        Gpio.pinMode(TRIG, Gpio.OUTPUT);
        Gpio.pinMode(ECHO, Gpio.INPUT);
               
        // time.sleep(2)
        // (measured in seconds)
        Gpio.delay(2000);
        
        // GPIO.output(TRIG, False)
        Gpio.digitalWrite(TRIG, true);
        
        // time.sleep(0.00001)
        // (or thereabouts)
        Gpio.delay(1);
        
        // GPIO.output(TRIG, False)
        Gpio.digitalWrite(TRIG, false);
                
        // while GPIO.input(ECHO) == 0:
        // pulse_start = time.time()
        while(Gpio.digitalRead(ECHO) == 0) {
            // convert from milliseconds to seconds
            pulse_start = (int) ((System.currentTimeMillis() / 1000) % 60);
        }
        
        // while GPIO.input(ECHO) == 1:
        // pulse_end = time.time()
        while(Gpio.digitalRead(ECHO) == 1) {
            // convert from milliseconds to seconds
            pulse_end = (int) ((System.currentTimeMillis() / 1000) % 60);
        }
        
        // pulse_duration = pulse_end - pulse_start
        pulse_duration = pulse_end - pulse_start;
        
        // distance = pulse_duration * 17150
        distance = pulse_duration * 17150;
                
        // distance = round(distance, 2)
        // convert to 2 decimal places
        distance = Math.round(distance * 100) / 100;
        
        System.out.println("THE DISTANCE IS: " + distance);
    }
    
    /**
     * First-point of call when creating a movement class
     */
    @Override
    public boolean initialise() {
        boolean connected = false;
                
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