package ku.cs.application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;

import java.io.IOException;

public class AdminComplaintController {
    @FXML private ListView<Complaint> complaintListView;
    @FXML private Label reporterLabel;
    @FXML private Label reportCategoryLabel;
    @FXML private Label titleLabel;
    @FXML private TextArea detailLabel;
    private DataSource<ComplaintList> clds;
    private ComplaintList complaintList;

    @FXML
    public void initialize(){
        clds = new ComplaintListDataSource("data","complaint.csv");
        complaintList = clds.readData();

//        showListView();
//        clearSelectedComplaint();
//        handleSelectedListView();
    }

    private void showListView() {
        complaintListView.getItems().addAll(complaintList.getAllComplaint());
        complaintListView.refresh();
    }

    private void handleSelectedListView() {
        complaintListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Complaint>() {
                    @Override
                    public void changed(ObservableValue<? extends Complaint> observableValue,
                                        Complaint complaint, Complaint c1) {
                        System.out.println(c1 + "is selected");
                        showSelectedComplaint(c1);
                    }
                });
    }

    private void showSelectedComplaint(Complaint complaint) {
        titleLabel.setText(complaint.getHeadComplaint());
        reportCategoryLabel.setText(complaint.getCategory());
        reporterLabel.setText(complaint.getNameWriter());
        detailLabel.setText(complaint.getBodyComplaint());
    }

    private void clearSelectedComplaint(){
        titleLabel.setText("");
        reportCategoryLabel.setText("");
        reporterLabel.setText("");
        detailLabel.setText("");
    }

    @FXML
    public void handleBack(ActionEvent event) {
        try {
            com.github.saacsos.FXRouter.goTo("adminscene");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า admin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
