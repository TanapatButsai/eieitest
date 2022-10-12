package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.application.models.*;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.*;


import java.io.IOException;

public class OfficerController {
    private Complaint complaint;
    private UserOfficer officer;
    private ComplaintList complaintList;
    private ComplaintList officerRoleList;
    private DataSource<ComplaintList> dataSource;

    private OfficerDataSource<ComplaintList> dataOfficer;
    @FXML private Label topicLabel;
    @FXML private Label officerLabel;
    @FXML private Label statusLabel;
    @FXML private Label fixTopicLabel;
    @FXML private ListView complaintListView;
    @FXML private TextArea bodyTextArea;


    //นำlist
    @FXML
    public void initialize(){
        officer = (UserOfficer)FXRouter.getData();
        if (officer.getRole().equals("officer1")) {
            dataOfficer = new OfficerRoleDataSource("data","complaint.csv");
            officerRoleList = dataOfficer.readData1();
            System.out.println("User is Officer1");
        }else if (officer.getRole().equals("officer2")) {
            dataOfficer = new OfficerRoleDataSource("data","complaint.csv");
            officerRoleList = dataOfficer.readData2();
            System.out.println("User is Officer2");
        }else if (officer.getRole().equals("officer3")) {
            dataOfficer = new OfficerRoleDataSource("data","complaint.csv");
            officerRoleList = dataOfficer.readData3();
            System.out.println("User is Officer3");
        }else if (officer.getRole().equals("officer4")) {
            dataOfficer = new OfficerRoleDataSource("data","complaint.csv");
            officerRoleList = dataOfficer.readData4();
            System.out.println("User is Officer4");
        }else if (officer.getRole().equals("officer5")){
            dataOfficer = new OfficerRoleDataSource("data","complaint.csv");
            officerRoleList = dataOfficer.readData5();
        }
        System.out.println("initialize ListData");
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        complaintList = dataSource.readData();
        topicLabel.setText("");
        statusLabel.setText("");
        fixTopicLabel.setText("");
        showOfficerData();
        handleSelectedListView();
    }
    //แสดงหน้าที่ของ officer
    public void showOfficerData(){
        if (officerRoleList == null){
            complaintListView.getItems().addAll(complaintList.getAllComplaint());
            complaintListView.refresh();
            officerLabel.setText("[     Officer all     ]");
        }else {
            complaintListView.getItems().addAll(officerRoleList.getAllComplaint());
            complaintListView.refresh();
            officerLabel.setText(officer.setRole());
        }
//        complaintListView.getItems().addAll(officerRoleList.getAllOfficer());
//        complaintListView.refresh();
//        officerLabel.setText(officerID.setRole());
    }

    //การเลือกค่าในList view
    private void handleSelectedListView(){
        complaintListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Complaint>() {
                    @Override
                    public void changed(ObservableValue<? extends Complaint> observable,
                                        Complaint oldValue, Complaint newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedOfficerData(newValue);
                        setSelectedOfficerData(newValue);
                    }
                });

    }

    private void showSelectedOfficerData(Complaint data){
        statusLabel.setText(data.getStatus());
        topicLabel.setText(data.getHeadComplaint());
        fixTopicLabel.setText(data.getFixComplaint());
        bodyTextArea.setText(data.getBodyComplaint());
        bodyTextArea.setEditable(false);
    }
    //นำ ค่าofficer จาก listที่ถูกเลือก
    private void  setSelectedOfficerData(Complaint data){
        complaint = data;
    }
    //ปุ่มกำหนดสถานะ
    @FXML private void handleDoneButton(ActionEvent actionEvent){
        if (complaint != null){
            complaintList.findData(complaint);
            complaintList.setDone(complaint);
            //officerRoleList.setDone(officer);
            statusLabel.setText(complaint.getStatus());
            complaintList.add(complaint);
            dataSource.writeData(complaintList);
        }
    }
    @FXML private void handleInProgress(ActionEvent actionEvent){
        if (complaint != null){
            complaintList.findData(complaint);
            complaintList.setInProgress(complaint);
            //officerRoleList.setInProgress(officer);
            statusLabel.setText(complaint.getStatus());
            complaintList.add(complaint);
            dataSource.writeData(complaintList);
        }
    }
    @FXML private void handleUnmanagedButton(ActionEvent actionEvent){
        if (complaint != null){
            complaintList.findData(complaint);
            complaintList.setUnmanaged(complaint);
            //officerRoleList.setInProgress(officer);
            statusLabel.setText(complaint.getStatus());
            complaintList.add(complaint);
            dataSource.writeData(complaintList);
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("login");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
