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



    public ArrayList<String> getAllOfficerUsername() {
        return allOfficerUsername;
    }


    public void addOfficer(String username){
        this.allOfficerUsername.add(username);
    }
    public String getRole() { return role;}

    //////

}
