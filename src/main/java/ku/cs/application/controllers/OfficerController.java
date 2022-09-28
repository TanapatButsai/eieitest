package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.application.models.Officer;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.OfficerID;
import ku.cs.application.models.OfficerList;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.OfficerDataSource;
import ku.cs.application.services.OfficersListDataSource;
import ku.cs.application.services.OfficerRoleDataSource;


import java.io.IOException;

public class OfficerController {
    private Officer officer;
    private OfficerID officerID;
    private OfficerList officerList;
    private OfficerList officerRoleList;
    private DataSource<OfficerList> dataSource;

    private OfficerDataSource<OfficerList> dataOfficer;
    @FXML private Label topicLabel;
    @FXML private Label officerLabel;
    @FXML private Label statusLabel;
    @FXML private Label fixTopicLabel;
    @FXML private ListView complaintListView;
    @FXML private TextArea bodyTextArea;


    @FXML
    public void initialize(){
        officerID = (OfficerID)FXRouter.getData();
        if (officerID.getRole().equals("officer1")) {
            dataOfficer = new OfficerRoleDataSource("data","officer.csv");
            officerRoleList = dataOfficer.readData1();
            System.out.println("User is Officer1");
        }else if (officerID.getRole().equals("officer2")) {
            dataOfficer = new OfficerRoleDataSource("data","officer.csv");
            officerRoleList = dataOfficer.readData2();
            System.out.println("User is Officer2");
        }else if (officerID.getRole().equals("officer3")) {
            dataOfficer = new OfficerRoleDataSource("data","officer.csv");
            officerRoleList = dataOfficer.readData3();
            System.out.println("User is Officer3");
        }else if (officerID.getRole().equals("officer4")) {
            dataOfficer = new OfficerRoleDataSource("data","officer.csv");
            officerRoleList = dataOfficer.readData4();
            System.out.println("User is Officer4");
        }
        System.out.println("initialize ListData");
        dataSource = new OfficersListDataSource("data","officer.csv");
        officerList = dataSource.readData();
        topicLabel.setText("");
        statusLabel.setText("");
        fixTopicLabel.setText("");
        showOfficerData();
        handleSelectedListView();
    }
    public void showOfficerData(){
        if (officerRoleList == null){
            complaintListView.getItems().addAll(officerList.getAllOfficer());
            complaintListView.refresh();
            officerLabel.setText("[     Officer all     ]");
        }else {
            complaintListView.getItems().addAll(officerRoleList.getAllOfficer());
            complaintListView.refresh();
            officerLabel.setText(officerID.setRole());
        }
//        complaintListView.getItems().addAll(officerRoleList.getAllOfficer());
//        complaintListView.refresh();
//        officerLabel.setText(officerID.setRole());
    }
    private void handleSelectedListView(){
        complaintListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Officer>() {
                    @Override
                    public void changed(ObservableValue<? extends Officer> observable,
                                        Officer oldValue, Officer newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedOfficerData(newValue);
                        setSelectedOfficerData(newValue);
                    }
                });

    }
    private void showSelectedOfficerData(Officer data){
        statusLabel.setText(data.getStatus());
        topicLabel.setText(data.getTopic());
        fixTopicLabel.setText(data.getFixComplaint());
        bodyTextArea.setText(data.getBody());
        bodyTextArea.setEditable(false);
    }
    private void  setSelectedOfficerData(Officer data){
        officer = data;
    }
    @FXML private void handleDoneButton(ActionEvent actionEvent){
        if (officer != null){
            officerList.findData(officer);
            officerList.setDone(officer);
            //officerRoleList.setDone(officer);
            statusLabel.setText(officer.getStatus());
            officerList.add(officer);
            dataSource.writeData(officerList);
        }
    }
    @FXML private void handleInProgress(ActionEvent actionEvent){
        if (officer != null){
            officerList.findData(officer);
            officerList.setInProgress(officer);
            //officerRoleList.setInProgress(officer);
            statusLabel.setText(officer.getStatus());
            officerList.add(officer);
            dataSource.writeData(officerList);
        }
    }
    @FXML private void handleUnmanagedButton(ActionEvent actionEvent){
        if (officer != null){
            officerList.findData(officer);
            officerList.setUnmanaged(officer);
            //officerRoleList.setInProgress(officer);
            statusLabel.setText(officer.getStatus());
            officerList.add(officer);
            dataSource.writeData(officerList);
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
