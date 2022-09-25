package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.application.models.Officer;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class OfficerController {
    private Officer officers;
    @FXML private Label topicLabel;
    @FXML private Label officerLabel;
    @FXML private ListView complaintListView;

    @FXML
    public void initialize(){
        System.out.println("initialize MemberCardDetailController");
        //memberCard = new MemberCard("John Smith", "081-222-8888");
        officers = (Officer) FXRouter.getData();
        showOfficerData();
    }
    public void showOfficerData(){
        officerLabel.setText(officers.getRole());

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
