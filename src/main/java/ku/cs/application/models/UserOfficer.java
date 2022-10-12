package ku.cs.application.models;


public class UserOfficer extends Users{
    private String role;
    private String officerID;
    private String OfficerPassword;
    public UserOfficer(String username, String password,String role) {
        super(username,password);
        this.officerID = username;
        this.OfficerPassword = password;
        this.role = role;
    }
    public void setOfficerID(String officerID) {
        this.officerID = officerID;
    }
    public void setOfficerPassword(String officerPassword) {
        OfficerPassword = officerPassword;
    }
    public String getRole() { return role;}
    public String getOfficerID() {
        return officerID;
    }
    public String getOfficerPassword() {
        return OfficerPassword;
    }
    public String setRole() {
        if (role.equals("officer1")){
            return "[     เรื่องร้องเรียงทั่วไป     ]";
        } else if (role.equals("officer2")) {
            return "[     เรื่องร้องเรียงอาจารย์/บุคลาการ     ]";
        } else if (role.equals("officer3")){
            return "[     เรื่องร้องเรียงอาคาร และ สถานที่     ]";
        } else if (role.equals("officer4")) {
            return  "[     เรื่องร้องเรียงการลงทะเบียนเรียน     ]";
        } else if (role.equals("officer5"))
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
}