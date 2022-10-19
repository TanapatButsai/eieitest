package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.Report;
import ku.cs.application.models.ReportList;
import ku.cs.application.models.Users;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.DataSource;
import ku.cs.application.services.ReportListDataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportComplaintController {
    private List userAndComplaint;
    private Users user;
    private Complaint complaint;
    private Report report;
    private DataSource<ReportList> dataSource;
    private ReportList reportList;
    @FXML
    private TextArea reportTextArea;

    @FXML
    private Label userLabel;
    @FXML
    public void initialize(){
        userAndComplaint = (List<Object>) FXRouter.getData();
        user = (Users) userAndComplaint.get(0);
        complaint = (Complaint) userAndComplaint.get(1);
        dataSource = new ReportListDataSource("data","report.csv");
        reportList = dataSource.readData();
        System.out.println(user);
        System.out.println(complaint);
    }


    @FXML
    void handleBackButton(ActionEvent event) {
        try {
            FXRouter.goTo("selected_complaint_detail",userAndComplaint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleReportComplaint(ActionEvent event) {
        String reason = reportTextArea.getText();
        if (!reason.equals("")) {
            String complaintID = complaint.getHeadComplaint() + ":" + complaint.getNameWriter() + ":" + complaint.getTime();
            report = new Report(user.getUsername(), complaint.getNameWriter(), reason, complaintID);
            reportList.add(report);
            dataSource.writeData(reportList);
            System.out.println(report);
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("รายงานเสร็จสิ้น");
                alert.setContentText("ขอบคุณสำหรับการรายงาน\nทางเราจะพิจารณาคำร้องเรียนดังกล่าวให้เร็วที่สุด");
                alert.showAndWait();
                FXRouter.goTo("home", user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText("พบช่องว่าง");
            alert.setContentText("กรุณากรอกข้อมูลให้ครบ");
            alert.showAndWait();
        }
    }

}
