package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.Users;
import ku.cs.application.services.ComplaintListDataSource;

import java.io.IOException;

public class HomeController {
    private Users user;
    @FXML private ListView<Complaint> complaintListView;
    @FXML private Label userLabel;
    private ComplaintListDataSource dataSource;
    private ComplaintList complaintList;
    private Complaint complaint;

    @FXML
    public void initialize(){
        user = (Users)FXRouter.getData();
        System.out.println(user);
        userLabel.setText(user.getName());
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        complaintList = dataSource.readData();
//        System.out.println(System.getProperty("file.separator"));
//        System.out.println(Arrays.toString(user.getUserImage().split("")));
        showListView();
        handleSelectListView();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleNormalComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("normalcomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนทั่วไป ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleTeacherComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("teachercomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาจารย์/บุคลากร ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handlePlaceComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("placecomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนอาคาร/สถานที่ ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleEnrollComplaint(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("enrollcomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนการลงทะเบียนเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleCorruptComplaint(ActionEvent actionEvent){
        try {
            FXRouter.goTo("corruptcomplaint",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ร้องเรียนการลงทะเบียนเรียน ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleStatusButton(ActionEvent actionEvent){
        complaintListView.getItems().setAll(complaintList.getAllComplaintSortByStatus());
        complaintListView.refresh();
    }
    @FXML
    void handleShowListView(){
        showListView();
    }



    @FXML
    void handleScoreButton(ActionEvent event) {
        complaintListView.getItems().setAll(complaintList.getAllComplaintSortByRating());
        complaintListView.refresh();
    }
    @FXML
    public void handleVoteButton(ActionEvent actionEvent){
        complaintList.vote(complaint);
        dataSource.writeData(complaintList);
    }
    private void showListView() {
        complaintListView.getItems().setAll(complaintList.getAllComplaint());
        complaintListView.refresh();
    }
    @FXML
    public void handleGoToUserAccountScene(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user_account",user);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า changepassword");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }
    private void handleSelectListView(){
        complaintListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Complaint>() {
            @Override
            public void changed(ObservableValue<? extends Complaint> observableValue, Complaint complaint, Complaint t1) {
                System.out.println(t1 + " is selected");
                selectComplaint(t1);
            }
        });
    }
    private void selectComplaint(Complaint complaint){
        this.complaint = complaint;
    }
//    complaintList.vote(complaint);
//    dataSource.writeData(complaintList);
    @FXML
    void handleTimeButton(ActionEvent event) {
        complaintListView.getItems().setAll(complaintList.getAllComplaintSortByTime());
        complaintListView.refresh();
    }
}
