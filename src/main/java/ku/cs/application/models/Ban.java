package ku.cs.application.models;

public class Ban {
    private String bannedID;
    private String user;
    private String bannedReason;
    private String objectID;
    private String time;
    private boolean active;
    private int tryLogin;

    public Ban(String bannedID, String user, String bannedReason, String objectID, String time, boolean active) {
        this.bannedID = bannedID;
        this.user = user;
        this.bannedReason = bannedReason;
        this.objectID = objectID;
        this.time = time;
        this.active = active;
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

    public String toCSV() {
        String banrs = bannedReason.replace("\n", "\\[newline]")
                .replace("\"","\\[doublequote]")
                .replace(",","\\[comma]");
        return bannedID + "," +
                user + "," +
                banrs;
    }

}
