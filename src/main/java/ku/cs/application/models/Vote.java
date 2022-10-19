package ku.cs.application.models;


import java.util.ArrayList;

public class Vote {
    String complaintObject;
    ArrayList<String> usernameVotedList = new ArrayList<>();

    public Vote(String complaintObject, ArrayList<String> usernameVotedList) {
        this.complaintObject = complaintObject;
        this.usernameVotedList = usernameVotedList;
    }

    public Vote(String complaintObject) {
        this.complaintObject = complaintObject;
        this.usernameVotedList .add("-");
    }

    public ArrayList<String> getUsernameVotedList() {
        return usernameVotedList;
    }

    public void setUsernameVotedList(ArrayList<String> usernameVotedList) {
        this.usernameVotedList = usernameVotedList;
    }
    public void addUsername(String username){
        usernameVotedList.add(username);
    }
    public String getComplaintObject() {
        return complaintObject;
    }
    public Boolean isVote(String username){
        return usernameVotedList.contains(username);
    }

    @Override
    public String toString() {
        return complaintObject+","+usernameVotedList.toString()
                .replace("[","")
                .replace("]","")
                .replace(",","-");
    }
}
