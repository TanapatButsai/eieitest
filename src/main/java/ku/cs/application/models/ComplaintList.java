package ku.cs.application.models;

import ku.cs.application.services.Filterer;

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
    public ArrayList<Complaint> getAllComplaint() {
        return complaintList;
    }

    public ArrayList<Complaint> getDoneComplaint() {
        ArrayList<Complaint> complaintListTemp = new ArrayList<>();
        for (Complaint complaint : complaintList) {
            if (complaint.isDone()) {
                complaintListTemp.add(complaint);
            }
        }
        return complaintListTemp;
    }

    public ArrayList<Complaint> filter(Filterer<ComplaintList> filterer) {
        return filterer.filter(this).getAllComplaint();
    }

    public ComplaintList filterCategory(Filterer<ComplaintList> filterer) {
        return filterer.filter(this);
    }
//    public ArrayList<Complaint> filterCategory(Filterer<ComplaintList> filterer);


    //    public ArrayList<Complaint> getInProgressComplaint(){
//        ArrayList<Complaint> complaintListTemp = new ArrayList<>();
//        ComplaintStatusFilterer complaintStatusFilterer = new ComplaintStatusFilterer(ComplaintStatus.inProgress);
//        for (Complaint complaint:complaintList){
//            if (complaint.isInProgress()){
//                complaintListTemp.add(complaint);
//            }
//        }
//        return complaintListTemp;
//    }
//    public ArrayList<Complaint> getUnManageComplain(){
//        ArrayList<Complaint> complaintListTemp = new ArrayList<>();
//        for (Complaint complaint:complaintList){
//            if (complaint.isUnmanaged()){
//                complaintListTemp.add(complaint);
//            }
//        }
//        return complaintListTemp;
//    }
//    public void recordTimeLogin(Users users){
//        Users usersTemp = findUser(users.getUsername());
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss-dd-MM-yy");
//        usersTemp.setLastTimeLogin(now.format(formatter));
//        userList.remove(users);
//        userList.add(usersTemp);
//public ArrayList<Complaint> getAllComplaintSortByStatus(){
//    ArrayList<Complaint> temp = new ArrayList<>(complaintList);
//    Collections.sort(temp, new Comparator<Complaint>() {
//        @Override
//        public int compare(Complaint o1, Complaint o2) {
//            return 0;
//        }
//    });
//    return temp;
//}
    public ArrayList<Complaint> getUserComplaint(String username) {
        ArrayList<Complaint> complaintListTemp = new ArrayList<>();
        for (Complaint complaint : complaintList) {
            if (complaint.getNameWriter().equals(username)) {
                complaintListTemp.add(complaint);
            }
        }
        return complaintListTemp;
    }

    public ArrayList<Complaint> getAllComplaintSortByRating() {
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        Collections.sort(temp);
        return temp;
    }


    public void add(Complaint complaint) {
        complaintList.add(complaint);
    }

    public void remove(Complaint complaint) {
        Complaint complaint1 = findComplaint(complaint);
        complaintList.remove(complaint1);
    }

    public Complaint findComplaint(Complaint complaint) {
        for (Complaint complaintTemp : complaintList) {
            if (complaint.equals(complaintTemp)) {
                return complaintTemp;
            }
        }
        return null;
    }

    public Complaint findComplaintByTime(Complaint complaint) {
        for (Complaint complaintTemp : complaintList) {
            if (complaintTemp.getHeadComplaint().equals(complaint.getHeadComplaint())
                    && complaintTemp.getTime().equals(complaint.getTime())) {
                return complaintTemp;
            }
        }
        return null;
    }

    public void vote(Complaint complaint) {
        Complaint complaintTemp = findComplaint(complaint);
        if (!(complaintTemp == null)) {
            complaintTemp.setRating(complaintTemp.getRating() + 1);
        }
    }

    //officer
    public void setDone(Complaint complaint) {
        complaint.setDone();
    }

    public void setInProgress(Complaint complaint) {
        complaint.setInProgress();
    }

    public void setUnmanaged(Complaint complaint) {
        complaint.setUnmanaged();
    }

    public Complaint findData(Complaint complaint) {
        for (Complaint temp : complaintList) {
            System.out.println(temp.getNameWriter());
            System.out.println(temp.getCategory());
            System.out.println("-----");
            System.out.println(complaint.getNameWriter());
            System.out.println(complaint.getCategory());
            System.out.println("-----");
            System.out.println(temp.isDone());
            System.out.println(complaint.isDone());
            if (temp.isDone() == complaint.isDone()
                    && temp.getCategory().equals(complaint.getCategory())
                    && temp.getNameWriter().equals(complaint.getNameWriter())
                    && temp.getTime().equals(complaint.getTime())
            ) {
                System.out.println("in loop");
                complaintList.remove(temp);
                return complaint;
            }
            System.out.println("out loop");
        }
        return null;
    }

    @Override
    public String toString() {
        return "ComplaintList{" +
                "complaintList=" + complaintList +
                '}';
    }

    public ArrayList<Complaint> sortTimeNew(ArrayList<Complaint> complaintList) {
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        temp.sort(new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                return Double.compare(o2.getTimeToSecond(), o1.getTimeToSecond());
            }
        });
        return temp;
    }

    public ArrayList<Complaint> sortTimeOld(ArrayList<Complaint> complaintList) {
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        temp.sort(new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                return Double.compare(o1.getTimeToSecond(), o2.getTimeToSecond());
            }
        });
        return temp;
    }

    public ArrayList<Complaint> sortRatingNew(ArrayList<Complaint> complaintList) {
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        temp.sort(new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                return Integer.compare(o2.getRating(), o1.getRating());
            }
        });
        return temp;
    }

    public ArrayList<Complaint> sortRatingOld(ArrayList<Complaint> complaintList) {
        ArrayList<Complaint> temp = new ArrayList<>(complaintList);
        temp.sort(new Comparator<Complaint>() {
            @Override
            public int compare(Complaint o1, Complaint o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        });
        return temp;
    }

    public void sortTimeNew() {
        complaintList = sortTimeNew(complaintList);
    }

    public void sortTimeOld() {
        complaintList = sortTimeOld(complaintList);
    }

    public void sortRatingNew() {
        complaintList = sortRatingNew(complaintList);
    }

    public void sortRatingOld() {
        complaintList = sortRatingOld(complaintList);
    }

    public Complaint findComplaintByReported(Report report){
        for (Complaint complaint: complaintList){
            String complaintID = complaint.getHeadComplaint()+":"+complaint.getNameWriter()+":"+complaint.getTime();
            if (complaintID.equals(report.getObjectID())) return complaint;
        }
        return null;
    }
}