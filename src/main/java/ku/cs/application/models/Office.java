package ku.cs.application.models;

import java.util.ArrayList;

public class Office {
    private String role;
    private ArrayList<String> allOfficerUsername;
///////
    public Office(String role, ArrayList<String> allOfficerUsername) {
        this.role = role;
        this.allOfficerUsername = allOfficerUsername;
    }

    @Override
    public String toString() {
        return "Officer{" +
                ", role='" + role + '\'' +
                ", allOfficerUsername=" + allOfficerUsername +
                '}';
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getAllOfficerUsername() {
        return allOfficerUsername;
    }

    public void setAllOfficerUsername(ArrayList<String> allOfficerUsername) {
        this.allOfficerUsername = allOfficerUsername;
    }

    public String getRole() { return role;}
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
    //////

}
