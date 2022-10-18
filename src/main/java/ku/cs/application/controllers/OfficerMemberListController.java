package ku.cs.application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.application.models.Officer;
import ku.cs.application.services.OfficerListDataSource;

import java.io.IOException;

public class OfficerMemberListController {
    private Officer userOfficer;
    @FXML private Label officerUsernameLabel;
    @FXML private Label officerNameLabel;
    @FXML private ListView officerMemberListView;
    @FXML private ImageView OfficerImageView;
    @FXML
    public void initialize(){
        userOfficer = (Officer) com.github.saacsos.FXRouter.getData();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("officer", userOfficer);
        } catch (IOException e) {
            System.err.println("ไปที่หน้า login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
