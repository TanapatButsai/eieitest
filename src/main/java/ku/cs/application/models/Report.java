package ku.cs.application.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Report {
    private String reporterUsername;
    private String reportedUsername;
    private String reason;
    private String objectID;
    private String reportTime;

    public Report(String reporterUsername,String reportedUsername, String reason, String objectID ) {
        this.reporterUsername = reporterUsername;
        this.reason = reason;
        this.objectID = objectID;
        this.reportedUsername = reportedUsername;
        setTime();
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public void setTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
        this.reportTime = now.format(formatter);
    }

    @Override
    public String toString() {
        return "Report{" +
                "reporterUsername='" + reporterUsername + '\'' +
                ", reportedUsername='" + reportedUsername + '\'' +
                ", reason='" + reason + '\'' +
                ", objectID='" + objectID + '\'' +
                ", reportTime='" + reportTime + '\'' +
                '}';
    }
}
