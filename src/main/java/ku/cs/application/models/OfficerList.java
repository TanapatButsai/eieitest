package ku.cs.application.models;

import java.util.ArrayList;

public class OfficerList {
    private ArrayList<Officer> officerList;

    public void setDone(Officer officer){
        officer.setDone();
    }
    public void setInProgress(Officer officer) { officer.setInProgress();}
    public void setUnmanaged(Officer officer){officer.setUnmanaged();}
    public OfficerList() {
        officerList = new ArrayList<>();
    }
    public void add(Officer officer){ officerList.add(officer);}
    public void removeData(Officer officer){
        System.out.println(officerList.size());
        officerList.remove(officer);
        System.out.println(officerList.size());
    }
    public ArrayList<Officer> getAllOfficer(){
        return officerList;
    }

    public String setRole(Officer role){
        return role.getRole();
    }
    public Officer findData(Officer officer){
        for (Officer temp : officerList) {
            System.out.println(temp.getRole());
            System.out.println(officer.getRole());
            if (temp.isDone() == officer.isDone()
                    && temp.getRole().equals(officer.getRole())
                    && temp.getTopic().equals(officer.getTopic())

                    ) {
                System.out.println("in loop");
                officerList.remove(temp);
                return officer;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "OfficerList{" +
                "officerList=" + officerList +
                '}';
    }
}
