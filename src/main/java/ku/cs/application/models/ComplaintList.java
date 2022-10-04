package ku.cs.application.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ComplaintList {
    private ArrayList<Complaint> complaintList;
  //  private ArrayList<Users> userList;

    public ComplaintList() {
        // create instance of arraylist
        complaintList = new ArrayList<>();

    }

//    public void addUser(Users user){
//
//        userList.add(user);
//    }
    public ArrayList<Complaint> getAllComplaint(){
        return complaintList;
    }

//    public void recordTimeLogin(Users users){
//        Users usersTemp = findUser(users.getUsername());
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
//        usersTemp.setLastTimeLogin(now.format(formatter));
//        userList.remove(users);
//        userList.add(usersTemp);

    public ArrayList<Complaint> getUserComplaint(String username){
        ArrayList<Complaint> complaintListTemp = new ArrayList<>();
        for (Complaint complaint:complaintList){
            if (complaint.getNameWriter().equals(username)){
                complaintListTemp.add(complaint);
            }
        }
        return complaintListTemp;
    }
    public void add(Complaint complaint){
        complaintList.add(complaint);
    }

    @Override
    public String toString() {
        return "ComplaintList{" +
                "complaintList=" + complaintList +
                '}';
    }
    public Complaint findComlaint(Complaint complaint) {
        for (Complaint complaintTemp : complaintList) {
            if (complaint.equals(complaintTemp)) {
                return complaintTemp;
            }
        }return null;
    }
    public void vote(Complaint complaint){
        Complaint complaintTemp = findComlaint(complaint);
        if (!(complaint == null)){
            complaintTemp.setRating(complaintTemp.getRating()+1);
        }
    }
}