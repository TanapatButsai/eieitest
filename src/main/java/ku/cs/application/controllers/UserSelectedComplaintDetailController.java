package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.*;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.VoteListDataSource;

public class UserSelectedComplaintDetailController {
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
    @FXML
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;
    @FXML private ImageView selectComplaintImage;

    private DataSource<VoteList> voteListDataSource;
    private VoteList voteList;


    @FXML void initialize(){
        userAndComplaint = (List) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);
        dataSource = new ComplaintListDataSource("data", "complaint.csv");
        voteListDataSource = new VoteListDataSource("data","vote.csv");
        complaintList = dataSource.readData();
        voteList =voteListDataSource.readData();

        showUserInfo();
        showComplaintDetail();
        System.out.println(user);
        System.out.println(complaintList);
    }
    @FXML
    void handleReport(ActionEvent event) {
        try {
            FXRouter.goTo("report_complaint",userAndComplaint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        if (!complaint.getImageUrl().equals("/ku/cs/complaint_images/default_complaint.png")){
            selectComplaintImage.setImage(new Image("file:"+complaint.getImageUrl()));
        }else {
            selectComplaintImage.setImage(new Image(getClass().getResource("/ku/cs/complaint_images/default_complaint.png").toExternalForm()));
        }
    }
    @FXML
    public void handleVote(ActionEvent actionEvent) {
        Vote vote = voteList.find(complaint.getHeadComplaint()+":"
                                    +complaint.getNameWriter()+":"+complaint.getTime());
        System.out.println(vote);
        if (!(vote ==null)){
                if (!vote.isVote(user.getUsername())){
                    voteList.add(complaint.getHeadComplaint()+":"
                                    +complaint.getNameWriter()+":"+complaint.getTime()
                            ,user.getUsername());
                    System.out.println(voteList);
                    voteListDataSource.writeData(voteList);
                    complaint.setRating(complaint.getRating()+1);
                    complaintList.vote(complaintList.findComplaintByTime(complaint));
                    dataSource.writeData(complaintList);
                    clearLabel();
                    showComplaintDetail();
                }
        }
    }
    public void clearLabel(){
        timeLabel.setText("");
        ratingLabel.setText("");
        bodyLabel.setText("");
        spacificLabel.setText("");
        categoryLabel.setText("");
    }

}
