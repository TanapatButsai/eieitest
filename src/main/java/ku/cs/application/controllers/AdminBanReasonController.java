package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ku.cs.application.models.*;
import com.github.saacsos.FXRouter;
import ku.cs.application.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class AdminBanReasonController {

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label userLabel;
    private UserList userList;
    private Complaint complaint;
    private BanList banList;
    private DataSource<UserList> dataSource;
    private DataSource<BanList> banListDataSource;
    private Users user;
    private DataSource<ReportList> reportListDataSource;

    private ReportList reportList;
    private Report report;
    private ArrayList<Object> objects;
    private Users admin;

    @FXML private void initialize(){
        dataSource = new UserListDataSource("data","user.csv");
        banListDataSource = new BanListDataSource(true);
        reportListDataSource = new ReportListDataSource("data","report.csv");
        userList = dataSource.readData();
        banList = banListDataSource.readData();
        reportList =reportListDataSource.readData();
        objects = (ArrayList<Object>) FXRouter.getData();
        complaint = (Complaint) objects.get(0);
        report = (Report) objects.get(1);
        admin = (Users) objects.get(2);
        System.out.println(report);
        System.out.println(complaint);
        user = userList.findUser(complaint.getNameWriter());
        System.out.println(banList);
    }

    @FXML
    void handleBack(ActionEvent event) {
        try {
            FXRouter.goTo("admin_selected_report_complaint",objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleBan(ActionEvent event) {
        System.out.println(user);
        if (!(user == null) && !reasonTextArea.getText().equals("")){
            ban();
            try {
                FXRouter.goTo("admincomplaint",admin);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText("พบช่องว่าง");
            alert.setContentText("กรุณากรอกข้อมูลให้ครบ");
            alert.showAndWait();
        }
    }
    public void ban(){
        userList.setBan(complaint.getNameWriter());
        dataSource.writeData(userList);
        createBanUser();
    }
    public void createBanUser(){
        String username = complaint.getNameWriter();
        String reason = reasonTextArea.getText();
        String bannedID = Integer.toString(banList.getSize()+1);
        String objectID = complaint.getHeadComplaint()+":"+complaint.getNameWriter()+":"+complaint.getTime();
        Ban ban =new Ban(bannedID,username,reason,objectID,true);
        ban.setTime();
        banList.addBan(ban);
        banListDataSource.writeData(banList);
        removeUsedReport(report);
    }

    public void removeUsedReport(Report report){
        reportList.remove(report);
        reportListDataSource.writeData(reportList);
    }
}