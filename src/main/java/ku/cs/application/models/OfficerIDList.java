package ku.cs.application.models;

import java.util.ArrayList;

public class OfficerIDList {
    private ArrayList<OfficerID> officerIDList;

    public OfficerIDList() {
        officerIDList = new ArrayList<>();
    }
    public void addOfficer(OfficerID officerID){

        officerIDList.add(officerID);
    }
    public ArrayList<OfficerID> getAllOfficerID(){
        return officerIDList;
    }

    public OfficerID findOfficer(String username){
        OfficerID officerID = null;
        for (OfficerID temp : officerIDList) {
            if (temp.getOfficerID().equals(username)) {
                officerID = temp;
            }
        }
        return officerID;
    }
}
