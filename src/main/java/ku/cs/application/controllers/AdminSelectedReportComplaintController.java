package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.application.models.Complaint;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class AdminSelectedReportComplaintController {

    @FXML
    private Label bodyLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label headLabel;

    @FXML
    private Label ratingLabel;

    @FXML
    private Label spacificLabel;

    @FXML
    private Label timeLabel;


    private Complaint complaint;
    @FXML public void initialize(){
        complaint = (Complaint) FXRouter.getData();
        showComplaintLabel();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            FXRouter.goTo("admincomplaint");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void showComplaintLabel(){
        headLabel.setText(complaint.getHeadComplaint());
        bodyLabel.setText(complaint.getBodyComplaint());
        categoryLabel.setText(complaint.getCategory());
        spacificLabel.setText(complaint.getFixComplaint());
        ratingLabel.setText(Integer.toString(complaint.getRating()));
        timeLabel.setText(complaint.getTime());

    }

    @FXML
    void handleApprove(ActionEvent event) {

    }

    @FXML
    void handleReject(ActionEvent event) {

    }
}

