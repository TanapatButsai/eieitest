package ku.cs.application.services;

import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.ComplaintStatus;

import java.util.ArrayList;

public class ComplaintStatusFilterer implements Filterer<ComplaintList> {
    private ComplaintStatus status;

    public ComplaintStatusFilterer(ComplaintStatus status) {
        this.status = status;
    }

    @Override
    public ComplaintList filter(ComplaintList complaintList) {
        ArrayList<Complaint> complaintListFilterer = new ArrayList<>(complaintList.getAllComplaint());
        ComplaintList complaintListFiltered = new ComplaintList();
        if (status.equals(ComplaintStatus.unmanaged)) {
            for (Complaint complaint : complaintListFilterer) {
                if (complaint.isUnmanaged()) {
                    complaintListFiltered.add(complaint);
                }
            }
        } else if (status.equals(ComplaintStatus.inProgress)) {
            for (Complaint complaint : complaintListFilterer) {
                if (complaint.isInProgress()) {
                    complaintListFiltered.add(complaint);
                }
            }
        } else if (status.equals(ComplaintStatus.done)) {
            for (Complaint complaint : complaintListFilterer) {
                if (complaint.isDone()) {
                    complaintListFiltered.add(complaint);
                }
            }
        }
        return complaintListFiltered;
    }

}
