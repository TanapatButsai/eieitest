package ku.cs.application.models;

public class Ban {
    private String bannedID;
    private String user;
    private String bannedReason;

    public Ban(String bannedID, String user, String bannedReason) {
        this.bannedID = bannedID;
        this.user = user;
        this.bannedReason = bannedReason;
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

    public String toCSV() {
        String banrs = bannedReason.replace("\n", "\\[newline]")
                .replace("\"","\\[doublequote]")
                .replace(",","\\[comma]");
        return bannedID + "," +
                user + "," +
                banrs;
    }

}
