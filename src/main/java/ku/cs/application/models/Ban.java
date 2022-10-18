package ku.cs.application.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Ban {
    private String bannedID;
    private String user;
    private String bannedReason;
    private String objectID;
    private String time;
    private boolean active;
    private int tryLogin;
    private String request;

    public Ban(String bannedID, String user, String bannedReason, String objectID, String time, boolean active,int tryLogin,String request) {
        this.bannedID = bannedID;
        this.user = user;
        this.bannedReason = bannedReason;
        this.objectID = objectID;
        this.time = time;
        this.active = active;
        this.tryLogin = tryLogin;
        this.request = request;
    }
    public Ban(String bannedID, String user, String bannedReason, String objectID, boolean active) {
        this.bannedID = bannedID;
        this.user = user;
        this.bannedReason = bannedReason;
        this.objectID = objectID;
        this.setTime();
        this.active = active;
        this.request = "none";
    }
    public String getBannedID() {
        return bannedID;
    }

    public String getUser() {
        return user;
    }

    public String getBannedReason() {
        return bannedReason;
    }
    public String getObjectID() {return  objectID;}

    public void setTime(String time) {
        this.time = time;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String toCSV() {
        String banrs = bannedReason.replace("\n", "\\[newline]")
                .replace("\"","\\[doublequote]")
                .replace(",","\\[comma]");
        return bannedID + "," +
                user + "," +
                banrs+ "," +
                objectID + "," +
                time + "," +
                Boolean.toString(isActive()) + "," +
                Integer.toString(tryLogin) + "," +
                request;
    }

    public void setBannedID(String bannedID) {
        this.bannedID = bannedID;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setBannedReason(String bannedReason) {
        this.bannedReason = bannedReason;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getTime() {
        return time;
    }

    public void setTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
        this.time = (now.format(formatter));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTryLogin() {
        return tryLogin;
    }

    public void setTryLogin(int tryLogin) {
        this.tryLogin = tryLogin;
    }
}
