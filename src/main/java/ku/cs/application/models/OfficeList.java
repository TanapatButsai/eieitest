package ku.cs.application.models;

import java.util.ArrayList;

public class OfficeList {
    private ArrayList<Office> officeList;

    public OfficeList() {
        officeList = new ArrayList<>();
    }
    public void addOfficer(Office office){

        officeList.add(office);
    }
    public ArrayList<Office> getAllOfficerID(){
        return officeList;
    }

//    public Officer findOfficer(String username){
//        Officer officer = null;
//        for (Officer temp : officerList) {
//            if (temp.getOfficerID().equals(username)) {
//                officer = temp;
//            }
//        }
//        return officer;
//    }
    public String findOfficerRole(String username){
        for (Office temp : officeList) {
            ArrayList<String> sss = temp.getAllOfficerUsername();
            for (String s:sss) {
                if (s.equals(username)){
                    return temp.getRole();
                }
            }
        }
        return null;
    }


    public void addOfficerINOffice(String username, String role){
        for (Office office : officeList){
            if (office.getRole().equals(role)){
                office.addOfficer(username);
            }

        }
    }
    @Override
    public String toString() {
        return "OfficerList{" +
                "officerList=" + officeList +
                '}';
    }
}
