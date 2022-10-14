package ku.cs.application.models;

public class Officer {
    private String officerID;
    private String OfficerPassword;
    private String role;
///////
    public Officer(String officerID, String officerPassword, String role) {
        this.officerID = officerID;
        OfficerPassword = officerPassword;
        this.role = role;
    }
    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }
    public void setOfficerPassword(String officerPassword) {
        this.OfficerPassword = officerPassword;
    }
    public String getRole() { return role;}
    public String getOfficerID() {
        return officerID;
    }
    public String getOfficerPassword() {
        return OfficerPassword;
    }
    public String setRole() {
        if (role.equals("normal")){
            return "[     เรื่องร้องเรียงทั่วไป     ]";
        } else if (role.equals("teacher")) {
            return "[     เรื่องร้องเรียงอาจารย์/บุคลาการ     ]";
        } else if (role.equals("place")){
            return "[     เรื่องร้องเรียงอาคาร และ สถานที่     ]";
        } else if (role.equals("enroll")) {
            return  "[     เรื่องร้องเรียงการลงทะเบียนเรียน     ]";
        } else if (role.equals("corrupt"))
            return "[     ร้องเรียนเกี่ยวกับการทุจริต     ]";
        return "";
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
