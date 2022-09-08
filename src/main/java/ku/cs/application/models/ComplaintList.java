package ku.cs.application.models;

import java.util.ArrayList;

public class ComplaintList {
    private ArrayList<Complaint> ComplaintList;

    public ComplaintList() {
        ComplaintList = new ArrayList<>();
    }
    public void addComplaint(Complaint complaint){
        ComplaintList.add(complaint);
    }
    public ArrayList<Complaint> getAllComplaint(){
        return ComplaintList;
    }

}
