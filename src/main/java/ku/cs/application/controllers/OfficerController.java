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
    private Officer userOfficer;
    private ComplaintList complaintList;
    private ComplaintList officerRoleList;
    private DataSource<ComplaintList> dataSource;
    @FXML private Label topicLabel;
    @FXML private Label officerLabel;
    @FXML private Label statusLabel;
    @FXML private Label fixTopicLabel;
    @FXML private Label errorLabel;

    @FXML private ListView complaintListView;
    @FXML private TextArea bodyTextArea;
    @FXML private TextArea fixBodyTextArea;
    @FXML private DataSource<ComplaintList> complaintListDataSource;

    //นำlist
    @FXML
    public void initialize(){
        complaintListDataSource = new ComplaintListDataSource("data","complaint.csv");
        complaintList = complaintListDataSource.readData();
        userOfficer = (Officer) FXRouter.getData();
        if (userOfficer.getRole().equals("normal")) {
            officerRoleList = complaintList.getOfficerComplaint("normal");
            System.out.println("User is Officer-normal");
        }else if (userOfficer.getRole().equals("teacher")) {
            officerRoleList = complaintList.getOfficerComplaint("teacher");
            System.out.println("User is Officer-teacher");
        }else if (userOfficer.getRole().equals("place")) {
            officerRoleList = complaintList.getOfficerComplaint("place");
            System.out.println("User is Officer-place");
        }else if (userOfficer.getRole().equals("enroll")) {
            officerRoleList = complaintList.getOfficerComplaint("enroll");
            System.out.println("User is Officer-enroll");
        }else if (userOfficer.getRole().equals("corrupt")){
            officerRoleList = complaintList.getOfficerComplaint("corrupt");
        }
        System.out.println("initialize ListData");
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        complaintList = dataSource.readData();
        topicLabel.setText("");
        statusLabel.setText("");
        fixTopicLabel.setText("");
        errorLabel.setText("");
        showOfficerData();
        handleSelectedListView();
    }
    //แสดงหน้าที่ของ officer
    public void showOfficerData(){
            complaintListView.getItems().setAll(officerRoleList.getAllComplaint());
            complaintListView.refresh();
            officerLabel.setText(userOfficer.getRole());
        if (userOfficer.getRole().equals("normal")){
            officerLabel.setText("[     เรื่องร้องเรียงทั่วไป     ]");
        } else if (userOfficer.getRole().equals("teacher")) {
            officerLabel.setText("[     เรื่องร้องเรียงอาจารย์/บุคลาการ     ]");
        } else if (userOfficer.getRole().equals("place")){
            officerLabel.setText("[     เรื่องร้องเรียงอาคาร และ สถานที่     ]");
        } else if (userOfficer.getRole().equals("enroll")) {
            officerLabel.setText("[     เรื่องร้องเรียงการลงทะเบียนเรียน     ]");
        } else if (userOfficer.getRole().equals("corrupt"))
            officerLabel.setText("[     ร้องเรียนเกี่ยวกับการทุจริต     ]");
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
    @FXML
    void handleAccount(ActionEvent event) {
        try {
            FXRouter.goTo("officer_change_picture",userOfficer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showSelectedOfficerData(Complaint data) {
        statusLabel.setText(data.getStatus());
        topicLabel.setText(data.getHeadComplaint());
        fixTopicLabel.setText(data.getFixComplaint());
        bodyTextArea.setText(data.getBodyComplaint());
        bodyTextArea.setEditable(false);
        if (data.getSolution().equals("no")){
            fixBodyTextArea.setText("");
        }else {
            fixBodyTextArea.setText(data.getSolution());
        }
    }
    //นำ ค่าofficer จาก listที่ถูกเลือก
    private void  setSelectedOfficerData(Complaint data){
        complaint = data;
    }
    //ปุ่มกำหนดสถานะ
    @FXML private void handleDoneButton(ActionEvent actionEvent){
        if (complaint != null){
            if (fixBodyTextArea.getText().equals("")){
                errorLabel.setText("*กรุณาใส่ช้อมูล*");
                complaint.setSolution("no");
            }else {
                complaintList.findData(complaint);
                complaintList.setDone(complaint);
                complaint.setSolution(fixBodyTextArea.getText());
                //officerRoleList.setDone(officer);
                statusLabel.setText(complaint.getStatus());
                complaintList.add(complaint);
                dataSource.writeData(complaintList);
            }
        }
    }
    @FXML private void handleInProgress(ActionEvent actionEvent){
        if (complaint != null){
            if (fixBodyTextArea.getText().equals("")){
                errorLabel.setText("*กรุณาใส่ช้อมูล*");
                complaint.setSolution("no");
            }else {
                complaintList.findData(complaint);
                complaintList.setInProgress(complaint);
                complaint.setSolution(fixBodyTextArea.getText());
                //officerRoleList.setInProgress(officer);
                statusLabel.setText(complaint.getStatus());
                complaintList.add(complaint);
                dataSource.writeData(complaintList);
            }
        }
    }
    @FXML private void handleUnmanagedButton(ActionEvent actionEvent){
        if (complaint != null){
            errorLabel.setText("");
            complaint.setSolution("no");
            fixBodyTextArea.clear();
            complaintList.findData(complaint);
            complaintList.setUnmanaged(complaint);
            //officerRoleList.setInProgress(officer);
            statusLabel.setText(complaint.getStatus());
            complaintList.add(complaint);
            dataSource.writeData(complaintList);
        }
    }

    @FXML
    public void handleChangePasswordButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("officer_change_password",userOfficer);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
    public void handleOfficerMemberButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("officer_list_member",userOfficer);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
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
