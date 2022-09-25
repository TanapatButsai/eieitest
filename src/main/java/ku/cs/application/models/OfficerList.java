package ku.cs.application.models;

import java.util.ArrayList;

public class OfficerList {
    private ArrayList<Officer> officerList;

    public OfficerList() {
        officerList = new ArrayList<>();
    }
    public void add(Officer officer){ officerList.add(officer);
    }
    public ArrayList<Officer> getAllOfficer(){
        return officerList;
    }
}
