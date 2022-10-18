package ku.cs.application.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.Complaint;
import ku.cs.application.models.ComplaintList;
import ku.cs.application.models.Users;
import ku.cs.application.services.ComplaintListDataSource;
import ku.cs.application.services.DataSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class NormalComplaintController {
    @FXML private TextArea bodyTextArea;
    @FXML private TextArea bodyTextArea1;
    @FXML private TextField headTextField;
    private DataSource<ComplaintList> dataSource;
    private ComplaintList complaintList;
    private Users user;
    @FXML
    private ImageView kasetsartImage;
    private ArrayList<Object> objects;
    private String category;
    @FXML
    public void initialize() {
        dataSource = new ComplaintListDataSource("data","complaint.csv");
        complaintList = dataSource.readData();
        objects = (ArrayList<Object>) com.github.saacsos.FXRouter.getData();
        user = (Users) objects.get(0);
        category = (String) objects.get(1);
        String imagePath = (String) objects.get(2);
        String url = getClass().getResource(imagePath).toExternalForm();
        kasetsartImage.setImage(new Image(url));
    }
    public void handleBackHomeButton(ActionEvent actionEvent){
        try {
            com.github.saacsos.FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePushComplaint(ActionEvent actionEvent){
        Alert alert;
        if (!(headTextField.getText().equals("") || bodyTextArea.getText().equals("") || bodyTextArea1.getText().equals(""))){
            String headComplaint = headTextField.getText();
            String bodyComplaint = bodyTextArea.getText();
            String bodyComplaint1 = bodyTextArea1.getText();
            Complaint complaint = new Complaint(headComplaint,bodyComplaint,bodyComplaint1,category,user.getUsername());
            complaint.recordTime();
            complaint.setSolution("");
            complaint.setSolution("no");
            complaintList.add(complaint);
            dataSource.writeData(complaintList);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("ส่งคำร้องเรียนเสร็จสิ้น");
            alert.setContentText("ขอบคุณสำหรับการร้องเรียน\nทางเราจะนำคำร้องเรียนส่งไปยังหน่วยงานที่เกี่ยวข้อง\nและดำเนินการให้เร็วที่สุด");
            alert.showAndWait();
            try {
                com.github.saacsos.FXRouter.goTo("home",user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText("พบช่องว่าง");
            alert.setContentText("กรุณากรอกข้อมูลให้ครบ");
            alert.showAndWait();
        }
    }
}
