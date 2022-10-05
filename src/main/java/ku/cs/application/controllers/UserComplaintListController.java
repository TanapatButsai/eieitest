package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.UserList;
import ku.cs.application.models.Users;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.UserListDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class UserComplaintListController {
    @FXML private Label bodyLabel;
    @FXML private Label specificsLabel;
    @FXML private Label headLabel;
    @FXML private Label categoryLabel;

    @FXML private ImageView imageBackGroundUrl;
    @FXML private ImageView logoku;
    @FXML private ImageView userImage;
    @FXML private ListView<Complaint> complaintListView;

    private DataSource<ComplaintList> dataSource;
    private Users user;
    private Complaint complaint;
    private ComplaintList complaintList;
    @FXML private void initialize(){
        user = (Users) com.github.saacsos.FXRouter.getData();
        System.out.println(user.getUsername());
        String url = getClass().getResource("/ku/cs/user_account_scene_image/logoku.png").toExternalForm();
        String url1 = getClass().getResource("/ku/cs/user_account_scene_image/background.jpg").toExternalForm();
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        imageBackGroundUrl.setImage(new Image(url1));
        logoku.setImage(new Image(url));
        complaintList = dataSource.readData();
        System.out.println(complaintList);
        showListView();
        handleSelectListView();
        clearSelectedComplaint();
    }
    private void showListView(){
        complaintListView.getItems().setAll(complaintList.getUserComplaint(user.getName()));
        complaintListView.refresh();
    }
    private void handleSelectListView(){
            complaintListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Complaint>() {
                @Override
                public void changed(ObservableValue<? extends Complaint> observableValue, Complaint complaint, Complaint t1) {
                    System.out.println(t1 + " is selected");

                    showSelectedComplaint(t1);
                }
            });
    }
    private void showSelectedComplaint(Complaint complaint){
        specificsLabel.setText(complaint.getFixComplaint());
        bodyLabel.setText(complaint.getBodyComplaint());
        headLabel.setText(complaint.getHeadComplaint());
        categoryLabel.setText(complaint.getCategory());
    }
    private void clearSelectedComplaint(){
        bodyLabel.setText("");
        specificsLabel.setText("");
        categoryLabel.setText("");
        headLabel.setText("");
    }
    @FXML void handleGoToHome(){
        try {
            FXRouter.goTo("user_account",user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

