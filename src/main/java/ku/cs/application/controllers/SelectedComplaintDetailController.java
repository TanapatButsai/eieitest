package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.Users;

public class SelectedComplaintDetailController {
    private List<Object> userAndComplaint;
    private Users user;
    private Complaint complaint;

    @FXML
    private Label bodyLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label headLabel;

    @FXML
    private Label spacificLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label ratingLabel;
    @FXML
    private Label timeLabel;
    @FXML void initialize(){
        userAndComplaint = (List) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);

        showUserInfo();
        showComplaintDetail();
        System.out.println(user);
        System.out.println(complaint);
    }


    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            FXRouter.goTo("home",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void showUserInfo(){
        userLabel.setText(user.getName());
    }

    void showComplaintDetail(){
        headLabel.setText(complaint.getHeadComplaint());
        categoryLabel.setText(complaint.getCategory());
        bodyLabel.setText(complaint.getBodyComplaint());
        spacificLabel.setText(complaint.getFixComplaint());
        ratingLabel.setText(Integer.toString(complaint.getRating()));
        timeLabel.setText(complaint.getTime());
    }

}
