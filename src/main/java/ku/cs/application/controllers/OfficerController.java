package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.application.models.Officer;
import com.github.saacsos.FXRouter;
import ku.cs.application.models.OfficerList;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.OfficersListDataSource;


import java.io.IOException;

public class OfficerController {
    private Officer officer;
    private OfficerList officerList;
    private DataSource<OfficerList> dataSource;
    @FXML private Label topicLabel;
    @FXML private Label officerLabel;
    @FXML private ListView complaintListView;

    @FXML
    public void initialize(){
        System.out.println("initialize MemberCardDetailController");
        dataSource = new OfficersListDataSource("data","officer.csv");
        officerList = dataSource.readData();
        showOfficerData();
    }
    public void showOfficerData(){
        Officer officer1 = new Officer("benny","officer1","trasgh","wwwwwwwwww");
        topicLabel.setText("");
        complaintListView.getItems().addAll(officerList.getAllOfficer());
        //officerLabel.setText(officerList.setRole(officer));
        officerLabel.setText(officer1.getRole());
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
