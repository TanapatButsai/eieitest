package ku.cs.application.models;

public class OfficerID {
    private String officerID;
    private String OfficerPassword;
    private String role;
///////
    public OfficerID(String officerID, String officerPassword, String role) {
        this.officerID = officerID;
        OfficerPassword = officerPassword;
        this.role = role;
    }
    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }
    public void setOfficerPassword(String officerPassword) {
        OfficerPassword = officerPassword;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getOfficerID() {
        return officerID;
    }
    public String getOfficerPassword() {
        return OfficerPassword;
    }
    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        return "OfficerID{" +
                "officerID='" + officerID + '\'' +
                ", OfficerPassword='" + OfficerPassword + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
//////

}
