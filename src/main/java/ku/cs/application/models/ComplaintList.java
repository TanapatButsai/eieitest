package ku.cs.application.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public ArrayList<Complaint> getAllComplaintSortByRating(){
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        Collections.sort(temp);
        return temp;
    }

    public ArrayList<Complaint> getAllComplaintSortByStatus(){
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        Collections.sort(temp, new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                int x = 0;
                int y = 0;
                if (o1.isUnmanaged()){
                    x = 3;
                } else if (o1.isInProgress()) {
                    x = 2;
                }else if(o1.isDone()){
                    x = 1;
                }

                if (o2.isUnmanaged()){
                    y = 3;
                } else if (o2.isInProgress()) {
                    y = 2;
                }else if(o2.isDone()){
                    y = 1;
                }
                return Integer.compare(x,y);
            }
        });
        return temp;
    }
    public void add(Complaint complaint){complaintList.add(complaint);}

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
    @Override
    public String toString() {
        return "ComplaintList{" +
                "complaintList=" + complaintList +
                '}';
    }
}