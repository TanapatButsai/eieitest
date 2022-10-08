package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.Users;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;

public class SelectedComplaintDetailController {
    private List<Object> userAndComplaint;
    private Users user;
    private Complaint complaint;
    private Complaint complaintTemp;

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
    @FXML
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;

    @FXML void initialize(){
        userAndComplaint = (List) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);
        dataSource = new ComplaintListDataSource("data", "complaint.csv");
        complaintList = dataSource.readData();

        showUserInfo();
        showComplaintDetail();
        System.out.println(user);
        System.out.println(complaintList);
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
        complaintList = dataSource.readData();
        headLabel.setText(complaint.getHeadComplaint());
        categoryLabel.setText(complaint.getCategory());
        bodyLabel.setText(complaint.getBodyComplaint());
        spacificLabel.setText(complaint.getFixComplaint());
        String[] arr = complaint.getTime().split("-");
        String time = arr[0] + ":" + arr[1] + ":" + arr[2] + " " + arr[3] + "-" + arr[4] + "-" + arr[5];
        timeLabel.setText(time);
        ratingLabel.setText(Integer.toString(complaint.getRating()));

    }
    @FXML
    public void handleVote(ActionEvent actionEvent) {
        complaintList.vote(complaintList.findComplaintByTime(complaint));
        dataSource.writeData(complaintList);
        clearLabel();
        showComplaintDetail();
    }
    public void clearLabel(){
        timeLabel.setText("");
        ratingLabel.setText("");
        bodyLabel.setText("");
        spacificLabel.setText("");
        categoryLabel.setText("");
    }

}
