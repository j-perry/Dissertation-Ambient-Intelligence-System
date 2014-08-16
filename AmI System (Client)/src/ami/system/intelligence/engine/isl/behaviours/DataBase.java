


package ami.system.intelligence.engine.isl.behaviours;

/**
 * Stores a fuzzy set based on the type of context identified by the system
 * @author Jonathan Perry
 */
public class DataBase {
    
    private int sessionId;
    private String hostname;
    
    private int value;
    private String type;
    private String linguisticType;
        
    private int hour;   
    private int minute;
    
    private String day;
    private String month;
    private int year;
    
    public DataBase() {
        
    }
    
    public DataBase(int value, String type, String linguisticType) {
        this.value = value;
        this.type = type;
        this.linguisticType = linguisticType;
    }
    
    public DataBase(int sessionId, String hostname, int hour, int minute, String day, String month, int year, int value, String type, String linguisticType) {
        this.sessionId = sessionId;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
                
        this.value = value;
        this.type = type;
        this.linguisticType = linguisticType;
    }
    
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }    
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public int getSessionId() {
        return sessionId;
    }
    
    public String getHostname() {
        return hostname;
    }
    
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
            
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setType(String type) {
        this.type = type;                
    }
    
    public String getType() {
        return type;
    }
    
    public void setLinguisticType(String linguisticType) {
        this.linguisticType = linguisticType;
    }
    
    public String getLinguisticType() {
        return linguisticType;
    }
    
}