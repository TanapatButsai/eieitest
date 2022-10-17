//package ku.cs.application.controllers;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import ku.cs.application.models.Complaint;
//import ku.cs.application.models.ComplaintList;
//import ku.cs.application.models.Users;
//import ku.cs.application.services.ComplaintListDataSource;
//import ku.cs.application.services.DataSource;
//
//import java.io.IOException;
//public class TeacherComplaintController {
//    @FXML private TextArea bodyTextArea;
//    @FXML private TextArea bodyTextArea1;
//    @FXML private TextField headTextField;
//    private DataSource<ComplaintList> dataSource;
//    private ComplaintList complaintList;
//    private Users user;
//    @FXML
//    public void handleBackButton(ActionEvent actionEvent){
//        try {
//            com.github.saacsos.FXRouter.goTo("home");
//        } catch (IOException e) {
//            System.err.println("ไปที่หน้า home ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกำหนด route");
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    private ImageView KU_9non;
//    @FXML
//    public void initialize() {
//        String url = getClass().getResource("/ku/cs/teachercomplaint_images/KU_9non.jpeg").toExternalForm();
//        KU_9non.setImage(new Image(url));
//
//        dataSource = new ComplaintListDataSource("data","complaint.csv");
//        complaintList = dataSource.readData();
//        user = (Users) com.github.saacsos.FXRouter.getData();
//    }
//    @FXML
//    public void handlePushComplaint(ActionEvent actionEvent){
//        String headComplaint = headTextField.getText();
//        String bodyComplaint = bodyTextArea.getText();
//        String bodyComplaint1 = bodyTextArea1.getText();
//        Complaint complaint = new Complaint(headComplaint,bodyComplaint,bodyComplaint1,"teacher",user.getName());
//        complaint.recordTime();
//        complaintList.add(complaint);
//        dataSource.writeData(complaintList);
//    }
//}
