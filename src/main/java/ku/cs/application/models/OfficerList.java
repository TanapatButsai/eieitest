package ku.cs.application.models;

import java.util.ArrayList;

public class OfficerList {
    private ArrayList<Officer> officerList;

    public OfficerList() {
        officerList = new ArrayList<>();
    }
    public void addOfficer(Officer officer){

        officerList.add(officer);
    }
    public ArrayList<Officer> getAllOfficerID(){
        return officerList;
    }

    public Officer findOfficer(String username){
        Officer officer = null;
        for (Officer temp : officerList) {
            if (temp.getOfficerID().equals(username)) {
                officer = temp;
            }
        }
        return officer;
    }
    public Officer findChangeUser(String username) {
        for (Officer officer : officerList) {
            if (officer.getOfficerID().equals(username)) {
                return officer;
            }
        }return null;
    }
    public void changePassword(String username,String newPassword){
        Officer officer = findChangeUser(username);
        if (officer != null) {
            officer.setOfficerPassword(newPassword);
        }
    }
}
