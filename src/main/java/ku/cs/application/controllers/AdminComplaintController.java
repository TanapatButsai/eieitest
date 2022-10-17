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
import ku.cs.application.models.Report;
import ku.cs.application.models.ReportList;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.ReportListDataSource;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class AdminComplaintController {
    @FXML private ListView<Report> reportListView;
    @FXML private Label reporterLabel;
    @FXML private Label reportCategoryLabel;
    @FXML private Label titleLabel;
    @FXML private Label reasonLabel;
    @FXML private Label detailLabel;


    private DataSource<ReportList> clds;
//    private ComplaintList complaintList;
    private ReportList reportList;
    private DataSource<ComplaintList> dataSource = new ComplaintListDataSource("data","complaint.csv");
    private ComplaintList complaintList = new ComplaintList();
    private  Complaint complaint;
    private Report report;


    @FXML
    public void initialize(){
        clds = new ReportListDataSource("data","report.csv");
        reportList = clds.readData();
        complaintList = dataSource.readData();
        System.out.println(reportList.toString());
        showListView();
        clearSelectedComplaint();
        handleSelectedListView();
    }

    private void showListView() {
        reportListView.getItems().setAll(reportList.getReportList());
        reportListView.refresh();
    }

    private void handleSelectedListView() {
        reportListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Report>() {
            @Override
            public void changed(ObservableValue<? extends Report> observableValue, Report report, Report t1) {
                System.out.println(t1);
                findComplaint(t1);
                showSelectedComplaint(t1,complaint);
            }
        });
    }

    private void showSelectedComplaint(Report report,Complaint complaint) {
        titleLabel.setText(complaint.getHeadComplaint());
        reportCategoryLabel.setText(complaint.getCategory());
        reporterLabel.setText(report.getReporterUsername());
        detailLabel.setText("");
        reasonLabel.setText(report.getReason());
    }

    private void clearSelectedComplaint(){
        titleLabel.setText("");
        reportCategoryLabel.setText("");
        reporterLabel.setText("");
        detailLabel.setText("");
        reasonLabel.setText("");
    }
    private void findComplaint(Report report){
        complaint = complaintList.findComplaintByReported(report);
        System.out.println(complaint);
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
    @FXML
    public void handleGoToComplaintDetail(ActionEvent actionEvent){
        try {
            if (!(complaint == null))
                FXRouter.goTo("admin_selected_report_complaint",complaint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
