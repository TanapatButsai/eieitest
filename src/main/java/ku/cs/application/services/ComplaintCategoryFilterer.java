package ku.cs.application.services;

import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;

import java.util.ArrayList;

public class ComplaintCategoryFilterer implements Filterer<ComplaintList> {
    private String category;

    public ComplaintCategoryFilterer(String category) {
        this.category = category;
    }

    @Override
    public ComplaintList filter(ComplaintList complaintList) {
        ArrayList<Complaint> complaintListFilterer = new ArrayList<>(complaintList.getAllComplaint());
        ComplaintList complaintListFiltered = new ComplaintList();
        for (Complaint complaint: complaintListFilterer) {
            if (complaint.getCategory().equals(this.category)){
                complaintListFiltered.add(complaint);
            }
        }
        return complaintListFiltered;
    }
}
