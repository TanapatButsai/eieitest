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

    private Ban ban;
    private BanList banList;
    private DataSource<BanList> banListDataSource;
    private DataSource<UserList> userListDataSource;
    private UserList userList;
    private Complaint complaint;

    @FXML public void initialize(){
        complaint = (Complaint) FXRouter.getData();
        banListDataSource = new BanListDataSource(false);
        userListDataSource = new UserListDataSource("data","user.csv");
        banList = banListDataSource.readData();
        userList = userListDataSource.readData();
        System.out.println(banList);
        System.out.println(userList);
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
//        Users users = userList.findUser(complaint.getNameWriter());
//        System.out.println(users);
//        userList.setBan(complaint.getNameWriter());

        try {
            FXRouter.goTo("admin_ban_reason",complaint);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void handleReject(ActionEvent event) {

    }
}

