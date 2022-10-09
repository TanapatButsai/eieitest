package ku.cs.application.controllers;

import javafx.fxml.FXML;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.Report;
import ku.cs.application.models.Users;
import com.github.saacsos.FXRouter;

import java.util.ArrayList;
import java.util.List;

public class ReportComplaintController {
    private List userAndComplaint;
    private Users user;
    private Complaint complaint;
    private Report report;
    @FXML
    public void initialize(){
        userAndComplaint = (List) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);
        System.out.println(user);
        System.out.println(complaint);

    }
}
