package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.Report;
import ku.cs.application.models.Users;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportComplaintController {
    private List userAndComplaint;
    private Users user;
    private Complaint complaint;
    private Report report;
    @FXML
    private TextArea reportTextArea;

    @FXML
    private Label userLabel;
    @FXML
    public void initialize(){
        userAndComplaint = (List<Object>) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);
        System.out.println(user);
        System.out.println(complaint);
    }


    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            FXRouter.goTo("selected_complaint_detail",userAndComplaint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleReportComplaint(ActionEvent event) {

    }
}
