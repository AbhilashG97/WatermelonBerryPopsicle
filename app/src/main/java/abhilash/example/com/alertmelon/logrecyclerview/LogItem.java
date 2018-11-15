package abhilash.example.com.alertmelon.logrecyclerview;

public class LogItem {

    private String logData;
    private String logTimeStamp;

    public LogItem(String logData, String logTimeStamp) {
        this.logData = logData;
        this.logTimeStamp = logTimeStamp;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }

    public String getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(String logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }
}
