package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.application.models.*;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.BanListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;

import java.io.IOException;
import java.util.ArrayList;

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
    private Label specificLabel;

    @FXML
    private Label timeLabel;

    private BanList banList;
    private DataSource<BanList> banListDataSource;
    private DataSource<UserList> userListDataSource;
    private UserList userList;
    private Complaint complaint;
    private Report report;
    private ArrayList<Object> objects;
    private Users admin;

    @FXML public void initialize(){
        objects = (ArrayList<Object>) FXRouter.getData();
        complaint = (Complaint) objects.get(0);
        report = (Report) objects.get(1);
        admin = (Users) objects.get(2);
        banListDataSource = new BanListDataSource(false);
        userListDataSource = new UserListDataSource("data","user.csv");
        banList = banListDataSource.readData();
        userList = userListDataSource.readData();
        System.out.println(report);

        showComplaintLabel();
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            FXRouter.goTo("admincomplaint",admin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void showComplaintLabel(){
        headLabel.setText(complaint.getHeadComplaint());
        bodyLabel.setText(complaint.getBodyComplaint());
        categoryLabel.setText(complaint.getCategory());
        specificLabel.setText(complaint.getFixComplaint());
        ratingLabel.setText(Integer.toString(complaint.getRating()));
        timeLabel.setText(complaint.getTime());

    }

    @FXML
    void handleApprove(ActionEvent event) {
        try {
            FXRouter.goTo("admin_ban_reason",objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handleReject(ActionEvent event) {

    }
}

